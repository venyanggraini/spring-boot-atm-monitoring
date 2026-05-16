package id.vanggraini.atm.monitoring_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.vanggraini.atm.monitoring_app.dto.atm.ATMFilterRequest;
import id.vanggraini.atm.monitoring_app.dto.atm.ATMFilterResponse;
import id.vanggraini.atm.monitoring_app.service.ATMService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/atm")
@AllArgsConstructor
public class ATMController {

    private final ATMService atmService;

    @PostMapping("/devices")
    public ResponseEntity<List<ATMFilterResponse>> getATMbyDeviceStatus(
       @RequestBody(required = false) ATMFilterRequest request) {
        return ResponseEntity.ok(atmService.getDeviceStatus(request));
    }
    
}
