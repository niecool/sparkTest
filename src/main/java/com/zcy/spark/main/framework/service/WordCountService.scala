package com.zcy.spark.main.framework.service

import com.zcy.spark.main.framework.common.TService
import com.zcy.spark.main.framework.dao.WordCountDao

class WordCountService extends TService{

  private val wordCountDao = new WordCountDao()

  /**
   * 服务层
   */
  def dataAnalysis()={
    val lines = wordCountDao.readFile("data/")
    lines.collect().foreach(println);
    val array : Array[(String,Int)]= lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).collect()
    array.foreach(println)
  }
}
