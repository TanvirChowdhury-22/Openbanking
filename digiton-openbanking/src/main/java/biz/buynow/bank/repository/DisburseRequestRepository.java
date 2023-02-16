package biz.buynow.bank.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import biz.buynow.bank.model.DisburseRequest;

public interface DisburseRequestRepository extends CrudRepository<DisburseRequest, Long> {
    Optional<DisburseRequest> findById(Long id);

    Optional<DisburseRequest> findByClientRefAndDataSecurityAndCreateTimeAfter(String clientRef, String dataSecurity,
            OffsetDateTime createTime);
}
