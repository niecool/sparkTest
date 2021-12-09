package com.zcy.spark.main.learn.acc

import com.zcy.spark.utils.SparkUtil
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object AccTest {

  def main(args: Array[String]): Unit = {
    //获取spark环境
    // 1.建立和spark框架的连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("persist-APPName");
    val sc = new SparkContext(sparkConf);

    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    val sumAcc = sc.longAccumulator("sum")
    rdd.foreach(num => {
      sumAcc.add(num)
    })
    println("sum = " + sumAcc.value)


    //分布式广播只读变量，每个executor一份，每个executor内的task共享
    val map = mutable.Map(("a", 1), ("b", 2))
    val bm = sc.broadcast(map)
    sc.stop();
  }



  class MyAccumulator extends AccumulatorV2[String,mutable.Map[String,Long]]{

    private var wcMap = mutable.Map[String,Long]()

    //判断是否为初始状态
    override def isZero: Boolean = {
      wcMap.isEmpty
    }

    override def copy(): AccumulatorV2[String, mutable.Map[String, Long]] = {
      new MyAccumulator()
    }

    override def reset(): Unit = {
      wcMap.clear()
    }

    override def add(word: String): Unit = {
      val newCnt = wcMap.getOrElse(word,0L) +1
      wcMap.update(word,newCnt)
    }

    /**
     * Driver合并多个累加器
     */
    override def merge(other: AccumulatorV2[String, mutable.Map[String, Long]]): Unit = {
    val map1 = this.wcMap
      val map2 = other.value
      map2.foreach{
        case(word,count)=>
          val newCnt = map1.getOrElse(word,0L)+count
          map1.update(word,newCnt)
      }
    }

    override def value: mutable.Map[String, Long] = {
    wcMap
    }
  }
}
