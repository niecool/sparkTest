package com.zcy.spark.main.framework.controller

import com.zcy.spark.main.framework.common.TController
import com.zcy.spark.main.framework.service.WordCountService

class WordCountController extends TController {
  private val wordCountService = new WordCountService()

  /**
   * 业务
   */
  override def disPatch(): Unit = {
    val array = wordCountService.dataAnalysis()
    println(array)
  }
}
