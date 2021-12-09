package com.zcy.spark.main.framework.service.v9

import com.zcy.spark.main.framework.common.TService
import com.zcy.spark.main.framework.dao.v9.GiTaskDao
import com.zcy.spark.main.framework.unitls.EnvUtil
import org.apache.spark.sql.functions.lit

class GiTaskService extends TService {

  private val giTaskDao = new GiTaskDao()

  private val v9_merchant_code="v9";
  /**
   * 服务层
   */
  def dataAnalysis() = {
    var df = giTaskDao.readTable("")
    val ssl = EnvUtil.take()
    val ss = ssl.getSparkSession
    import ss.implicits._
    //打印task_no列
    df.map(task => task.getAs[String]("task_no")).show()
    //过滤
//    df = df.filter(task=>task.getAs("deleted")!=null)
    //增加商家字段
//    df = df.withColumn("merchant_id", lit(52))

    //add column 商品供应商id
//    df = df.withColumn("product_supplier_id", lit(null))
    //add column 商品供应商名称
    //add column 工单处理类型
    //add column 仓库处理类型
    //add column 作业开始时间
    //add column 作业结束时间
    //add column 波次模板id
    //add column 交货时间
    //add column 容器编码id
    //add column 加急标识
    //add column 是否称重
    //add column 仓位排序
    //add column 拣配渠道

    //去掉deleted字段
//    df = df.drop("deleted")

    giTaskDao.saveTable(df, "wms_gi_v9", "gi_task")
  }
}
