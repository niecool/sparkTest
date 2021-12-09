package com.zcy.spark.main.learn.operator.transform

import com.zcy.spark.utils.SparkUtil
import org.apache.spark.HashPartitioner
import org.apache.spark.rdd.RDD

object RDDTransformOperatorTest {
  def main(args: Array[String]): Unit = {
    //获取spark环境
    val ss = SparkUtil.getSparkSession
    val sc = ss.sparkContext
    //从内存中创建rdd
    val rdd1 = sc.parallelize(List(1, 2, 3, 4))
    sc.parallelize(List(1, 2, 3, 4))
    val rdd2 = sc.makeRDD(List(1, 2, 3, 4), 2)
    //从外部存储中创建rdd
    val rdd3: RDD[String] = sc.textFile("input")
    rdd3.collect().foreach(println);
    //从其它的RDD创建
    //主要是通过一个RDD运算完后，再产生RDD
    //直接创建
    //使用new方法直接构造RDD,一般由Spark框架自身使用。
    rdd2.map(_ * 2).collect().foreach(println);
    val maxRDD = rdd2.mapPartitions(iter => {
      List(iter.max).iterator
    })
    maxRDD.collect().foreach(println)

    rdd2.mapPartitionsWithIndex((index, iter) => {
      if (index == 1) {
        iter
      } else {
        //空集合的迭代器
        Nil.iterator
      }
    })

    //类型模式匹配
    val rdd4: RDD[Any] = sc.makeRDD(List(List(1, 2),3, List(3, 4), 5))
    rdd4.flatMap {
      case list: List[_] => list
      case data => List(data)
    }

    //glom 将同一个分区数据直接转换为想用类型的内存数组进行处理，分区不变。
    val rdd5: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
    val glomRDD: RDD[Array[Int]] = rdd5.glom()
    glomRDD.collect().foreach(data => println(data.mkString(",")))

    //groupBy
    val rdd6: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
    rdd6.groupBy(_ % 2).collect().foreach(println)

    //filter
    val rdd7: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    rdd7.filter(_ % 2 == 0).collect().foreach(println)

    //sample 抽取样品数据
    val rdd8: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    rdd8.sample(false, 0.4, 1).collect().foreach(println)

    //distinct(scala使用hashSet实现，spark使用reduceByKey实现分布式去重)

    //coalesce 默认情况下不会将分区数据打乱重新组合
    //repartition底层使用coalesce
    val rdd9: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 4)
    rdd9.coalesce(2, true).saveAsTextFile("output")

    //sortBy

    //双value类型
    val rdd10 = sc.makeRDD(List(1, 2, 3, 4))
    val rdd11 = sc.makeRDD(List(3, 4, 5, 6))
    //交集[1, 2, 3, 4,5,6]
    val rdd12: RDD[Int] = rdd10.intersection(rdd11)
    //并集[3,4]
    val rdd13: RDD[Int] = rdd10.union(rdd11)
    //差集[1,2]
    val rdd14: RDD[Int] = rdd10.subtract(rdd11)
    //拉链[1-3,2-4,3-5,4-6]
    val rdd15: RDD[(Int, Int)] = rdd10.zip(rdd11)


    //partitionBy
    val rdd16 = sc.makeRDD(List(1, 2, 3, 4))
    val mapRDD: RDD[(Int, Int)] = rdd16.map((_, 1))
    //RDD=>PairRDDFunctions
    mapRDD.partitionBy(new HashPartitioner(2)).saveAsTextFile("output")

    //reduceByKey:相同的key分组，value做聚合操作。
    //spark两两聚合，一个的话不走聚合函数，直接输出结果
    val rdd17 = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("b", 4)))
    rdd17.reduceByKey((x: Int, y: Int) => {
      println(s"x=${x},y=${y}")
      x + y
    })

    //groupByKey
    //将数据源中的数据，相同key的数据分在一个组中，形成一个对偶元组
    //元组的第一个元素就是key
    //元组的第二个元素就是相同key的value的集合
    //reduceByKey和groupBykey都存在shuffle的操作，但是reduceByKey可以在shuffle前对分区内相同key的数据进行预聚合功能。
    val rdd18 = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("b", 4)))
    rdd18.groupByKey()

    //aggregateByKey 存在函数柯里化
    //第一个参数
    //        主要用于当碰见第一个key的时候，和第一个value进行分区内计算（两两相比）
    //第二个参数列表
    //      分区内计算规则
    //      分区间操作规则
    val rdd19 = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("b", 4)))
    rdd19.aggregateByKey(0)((x, y) => math.max(x, y), (x, y) => x + y)

    //聚合计算时，分区内和分区间计算相同，使用foldByKey
    rdd19.foldByKey(0)(_ + _)

    //combineByKey:方法需要三个参数
    rdd19.combineByKey(v => v, (x: Int, y) => x + y, (x: Int, y: Int) => x + y)
    //reduceByKey
    //aggregateByKey
    //foldByKey
    //combineByKey 都是使用combineByKey


    //join leftOuterJoin rightOuterJoin效果和平时认知一致
    val rdd20 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val rdd21 = sc.makeRDD(List(("a", 4), ("b", 5), ("c", 6),("c", 7)))
    rdd20.join(rdd21).collect().foreach(println)

    //cogroup : connect+group 相同的key合并成arrayList(a,(1,4))
    rdd20.cogroup(rdd21).collect().foreach(println)


    //行动算子
  }

}
