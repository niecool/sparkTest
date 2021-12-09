package com.zcy.spark.main.framework.common

import com.zcy.spark.main.framework.unitls.EnvUtil
import org.apache.spark.sql.{DataFrame, Row}

trait TDao {


  def readFile(path: String) = {
    val sparkSessionLocalssl = EnvUtil.take()
    val sc = sparkSessionLocalssl.getSparkContext
    sc.textFile(path)
  }
  def readTable(table:String):DataFrame

}
