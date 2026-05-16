package id.vanggraini.atm.monitoring_app.dto.atm;

import id.vanggraini.atm.monitoring_app.enums.ATMStatus;
import id.vanggraini.atm.monitoring_app.enums.DeviceHealth;
import lombok.Data;

@Data
public class ATMFilterRequest {

    private String atmId;
    private ATMStatus atmStatus;
    private DeviceHealth cashRemainingStatus;
    private DeviceHealth cardReaderStatus;
    private DeviceHealth receiptPrinterStatus;
}
