package com.mtbl.bank;

public class MTBOnlineStmtReqJson {
    private String accNo;
    private String fromDate;
    private String nooftrn;
    private String toDate;
    private String channelId;

    public MTBOnlineStmtReqJson(String accNo, String fromDate, String nooftrn, String toDate,
            String channelId) {
        super();
        this.accNo = accNo;
        this.fromDate = fromDate;
        this.nooftrn = nooftrn;
        this.toDate = toDate;
        this.channelId = channelId;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getNooftrn() {
        return nooftrn;
    }

    public void setNooftrn(String nooftrn) {
        this.nooftrn = nooftrn;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        String jsonStringRequest = "{\"accNo\": \"" + accNo + "\", \"fromDate\": \"" + fromDate
                + "\", \"nooftrn\": \"" + nooftrn + "\", \"toDate\": \"" + toDate + "\", \"channelId\": \""
                + channelId + "\"}";
        return jsonStringRequest;
    }

}
