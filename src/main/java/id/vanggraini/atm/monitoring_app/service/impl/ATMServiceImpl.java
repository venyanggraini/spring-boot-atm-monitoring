package id.vanggraini.atm.monitoring_app.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import id.vanggraini.atm.monitoring_app.dto.ATMFilterRequest;
import id.vanggraini.atm.monitoring_app.dto.ATMFilterResponse;
import id.vanggraini.atm.monitoring_app.entity.ATM;
import id.vanggraini.atm.monitoring_app.exception.ATMStatusNotFoundException;
import id.vanggraini.atm.monitoring_app.repository.ATMRepository;
import id.vanggraini.atm.monitoring_app.service.ATMService;
import id.vanggraini.atm.monitoring_app.specification.ATMSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ATMServiceImpl implements ATMService {

    private final ATMRepository atmRepository;

    @Override
    public List<ATMFilterResponse> getDeviceStatus(ATMFilterRequest request) {

        log.info("Start find ATM devices");

        List<ATMFilterResponse> responses = atmRepository.findAll(ATMSpecification.filterByDeviceStatus(request))
                .stream()
                .map(this::mapToResponse)
                .toList();

        log.info("ATM Request data: " + request);
        
        if (responses.size() == 0) {
            throw new ATMStatusNotFoundException(request.getAtmId());
        }

        log.info("Finish get ATM devices data");

        return atmRepository.findAll(ATMSpecification.filterByDeviceStatus(request))
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ATMFilterResponse mapToResponse(ATM atm) {
        ATMFilterResponse response = new ATMFilterResponse();
        response.setAtmId(atm.getAtmId());
        response.setAtmStatus(atm.getAtmStatus());
        response.setLastUpdated(atm.getLastUpdated());

        if (atm.getDeviceStatus() != null) {
            response.setCashRemainingStatus(atm.getDeviceStatus().getCashRemainingStatus());
            response.setCardReaderStatus(atm.getDeviceStatus().getCardReaderStatus());
            response.setReceiptPrinterStatus(atm.getDeviceStatus().getReceiptPrinterStatus());
        }

        return response;
    }
}
