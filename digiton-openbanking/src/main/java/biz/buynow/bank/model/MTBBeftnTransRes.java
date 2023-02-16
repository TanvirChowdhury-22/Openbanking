package biz.buynow.bank.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "transfer_res_mtb_beftn")
public class MTBBeftnTransRes extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_res_mtb_beftn_id")
    private Long id;

    @NotEmpty
    private String resCode;

    @NotEmpty
    private String resMsg;

    @NotEmpty
    private String apiMsg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inword_beftn_info_mtb_id", referencedColumnName = "inword_beftn_info_mtb_id")
    private InWordBeftnInfo inwordbeftninfo;

    @OneToMany(mappedBy = "mtblBeftnTransferResponse")
    private List<MTBBeftnStatusRes> beftnStatusResponse;

    private Long recipientUser;

    private boolean isOutwardStatusPending = true;

    public boolean isOutwardStatusPending() {
        return isOutwardStatusPending;
    }

    public Long getRecipientUser() {
        return recipientUser;
    }

    public void setRecipientUser(Long recipientUser) {
        this.recipientUser = recipientUser;
    }

    public MTBBeftnTransRes(Long id, String resCode, String resMsg, String apiMsg) {
        this.id = id;
        this.resCode = resCode;
        this.resMsg = resMsg;
        this.apiMsg = apiMsg;
    }

    public MTBBeftnTransRes() {
        // TODO Auto-generated constructor stub
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

    public String getApiMsg() {
        return apiMsg;
    }

    public void setApiMsg(String apiMsg) {
        this.apiMsg = apiMsg;
    }

    public InWordBeftnInfo getInwordbeftninfo() {
        return inwordbeftninfo;
    }

    public void setInwordbeftninfo(InWordBeftnInfo inwordbeftninfo) {
        this.inwordbeftninfo = inwordbeftninfo;
    }

    public List<MTBBeftnStatusRes> getBeftnStatusResponse() {
        return beftnStatusResponse;
    }

    public void setBeftnStatusResponse(List<MTBBeftnStatusRes> beftnStatusResponse) {
        this.beftnStatusResponse = beftnStatusResponse;
    }

    public void setOutwardStatusPending(boolean isOutwardStatusPending) {
        this.isOutwardStatusPending = isOutwardStatusPending;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}