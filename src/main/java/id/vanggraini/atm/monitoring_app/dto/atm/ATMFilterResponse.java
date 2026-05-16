package id.vanggraini.atm.monitoring_app.dto.atm;

import java.time.LocalDateTime;

import id.vanggraini.atm.monitoring_app.enums.ATMStatus;
import id.vanggraini.atm.monitoring_app.enums.DeviceHealth;
import lombok.Data;

@Data
public class ATMFilterResponse {

    private String atmId;
    private ATMStatus atmStatus;
    private LocalDateTime lastUpdated;
    private DeviceHealth cashRemainingStatus;
    private DeviceHealth cardReaderStatus;
    private DeviceHealth receiptPrinterStatus;
    
}
