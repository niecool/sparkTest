package com.zcy.spark.main.framework.model

class GiTaskDo {

  // 仓库ID
  private var whId = 0L
  // 仓库工单号
  private var woCode = null
  // 授权方id
  private var epId = 0L
  // 客户ID
  private var customerId = 0L
  // 波次ID
  private var waveId = 0L
  // 波次号
  private var waveCode = null
  // 波次行项目ID
  private var waveItemId = 0L
  // 任务编号
  private var taskNo = null
  // 任务行项目编号
  private var taskSubNo = null
  // 任务状态
  private var state = null
  // 任务类型
  private var taskType = null
  // 出货单ID
  private var outboundId = 0L
  // 出货单编码
  private var outboundCode = null
  // 出货单行项目ID
  private var outboundItemId = 0L
  private var outboundItemCode = null
  // 销售订单ID
  private var soId = 0L
  // 销售订单编码
  private var soCode = null
  // 销售订单行项目ID
  private var soItemId = 0L
  // 销售订单行项目编码
  private var soItemCode = null
  // 商品ID
  private var productId = 0L
  // 商品code
  private var productCode = null
  // 库存流水号
  private var stockTxSeqNo = null
  // 订单中的应发商品数量
  private var planNum = null
  // 实际要发的商品数量
  private var actualNum = null
  // 商品单位
  private var productUnitId = 0L
  // 供应商ID
  private var supplierId = 0L
  // 供应商编码
  private var supplierCode = null
  // 批次
  private var batch = null
  // 库存地
  private var whStockLocationId = 0L
  private var whStockLocationCode = null
  //    // 交货时间
  //    private Date giTime;
  // 源存储类型ID
  private var srcClassId = 0L
  // 源存储区域ID
  private var srcSectionId = 0L
  // 源仓位ID
  private var srcBinId = 0L
  // 目标存储类型
  private var destClassId = 0L
  // 目标存储区域
  private var destSectionId = 0L
  // 目标存储仓位id
  private var destBinId = 0L
  //    // 例外代码（特权编码）
  //    private String exceptionCode;
  // 容器编码
  private var containerCode = null
  // 重量
  private var weight = null
  // 重量单位
  private var weightUnitId = 0L
  // 体积单位ID
  private var volumeUnitId = 0L
  // 体积
  private var volume = null
  //    // 创建者类型
  //    private Integer creatorType;
  // 工单ID
  private var woId = 0L
  // 目标存储仓位code
  private var destBinCode = null
  // 源仓位code
  private var srcBinCode = null
  // 生成时间
  private var productionTime = null
  // 到期时间
  private var expirationTime = null
  // 订单类型Id
  private var orderTypeId = 0L
  // 行明细类型Id
  private var itemTypeId = 0L
  //    // 源任务id(部分确认新任务要记录旧任务id)
  //    private Long sourceTaskId;
  // 客户编码
  private var customerCode = null
  // 客户描述
  private var customerDesc = null
  // 客户规模
  private var customerScale = null
  // 原容器
  private var srcContainerCode = null
  // 原库存
  private var srcProductStockId = 0L
  // 打印状态, 10=未打印;20=打印中;30=已打印
  private var printState = null
  private var woPrintState = null
  // 确认人
  private var confirmerId = 0L
  // 确认终端类型, 1=手持;2=PC
  private var confirmerType = null
  // 确认时间
  private var confirmedTime = null
  // 作业区域ID
  private var activityAreaId = 0L
  // 老出库控制条件(整箱拆零有值)
  private var oldOutControlId = 0L
  // 出库控制标识ID
  private var outControlId = 0L
  // 库存类型ID
  private var whStockTypeId = 0L
  // 物流类型
  private var logisticsType = null
  //    // 步骤编码
  //    private Long whGlobalStepId;
  //    // 处理步骤编码
  //    private Long whGlobalStepFlowId;
  //    // 释放批次(用于计算波次释放时任务进行操作归类)
  //    private String releaseBatch;
  // 序号
  private var seqNo = null
  // 标件数量
  private var spWeight = null
  // 标件单位
  private var spUnitId = 0L
  // 标件件数
  private var spQty = null
  // 拥有者ID
  private var ownerId = 0L
  // 拥有者编码
  private var ownerCode = null
  // 客户排序
  private var customerSeqNo = null


}
