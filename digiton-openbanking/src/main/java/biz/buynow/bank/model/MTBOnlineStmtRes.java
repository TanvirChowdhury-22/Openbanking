package biz.buynow.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "online_statement_res_mtb")
public class MTBOnlineStmtRes extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_statement_res_mtb_id")
    private Long id;

    @NotEmpty
    private String noOfRows;

    @NotEmpty
    private String noOfRowsSpecified;

    @NotEmpty
    private String resCode;

    @NotEmpty
    private String resMsg;

    // @Column(unique = true)
    private Long lastTransactionId;

    public MTBOnlineStmtRes(String noOfRows, String noOfRowsSpecified, String resCode, String resMsg) {
        super();
        this.noOfRows = noOfRows;
        this.noOfRowsSpecified = noOfRowsSpecified;
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public MTBOnlineStmtRes() {
        // TODO Auto-generated constructor stub
    }

    public String getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(String noOfRows) {
        this.noOfRows = noOfRows;
    }

    public String getNoOfRowsSpecified() {
        return noOfRowsSpecified;
    }

    public void setNoOfRowsSpecified(String noOfRowsSpecified) {
        this.noOfRowsSpecified = noOfRowsSpecified;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastTransactionId() {
        return lastTransactionId;
    }

    public void setLastTransactionId(Long lastTransactionId) {
        this.lastTransactionId = lastTransactionId;
    }
}
