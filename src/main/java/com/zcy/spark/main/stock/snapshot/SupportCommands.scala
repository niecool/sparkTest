package com.zcy.spark.main.stock.snapshot

import com.dmall.spark.utils.SparkUtil
import com.wumart.scm.framework.common.util.DateUtil
import com.zcy.spark.utils.{ArgsUtil, SparkUtil}
import org.apache.spark.sql.SparkSession

import java.util.Date

/**
 * 基础操作
 */
object SupportCommands {
  def main(args: Array[String]): Unit = {
    println("SupportCommands program begin")
    val nodeParams = ArgsUtil.getNodeParams(args)
    if (nodeParams == null ||
      nodeParams.destDataBase == null || nodeParams.destTable == null) {
      println(String.format("params is null ,system exist!%s", nodeParams))
      return
    }
    //获取spark环境
    println("start getSparkSession!")
    val ss = SparkUtil.getSparkSession


    //清除分区
    val partition = DateUtil.dateToString(DateUtil.addDay(new Date(),-1), DateUtil.Format_7)
    cleanPartition(ss, nodeParams, partition)
    // 3.关闭连接
    ss.stop()
  }

  /**
   * 清除分区数据
   */
  def cleanPartition(sparkSession: SparkSession, runParam: NodeParam, partition: String): Unit = {
    val sqlFormat = "ALTER TABLE %s.%s DROP IF EXISTS PARTITION (dt='%s')"
    val sql = String.format(sqlFormat, runParam.destDataBase, runParam.destTable, partition)
    println("clean sql=" + sql)
    sparkSession.sql(sql)
  }

  /**
   * 入参类型
   */
  class NodeParam extends Serializable {
    var sourceDataBase: String = _
    var sourceTable: String = _
    var destDataBase: String = _
    var destTable: String = _

    def setSourceDataBase(sourceDataBase: String): Unit = {
      this.sourceDataBase = sourceDataBase
    }

    def setSourceTable(sourceTable: String): Unit = {
      this.sourceTable = sourceTable
    }

    def setDestDataBase(destDataBase: String): Unit = {
      this.destDataBase = destDataBase
    }

    def setDestTable(destTable: String): Unit = {
      this.destTable = destTable
    }
  }

}
