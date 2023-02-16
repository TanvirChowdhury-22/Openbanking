package biz.buynow.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "status_response_mtb_beftn")
public class MTBBeftnStatusRes extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_response_mtb_beftn_id")
    private Long id;

    @NotEmpty
    private String logId;

    private String logIdSpecified;

    @OneToOne(mappedBy = "mtblBeftnStatusResponse")
    private MTBBeftnStatusOutRes mtblBeftnOutwardStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transfer_res_mtb_beftn_id", nullable = false)
    private MTBBeftnTransRes mtblBeftnTransferResponse;

    @NotEmpty
    private String resCode;

    @NotEmpty
    private String resMsg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogIdSpecified() {
        return logIdSpecified;
    }

    public void setLogIdSpecified(String logIdSpecified) {
        this.logIdSpecified = logIdSpecified;
    }

    public MTBBeftnStatusOutRes getMTBLBeftnOutwardStatus() {
        return mtblBeftnOutwardStatus;
    }

    public void setMTBLBeftnOutwardStatus(MTBBeftnStatusOutRes mtblBeftnOutwardStatus) {
        this.mtblBeftnOutwardStatus = mtblBeftnOutwardStatus;
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

    public MTBBeftnTransRes getMTBLBeftnTransferResponse() {
        return mtblBeftnTransferResponse;
    }

    public void setMTBLBeftnTransferResponse(MTBBeftnTransRes mtblBeftnTransferResponse) {
        this.mtblBeftnTransferResponse = mtblBeftnTransferResponse;
    }
}
