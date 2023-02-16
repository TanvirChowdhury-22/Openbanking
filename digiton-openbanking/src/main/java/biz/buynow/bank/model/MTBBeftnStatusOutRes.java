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
@Table(name = "outward_status_mtb_beftn")
public class MTBBeftnStatusOutRes extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outward_status_mtb_beftn_id")
    private Long id;

    @NotEmpty
    private String status;

    @NotEmpty
    private String statusCode;

    private String message;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_response_mtb_beftn_id", nullable = false)
    private MTBBeftnStatusRes mtblBeftnStatusResponse;

    @OneToMany(mappedBy = "mtblBeftnOutwardStatus", cascade = CascadeType.ALL)
    private List<MTBBeftnStatusOutDataRes> mtblBeftnOutwardStatusDataList;

    public MTBBeftnStatusOutRes(Long id, String status, String statusCode, String message,
            List<MTBBeftnStatusOutDataRes> mtblBeftnOutwardStatusDataList) {
        super();
        this.id = id;
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.mtblBeftnOutwardStatusDataList = mtblBeftnOutwardStatusDataList;
    }

    public MTBBeftnStatusOutRes() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MTBBeftnStatusOutDataRes> getMTBLBeftnOutwardStatusDataList() {
        return mtblBeftnOutwardStatusDataList;
    }

    public void setMTBLBeftnOutwardStatusDataList(List<MTBBeftnStatusOutDataRes> mtblBeftnOutwardStatusDataList) {
        this.mtblBeftnOutwardStatusDataList = mtblBeftnOutwardStatusDataList;
    }

    public MTBBeftnStatusRes getMTBLBeftnStatusResponse() {
        return mtblBeftnStatusResponse;
    }

    public void setMTBLBeftnStatusResponse(MTBBeftnStatusRes mtblBeftnStatusResponse) {
        this.mtblBeftnStatusResponse = mtblBeftnStatusResponse;
    }

}