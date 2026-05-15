package id.vanggraini.atm.monitoring_app.entity;

import java.time.LocalDateTime;

import id.vanggraini.atm.monitoring_app.enums.ATMStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "atm")
@Data
public class ATM {
    @Id
    private String atmId;

    @Enumerated(EnumType.STRING)
    @Column(name = "atm_status")
    private ATMStatus atmStatus;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Embedded
    private DeviceStatus deviceStatus;
    
}
