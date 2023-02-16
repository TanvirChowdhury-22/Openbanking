package com.mtbl.bank;

public class MTBBeftnPullBeneficiaryReqJson {

    private String dfiAccountno;
    private String receiveBank;
    private String receiverName;
    private String channelId;

    public MTBBeftnPullBeneficiaryReqJson(String dfiAccountno, String receiveBank, String receiverName,
            String channelId) {
        this.dfiAccountno = dfiAccountno;
        this.receiveBank = receiveBank;
        this.receiverName = receiverName;
        this.channelId = channelId;
    }

    public String getDfiAccountno() {
        return dfiAccountno;
    }

    public void setDfiAccountno(String dfiAccountno) {
        this.dfiAccountno = dfiAccountno;
    }

    public String getReceiveBank() {
        return receiveBank;
    }

    public void setReceiveBank(String receiveBank) {
        this.receiveBank = receiveBank;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        String jsonGeneration = "{";
        jsonGeneration += "\"dfiAccountno\": \"" + dfiAccountno + "\"," + "\"receiveBank\": \"" + receiveBank + "\","
                + "\"receiverName\": \"" + receiverName + "\"," + "\"channelId\": \"" + channelId + "\"" + "}";
        return jsonGeneration;
    }

}