package com.bank.main;

import java.util.Scanner;
import com.bank.design.Heading;
import com.bank.model.Account;
import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.service.Bank;

public class BankApp {
    static int a = 0;

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        bank.loadAccounts();

        while (true) {
            if (a == 0) {
                Heading.disp();
                a++;
            }

            System.out.println("\n\n=== CV 225 BANK ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Money");
            System.out.println("6. Display Account Info");
            System.out.println("7. Display All Accounts");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    String accNo = bank.generateAccountNumber();
                    System.out.print("Enter Holder Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double bal = sc.nextDouble();
                    sc.nextLine(); // consume newline

                    System.out.print("Select Account Type (1. Savings  2. Current): ");
                    int type = sc.nextInt();
                    sc.nextLine();

                    Account acc = null;
                    if (type == 1) {
                        acc = new SavingsAccount(accNo, name, bal);
                    } else if (type == 2) {
                        acc = new CurrentAccount(accNo, name, bal);
                    } else {
                        System.out.println("Invalid Account Type!");
                        break;
                    }
                    bank.createAccount(acc);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextLine();
                    acc = bank.findAccount(accNo);
                    if (acc != null) {
                        System.out.print("Enter Deposit Amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        acc.deposit(amt);
                    } else {
                        System.out.println("!!!!! Account not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextLine();
                    acc = bank.findAccount(accNo);
                    if (acc != null) {
                        System.out.print("Enter Withdraw Amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        acc.withdraw(amt);
                    } else {
                        System.out.println("!!! Account not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextLine();
                    acc = bank.findAccount(accNo);
                    if (acc != null) {
                        System.out.println("Current Balance: Rs." + acc.getBalance());
                    } else {
                        System.out.println("!!! Account not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Sender Account Number: ");
                    String from = sc.nextLine();
                    System.out.print("Enter Receiver Account Number: ");
                    String to = sc.nextLine();
                    System.out.print("Enter Transfer Amount: ");
                    double amt = sc.nextDouble();
                    sc.nextLine();
                    bank.transferMoney(from, to, amt);
                    break;

                case 6:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextLine();
                    acc = bank.findAccount(accNo);
                    if (acc != null) {
                        acc.displayAccountInfo();
                    } else {
                        System.out.println("!!! Account not found!");
                    }
                    break;

                case 7:
                    bank.displayAllAccounts();
                    break;

                case 0:
                    bank.saveAccounts();
                    System.out.println("Yeah!! Exiting... Data saved successfully!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("!!!! Invalid choice!");
            }
        }
    }
}