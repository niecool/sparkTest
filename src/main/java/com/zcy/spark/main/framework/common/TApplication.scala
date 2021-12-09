package com.zcy.spark.main.framework.common

import com.dmall.spark.main.framework.unitls.SparkSessionLocal
import com.zcy.spark.main.framework.unitls.{EnvUtil, SparkSessionLocal}
import com.zcy.spark.utils.SparkUtil
import org.apache.spark.{SparkConf, SparkContext}

trait TApplication {

  def localStart(master: String = "local[*]", app: String = "App")(op: => Unit): Unit = {
    val sparkConf = new SparkConf().setMaster(master).setAppName(app);
    val sc = new SparkContext(sparkConf)
    val ssl = new SparkSessionLocal(sc);
    EnvUtil.put(ssl)
    try {
      op
    } catch {
      case ex: Throwable => println(ex.getMessage)
    }
    sc.stop()
    EnvUtil.clear()
  }


  def start(master: String = "local[*]", app: String = "App")(op: => Unit): Unit = {
//    val ss = SparkUtil.getSparkSession
    val ss = SparkUtil.getLocalSparkSession
    val ssl = new SparkSessionLocal(ss)
    EnvUtil.put(ssl)
    try {
      op
    } catch {
      case ex: Throwable => println(ex.getMessage)
    }
    ss.stop()
    EnvUtil.clear()
  }

}
