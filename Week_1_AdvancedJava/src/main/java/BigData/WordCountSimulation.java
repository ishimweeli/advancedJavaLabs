package BigData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCountSimulation {
    public static Map<String, Integer> mapLine(String line) {
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : line.split("\\s+")) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        return wordCounts;
    }

    public static Map<String, Integer> reduceCounts(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> combinedCounts = new HashMap<>(map1);
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            combinedCounts.put(entry.getKey(), combinedCounts.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
        return combinedCounts;
    }

    public static void main(String[] args) {
        String text = "This is a sample text to count words. Here are some more words to test.";
        List<String> lines = Arrays.asList(text.split("\n"));

        Map<String, Integer> combinedMapResults = lines.stream()
                .map(WordCountSimulation::mapLine)
                .reduce(new HashMap<>(), WordCountSimulation::reduceCounts);

        System.out.println("Word count results:");
        combinedMapResults.forEach((word, count) -> System.out.println(word + ": " + count));
    }
}