package streaming

import org.apache.flink.streaming.api.scala._

object StreamingWordCount {

    def main(args: Array[String]) {

        val env = StreamExecutionEnvironment.getExecutionEnvironment

        // create a stream using socket

        val socketStream = env.socketTextStream("localhost", 9123)

        // implement word count

        val wordsStream = socketStream.flatMap(value => value.split("\\s+")).map(value => (value, 1))

        val keyValuePair = wordsStream.keyBy(0)

        val countPair = keyValuePair.sum(1)

        // print the results

        countPair.print()
        countPair.writeAsText("hdfs://hadoop-poc/test-data/output/out3").setParallelism(1)

        // execute the program
        print("Executing the code")
        env.execute()

    }

}
