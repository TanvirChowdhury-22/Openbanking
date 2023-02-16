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
@Table(name = "transfer_res_mtb_to_mtb")
public class MTBtoMTBTransRes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_res_mtb_to_mtb_id")
    private Long id;

    @NotEmpty
    private String cbsJournalNo;

    @NotEmpty
    private String cbsTxnID;

    @NotEmpty
    private String cbsCode;

    @NotEmpty
    private String cbsResDate;

    @NotEmpty
    private String resCode;

    @NotEmpty
    private String resMsg;

    @NotEmpty
    private String logId;

    private Long recipientUser;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime createTime = OffsetDateTime.now();

    public MTBtoMTBTransRes(Long id, String resCode, String resMsg, String cbsJournalNo, String cbsTxnID,
            String cbsCode, String cbsResDate, String logId) {
        this.id = id;
        this.resCode = resCode;
        this.resMsg = resMsg;
        this.cbsJournalNo = cbsJournalNo;
        this.cbsTxnID = cbsTxnID;
        this.cbsCode = cbsCode;
        this.cbsResDate = cbsResDate;
        this.logId = logId;
    }

    public String getCbsJournalNo() {
        return cbsJournalNo;
    }

    public void setCbsJournalNo(String cbsJournalNo) {
        this.cbsJournalNo = cbsJournalNo;
    }

    public String getCbsTxnID() {
        return cbsTxnID;
    }

    public void setCbsTxnID(String cbsTxnID) {
        this.cbsTxnID = cbsTxnID;
    }

    public String getCbsCode() {
        return cbsCode;
    }

    public void setCbsCode(String cbsCode) {
        this.cbsCode = cbsCode;
    }

    public String getCbsResDate() {
        return cbsResDate;
    }

    public void setCbsResDate(String cbsResDate) {
        this.cbsResDate = cbsResDate;
    }

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

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Long getRecipientUser() {
        return recipientUser;
    }

    public void setRecipientUser(Long recipientUser) {
        this.recipientUser = recipientUser;
    }

    public MTBtoMTBTransRes() {
        // TODO Auto-generated constructor stub
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }
}
