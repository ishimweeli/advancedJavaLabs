package mavenMicroservice;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;

public class RecommendationEngine {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("RecommendationEngine")
                .master("local[*]")
                .getOrCreate();

        // Load data
        Dataset<Row> ratings = spark.read()
                .format("csv")
                .option("header", "true")
                .load("data/ratings.csv");

        // Prepare data for ALS
        Dataset<Row> ratingsDF = ratings.withColumn("userId", ratings.col("userId").cast("integer"))
                .withColumn("movieId", ratings.col("movieId").cast("integer"))
                .withColumn("rating", ratings.col("rating").cast("float"));

        // Build the recommendation model
        ALS als = new ALS()
                .setMaxIter(10)
                .setRegParam(0.01)
                .setUserCol("userId")
                .setItemCol("movieId")
                .setRatingCol("rating");

        ALSModel model = als.fit(ratingsDF);

        // Generate top 10 recommendations for each user
        Dataset<Row> userRecs = model.recommendForAllUsers(10);

        // Show recommendations
        userRecs.show();

        spark.stop();
    }
}
