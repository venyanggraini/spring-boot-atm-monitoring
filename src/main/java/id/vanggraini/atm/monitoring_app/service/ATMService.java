package id.vanggraini.atm.monitoring_app.service;

import java.util.List;

import id.vanggraini.atm.monitoring_app.dto.ATMFilterRequest;
import id.vanggraini.atm.monitoring_app.dto.ATMFilterResponse;

public interface ATMService {
    List<ATMFilterResponse> getDeviceStatus(ATMFilterRequest filterRequest);
}
