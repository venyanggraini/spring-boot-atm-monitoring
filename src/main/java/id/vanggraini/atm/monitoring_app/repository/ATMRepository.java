package id.vanggraini.atm.monitoring_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import id.vanggraini.atm.monitoring_app.entity.ATM;

public interface ATMRepository extends JpaRepository<ATM, String>, JpaSpecificationExecutor<ATM> {
}
