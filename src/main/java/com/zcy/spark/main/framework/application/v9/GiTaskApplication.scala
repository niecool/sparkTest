package com.zcy.spark.main.framework.application.v9

import com.zcy.spark.main.framework.common.TApplication
import com.zcy.spark.main.framework.controller.WordCountController
import com.zcy.spark.main.framework.controller.v9.GiTaskController

/**
 * wordCount工程化代码
 */
object GiTaskApplication extends App with TApplication {

  start() {
    val controller = new GiTaskController()
    controller.disPatch()
  }

}
