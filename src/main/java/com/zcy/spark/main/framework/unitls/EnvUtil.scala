package com.zcy.spark.main.framework.unitls

object EnvUtil {

  private val scLocal = new ThreadLocal[SparkSessionLocal]()

  def put(ssl: SparkSessionLocal): Unit = {
    scLocal.set(ssl)
  }

  def take(): SparkSessionLocal = {
    scLocal.get()
  }

  def clear(): Unit = {
    scLocal.remove()
  }
}
