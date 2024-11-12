import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private Map<String, Integer> wordCount;
    private Map<String, Integer> spamWords;
    private int avgWords;
    private int avgSentences;

    public Statistics() {
        wordCount = new HashMap<String, Integer>();
        spamWords = new HashMap<String, Integer>();
        avgWords = 0;
        avgSentences = 0;
    }

    public void addEmail(Email e) {

    }
    public int getAvgWords() {
        return avgWords;
    }
    public int getAvgSentences() {
        return avgSentences;
    }
}
