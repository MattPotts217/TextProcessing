import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/*
        This class will work as the final testing for the spam filter
        Created by Matt Potts: Dec. 5, 2024
 */
public class TestFinal {
    static ArrayList<Email> trainingEmails = new ArrayList<>();
    static ArrayList<Email> testingEmails = new ArrayList<>();

        public static void main(String[] args) throws FileNotFoundException {
            //create statistics based on the training set
            String fileName = "full_training.csv";
            Statistics s = new Statistics();
            createTrainingSet(fileName, s);

            //now for the real test
            fileName = "spam_or_not_spam.csv";
            createDataSet(fileName);
            ArrayList<Email> testSpam = new ArrayList<>();
            ArrayList<Email> testHam = new ArrayList<>();
            for(Email email : testingEmails) {
                email.calculateWeight(s);
                email.changeSpam();
                if(email.isSpam()) {
                    testSpam.add(email);
                }
                else
                    testHam.add(email);
            }
            //correctness based on what i'm pretty sure is the correct answer from the set
            System.out.println("Spam Emails total: " + testSpam.size() + "\nHam Emails total: " + testHam.size());
            System.out.println("Correct: " + testSpam.size() / 500.0 + "%");
        }

        /*
        A test method to create a data set based on the training sets
         */
        public static void createDataSet(String fileName) throws FileNotFoundException {
            Scanner sc = new Scanner(new File(fileName));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Email e = new Email(line);
                testingEmails.add(e);
            }
            sc.close();
        }

        public static void createTrainingSet(String fileName, Statistics s) throws FileNotFoundException {
            Scanner sc = new Scanner(new File(fileName));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                boolean spam = false;
                if (line.charAt(line.length() - 1) == '1')
                    spam = true;
                Email e = new Email(line.substring(0, line.length() - 1), spam);
                trainingEmails.add(e);
            }
            for (Email email : trainingEmails) {
                s.addEmail(email);
            }
            s.calculateWordWeights();
            for(Email email : trainingEmails) {
                email.calculateWeight(s);
                email.changeSpam();
            }
            sc.close();
        }
    }