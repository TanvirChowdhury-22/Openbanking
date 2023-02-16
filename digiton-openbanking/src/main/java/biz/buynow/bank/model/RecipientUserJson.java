package biz.buynow.bank.model;

import java.util.List;

public class RecipientUserJson {
    private String userId;
    private String amount;
    private String comments;
    private String currency;
    private List<UserBankJson> userBankJsonList;

    public RecipientUserJson(String userId, String amount, String comments, String currency,
            List<UserBankJson> userBankJsonList) {
        super();
        this.userId = userId;
        this.amount = amount;
        this.comments = comments;
        this.currency = currency;
        this.userBankJsonList = userBankJsonList;
    }

    public RecipientUserJson() {
        // TODO Auto-generated constructor stub
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<UserBankJson> getUserBankJsonList() {
        return userBankJsonList;
    }

    public void setUserBankJsonList(
            List<UserBankJson> userBankJsonList) {
        this.userBankJsonList = userBankJsonList;
    }

    @Override
    public String toString() {
        String jsonStrData = "{ \"userId\" : \"" + userId + "\", \"amount\" : \"" + amount + "\", \"comments\" : \""
                + comments + "\", \"currency\" : \"" + currency + "\", \"bankData\" : "
                + userBankJsonList + " }";
        return jsonStrData;
    }
}
