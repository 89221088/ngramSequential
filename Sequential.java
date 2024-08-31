import java.util.*;

public class Sequential extends NGramInterface {

    @Override
    public Map<String, Float> count(String text, int n) {
        Map<String, Float> nGrams = new HashMap<>();
        String[] words = text.split("\\s+"); // Split text into words

        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder nGram = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j > 0) nGram.append(" ");
                nGram.append(words[i + j]);
            }
            String nGramStr = nGram.toString().trim();
            nGrams.put(nGramStr, nGrams.getOrDefault(nGramStr, 0.0F) + 1);
        }

        return nGrams;
    }

    @Override
    public Map<String, Float> probability(Map<String, Float> nGrams) {
        Map<String, Float> probabilities = new HashMap<>();
        Map<String, Float> prefixCounts = new HashMap<>();

        // Calculate the total count for each prefix (first word of the n-gram)
        for (Map.Entry<String, Float> entry : nGrams.entrySet()) {
            String nGram = entry.getKey();
            String prefix = nGram.split(" ")[0];
            prefixCounts.put(prefix, prefixCounts.getOrDefault(prefix, Float.valueOf(0)) + entry.getValue());
        }

        // Calculate the probability P(B|A) for each n-gram
        for (Map.Entry<String, Float> entry : nGrams.entrySet()) {
            String nGram = entry.getKey();
            String prefix = nGram.split(" ")[0];
            Float totalPrefixCount = prefixCounts.get(prefix);
            float probability = entry.getValue() / (float) totalPrefixCount;
            probabilities.put(nGram, probability);
        }

        return probabilities;
    }

    @Override
    public Map<String, Float> weight(Map<String, Float> elements, Map<String, Float> probabilities) {
        Map<String, Float> weightedElements = new HashMap<>();

        for (Map.Entry<String, Float> element : elements.entrySet()) {
            String nGram = element.getKey();
            Float probability = probabilities.getOrDefault(nGram, 0.0f);
            weightedElements.put(nGram, element.getValue() * probability);
        }

        return weightedElements;

    }
}