public class Email {
    private String contents;
    private boolean spam;
    private int length;
    private int numChars;
    private double weight;
    public Email() {
        contents = "";
        spam = false;
    }
    public Email(String contents, boolean spam) {

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
        return spam;
    }
}
