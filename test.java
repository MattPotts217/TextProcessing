import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/*
    This class serves as a "sandbox" to test if the everything is working properly
 */
public class test {
    static ArrayList<Email> emails = new ArrayList<>();
    static ArrayList<Email> spamMail = new ArrayList<>();
    static ArrayList<Email> notSpam = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("full_training.csv"));
        createDataSet(sc);
//        sc = new Scanner(new File("spam_training.csv"));
//        createDataSet(sc);
        Statistics s = new Statistics();
        for (Email email : emails) {
            System.out.println(email);
            s.addEmail(email);
        }
        s.calculateWordWeights();
        ArrayList<Email> newEmail = new ArrayList<>();
        int newSpam = 0;
        int newHam = 0;
        for(Email email : emails) {
            email.calculateWeight(s);
            email.changeSpam();
            if(email.isSpam())
                newSpam++;
            else
                newHam++;
            System.out.println(email.getWeight() + " | " + email.isSpam());
            newEmail.add(email);
        }
        System.out.println(spamMail.size() + " | " + notSpam.size());
        System.out.println(newSpam + " | " + newHam);
        System.out.println("Spam percent correct:" + (double) newSpam / spamMail.size());
        System.out.println("Ham percent correct:" + (double) newHam / notSpam.size());
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
