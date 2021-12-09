package com.zcy.spark.main.learn.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * DSL语法
 */
object SparkSqlTest {
  def main(args: Array[String]): Unit = {

    /**
     * dataFrame
     */
    //sparkSession是sparkContext和HiveContext的组合
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSql")
    val ss = SparkSession.builder().config(sparkConf).getOrCreate()
//    import spark.implicits._
    //createOr
    val df = ss.read.json("data/input.txt")
    df.createTempView("user")
    ss.sql("select * from user")
    //新session不能访问tempView,全局视图可以夸session访问
    ss.newSession().sql("")
    df.createGlobalTempView("user")
    ss.newSession().sql("select * from global_temp.user")
    //DSL语言，不用创建视图，直接操作dataFrame
    df.printSchema()
    df.select("age").show()
    //涉及到运算的时候
//    df.select('age+1).show()
    //df.filter("age">11).show()

    //dataFrame和Rdd转换
    val rdd = ss.sparkContext.makeRDD(List(1,2,3,4))
//    val df = rdd.toDF("id")
//    df.rdd

    //
    val seq = Seq(1,2,3,4)
//    seq.toDS()





    /**
     * dataSet
     */
    //df.as
    //rdd.toDs 必须准备好样例类
    //





    ss.close()
  }



}
