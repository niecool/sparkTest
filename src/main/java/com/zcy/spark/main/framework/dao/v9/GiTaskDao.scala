package com.zcy.spark.main.framework.dao.v9

import com.zcy.spark.main.framework.common.TDao
import com.zcy.spark.main.framework.unitls.EnvUtil
import org.apache.spark.sql.{DataFrame, Row, SaveMode}


class GiTaskDao extends TDao {

  override def readTable(table: String): DataFrame = {
    val ssl = EnvUtil.take()
    val ss = ssl.getSparkSession
    //    val df = ss.sql("select id, wh_id, ep_id, wo_id, wo_code, customer_scale, customer_id, wave_id, wave_code, wave_item_id, task_no, task_sub_no, state, task_type, outbound_id, outbound_code, outbound_item_id, outbound_item_code, so_id, so_code, so_item_id, so_item_code, product_id, product_code, stock_tx_seq_no, plan_num, actual_num, product_unit_id, supplier_id, supplier_code, batch, wh_stock_location_id, wh_stock_location_code, gi_time, src_class_id, src_section_id, src_bin_id, dest_class_id, dest_section_id, dest_bin_id, exception_code, container_code, weight, weight_unit_id, volume_unit_id, volume, creator_type, dest_bin_code, src_bin_code, production_time, expiration_time, order_type_id, item_type_id, source_task_id, customer_code, customer_desc, src_container_code, src_product_stock_id, print_state, wo_print_state, confirmer_id, confirmer_type, confirmed_time, activity_area_id, old_out_control_id, out_control_id, wh_stock_type_id, logistics_type, wh_global_step_id, wh_global_step_flow_id, release_batch, seq_no, sp_weight, sp_unit_id, sp_qty, owner_id, owner_code, creator_id, created_time, last_modifier_id, last_modified_time, deleted, unique_code, customer_seq_no, dt  from wms_gi_v9.gi_task  where deleted=0 and dt>'19700101'")
    import ss.implicits._
    ss.sql("show databases").show(false)
    ss.sql("use wms_gi_v9").show(false)
    val ds = ss.read
      .option("header", "true")
      .option("inferSchema", true)
      .option("delimiter", ",")
      .csv("/zcy/giTask_20211208_174735898.csv")
    var df = ds.toDF()
    df
  }

   def saveTable(df: DataFrame,destDatabase:String,destTable:String): Unit = {
    val ssl = EnvUtil.take()
    val ss = ssl.getSparkSession
     val insetFormat = "%s.%s"
     df.write.mode(SaveMode.Append).format("parquet").insertInto(String.format(insetFormat, destDatabase, destTable))
//     df.write.mode(SaveMode.Overwrite).option("header", "true")
//       .option("inferSchema", true)
//       .option("delimiter", ",")
//       .csv("data/giTask/output")
  }


}
