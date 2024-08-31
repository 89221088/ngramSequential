import java.util.Map;

public abstract class NGramInterface {

    // Map<String, Integer> analyze(String text, int n);
    //  Map<String, Double> computeConditionalProbabilities(Map<String, Integer> nGrams);

    abstract Map<String, Float> count(String words, int n);

    abstract Map<String, Float> probability(Map<String, Float> nGrams);

    abstract public Map<String, Float> weight(Map<String, Float> nGrams, Map<String, Float> probabilities);

}