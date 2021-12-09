package com.zcy.spark.utils

import com.alibaba.fastjson.{JSON, TypeReference}
import com.zcy.spark.main.stock.snapshot.SupportCommands.NodeParam
import com.zcy.spark.main.stock.snapshot.SupportCommands.NodeParam

object ArgsUtil {

  def getNodeParams(args: Array[String]): NodeParam = {
    val params = Base64Util.decodeBase64String(args(0))
//  var params = "{\"category\":3,\"dagParams\":\"{}\",\"executeId\":\"16ac2853-572f-4ebb-b763-fc0c11a0b57c\",\"nodeParams\":\"{\\\"destTable\\\":\\\"ads_dwd_wms_stk_product_stock_test\\\",\\\"destDataBase\\\":\\\"ads_dmall_lds\\\"}\",\"runParams\":\"{}\",\"taskId\":373121,\"unidataAppKey\":\"ddw_wlyf\"}"
    println(params)
    val arg:Arg = JSON.parseObject(params,classOf[Arg])
    val nodeParamsStr = arg.nodeParams
    val nodeParams = JSON.parseObject(nodeParamsStr,classOf[NodeParam])
    nodeParams
  }

  class Arg  extends Serializable {
    var category: String = _
    var dagParams: String = _
    var executeId: String = _
    var nodeParams: String = _
    var runParams: String = _
    var taskId: String = _
    var unidataAppKey: String = _

    def setCategory(category: String): Unit = {
      this.category = category
    }

    def setNodeParams(nodeParams: String): Unit = {
      this.nodeParams = nodeParams
    }

    def getNodeParams: String = {
      this.nodeParams
    }

  }
}
