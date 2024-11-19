import java.util.HashMap;
import java.util.Map;

/*

A Statistics Class that holds information regarding words in emails and
information that will be used to deduce whether an email is spam or not

 */
public class Statistics {
    private Map<String, Integer> wordCount, spamWords, hamWords;
    private Map<String, Double> spamWordWeights, hamWordWeights;
    private int numMail, numSpam, numHam;
    private int spamWordsCount, hamWordsCount;
    private double spamWeight, hamWeight;

    public Statistics() {
        wordCount = new HashMap<String, Integer>();
        spamWords = new HashMap<String, Integer>();
        spamWordWeights = new HashMap<String, Double>();
        hamWords = new HashMap<String, Integer>();
        hamWordWeights = new HashMap<>();
        numMail = 0; numHam = 0; numSpam = 0;
        spamWeight = 0; hamWeight = 0;
        spamWordsCount = 0; hamWordsCount = 0;
    }

    /*
    Adds an email to the set list of a Statistics Class, then updates all statistics
    Created by Matt Potts, Nov. 17, 2024
    Edited by Matt Potts, Nov 18, 2024
    Edited by Matt Potts, Nov 19, 2024: Added counts for numbers of spam emails or not spam emails
     */
    public void addEmail(Email e) {
        String[] contents = e.getContents().split(" ");
        numMail++;
        if(e.isSpam())
            numSpam++;
        else
            numHam++;
        for(int i = 0; i < contents.length; i++) {
            wordCount.put(contents[i], wordCount.getOrDefault(contents[i], 0) + 1);
            if(e.isSpam()) {
                spamWordsCount++;
                spamWords.put(contents[i], spamWords.getOrDefault(contents[i], 0) + 1);
            }
            else {
                hamWordsCount++;
                hamWords.put(contents[i], hamWords.getOrDefault(contents[i], 0) + 1);
            }
        }
    }


    /*
    Calculates the weight of all words in wordCount against spamWords, if it exists in spamWords
    Created by Matt Potts, Nov. 17, 2024
    Edited by Matt Potts, Nov 18, 2024
    Edited by Matt Potts, Nov 19, 2024: I believe this works properly now, does not complete implement Naive Bayes
     */
    public void calculateWordWeights() {
        for(Map.Entry<String, Integer> entry : spamWords.entrySet()) {
            double weight = (double) (entry.getValue() + 1) / spamWords.size();
            spamWordWeights.put(entry.getKey(), weight);
        }
        for(Map.Entry<String, Integer> entry : hamWords.entrySet()) {
            double weight = (double) (entry.getValue() + 1) / hamWords.size();
            hamWordWeights.put(entry.getKey(), weight);
        }
        spamWeight = (double) numSpam / numMail;
        hamWeight = (double) numHam / numMail;
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
    /*
    returns the total number of Mail that the statistics class has received
    Created by Matt Potts, Nov. 19, 2024
     */
    public int getNumMail() {
        return numMail;
    }
    /*
    returns the total number of spam mail the statistics class has received
    Created by Matt Potts, Nov. 19, 2024
     */
    public int getNumSpam() {
        return numSpam;
    }
    /*
    returns the total number of spam mail that the statistics class has received
    Created by Matt Potts, Nov. 19, 2024
     */
    public int getNumHam() {
        return numHam;
    }
    /*
    returns the total number of spam words that the statistics class has received
    Created by Matt Potts, Nov 19. 2024
     */
    public int getSpamWordsCount() {
        return spamWordsCount;
    }
    /*
    returns the total number of ham words that the statistics class has received
    Created by Matt Potts, Nov 19. 2024
     */
    public int getHamWordsCount() {
        return hamWordsCount;
    }
    /*
    returns the weight/probability that an email is spam based on the total number of spam emails
    received by the statistics class divided by the total number of emails received
    Created by Matt Potts, Nov. 19, 2024
     */
    public double getSpamWeight() {
        return spamWeight;
    }
    /*
    returns the weight/probability that an email is not spam based on the total number of spam emails
    received by the statistics class divided by the total number of emails received
    Created by Matt Potts, Nov 19. 2024
     */
    public double getHamWeight() {
        return hamWeight;
    }
}
