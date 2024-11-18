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
        this.contents = contents;
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
        this.contents = contents;
        this.spam = spam ? 1 : 0;
        numChars = contents.length() - 1;
        String[] s = contents.split(" ");
        length = s.length;
        weight = 0;
    }

    /*
    Calculates the weight of the Email, takes a Statistics class as a parameter to help weigh it
    Created by Matt Potts, Nov 17, 2024
    @TODO: this does not work properly
     */
    public void calculateWeight(Statistics s) {

        double spamWeight = 1.0;
        String[] content = contents.split(" ");
        for(String entry : content) {
            if(s.getSpamWordWeights().containsKey(entry))
                if (s.getSpamWordWeights().get(entry) > 0)
                    spamWeight *= s.getSpamWordWeights().get(entry);
        }

        double hamWeight = 1.0;
        for(String entry : content) {
            if(s.getHamWordWeights().containsKey(entry))
                if (s.getHamWordWeights().get(entry)  > 0)
                    hamWeight *= s.getHamWordWeights().get(entry);
        }
        if(hamWeight > spamWeight)
            weight = hamWeight;
        else
            weight = spamWeight;
    }

    /*
    returns the contents of an email as a string
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
}
