package com.mtbl.bank;

import java.util.List;

public class MTBOnlineStmtResJson {
    private String noOfRows;
    private String noOfRowsSpecified;
    private String resCode;
    private String resMsg;
    private List<MTBOnlineStmtTrnxListJson> transactionList;

    public MTBOnlineStmtResJson(String noOfRows, String noOfRowsSpecified, String resCode, String resMsg,
            List<MTBOnlineStmtTrnxListJson> transactionList) {
        super();
        this.noOfRows = noOfRows;
        this.noOfRowsSpecified = noOfRowsSpecified;
        this.resCode = resCode;
        this.resMsg = resMsg;
        this.transactionList = transactionList;
    }

    public MTBOnlineStmtResJson() {
        // TODO Auto-generated constructor stub
    }

    public String getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(String noOfRows) {
        this.noOfRows = noOfRows;
    }

    public String getNoOfRowsSpecified() {
        return noOfRowsSpecified;
    }

    public void setNoOfRowsSpecified(String noOfRowsSpecified) {
        this.noOfRowsSpecified = noOfRowsSpecified;
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

    public List<MTBOnlineStmtTrnxListJson> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<MTBOnlineStmtTrnxListJson> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        String jsonStringResponse = "{\"noOfRows\" : \"" + noOfRows + "\", \"noOfRowsSpecified\" : \""
                + noOfRowsSpecified + "\", \"resCode\"=\"" + resCode + "\", \"resMsg\" : \"" + resMsg;
        if (transactionList != null && transactionList.size() > 0) {
            jsonStringResponse += "\", \"transactionList\" : " + transactionList;
        } else {
            jsonStringResponse += "\", \"transactionList\" : null";
        }
        jsonStringResponse += "}";
        return jsonStringResponse;
    }
}
