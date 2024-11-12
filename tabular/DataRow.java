package tabular;

/**
 * Author: Matthew Potts
 * Date: October 2, 2024
 * Purpose: To store rows by separating the dependent variable from an array of independent variables
 */


public class DataRow {

    private double y;
    private double[] x;

    /**
     * @param y the dependent variable
     * @param x the array of independent variables
     */
    public DataRow(double y, double[] x)
    {
        this.y = y;
        this.x = x;
    }

    /**
     * @return the dependent variable
     */
    public double getDependentVariable() {
        // FIXME: return the right thing here
        return y;
    }

    /**
     * @return the array of independent variables
     */
    public double[] getIndependentVariables() {
        // FIXME: return the right thing here
        return x;
    }
}
