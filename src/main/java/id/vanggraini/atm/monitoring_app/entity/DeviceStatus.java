package id.vanggraini.atm.monitoring_app.entity;

import id.vanggraini.atm.monitoring_app.enums.DeviceHealth;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Embeddable
@Data
public class DeviceStatus {

    @Enumerated(EnumType.STRING)
    @Column(name = "cash_remaining_status")
    private DeviceHealth cashRemainingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_reader_status")
    private DeviceHealth cardReaderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "receipt_printer_status")
    private DeviceHealth receiptPrinterStatus;
    
}
