public class Email {
    private String contents;
    private int spam;
    private int length;
    private int numChars;
    private double weight;

    /*
    Constructor for an empty Email with no parameters
    Created by Matt Potts, Nov 17, 2024
     */
    public Email() {
        contents = "";
        spam = 0;
    }

    /*
    Constructor for an Email if it is not known if it is spam or not. This will be used in the testing phase
    Created by Matt Potts, Nov. 17, 2024
    Edited by Matt Potts, Nov 18, 2024: fixed the calculation for the number of characters in an email
     */
    public Email(String contents) {
        this.contents = contents.substring(0, contents.length() - 1);
        numChars = contents.length() - 1;
        spam = 0;
        String[] s = contents.split(" ");
        length = s.length;
        weight = 0;
    }

    /*
    Constructor for an Email if it is known if it is spam or not
    Created by Matt Potts, Nov 17, 2024
    Edited by Matt Potts, Nov 18, 2024: was missing info for num of chars and length
     */
    public Email(String contents, boolean spam) {
        this.contents = contents.substring(0, contents.length() - 1);
        this.spam = spam ? 1 : 0;
        numChars = contents.length() - 1;
        String[] s = contents.split(" ");
        length = s.length;
        weight = 0;
    }

    /*
    Calculates the weight of the Email, takes a Statistics class as a parameter to help weigh it
    Created by Matt Potts, Nov 17, 2024
    Edited by Matt Potts, Nov. 19, 2024: I think this works properly now?
     */
    public void calculateWeight(Statistics s) {
        double spamWeight = Math.log(s.getSpamWeight());
        double hamWeight = Math.log(s.getHamWeight());
        String[] content = contents.split(" ");
        for(String entry : content) {
            spamWeight += Math.log(s.getSpamWordWeights().getOrDefault(entry, 1.0 / (s.getSpamWordWeights().size() + 1)));
            hamWeight += Math.log(s.getHamWordWeights().getOrDefault(entry, 1.0 / (s.getHamWordWeights().size() + 1)));
        }
        double maxWeight = Math.max(spamWeight, hamWeight);
        double logDenominator = Math.log(Math.exp(spamWeight - maxWeight) + Math.exp(hamWeight - maxWeight)) + maxWeight;
        double spamProbability = Math.exp(spamWeight - logDenominator);
        double hamProbability = Math.exp(hamWeight - logDenominator);
        if(spamProbability < 1e-5)
            spamProbability = 0;
        if(hamProbability < 1e-5)
            hamProbability = 0;
        if(spamProbability > hamProbability)
            weight = spamProbability;
        else
            weight = 1 - hamProbability;
    }

    /*
    returns the contents of an email
    Created by Matt Potts, Nov 17, 2024
     */
    public String getContents() {
        return this.contents;
    }

    /*
    returns the length of an email by words
    Created by Matt Potts, Nov 17, 2024
     */
    public int getLength() {
        return length;
    }

    /*
    returns the number of characters in an email
    Created by Matt Potts, Nov 17, 2024
     */
    public int getNumChars() {
        return numChars;
    }
    public void changeSpam() {
        if(weight > .6)
            spam = 1;
        else
            spam = 0;
    }
    /*
    returns whether an email is marked as spam
    Created by Matt Potts, Nov 17, 2024
     */
    public boolean isSpam() {
        return spam != 0;
    }

    /*
    returns the weight of the email, which can help determine whether an email is spam or not
    Created by Matt Potts, Nov. 17, 2024
     */
    public double getWeight() {
        return weight;
    }

    /*
    returns a String version of an email in the form "Email: " + contents
    Created by Matt Potts, Nov 19, 2024
     */
    public String toString() {
        return "Email: " + contents;
    }
}
