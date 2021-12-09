package com.zcy.spark.main.learn.operator.action

import com.sun.org.apache.xpath.internal.Arg
import com.zcy.spark.utils.SparkUtil
import org.apache.spark.HashPartitioner
import org.apache.spark.rdd.RDD

object RDDActionOperatorTest {
  def main(args: Array[String]): Unit = {
    //获取spark环境
    val ss = SparkUtil.getSparkSession
    val sc = ss.sparkContext
//    val sparkConf = sc.getConf
//    sparkConf.set("spark.serializer","org.apache.spark.serializer.KryoSerializer").registerKryoClasses(Array[classOf(Arg)]))

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))
    //所谓的行动算子，其实就是触发作业执行的方法
    //底层代码调用的是环境对象的runJob方法
    rdd1.collect()
    //reduce
    rdd1.reduce(_+_)
    //collect
    rdd1.collect()
    //count
    rdd1.count()
    //first
    rdd1.first()
    //take 取多少个数据
    rdd1.take(10)
    //takeOrdered 排序后取出
    rdd1.takeOrdered(10)(Ordering[Int].reverse)
    //aggregate 初始值会参与到分区内和分区间计算 （1+2+10）+（3+4+10）+10=40
    //aggeagateByKey 初始值只参与到分区内计算（1+2+10）+（3+4+10）=30
    rdd1.aggregate(10)(_+_,_+_)
    //fold是分区内和分区间计算规则相同时使用
    //countByKey key出现的次数
    val map = rdd1.countByValue()
    println(map)
    //save
    rdd1.saveAsTextFile("output")
    rdd1.saveAsObjectFile("output1")
    //foreach driver内存端执行
    rdd1.collect().foreach(println)
    //executor端异步执行
    rdd1.foreach(println)
    //RDD的方法和scala集合对象的方法不一样
    //RDD的方法可以将计算逻辑发送到executor端分布式执行。


    //样例类自动实现序列化特质
    //RDD算子中传递的函数是闭包操作，那么就会启用闭包检测



    //Kryo序列化框架 速度是scala的10倍


    //算子间的血缘关系
    //RDD不保存数据，为了容错性，可以根据血缘关系将数据源重新读取进行计算
    //每个RDD都保存血缘关系
    print(rdd1.toDebugString)//血缘关系
    print(rdd1.dependencies)//相邻关系
    //ontToOneDependency窄依赖
    //shuffle宽依赖

    //窄依赖可以合并成一个任务，宽依赖需要多个任务

    //阶段的数量=shuffle数量+1
    /**
     * Application:初始化一个sparkContext即生成一个application
     * Job：一个行动算子生成一个Job
     * Stage:等于宽依赖的个数加一
     * Task:一个Stage阶段中，最后一个RDD的分区个数就是Task的个数
     *
     * Application->Job->Stage->Task 每一层都是1对多
     */





  }

}
