package com.zcy.spark.main.learn.operator.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDPersistTest {

  /**
   * hadoop:HDFS YARN
   * spark:
   * storm
   * flink
   *
   * @param args
   */

  def main(args: Array[String]): Unit = {
    // 1.建立和spark框架的连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("persist-APPName");
    val sc = new SparkContext(sparkConf);

    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"), 2)
    //wordCount
    val rdd2 = rdd.flatMap(_.split(" ")).map((_, 1)).cache().persist().reduceByKey(_ + _)
    rdd2.collect().foreach(println)
    rdd2.saveAsTextFile("output")
    rdd2.checkpoint()

    //rdd中不存储数据，那么rdd对象重用，实际上是重新计算链路。
    //解决办法，可以把阶段性结果存起来。
    //.cache() 内存持久化 。persist() cache不切断血缘关系
    //在执行链路比较长的死后也可以把结果保存起来。

    //checkpoint()需要落盘，需要指定路径，一般路径是分布式存储路径 checkpoint切断血缘关系

  }


}
