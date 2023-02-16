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
@Table(name = "transfer_req_mtb_beftn")
public class MTBBeftnTransReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_req_mtb_beftn_id")
    private Long id;

    @NotEmpty
    private String eftTrnsType;

    @NotEmpty
    private String amount;

    @NotEmpty
    private String dfiAccountno;

    @NotEmpty
    private String dscrptn;

    @NotEmpty
    private String receiveBank;

    @NotEmpty
    private String receiverName;

    @NotEmpty
    private String channelId;

    @NotEmpty
    private String uniqueID;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime createTime = OffsetDateTime.now();

    public MTBBeftnTransReq(String eftTrnsType, String amount, String dfiAccountno, String dscrptn,
            String receiveBank, String receiverName, String channelId, String uniqueID) {
        this.eftTrnsType = eftTrnsType;
        this.amount = amount;
        this.dfiAccountno = dfiAccountno;
        this.dscrptn = dscrptn;
        this.receiveBank = receiveBank;
        this.receiverName = receiverName;
        this.channelId = channelId;
        this.uniqueID = uniqueID;
    }

    public MTBBeftnTransReq() {
        // TODO Auto-generated constructor stub
    }

    public String getEftTrnsType() {
        return eftTrnsType;
    }

    public void setEftTrnsType(String eftTrnsType) {
        this.eftTrnsType = eftTrnsType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDfiAccountno() {
        return dfiAccountno;
    }

    public void setDfiAccountno(String dfiAccountno) {
        this.dfiAccountno = dfiAccountno;
    }

    public String getDscrptn() {
        return dscrptn;
    }

    public void setDscrptn(String dscrptn) {
        this.dscrptn = dscrptn;
    }

    public String getReceiveBank() {
        return receiveBank;
    }

    public void setReceiveBank(String receiveBank) {
        this.receiveBank = receiveBank;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
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
