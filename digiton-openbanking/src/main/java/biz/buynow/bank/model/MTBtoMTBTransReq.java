package biz.buynow.bank.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "transfer_req_mtb_to_mtb")
public class MTBtoMTBTransReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_req_mtb_to_mtb_id")
    private Long id;

    @NotEmpty
    private String amount;

    @NotEmpty
    private String creditAccount;

    @NotEmpty
    private String debitAccount;

    @NotEmpty
    private String remarks;

    @NotEmpty
    private String transactionChannelId;

    @NotEmpty
    private String uniqueTxnId;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime createTime = OffsetDateTime.now();

    public MTBtoMTBTransReq(String amount, String creditAccount, String debitAccount, String remarks,
            String transactionChannelId, String uniqueTxnId) {
        this.amount = amount;
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
        this.remarks = remarks;
        this.transactionChannelId = transactionChannelId;
        this.uniqueTxnId = uniqueTxnId;
    }

    public MTBtoMTBTransReq() {
        // TODO Auto-generated constructor stub
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTransactionChannelId() {
        return transactionChannelId;
    }

    public void setTransactionChannelId(String transactionChannelId) {
        this.transactionChannelId = transactionChannelId;
    }

    public String getUniqueTxnId() {
        return uniqueTxnId;
    }

    public void setUniqueTxnId(String uniqueTxnId) {
        this.uniqueTxnId = uniqueTxnId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }

}
