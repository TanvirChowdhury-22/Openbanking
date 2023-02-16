package biz.buynow.bank.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "inword_beftn_info_mtb")
public class InWordBeftnInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inword_beftn_info_mtb_id")
    private Long id;

    @NotEmpty
    private String cbsfjournalNo;

    @NotEmpty
    private String cbsftrnNum;

    @NotEmpty
    private String edrTraceno;

    @NotEmpty
    private String msg;

    @NotEmpty
    private String msgCode;

    @NotEmpty
    private String originalTraceno;

    @NotEmpty
    private String stlmDate;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime createTime = OffsetDateTime.now();

    @OneToOne(mappedBy = "inwordbeftninfo")
    private MTBBeftnTransRes mtblBeftnTransferResponse;

    public InWordBeftnInfo(Long id, String cbsfjournalNo, String cbsftrnNum, String edrTraceno, String msg,
            String msgCode, String originalTraceno, String stlmDate) {
        super();
        this.id = id;
        this.cbsfjournalNo = cbsfjournalNo;
        this.cbsftrnNum = cbsftrnNum;
        this.edrTraceno = edrTraceno;
        this.msg = msg;
        this.msgCode = msgCode;
        this.originalTraceno = originalTraceno;
        this.stlmDate = stlmDate;
    }

    public InWordBeftnInfo() {
        // TODO Auto-generated constructor stub
    }

    public MTBBeftnTransRes getMTBLBeftnTransferResponse() {
        return mtblBeftnTransferResponse;
    }

    public void setMTBLBeftnTransferResponse(MTBBeftnTransRes mtblBeftnTransferResponse) {
        this.mtblBeftnTransferResponse = mtblBeftnTransferResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCbsfjournalNo() {
        return cbsfjournalNo;
    }

    public void setCbsfjournalNo(String cbsfjournalNo) {
        this.cbsfjournalNo = cbsfjournalNo;
    }

    public String getCbsftrnNum() {
        return cbsftrnNum;
    }

    public void setCbsftrnNum(String cbsftrnNum) {
        this.cbsftrnNum = cbsftrnNum;
    }

    public String getEdrTraceno() {
        return edrTraceno;
    }

    public void setEdrTraceno(String edrTraceNo) {
        this.edrTraceno = edrTraceNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getOriginalTraceno() {
        return originalTraceno;
    }

    public void setOriginalTraceno(String originalTraceno) {
        this.originalTraceno = originalTraceno;
    }

    public String getStlmDate() {
        return stlmDate;
    }

    public void setStlmDate(String stlmDate) {
        this.stlmDate = stlmDate;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }
}
