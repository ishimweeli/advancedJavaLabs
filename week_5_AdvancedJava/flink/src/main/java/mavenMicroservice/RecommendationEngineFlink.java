package mavenMicroservice;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class RecommendationEngineFlink {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> dataStream = env.addSource(new SourceFunction<String>() {
            @Override
            public void run(SourceContext<String> ctx) throws Exception {
                ctx.collect("user1,movie1,5");
                ctx.collect("user1,movie2,3");
            }

            @Override
            public void cancel() {
            }
        });

        DataStream<String> recommendations = dataStream
                .map(new MapFunction<String, String>() {
                    @Override
                    public String map(String value) throws Exception {
                        String[] parts = value.split(",");
                        String user = parts[0];
                        String movie = parts[1];
                        String rating = parts[2];
                        return "Recommended for " + user + ": " + movie + " with rating " + rating;
                    }
                });

        recommendations.print();

        env.execute("Recommendation Engine");
    }
}
