package biz.buynow.bank.model;

public class UserBankJson {
    private String bankAccNo;    
    private String bankName;
    private String bankBranch;
    private String bankRouting;
    private String accountHoldersName;

    public UserBankJson(String bankAccNo, String accountHoldersName, String bankName, String bankBranch,
            String bankRouting) {
        super();
        this.bankAccNo = bankAccNo;
        this.accountHoldersName = accountHoldersName;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.bankRouting = bankRouting;
    }

    public UserBankJson() {
        // TODO Auto-generated constructor stub
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankRouting() {
        return bankRouting;
    }

    public void setBankRouting(String bankRouting) {
        this.bankRouting = bankRouting;
    }

    public String getAccountHoldersName() {
        return accountHoldersName;
    }

    public void setAccountHoldersName(String accountHoldersName) {
        this.accountHoldersName = accountHoldersName;
    }

    @Override
    public String toString() {
        String jsonStrBankData = "{ \"bankAccNo\" : \"" + bankAccNo + "\", \"accountHoldersName\" : \"" + accountHoldersName + "\", \"bankName\" : \"" + bankName
                + "\", \"bankBranch\" : \"" + bankBranch + "\", \"bankRouting\" : \"" + bankRouting + "\" }";
        return jsonStrBankData;
    }
}
