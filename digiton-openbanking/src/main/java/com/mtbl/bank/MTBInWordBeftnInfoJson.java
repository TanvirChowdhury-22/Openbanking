package com.mtbl.bank;

public class MTBInWordBeftnInfoJson {
    private String cbsfjournalNo;
    private String cbsftrnNum;
    private String edrTraceno;
    private String msg;
    private String msgCode;
    private String originalTraceno;
    private String stlmDate;

    public String getCbsfjournalNo() {
        return cbsfjournalNo;
    }

    public void setCbsfjournalNo(String cbsfjournalNo) {
        this.cbsfjournalNo = cbsfjournalNo;
    }

    public String getCbsftrnNum() {
        return cbsftrnNum;
    }

    public void setCbsftrnNum(String cbsftrnNum) {
        this.cbsftrnNum = cbsftrnNum;
    }

    public String getEdrTraceno() {
        return edrTraceno;
    }

    public void setEdrTraceno(String edrTraceNo) {
        this.edrTraceno = edrTraceNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getOriginalTraceno() {
        return originalTraceno;
    }

    public void setOriginalTraceno(String originalTraceno) {
        this.originalTraceno = originalTraceno;
    }

    public String getStlmDate() {
        return stlmDate;
    }

    public void setStlmDate(String stlmDate) {
        this.stlmDate = stlmDate;
    }

    @Override
    public String toString() {
        String jsonStringInWord = "{";
        String jsonStringInWordResponse = "\"msgCode\": \"" + msgCode + "\"," + "\"msg\": \"" + msg + "\","
                + "\"edrTraceno\": \"" + edrTraceno + "\"," + "\"originalTraceno\": \"" + originalTraceno + "\","
                + "\"stlmDate\": \"" + stlmDate + "\"," + "\"cbsfjournalNo\": \"" + cbsfjournalNo + "\","
                + "\"cbsftrnNum\": \"" + cbsftrnNum + "\"" + "}";
        String jsonStringInWordResponseDiffSeq = "\"cbsfjournalNo\": \"" + cbsfjournalNo + "\"," + "\"cbsftrnNum\": \""
                + cbsftrnNum + "\"," + "\"edrTraceno\": \"" + edrTraceno + "\"," + "\"msg\": \"" + msg + "\","
                + "\"msgCode\": \"" + msgCode + "\"," + "\"originalTraceno\": \"" + originalTraceno + "\","
                + "\"stlmDate\": \"" + stlmDate + "\"" + "}";
        String jsonStringInWordSuccessResponse = jsonStringInWord + jsonStringInWordResponseDiffSeq;
        String jsonStringInWordErrorResponse = jsonStringInWord + jsonStringInWordResponse;
        String jsonStringInWordResponseByMsgCode = "";

        if (msgCode.equals("13")) {
            jsonStringInWordResponseByMsgCode = jsonStringInWordErrorResponse;
        } else if (msgCode.equals("000")) {
            jsonStringInWordResponseByMsgCode = jsonStringInWordSuccessResponse;
        }
        return jsonStringInWordResponseByMsgCode;

    }
}
