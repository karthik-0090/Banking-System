package com.bank.model;

import java.io.Serializable;

public abstract class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String accountNumber;
    protected String holderName;
    protected double balance;

    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("No!!!! Deposit amount must be positive!");
            return;
        }
        balance += amount;
        System.out.println("Hurry!! Amount deposited successfully!");
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Hurry!! Withdrawal successful!");
            return true;
        } else {
            System.out.println("Oops!!! Insufficient balance!");
            return false;
        }
    }

    public abstract void displayAccountInfo();
}