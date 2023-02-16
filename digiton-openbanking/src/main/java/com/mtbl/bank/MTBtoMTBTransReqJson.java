package com.mtbl.bank;

public class MTBtoMTBTransReqJson {
    private String amount;
    private String creditAccount;
    private String debitAccount;
    private String remarks;
    private String transactionChannelId;
    private String uniqueTxnId;

    public MTBtoMTBTransReqJson(String amount, String creditAccount, String debitAccount, String remarks,
            String transactionChannelId, String uniqueTxnId) {
        this.amount = amount;
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
        this.remarks = remarks;
        this.transactionChannelId = transactionChannelId;
        this.uniqueTxnId = uniqueTxnId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTransactionChannelId() {
        return transactionChannelId;
    }

    public void setTransactionChannelId(String transactionChannelId) {
        this.transactionChannelId = transactionChannelId;
    }

    public String getUniqueTxnId() {
        return uniqueTxnId;
    }

    public void setUniqueTxnId(String uniqueTxnId) {
        this.uniqueTxnId = uniqueTxnId;
    }

    @Override
    public String toString() {
        return "{\"amount\":\"" + amount + "\", \"crAc\":\"" + creditAccount + "\", \"drAc\":\"" + debitAccount
                + "\", \"remarks\":\"" + remarks + "\", \"channelId\":\"" + transactionChannelId
                + "\", \"uniqueTxnId\":\"" + uniqueTxnId + "\"}";
    }

}
