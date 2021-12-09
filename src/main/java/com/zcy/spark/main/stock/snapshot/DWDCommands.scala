package com.zcy.spark.main.stock.snapshot

import com.dmall.spark.utils.SparkUtil
import com.wumart.scm.framework.common.util.DateUtil
import com.zcy.spark.utils.{ArgsUtil, SparkUtil}
import org.apache.spark.sql.SaveMode

import java.util.Date

/**
 * 背景：每个商家库存表的库名不同
 * 根据参数里的库名，查询库存表数据，然后批量清洗到dwd层。
 */
object DWDCommands {
  def main(args: Array[String]): Unit = {
    println("SupportCommands program begin")
    val nodeParams = ArgsUtil.getNodeParams(args)
    if (nodeParams == null ||
      nodeParams.sourceDataBase == null || nodeParams.destDataBase == null ||
      nodeParams.destDataBase == null || nodeParams.destTable == null) {
      println(String.format("params is null ,system exist!%s", nodeParams))
      return
    }
    val sourceDatabase = nodeParams.sourceDataBase
    val sourceTable = nodeParams.sourceTable
    val destDatabase = nodeParams.destDataBase
    val destTable = nodeParams.destTable
    //获取spark环境
    val ss = SparkUtil.getSparkSession
    //执行业务操作
    val yesterdayPartition = DateUtil.dateToString(DateUtil.addDay(new Date(), -1), DateUtil.Format_7)
    //    val showPartitionsSql = String.format("show partitions %s.%s",sourceDatabase,sourceTable)
    //    val dt_DF = ss.sql(showPartitionsSql)
    //    val dataSqlFormat =
    //      "select id,bin_code,bin_id,class_id,class_code,wh_id,wh_code,product_id,product_code,supplier_batch,batch,production_date,stock_unit_id,expiration_date,receive_date,container_code,wh_stock_type_id,owner_id,available_stock_num,frozen_num,stock_num,entitle_party_id,so_id,so_code,so_detail_id,stock_num,frozen_num,available_stock_num,created_time,last_modified_time,section_id,version,storage_location_id,ref_doc_item_id,ref_doc_type,class_role,creator_id,last_modifier_id ,%s as dt from %s.%s where %s and (stock_num is not null or available_stock_num is not null)"
    //    dt_DF.rdd.foreach(c=>{
    //      val dtStr = c.getString(0)
    //      val sql = String.format(dataSqlFormat, yesterdayPartition,sourceDatabase,sourceTable, dtStr)
    //      println(sql)
    //      val data_df = ss.sql(sql)
    //      val insetFormat="%s.%s"
    //      data_df.write.mode(SaveMode.Append).format("parquet").insertInto(String.format(insetFormat,destDatabase,destTable))
    //    })
    val dataSqlFormat = "select id,bin_code,bin_id,class_id,class_code,wh_id,wh_code,product_id,product_code,supplier_batch,batch,production_date,stock_unit_id,expiration_date,receive_date,container_code,wh_stock_type_id,owner_id,available_stock_num,frozen_num,stock_num,entitle_party_id,so_id,so_code,so_detail_id,created_time,creator_id,last_modifier_id,last_modified_time,section_id,storage_location_id,ref_doc_item_id,ref_doc_type,class_role,%s as dt from %s.%s where dt>'19700101' and (stock_num >0 or available_stock_num >0)"
    val sql = String.format(dataSqlFormat, yesterdayPartition, sourceDatabase, sourceTable)
    println(sql)
    val data_df = ss.sql(sql)
    val insetFormat = "%s.%s"
    data_df.write.mode(SaveMode.Append).format("parquet").insertInto(String.format(insetFormat, destDatabase, destTable))
    // 3.关闭连接
    ss.stop()
  }

}