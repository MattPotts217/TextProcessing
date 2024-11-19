import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
/*
    This class serves as a "sandbox" to test if the everything is working properly
 */
public class test {
    static ArrayList<Email> emails = new ArrayList<>();
    static ArrayList<Email> spamMail = new ArrayList<>();
    static ArrayList<Email> notSpam = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("ham_training.csv"));
        createDataSet(sc);
        sc = new Scanner(new File("spam_training.csv"));
        createDataSet(sc);
        Statistics s = new Statistics();
        for (Email email : emails) {
            s.addEmail(email);
        }
        s.calculateWordWeights();
        for(Email email : emails) {
            email.calculateWeight(s);
        }
        System.out.println(emails.get(1500).getWeight());
    }

    /*
    A test method to create a data set based on the training sets
     */
    public static void createDataSet(Scanner sc) {
        sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            boolean spam = false;
            if(line.charAt(line.length()-1) == '1')
                spam = true;
            Email e = new Email(line.substring(0, line.length() - 1), spam);
            emails.add(e);
            if(spam)
                spamMail.add(e);
            else
                notSpam.add(e);
        }
    }

}
