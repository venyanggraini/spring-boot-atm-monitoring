package id.vanggraini.atm.monitoring_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.vanggraini.atm.monitoring_app.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo,Long>{
    Optional<UserInfo> findByUsername(String username);
}
