package biz.buynow.bank.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "disburse_request")
public class DisburseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disburse_request_id")
    private Long id;

    @NotNull
    public BigDecimal totalAmount;

    @NotEmpty
    private String clientRef;

    @NotEmpty
    private String dataSecurity;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime startDate;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime endDate;

    private String recurFreq;

    private String recurDate;

    @NotNull
    private Integer status;

    @NotNull
    private Integer result;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime createTime = OffsetDateTime.now();

    @OneToMany(mappedBy = "disburseRequest", cascade = CascadeType.ALL)
    private List<RecipientUser> recipientUserList;

    public DisburseRequest(Long id, String clientRef, String dataSecurity, OffsetDateTime startDate,
            OffsetDateTime endDate, String recurFreq, String recurDate) {
        super();
        this.id = id;
        this.clientRef = clientRef;
        this.dataSecurity = dataSecurity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurFreq = recurFreq;
        this.recurDate = recurDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DisburseRequest() {
        // TODO Auto-generated constructor stub
    }

    public String getClientRef() {
        return clientRef;
    }

    public void setClientRef(String clientRef) {
        this.clientRef = clientRef;
    }

    public String getDataSecurity() {
        return dataSecurity;
    }

    public void setDataSecurity(String dataSecurity) {
        this.dataSecurity = dataSecurity;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public void setStartDateFromString(String startDateString) {
        if (startDateString != null && !startDateString.equals("")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ssX");
            this.startDate = OffsetDateTime.parse(startDateString, dtf);
        }
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public void setEndDateFromString(String endDateString) {
        if (endDateString != null && !endDateString.equals("")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ssX");
            this.endDate = OffsetDateTime.parse(endDateString, dtf);
        }
    }

    public String getRecurFreq() {
        return recurFreq;
    }

    public void setRecurFreq(String recurFreq) {
        this.recurFreq = recurFreq;
    }

    public String getRecurDate() {
        return recurDate;
    }

    public void setRecurDate(String recurDate) {
        this.recurDate = recurDate;
    }

    public List<RecipientUser> getRecipientUserList() {
        return recipientUserList;
    }

    public void setRecipientUserList(List<RecipientUser> recipientUserList) {
        this.recipientUserList = recipientUserList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }
}
