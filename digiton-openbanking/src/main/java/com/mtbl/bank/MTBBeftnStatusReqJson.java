package com.mtbl.bank;

public class MTBBeftnStatusReqJson {
    private String traceNo;
    private String fromDate;
    private String toDate;
    private String channelId;

    public MTBBeftnStatusReqJson(String traceNo, String fromDate, String toDate, String channelId) {
        super();
        this.traceNo = traceNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.channelId = channelId;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
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
        String tempFromDate = "";
        String tempToDate = "";

        String jsonGeneration = "{";
        if (traceNo.isEmpty()) {
            tempFromDate = fromDate;
            tempToDate = toDate;
        }

        String traceJsonPart = "\"traceno\":\"" + traceNo + "\",";
        jsonGeneration += traceJsonPart;
        String dateJsonPart = "\"fromDate\":\"" + tempFromDate + "\"," + "\"toDate\":\"" + tempToDate + "\",";
        jsonGeneration += dateJsonPart;
        jsonGeneration += "\"channelId\":\"" + channelId + "\"" + "}";
        return jsonGeneration;
    }
}
