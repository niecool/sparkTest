package com.zcy.spark.utils

import java.util.Base64


object Base64Util {
  def decodeBase64String(string: String): String = {
    val byte = Base64.getDecoder.decode(string)
    new String(byte, "UTF-8")
  }

  //加密
  def encodeBase64String(string: String): String = {
    Base64.getEncoder.encodeToString(string.getBytes("UTF-8"))
  }

  def main(args: Array[String]): Unit = {
    val str = encodeBase64String("""java -jar ...""")
    println(str)
  }

}