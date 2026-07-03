package com.bank.model;

import java.io.Serializable;

public class CurrentAccount extends Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final double OVERDRAFT_LIMIT = 1000.0;

    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance + OVERDRAFT_LIMIT >= amount) {
            balance -= amount;
            System.out.println("Yeah!! Withdrawal successful! (Overdraft used if needed)");
            return true;
        } else {
            System.out.println("Insufficent Account Balance! (Overdraft limit exceeded)");
            return false;
        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("---- Current Account ----");
        System.out.println("Account No: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: Rs." + balance);
        System.out.println("Overdraft Limit: Rs." + OVERDRAFT_LIMIT);
        System.out.println("-------------------------");
    }
}