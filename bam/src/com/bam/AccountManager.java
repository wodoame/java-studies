package com.bam;

public class AccountManager {
    private Account[] accounts;
    private int accountCount;

    public AccountManager() {
        this.accounts = new Account[50];
        this.accountCount = 0;
    }

    public void addAccount(Account account) {
        if (accountCount < accounts.length) {
            accounts[accountCount++] = account;
            System.out.println("Account created successfully. Account Number: " + account.getAccountNumber());
        } else {
            System.out.println("Account limit reached. Cannot create new account.");
        }
    }

    public Account findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }

    public void viewAllAccounts() {
        System.out.println("\n--- All Accounts ---");
        for (int i = 0; i < accountCount; i++) {
            accounts[i].displayAccountDetails();
            System.out.println("--------------------");
        }
        System.out.println("Total Accounts: " + accountCount);
        System.out.println("Total Bank Balance: $" + getTotalBalance());
    }

    public double getTotalBalance() {
        double total = 0;
        for (int i = 0; i < accountCount; i++) {
            total += accounts[i].getBalance();
        }
        return total;
    }

    public int getAccountCount() {
        return accountCount;
    }
}
