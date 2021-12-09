package com.zcy.spark.main.framework.application

import com.zcy.spark.main.framework.common.TApplication
import com.zcy.spark.main.framework.controller.WordCountController

/**
 * wordCount工程化代码
 */
object WordCountApplication extends App with TApplication {

  localStart() {
    val controller = new WordCountController()
    controller.disPatch()
  }

}
