package biz.buynow.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "online_statement_req_mtb")
public class MTBOnlineStmtReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statement_req_mtb_beftn_id")
    private Long id;

    @NotEmpty
    private String accNo;

    @NotEmpty
    private String nooftrn;

    @NotEmpty
    private String channelId;

    private String fromDate;

    private String toDate;

    public MTBOnlineStmtReq(String accNo, String fromDate, String nooftrn, String toDate, String channelId) {
        super();
        this.accNo = accNo;
        this.fromDate = fromDate;
        this.nooftrn = nooftrn;
        this.toDate = toDate;
        this.channelId = channelId;
    }

    public MTBOnlineStmtReq() {
        // TODO Auto-generated constructor stub
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getNooftrn() {
        return nooftrn;
    }

    public void setNooftrn(String nooftrn) {
        this.nooftrn = nooftrn;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
