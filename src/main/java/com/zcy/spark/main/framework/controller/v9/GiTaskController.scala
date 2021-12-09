package com.zcy.spark.main.framework.controller.v9

import com.zcy.spark.main.framework.common.TController
import com.zcy.spark.main.framework.service.WordCountService
import com.zcy.spark.main.framework.service.v9.GiTaskService

class GiTaskController extends TController {
  private val giTaskService = new GiTaskService()

  /**
   * 业务
   */
  override def disPatch(): Unit = {
    val array = giTaskService.dataAnalysis()
    println(array)
  }
}
