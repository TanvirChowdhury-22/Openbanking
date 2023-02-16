package biz.buynow.bank.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "server_status")
public class ServerStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "server_status_id")
    private Long id;

    @Column(name = "bank_name")
    private String bank;

    @Column(columnDefinition = "timestamptz")
    private OffsetDateTime downTime = OffsetDateTime.now();

    private boolean isDown;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public OffsetDateTime getDownTime() {
        return downTime;
    }

    public void setDownTime(OffsetDateTime downTime) {
        this.downTime = downTime;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean isDown) {
        this.isDown = isDown;
    }

}
