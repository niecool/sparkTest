package com.zcy.spark.main.framework.unitls;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;

/**
 * SparkSessionLocal
 *
 * @author chengye.zhao
 * @date 2021/12/6
 */
public class SparkSessionLocal {
    private SparkSession sparkSession;
    private SparkContext sparkContext;

    public SparkSessionLocal() {
    }

    public SparkSessionLocal(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    public SparkSessionLocal(SparkContext sparkContext) {
        this.sparkContext = sparkContext;
    }

    public SparkSessionLocal(SparkSession sparkSession, SparkContext sparkContext) {
        this.sparkSession = sparkSession;
        this.sparkContext = sparkContext;
    }

    public SparkSession getSparkSession() {
        return sparkSession;
    }

    public void setSparkSession(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    public SparkContext getSparkContext() {
        return sparkContext;
    }

    public void setSparkContext(SparkContext sparkContext) {
        this.sparkContext = sparkContext;
    }
}
