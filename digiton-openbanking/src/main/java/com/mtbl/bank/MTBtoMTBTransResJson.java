package com.mtbl.bank;

public class MTBtoMTBTransResJson {
    public String cbsJournalNo;
    public String cbsTxnID;
    public String cbsCode;
    public String cbsResDate;
    public String resCode;
    public String resMsg;
    public String logId;

    public String getCbsJournalNo() {
        return cbsJournalNo;
    }

    public void setCbsJournalNo(String cbsJournalNo) {
        this.cbsJournalNo = cbsJournalNo;
    }

    public String getCbsTxnID() {
        return cbsTxnID;
    }

    public void setCbsTxnID(String cbsTxnID) {
        this.cbsTxnID = cbsTxnID;
    }

    public String getCbsCode() {
        return cbsCode;
    }

    public void setCbsCode(String cbsCode) {
        this.cbsCode = cbsCode;
    }

    public String getCbsResDate() {
        return cbsResDate;
    }

    public void setCbsResDate(String cbsResDate) {
        this.cbsResDate = cbsResDate;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Override
    public String toString() {
        return "{\"cbsJournalNo\":\"" + cbsJournalNo + "\", \"cbsTxnID\":\"" + cbsTxnID + "\", \"cbsCode\":\"" + cbsCode
                + "\", \"cbsResDate\":\"" + cbsResDate + "\", \"resCode\":\"" + resCode + "\", \"resMsg\":\"" + resMsg
                + "\", \"logId\":\"" + logId + "\"}";
    }
}
