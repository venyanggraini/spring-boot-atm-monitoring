package id.vanggraini.atm.monitoring_app.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import id.vanggraini.atm.monitoring_app.dto.atm.ATMFilterRequest;
import id.vanggraini.atm.monitoring_app.entity.ATM;
import jakarta.persistence.criteria.Predicate;

public class ATMSpecification {

    public static Specification<ATM> filterByDeviceStatus(ATMFilterRequest request) {
        return (root, query, cb) -> {

            if (request == null) {
                return cb.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            if (request.getAtmId() != null) {
                predicates.add(cb.equal(root.get("atmId"), request.getAtmId()));
            }

            if (request.getAtmStatus() != null) {
                predicates.add(cb.equal(root.get("atmStatus"), request.getAtmStatus()));
            }

            if (request.getCashRemainingStatus() != null) {
                predicates.add(cb.equal(
                    root.get("deviceStatus").get("cashRemainingStatus"),
                    request.getCashRemainingStatus()));
            }

            if (request.getCardReaderStatus() != null) {
                predicates.add(cb.equal(
                    root.get("deviceStatus").get("cardReaderStatus"),
                    request.getCardReaderStatus()));
            }

            if (request.getReceiptPrinterStatus() != null) {
                predicates.add(cb.equal(
                    root.get("deviceStatus").get("receiptPrinterStatus"),
                    request.getReceiptPrinterStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}
