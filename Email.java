public class Email {
    private String contents;
    private int spam;
    private int length;
    private int numChars;
    private double weight;

    public Email() {
        contents = "";
        spam = 0;
    }

    public Email(String contents) {
        this.contents = contents;
        spam = 0;
        String[] s = contents.split(" ");
        length = s.length;
        numChars = s.length - 1;
        weight = 0;
    }

    public Email(String contents, boolean spam) {
        this.contents = contents;
        this.spam = spam ? 1 : 0;
        weight = 0;
    }

    //This does not work properly
    public void calculateWeight(Statistics s) {
        double wordWeight = 0.0;
        String[] content = contents.split(" ");
        for(String entry: content) {
            if (s.getSpamWords().containsKey(entry))
                wordWeight += s.getWordWeight(entry);
        }
        wordWeight = wordWeight * s.getAvgWords();
        wordWeight = wordWeight + this.length * s.getAvgCharacters();
        this.weight = wordWeight / this.numChars;
    }

    public String getContents() {
        return this.contents;
    }
    public int getLength() {
        return length;
    }
    public int getNumChars() {
        return numChars;
    }
    public boolean isSpam() {
        return spam != 0;
    }

    public double getWeight() {
        return weight;
    }
}
