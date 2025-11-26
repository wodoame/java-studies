package com.bam;

public class TransactionManager {
    private Transaction[] transactions;
    private int transactionCount;

    public TransactionManager() {
        this.transactions = new Transaction[200];
        this.transactionCount = 0;
    }

    public void addTransaction(Transaction transaction) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = transaction;
        } else {
            System.out.println("Transaction history full. Cannot add new transaction.");
        }
    }

    public void viewTransactionsByAccount(String accountNumber) {
        System.out.println("Transaction History for Account: " + accountNumber);
        boolean found = false;
        // Display in reverse chronological order (newest first)
        for (int i = transactionCount - 1; i >= 0; i--) {
            if (transactions[i].getAccountNumber().equals(accountNumber)) {
                transactions[i].displayTransactionDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for this account.");
        }
    }

    public double calculateTotalDeposits() {
        double total = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getType().equalsIgnoreCase("deposit")) {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }

    public double calculateTotalWithdrawals() {
        double total = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getType().equalsIgnoreCase("withdrawal")) {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }

    public int getTransactionCount() {
        return transactionCount;
    }
}
