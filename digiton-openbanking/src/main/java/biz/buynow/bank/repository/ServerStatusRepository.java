package biz.buynow.bank.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import biz.buynow.bank.model.ServerStatus;

@Repository
public interface ServerStatusRepository extends CrudRepository<ServerStatus, Integer> {

    Optional<ServerStatus> findTopByDownTimeOrderByIdDesc(OffsetDateTime downTime);

    Optional<ServerStatus> findTopByBankOrderByIdDesc(String bank);
}
