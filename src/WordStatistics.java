import java.io.*;
import java.util.*;

public class WordStatistics {

    public static void main(String[] args) throws IOException {
        File file = new File("text.txt");

        List<String> words = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("[\\s\\p{Punct}]+");

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                if (!word.isEmpty() && word.matches("[a-zA-Z]+")) {
                    words.add(word);
                }
            }

        }

        Collections.sort(words);
        System.out.println("Отсортированные слова:");
        for (String word : words) {
            System.out.println(word);
        }

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        int totalWords = words.size();

        System.out.println("Статистика по словам:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            double percentage = (count * 100.0) / totalWords;
            System.out.printf("%s: %d (%.2f%%)%n", word, count, percentage);
        }

        int maxCount = Collections.max(wordCount.values());

        System.out.println("Слова с максимальной частотой (" + maxCount + "):");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                System.out.println(entry.getKey());
            }
        }
    }
}