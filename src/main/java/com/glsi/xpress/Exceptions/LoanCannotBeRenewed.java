package com.glsi.xpress.Exceptions;

public class LoanCannotBeRenewed extends Exception{
    public LoanCannotBeRenewed(String message) {
        super(message);
    }

    public LoanCannotBeRenewed() {
        super("Loan cannot be renewed!");
    }
}
