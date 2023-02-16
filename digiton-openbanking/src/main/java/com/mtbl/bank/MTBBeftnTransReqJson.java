package com.mtbl.bank;

public class MTBBeftnTransReqJson {
    private String eftTrnsType;
    private String amount;
    private String dfiAccountno;
    private String dscrptn;
    private String receiveBank;
    private String receiverName;
    private String channelId;
    private String uniqueID;

    public MTBBeftnTransReqJson(String eftTrnsType, String amount, String dfiAccountno, String dscrptn,
            String receiveBank, String receiverName, String channelId, String uniqueID) {
        this.eftTrnsType = eftTrnsType;
        this.amount = amount;
        this.dfiAccountno = dfiAccountno;
        this.dscrptn = dscrptn;
        this.receiveBank = receiveBank;
        this.receiverName = receiverName;
        this.channelId = channelId;
        this.uniqueID = uniqueID;
    }

    public String getEftTrnsType() {
        return eftTrnsType;
    }

    public void setEftTrnsType(String eftTrnsType) {
        this.eftTrnsType = eftTrnsType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDfiAccountno() {
        return dfiAccountno;
    }

    public void setDfiAccountno(String dfiAccountno) {
        this.dfiAccountno = dfiAccountno;
    }

    public String getDscrptn() {
        return dscrptn;
    }

    public void setDscrptn(String dscrptn) {
        this.dscrptn = dscrptn;
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

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    @Override
    public String toString() {
        String tempEftTrnsType = "";
        String jsonGeneration = "{";

        if (eftTrnsType.contains("PUSH")) {
            tempEftTrnsType = eftTrnsType;
        } else if (eftTrnsType.contains("PULL")) {
            tempEftTrnsType = eftTrnsType;
        }

        String pullJsonPart = "\"eftTrnsType\": \"" + tempEftTrnsType + "\",";

        jsonGeneration += pullJsonPart;
        jsonGeneration += "\"amount\": \"" + amount + "\"," + "\"dfiAccountno\": \"" + dfiAccountno + "\","
                + "\"dscrptn\": \"" + dscrptn + "\"," + "\"receiveBank\": \"" + receiveBank + "\","
                + "\"receiverName\": \"" + receiverName + "\"," + "\"channelId\": \"" + channelId + "\","
                + "\"uniqueID\": \"" + uniqueID + "\"" + "}";

        // return "[eftTrnsType=" + eftTrnsType + ", amount=" + amount + ",
        // dfiAccountno=" + dfiAccountno + ", dscrptn=" + dscrptn + ",receiveBank=" +
        // receiveBank + ",receiverName=" + receiverName + ",channelId=" + channelId +
        // ",uniqueID=" + uniqueID + "]";
        return jsonGeneration;

    }
}
