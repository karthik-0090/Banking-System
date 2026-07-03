package com.bank.model;

import java.io.Serializable;

public class SavingsAccount extends Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final double interest = 4.5;

    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("---- Savings Account ----");
        System.out.println("Account No: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: Rs." + balance);
        System.out.println("Interest Rate: " + interest + "%");
        System.out.println("-------------------------");
    }
}