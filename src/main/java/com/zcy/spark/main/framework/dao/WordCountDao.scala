package com.zcy.spark.main.framework.dao

import com.zcy.spark.main.framework.common.TDao
import org.apache.spark.sql.{DataFrame, Row}


class WordCountDao extends TDao{
  override def readTable(table: String): DataFrame = {
    null
  }
}
