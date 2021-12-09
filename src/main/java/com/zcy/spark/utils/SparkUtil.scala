package com.zcy.spark.utils

import org.apache.spark.sql.SparkSession

import java.io.File

object SparkUtil {

  def getSparkSession: SparkSession = {
    //引入spark环境
    val warehouseLocation = new File("logistics-data-dw").getAbsolutePath
    val spark: SparkSession = SparkSession
      .builder()
      .appName("StockSnapshot")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .config("hive.exec.dynamic.partition", value = true)
      .config("hive.exec.dynamic.partition.mode", "nostrick")
      .enableHiveSupport()
      .getOrCreate()
    spark
  }


  def getLocalSparkSession: SparkSession = {
    //引入spark环境
    val spark: SparkSession = SparkSession.builder()
      .appName("SparkSQLDemo") //指定相对应运行名称
      .master("local[*]") //指定相对应运行模式
      .config("hive.exec.dynamic.partition", value = true)
      .config("hive.exec.dynamic.partition.mode", "nostrick")
      .enableHiveSupport()
      .getOrCreate //开始创建
    spark
  }

}
