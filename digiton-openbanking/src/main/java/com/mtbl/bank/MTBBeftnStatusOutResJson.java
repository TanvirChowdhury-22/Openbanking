package com.mtbl.bank;

import java.util.List;

public class MTBBeftnStatusOutResJson {

    private String status;
    private String statusCode;
    private String message;
    private List<MTBBeftnStatusOutDataResJson> beftnResponseDataJsonList;

    public MTBBeftnStatusOutResJson() {
    }

    public MTBBeftnStatusOutResJson(String status, String statusCode, String message,
            List<MTBBeftnStatusOutDataResJson> beftnResponseDataJsonList) {
        super();
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.beftnResponseDataJsonList = beftnResponseDataJsonList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<MTBBeftnStatusOutDataResJson> getBeftnResponseDataJsonList() {
        return beftnResponseDataJsonList;
    }

    public void setBeftnResponseDataJsonList(List<MTBBeftnStatusOutDataResJson> beftnResponseDataJsonList) {
        this.beftnResponseDataJsonList = beftnResponseDataJsonList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String jsonStringOutwardResponse = " { \"status\":\"" + status + "\", \"statusCode\":\"" + statusCode;
        if (message != null) {
            jsonStringOutwardResponse += "\", \"message\": " + message;
        } else {
            jsonStringOutwardResponse += "\", \"message\": " + "null";
        }
        jsonStringOutwardResponse += "\", \"data\": " + beftnResponseDataJsonList + " }";
        return jsonStringOutwardResponse;
    }
}