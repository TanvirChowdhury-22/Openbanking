package biz.buynow.bank.model;

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
@Table(name = "outward_status_data_mtb_beftn")
public class MTBBeftnStatusOutDataRes extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outward_status_data_mtb_beftn_id")
    private Long id;

    @NotEmpty
    private String compAccountNum;

    @NotEmpty
    private String compId;

    @NotEmpty
    private String compName;

    @NotEmpty
    private String compEntryDesc;

    @NotEmpty
    private String channelId;

    @NotEmpty
    private String dfiAccountNo;

    @NotEmpty
    private String amount;

    private String amountSpecified;

    @NotEmpty
    private String receiverName;

    @NotEmpty
    private String individualId;

    @NotEmpty
    private String receive_bank;

    @NotEmpty
    private String fileGenerated;

    @NotEmpty
    private String bbAcknoledged;

    @NotEmpty
    private String tranDate;

    @NotEmpty
    private String returnStat;

    // Only needed for stopped beftn
    @NotEmpty
    private String originalTraceNumber;

    @NotEmpty
    private String returnDate;

    @NotEmpty
    private String retReason;

    @ManyToOne(optional = false)
    @JoinColumn(name = "outward_status_mtb_beftn_id", nullable = false)
    private MTBBeftnStatusOutRes mtblBeftnOutwardStatus;

    public MTBBeftnStatusOutDataRes(Long id, String compAccountNum, String compId, String compName,
            String compEntryDesc, String channelId, String dfiAccountNo, String amount, String amountSpecified,
            String receiverName, String individualId, String receive_bank, String originalTraceNumber,
            String fileGenerated, String bbAcknoledged, String tranDate, String returnStat, String returnDate,
            String retReason) {
        super();
        this.id = id;
        this.compAccountNum = compAccountNum;
        this.compId = compId;
        this.compName = compName;
        this.compEntryDesc = compEntryDesc;
        this.channelId = channelId;
        this.dfiAccountNo = dfiAccountNo;
        this.amount = amount;
        this.amountSpecified = amountSpecified;
        this.receiverName = receiverName;
        this.individualId = individualId;
        this.receive_bank = receive_bank;
        this.originalTraceNumber = originalTraceNumber;
        this.fileGenerated = fileGenerated;
        this.bbAcknoledged = bbAcknoledged;
        this.tranDate = tranDate;
        this.returnStat = returnStat;
        this.returnDate = returnDate;
        this.retReason = retReason;
    }

    public MTBBeftnStatusOutDataRes() {
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MTBBeftnStatusOutRes getMTBLBeftnOutwardStatus() {
        return mtblBeftnOutwardStatus;
    }

    public void setMTBLBeftnOutwardStatus(MTBBeftnStatusOutRes mtblBeftnOutwardStatus) {
        this.mtblBeftnOutwardStatus = mtblBeftnOutwardStatus;
    }

    public String getCompAccountNum() {
        return compAccountNum;
    }

    public void setCompAccountNum(String compAccountNum) {
        this.compAccountNum = compAccountNum;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompEntryDesc() {
        return compEntryDesc;
    }

    public void setCompEntryDesc(String compEntryDesc) {
        this.compEntryDesc = compEntryDesc;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDfiAccountNo() {
        return dfiAccountNo;
    }

    public void setDfiAccountNo(String dfiAccountNo) {
        this.dfiAccountNo = dfiAccountNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountSpecified() {
        return amountSpecified;
    }

    public void setAmountSpecified(String amountSpecified) {
        this.amountSpecified = amountSpecified;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }

    public String getReceive_bank() {
        return receive_bank;
    }

    public void setReceive_bank(String receive_bank) {
        this.receive_bank = receive_bank;
    }

    public String getOriginalTraceNumber() {
        return originalTraceNumber;
    }

    public void setOriginalTraceNumber(String originalTraceNumber) {
        this.originalTraceNumber = originalTraceNumber;
    }

    public String getFileGenerated() {
        return fileGenerated;
    }

    public void setFileGenerated(String fileGenerated) {
        this.fileGenerated = fileGenerated;
    }

    public String getBbAcknoledged() {
        return bbAcknoledged;
    }

    public void setBbAcknoledged(String bbAcknoledged) {
        this.bbAcknoledged = bbAcknoledged;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getReturnStat() {
        return returnStat;
    }

    public void setReturnStat(String returnStat) {
        this.returnStat = returnStat;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getRetReason() {
        return retReason;
    }

    public void setRetReason(String retReason) {
        this.retReason = retReason;
    }
}
