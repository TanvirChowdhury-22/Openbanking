package com.mtbl.bank;

public class MTBBeftnStatusOutDataResJson {

    private String compAccountNum;
    private String compId;
    private String compName;
    private String compEntryDesc;
    private String channelId;
    private String dfiAccountNo;
    private String amount;
    private String amountSpecified;
    private String receiverName;
    private String individualId;
    private String receive_bank;
    private String fileGenerated;
    private String bbAcknoledged;
    private String tranDate;
    private String returnStat;

    // Only needed for stopped beftn
    private String originalTraceNumber;
    private String returnDate;
    private String retReason;

    public MTBBeftnStatusOutDataResJson(String compAccountNum, String compId, String compName, String compEntryDesc,
            String channelId, String dfiAccountNo, String amount, String amountSpecified, String receiverName,
            String individualId, String receive_bank, String originalTraceNumber, String fileGenerated,
            String bbAcknoledged, String tranDate, String returnStat, String returnDate, String retReason) {
        super();
        this.compAccountNum = compAccountNum;
        this.compId = compId;
        this.compName = compName;
        this.compEntryDesc = compEntryDesc;
        this.channelId = channelId;
        this.dfiAccountNo = dfiAccountNo;
        this.amount = amount;
        this.amountSpecified = amountSpecified;
        this.receiverName = receiverName;
        this.individualId = individualId;
        this.receive_bank = receive_bank;
        this.originalTraceNumber = originalTraceNumber;
        this.fileGenerated = fileGenerated;
        this.bbAcknoledged = bbAcknoledged;
        this.tranDate = tranDate;
        this.returnStat = returnStat;
        this.returnDate = returnDate;
        this.retReason = retReason;
    }

    public MTBBeftnStatusOutDataResJson() {
        // TODO Auto-generated constructor stub
    }

    public String getCompAccountNum() {
        return compAccountNum;
    }

    public void setCompAccountNum(String compAccountNum) {
        this.compAccountNum = compAccountNum;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompEntryDesc() {
        return compEntryDesc;
    }

    public void setCompEntryDesc(String compEntryDesc) {
        this.compEntryDesc = compEntryDesc;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDfiAccountNo() {
        return dfiAccountNo;
    }

    public void setDfiAccountNo(String dfiAccountNo) {
        this.dfiAccountNo = dfiAccountNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountSpecified() {
        return amountSpecified;
    }

    public void setAmountSpecified(String amountSpecified) {
        this.amountSpecified = amountSpecified;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }

    public String getReceive_bank() {
        return receive_bank;
    }

    public void setReceive_bank(String receive_bank) {
        this.receive_bank = receive_bank;
    }

    public String getOriginalTraceNumber() {
        return originalTraceNumber;
    }

    public void setOriginalTraceNumber(String originalTraceNumber) {
        this.originalTraceNumber = originalTraceNumber;
    }

    public String getFileGenerated() {
        return fileGenerated;
    }

    public void setFileGenerated(String fileGenerated) {
        this.fileGenerated = fileGenerated;
    }

    public String getBbAcknoledged() {
        return bbAcknoledged;
    }

    public void setBbAcknoledged(String bbAcknoledged) {
        this.bbAcknoledged = bbAcknoledged;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getReturnStat() {
        return returnStat;
    }

    public void setReturnStat(String returnStat) {
        this.returnStat = returnStat;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getRetReason() {
        return retReason;
    }

    public void setRetReason(String retReason) {
        this.retReason = retReason;
    }

    @Override
    public String toString() {
        String jsonStringData = "{ \"compAccountNum\":\"" + compAccountNum + "\", \"compId\":\"" + compId
                + "\", \"compName\":\"" + compName + "\", \"compEntryDesc\":\"" + compEntryDesc + "\", \"channelId\":\""
                + channelId + "\", \"dfiAccountNo\":\"" + dfiAccountNo + "\", \"amount\":\"" + amount
                + "\", \"amountSpecified\":\"" + amountSpecified + "\",\"receiverName\":\"" + receiverName
                + "\", \"individualId\":\"" + individualId + "\", \"receive_bank\":\"" + receive_bank;

        if (returnStat != null) {
            if (returnStat.equals("returned")) {
                jsonStringData += "\", \"originalTraceNumber\":\"" + originalTraceNumber + "\"";
            }
        }

        jsonStringData += ", \"fileGenerated\":\"" + fileGenerated + "\", \"bbAcknoledged\":\"" + bbAcknoledged
                + "\", \"tranDate\":\"" + tranDate + "\", \"returnStat\":\"" + returnStat + "\"";

        if (returnStat != null) {
            if (returnStat.equals("returned")) {
                jsonStringData += ", \"returnDate\":\"" + returnDate + "\", \"retReason\":\"" + retReason + "\" ";
            }
        }

        jsonStringData += "}";
        return jsonStringData;
    }
}
