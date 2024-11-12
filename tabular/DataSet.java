package tabular;
/**
 * Author: Matthew Potts
 * Date: October 3rd, 2024
 * Purpose: Create a data set of independent variables based on dependent variables
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSet {

    private ArrayList<DataRow> data;
    private int numIndepVariables;

    /**
     * @param filename the name of the file to read the data set from
     */
    public DataSet(String filename) throws FileNotFoundException
    {
        File file = new File(filename); //create a file with the given name
        data = new ArrayList<>(); //instantiate the arraylist
        Scanner sc = new Scanner(file); //create a scanner for the file

        String line = sc.nextLine(); //grab the first row, this variable will be used to read rest of rows also
        String[] nums = line.split(","); //split the row, so we can check for how many independent variables
        numIndepVariables = nums.length - 1; //instantiate numIndepVariables, -1 because first num is dependent variables

        while(sc.hasNextLine()) { //while there's more lines in the file to read
            line = sc.nextLine(); //grab the next line
            nums = line.split(","); //split the line so we can read variables
            double[] dArray = new double[nums.length - 1]; //create an array that we will use to place in data
            for(int i = 1; i < nums.length; i++) {
                dArray[i - 1] = Double.parseDouble(nums[i]); //add the numbers that ARE NOT the first, dependent variable, to dArray
            }
            DataRow d = new DataRow(Double.parseDouble(nums[0]), dArray); //create a DataRow with the dependent variable, and the array of independent variables
            data.add(d); //add that DataRow to the data ArrayList
        }
    }

    /**
     * @return the list of rows
     */
    public ArrayList<DataRow> getRows() {
        // FIXME: return the right thing here
        return data;
    }

    /**
     * @return the number of independent variables in each row of the data set
     */
    public int getNumIndependentVariables() {
        // FIXME: return the right thing here
        return numIndepVariables;
    }
}
