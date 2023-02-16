package biz.buynow.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "res_json_dump")
public class ResponseDumpJson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_json_dump_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String fullJson;

    @NotNull
    private Long requestId;

    @NotNull
    private Integer reqType;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullJson() {
        return fullJson;
    }

    public void setFullJson(String fullJson) {
        this.fullJson = fullJson;
    }

    public Integer getReqType() {
        return reqType;
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

}
