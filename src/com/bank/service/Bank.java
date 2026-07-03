package com.bank.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.bank.model.Account;

public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;
	private Account[] accounts;
    private int count;
    private static final String DATA_FILE = "accounts.dat";
    private static final String META_FILE = "account_meta.dat";
    private static int lastAccountNumber = 100000000;

    public Bank() {
        accounts = new Account[50];
        count = 0;
        loadMeta();
    }

    public String generateAccountNumber() {
        lastAccountNumber++;
        saveMeta();
        return "ACC" + lastAccountNumber;
    }

    private void saveMeta() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(META_FILE))) {
            out.writeObject(lastAccountNumber);
        } catch (Exception e) {
            System.out.println("!!! Error saving meta data: " + e.getMessage());
        }
    }

    private void loadMeta() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(META_FILE))) {
            lastAccountNumber = (int) in.readObject();
        } catch (Exception e) {
            lastAccountNumber = 100000000; // default start
        }
    }

    public void loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Object[] loaded = (Object[]) in.readObject();
            accounts = new Account[loaded.length];
            System.arraycopy(loaded, 0, accounts, 0, loaded.length);
            count = loaded.length;
        } catch (Exception e) {
            accounts = new Account[50];
            count = 0;
        }
    }

    public void saveAccounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            Account[] saved = new Account[count];
            System.arraycopy(accounts, 0, saved, 0, count);
            out.writeObject(saved);
        } catch (Exception e) {
            System.out.println("Sorry!!! Error saving data: " + e.getMessage());
        }
    }

    public void createAccount(Account acc) {
        if (count == accounts.length) {
            expandArray();
        }
        accounts[count++] = acc;
        saveAccounts();
        System.out.println("Yeah!!! Account created successfully! Account No: " + acc.getAccountNumber());
    }

    private void expandArray() {
        Account[] newArr = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, newArr, 0, accounts.length);
        accounts = newArr;
    }

    public Account findAccount(String accNo) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber().equals(accNo)) {
                return accounts[i];
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        if (count == 0) {
            System.out.println("No accounts found!");
            return;
        }
        System.out.println("\n==== 🧾 ACCOUNT LIST ====");
        for (int i = 0; i < count; i++) {
            accounts[i].displayAccountInfo();
            System.out.println("---------------------------");
        }
    }

    public void transferMoney(String fromAcc, String toAcc, double amount) {
        Account sender = findAccount(fromAcc);
        Account receiver = findAccount(toAcc);

        if (sender == null || receiver == null) {
            System.out.println("Sorry!!! Invalid account number(s)!");
            return;
        }

        if (sender.withdraw(amount)) {
            receiver.deposit(amount);
            saveAccounts();
            System.out.println("Yes.... Transfer successful!");
        }
    }
}