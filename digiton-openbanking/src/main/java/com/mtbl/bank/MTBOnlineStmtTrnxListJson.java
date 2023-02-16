package com.mtbl.bank;

import java.util.Date;

public class MTBOnlineStmtTrnxListJson {

    private String currencyName;
    private String currentBalance;
    private String deposit;
    private String description;
    private String narration;
    private Date transactionDate;
    private String transactionType;
    private String withdrawal;

    public MTBOnlineStmtTrnxListJson(String currencyName, String currentBalance, String deposit,
            String description, String narration, Date transactionDate, String transactionType, String withdrawal) {
        super();
        this.currencyName = currencyName;
        this.currentBalance = currentBalance;
        this.deposit = deposit;
        this.description = description;
        this.narration = narration;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.withdrawal = withdrawal;
    }

    public MTBOnlineStmtTrnxListJson() {
        // TODO Auto-generated constructor stub
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    @Override
    public String toString() {
        String jsonStringList = "{\"currencyName\" : \"" + currencyName + "\", \"currentBalance\" : \"" + currentBalance
                + "\", \"deposit\" : \"" + deposit + "\", \"description\" : \"" + description + "\", \"narration\" : \""
                + narration + "\", \"transactionDate\" : \"" + transactionDate + "\", \"transactionType\" : \""
                + transactionType + "\", \"withdrawal\" : \"" + withdrawal + "\"}";
        return jsonStringList;
    }

}
