import java.util.HashMap;
import java.util.Map;

/*

A Statistics Class that holds information regarding words in emails and
information that will be used to deduce whether an email is spam or not

 */
public class Statistics {
    private Map<String, Integer> wordCount;
    private Map<String, Integer> spamWords;
    private Map<String, Integer> hamWords;
    private Map<String, Double> spamWordWeights;
    private Map<String, Double> hamWordWeights;
    private int numMail;
    private double probability;

    public Statistics() {
        wordCount = new HashMap<String, Integer>();
        spamWords = new HashMap<String, Integer>();
        numMail = 0;
        spamWordWeights = new HashMap<String, Double>();
        hamWords = new HashMap<String, Integer>();
        hamWordWeights = new HashMap<>();
        probability = 0.0;
    }

    /*
    Adds an email to the set list of a Statistics Class, then updates all statistics
    Created by Matt Potts, Nov. 17, 2024
    Edited by Matt Potts, Nov 18, 2024
     */
    public void addEmail(Email e) {
        String[] contents = e.getContents().split(" ");
        numMail++;
        for(int i = 0; i < contents.length; i++) {
            wordCount.put(contents[i], wordCount.getOrDefault(contents[i], 0) + 1);
            if(e.isSpam())
                spamWords.put(contents[i], spamWords.getOrDefault(contents[i], 0) + 1);
            else
                hamWords.put(contents[i], hamWords.getOrDefault(contents[i], 0) + 1);
        }
        calculateWordWeights();
    }


    /*
    Calculates the weight of all words in wordCount against spamWords, if it exists in spamWords
    Created by Matt Potts, Nov. 17, 2024
    Edited by Matt Potts, Nov 18, 2024
    @TODO this does not calculate correctly
     */
    private void calculateWordWeights() {
        for(Map.Entry<String, Integer> entry : spamWords.entrySet()) {
            double weight = (double) spamWords.get(entry.getKey()) / wordCount.size();
            spamWordWeights.put(entry.getKey(), weight);
        }
        for(Map.Entry<String, Integer> entry : hamWords.entrySet()) {
            double weight = (double) hamWords.get(entry.getKey()) / wordCount.size();
            hamWordWeights.put(entry.getKey(), weight);
        }
    }


    /*
    Returns the weight of a given spam word
    Created by Matt Potts, Nov. 17, 2024
     */
    public Double getSpamWordWeight(String word) {
        return spamWordWeights.get(word);
    }


    /*
    Returns the map for the count of all words
    Created by Matt Potts, Nov. 17, 2024
     */
    public Map<String, Integer> getWordCount() {
        return wordCount;
    }
    /*
    returns the map for the count of the spam words
    Created by Matt Potts, Nov. 17, 2024
     */
    public Map<String, Integer> getSpamWords() {
        return spamWords;
    }

    /*
    returns the map for the weight of the words
    Created by Matt Potts, Nov. 17, 2024
     */
    public Map<String, Double> getSpamWordWeights() {
        return spamWordWeights;
    }

    /*
    returns the map for all of the words in the not spam map
    Created by Matt Potts, Nov. 18, 2024
     */
    public Map<String, Integer> getHamWords() {
        return hamWords;
    }
    /*
    returns the map of the weights for hamWords
    Created by Matt Potts, Nov. 18, 2024
     */
    public Map<String, Double> getHamWordWeights() {
        return hamWordWeights;
    }
}
