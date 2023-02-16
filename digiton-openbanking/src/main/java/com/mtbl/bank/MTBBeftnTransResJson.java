package com.mtbl.bank;

public class MTBBeftnTransResJson {
    private String resCode;
    private String resMsg;
    private String apiMsg;
    private MTBInWordBeftnInfoJson inwordbeftninfo;

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

    public String getApiMsg() {
        return apiMsg;
    }

    public void setApiMsg(String apiMsg) {
        this.apiMsg = apiMsg;
    }

    public MTBInWordBeftnInfoJson getInwordbeftninfo() {
        return inwordbeftninfo;
    }

    public void setInwordbeftninfo(MTBInWordBeftnInfoJson inwordbeftninfo) {
        this.inwordbeftninfo = inwordbeftninfo;
    }

    @Override
    public String toString() {
        String jsonStringResponse = "{";
        String jsonStringResponse1 = "\"apiMsg\": \"" + apiMsg + "\"," + "\"inwordbeftninfo\": \"" + inwordbeftninfo
                + "\"," + "\"resCode\": \"" + resCode + "\"," + "\"resMsg\": \"" + resMsg + "\"" + "}";
        String jsonStringResponseDiffSeq = "\"resCode\": \"" + resCode + "\"," + "\"resMsg\": \"" + resMsg + "\","
                + "\"apiMsg\": \"" + apiMsg + "\"," + "\"inwordbeftninfo\": \"" + inwordbeftninfo + "\"" + "}";
        String jsonStringSuccessResponse = jsonStringResponse + jsonStringResponse1;
        String jsonStringErrorResponse = jsonStringResponse + jsonStringResponseDiffSeq;
        String jsonStringResponseByResCode = "";

        if (resCode != null) {
            if (resCode.equals("001")) {
                jsonStringResponseByResCode = jsonStringErrorResponse;
            } else if (resCode.equals("000")) {
                jsonStringResponseByResCode = jsonStringSuccessResponse;
            }
        }

        return jsonStringResponseByResCode;
    }
}