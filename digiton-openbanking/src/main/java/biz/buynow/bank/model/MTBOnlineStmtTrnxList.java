package biz.buynow.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "transaction_list_mtb")
public class MTBOnlineStmtTrnxList extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_list_mtb_id")
    private Long id;

    @NotEmpty
    private String currencyName;

    @NotEmpty
    private String currentBalance;

    @NotEmpty
    private String deposit;

    private String description;

    @NotEmpty
    private String narration;

    @NotEmpty
    private String transactionDate;

    @NotEmpty
    private String transactionType;

    @NotEmpty
    private String withdrawal;

    public MTBOnlineStmtTrnxList(String currencyName, String currentBalance, String deposit, String description,
            String narration, String transactionDate, String transactionType, String withdrawal) {
        super();
        this.currencyName = currencyName;
        this.currentBalance = currentBalance;
        this.deposit = deposit;
        this.description = description;
        this.narration = narration;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.withdrawal = withdrawal;
    }

    public MTBOnlineStmtTrnxList() {
        // TODO Auto-generated constructor stub
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
