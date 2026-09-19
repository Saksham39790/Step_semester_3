package string.assigment_problems;

import java.util.*;

public class StopWordFrequencyReport {

    static void generateReport(String sentence) {

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        sentence = sentence.toLowerCase();
        sentence = sentence.replaceAll("[.,!?;:]", "");

        String[] words = sentence.split("\\s+");

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {

            boolean isStopWord = false;

            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(frequency.entrySet());

        entries.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        generateReport(sentence);

        sc.close();
    }
}