package com.bam;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private String accountNumber;
    private String type;
    private double amount;
    private double balanceAfter;
    private Date timestamp;
    private static int transactionCounter = 1;

    public Transaction(String accountNumber, String type, double amount, double balanceAfter) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = new Date();
        this.transactionId = generateTransactionId();
    }

    private String generateTransactionId() {
        return String.format("TXN%03d", transactionCounter++);
    }

    public void displayTransactionDetails() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Date: " + timestamp);
        System.out.println("Type: " + type);
        System.out.println("Amount: $" + amount);
        System.out.println("Balance After: $" + balanceAfter);
        System.out.println("-------------------------");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}
