package com.zcy.spark.main.learn.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreamingTest {

  def main(args: Array[String]): Unit = {

    //StreamingContext创建时，需要传递两个参数
    //第一个参数spark配置
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming")
    //采集周期
    val ssc = new StreamingContext(sparkConf,Seconds(3))

    val lines = ssc.socketTextStream("localhost", 9999)
    val words = lines.flatMap(_.split(""))
    val wordToCount = words.map((_, 1))
    wordToCount.print()
//    ssc.stop() 由于streaming是长期执行的任务，所以不能直接关闭
    //如果main方法执行完，程序会自动结束
  }
}
