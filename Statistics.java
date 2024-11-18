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
        hamWords = new HashMap<String, Integer>();
    }

    /*
    Adds an email to the set list of a Statistics Class, then updates all statistics
    Created by Matt Potts, Nov. 17, 2024
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
            if(contents[i].contains("."))
                numSentences++;
        }
        calulateAverageCharacters();
        calculateAverageWords();
        calculateAverageSentences();
        calculateWordWeights();
    }

    /*
    Calculates the average number of words across all emails by taking the count of all words, then dividing by the number of total emails
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
    @TODO introduce hamWords count
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

    /*
    Calculate the average number of characters by taking the total characters across all emails then dividing by total emails
    @TODO I'm not sure if this does what it is supposed to do, will look into
    Created by Matt Potts, Nov. 17, 2024
     */
    private void calulateAverageCharacters() {
        int sum = 0;
        for(Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            sum += entry.getValue();
        }
        avgCharacters = sum / numMail;
    }

    /*
    Returns the weight of a given word
    Created by Matt Potts, Nov. 17, 2024
     */
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

    /*
    returns total average number of words
    Created by Matt Potts Nov. 17, 2024
     */
    public int getAvgWords() {
        return avgWords;
    }

    /*
   returns the average number of sentences
   Created by Matt Potts, Nov. 17, 2024
     */
    public int getAvgSentences() {
        return avgSentences;
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
    public Map<String, Double> getWordWeights() {
        return wordWeights;
    }

    /*
    returns the map for all of the words in the not spam map
    Created by Matt Potts, Nov. 18, 2024
     */
    public Map<String, Integer> getHamWords() {
        return hamWords;
    }
    /*
    returns the average number of characters, calculated by the length of all the emails divided by all emails
    Created by Matt Potts, Nov 17, 2024
     */
    public int getAvgCharacters() {
        return avgCharacters;
    }
}
