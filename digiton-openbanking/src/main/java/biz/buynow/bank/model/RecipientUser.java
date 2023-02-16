package biz.buynow.bank.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "recipient_user")
public class RecipientUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipient_user_id")
    private Long id;

    @NotEmpty
    private String userId;

    @NotEmpty
    private String amount;

    @NotEmpty
    private String comments;

    @NotEmpty
    private String currency;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime createTime = OffsetDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "disburse_request_id", nullable = false)
    private DisburseRequest disburseRequest;

    @OneToMany(mappedBy = "recipientUser", cascade = CascadeType.ALL)
    private List<UserBank> userBankList;

    // it will hold the bankId used for disbursement
    String disbursedBankId;

    @NotNull
    private Integer status;

    @NotNull
    private Integer paymentStatus;

    public RecipientUser(Long id, String userId, String amount, String comments, String currency) {
        super();
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.comments = comments;
        this.currency = currency;
    }

    public DisburseRequest getDisburseRequest() {
        return disburseRequest;
    }

    public void setDisburseRequest(DisburseRequest disburseRequest) {
        this.disburseRequest = disburseRequest;
    }

    public List<UserBank> getUserBankList() {
        return userBankList;
    }

    public void setUserBankList(List<UserBank> userBankList) {
        this.userBankList = userBankList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecipientUser() {
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

    public String getDisbursedBankId() {
        return disbursedBankId;
    }

    public void setDisbursedBankId(String disbursedBankId) {
        this.disbursedBankId = disbursedBankId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
