package biz.buynow.bank.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user_bank")
public class UserBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_bank_id")
    private Long id;

    @NotEmpty
    private String bankAccNo;

    @NotEmpty
    private String bankName;

    @NotEmpty
    private String bankBranch;

    @NotEmpty
    private String bankRouting;

    @NotEmpty
    private String accountHoldersName;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime createTime = OffsetDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_user_id", nullable = false)
    private RecipientUser recipientUser;

    public UserBank(Long id, String bankAccNo, String accountHoldersName, String bankName, String bankBranch,
            String bankRouting) {
        super();
        this.id = id;
        this.bankAccNo = bankAccNo;
        this.accountHoldersName = accountHoldersName;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.bankRouting = bankRouting;
    }

    public RecipientUser getRecipientUser() {
        return recipientUser;
    }

    public void setRecipientUser(RecipientUser recipientUser) {
        this.recipientUser = recipientUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserBank() {
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

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }
}
