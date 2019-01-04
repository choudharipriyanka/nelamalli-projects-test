package com.nelamalli.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object RDDWordCount {


  def main(args:Array[String]): Unit = {

    val spark:SparkSession = SparkSession.builder()
      .master("local[3]")
      .appName("SparkByExample")
      .getOrCreate()
    val sc = spark.sparkContext

    val rdd:RDD[String] = sc.textFile("C://000_Projects/opt/BigData/test.txt")

    // rdd.collect
    val lis:Array[String] = rdd.collect()
    rdd.foreach(println)
    for(i<-lis)println(i)

    val rdd2 = rdd.flatMap(f=>f.split(" "))

    rdd2.foreach(f=>println(f))

    //Create a Tuple by adding 1 to each word
    val rdd3 = rdd2.map(m=>(m,1))

    rdd3.foreach(println)

    //Filter
    val rdd4 = rdd3.filter(a=> a._1.startsWith("a"))

    rdd4.foreach(println)

    //ReduceBy
   val rdd5 = rdd3.reduceByKey(_ + _)

    rdd5.foreach(println)

    //Swap word,count and sort by key
    rdd5.map(a=>(a._2,a._1)).sortByKey().foreach(println)


  }
}