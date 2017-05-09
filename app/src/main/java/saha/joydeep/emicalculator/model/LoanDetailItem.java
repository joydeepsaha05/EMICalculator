package saha.joydeep.emicalculator.model;

/**
 * Created by joydeep on 09/05/17.
 */

public class LoanDetailItem {

    public double tenure, interestPerMonth, totalPayable;

    LoanDetailItem(double tenure, double interestPerMonth, double totalPayable) {
        this.tenure = tenure;
        this.interestPerMonth = interestPerMonth;
        this.totalPayable = totalPayable;
    }

}
