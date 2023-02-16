package com.mtbl.bank;

public class MTBBeftnStatusResJson {

    private String logId;
    private String logIdSpecified;
    private MTBBeftnStatusOutResJson beftnOutwardResponse;
    private String resCode;
    private String resMsg;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public MTBBeftnStatusOutResJson getBeftnOutwardResponse() {
        return beftnOutwardResponse;
    }

    public void setBeftnOutwardResponse(MTBBeftnStatusOutResJson beftnOutwardResponse) {
        this.beftnOutwardResponse = beftnOutwardResponse;
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

    public String getLogIdSpecified() {
        return logIdSpecified;
    }

    public void setLogIdSpecified(String logIdSpecified) {
        this.logIdSpecified = logIdSpecified;
    }

    @Override
    public String toString() {
        String jsonStringResponse = "{ \"logId\":\"" + logId + "\", \"logIdSpecified\":\"" + logIdSpecified
                + "\", \"beftnOutwardResponse\":" + beftnOutwardResponse + ", \"resCode\":\"" + resCode
                + "\", \"resMsg\":\"" + resMsg + "\"}";

        return jsonStringResponse;
    }

}
