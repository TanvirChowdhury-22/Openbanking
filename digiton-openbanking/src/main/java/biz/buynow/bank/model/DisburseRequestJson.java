package biz.buynow.bank.model;

import java.util.List;

public class DisburseRequestJson {
    private String clientRef;
    private String dataSecurity;
    private String startDate;
    private String endDate;
    private String recurFreq;
    private String recurDate;
    private List<RecipientUserJson> recipientUserJsonList;

    public DisburseRequestJson(String clientRef, String dataSecurity, String startDate, String endDate,
            String recurFreq, String recurDate, List<RecipientUserJson> recipientUserJsonList) {
        super();
        this.clientRef = clientRef;
        this.dataSecurity = dataSecurity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurFreq = recurFreq;
        this.recurDate = recurDate;
        this.recipientUserJsonList = recipientUserJsonList;
    }

    public DisburseRequestJson() {
        // TODO Auto-generated constructor stub
    }

    public String getClientRef() {
        return clientRef;
    }

    public void setClientRef(String clientRef) {
        this.clientRef = clientRef;
    }

    public String getDataSecurity() {
        return dataSecurity;
    }

    public void setDataSecurity(String dataSecurity) {
        this.dataSecurity = dataSecurity;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRecurFreq() {
        return recurFreq;
    }

    public void setRecurFreq(String recurFreq) {
        this.recurFreq = recurFreq;
    }

    public String getRecurDate() {
        return recurDate;
    }

    public void setRecurDate(String recurDate) {
        this.recurDate = recurDate;
    }

    public List<RecipientUserJson> getRecipientUserJsonList() {
        return recipientUserJsonList;
    }

    public void setRecipientUserJsonList(List<RecipientUserJson> recipientUserJsonList) {
        this.recipientUserJsonList = recipientUserJsonList;
    }

    @Override
    public String toString() {
        String jsonStr = "{ \"clientRef\" : \"" + clientRef + "\", \"dataSecurity\" : \"" + dataSecurity + "\",";

        if (startDate != null && !startDate.trim().isEmpty() && (endDate == null || endDate.trim().isEmpty())) {
            jsonStr += "\"startdate\" : \"" + startDate + "\",";
        } else if ((startDate == null || startDate.trim().isEmpty()) && endDate != null && !endDate.trim().isEmpty()) {
            jsonStr += "\"enddate\" : \"" + endDate + "\",";
        } else if (startDate != null && !startDate.trim().isEmpty() && endDate != null && !endDate.trim().isEmpty()) {
            jsonStr += "\"startdate\" : \"" + startDate + "\", \"endDate\" : \"" + endDate + "\",";

            if (recurFreq != null && !recurFreq.trim().isEmpty() && recurDate != null && !recurDate.trim().isEmpty()) {
                jsonStr += "\"recurFreq\" : \"" + recurFreq + "\", \"recurDate\" : \"" + recurDate + "\",";
            }
        }

        jsonStr += "data= " + recipientUserJsonList + " }";

        return jsonStr;
    }
}
