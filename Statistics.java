import java.util.HashMap;
import java.util.Map;

/*

A Statistics Class that holds information regarding words in emails and
information that will be used to deduce whether an email is spam or not

 */
public class Statistics {
    private Map<String, Integer> wordCount;
    private Map<String, Integer> spamWords;
    private Map<String, Double> wordWeights;
    private int avgWords;
    private int avgSentences;
    private int numSentences;
    private int avgCharacters;
    private int numMail;

    public Statistics() {
        wordCount = new HashMap<String, Integer>();
        spamWords = new HashMap<String, Integer>();
        avgWords = 0;
        avgSentences = 0;
        numMail = 0;
        numSentences = 0;
        avgCharacters = 0;
        wordWeights = new HashMap<String, Double>();
    }

    /*
    Adds an email to the set list of a Statistics Class
    Created by Matt Potts, Nov. 17, 2024
     */
    public void addEmail(Email e) {
        String[] contents = e.getContents().split(" ");
        numMail++;
        for(int i = 0; i < contents.length; i++) {
            wordCount.put(contents[i], wordCount.getOrDefault(contents[i], 0) + 1);
            if(e.isSpam())
                spamWords.put(contents[i], spamWords.getOrDefault(contents[i], 0) + 1);
            if(contents[i].contains("."))
                numSentences++;
        }
        calulateAverageCharacters();
        calculateAverageWords();
        calculateAverageSentences();
        calculateWordWeights();
        e.calculateWeight(this);
    }

    /*
    Calculates the average number of words across all emails
    Created by Matt Potts, Nov. 17, 2024
     */
    private void calculateAverageWords() {
        int sum = 0;
        for(Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            sum += entry.getValue();
        }
        avgWords = sum / numMail;
    }

    /*
    Calculates the weight of all words in wordCount against spamWords, if it exists in spamWords
    Created by Matt Potts, Nov. 17, 2024
     */
    private void calculateWordWeights() {
        for(Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if(spamWords.containsKey(entry.getKey())) {
                double weight = (double) spamWords.get(entry.getKey()) / wordCount.get(entry.getKey());
                wordWeights.put(entry.getKey(), weight);
            }
        }
    }

    private void calulateAverageCharacters() {
        int sum = 0;
        for(Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            sum += entry.getValue();
        }
        avgCharacters = sum / numMail;
    }

    public Double getWordWeight(String word) {
        return wordWeights.get(word);
    }
    /*
    Calculates the average number of sentences
    Created by Matt Potts, Nov. 17, 2024
     */
    private void calculateAverageSentences() {
        avgSentences = numSentences / numMail;
    }

    public int getAvgWords() {
        return avgWords;
    }

    public int getAvgSentences() {
        return avgSentences;
    }

    public Map<String, Integer> getWordCount() {
        return wordCount;
    }

    public Map<String, Integer> getSpamWords() {
        return spamWords;
    }

    public Map<String, Double> getWordWeights() {
        return wordWeights;
    }

    public int getAvgCharacters() {
        return avgCharacters;
    }
}
