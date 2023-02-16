package biz.buynow.bank.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import biz.buynow.bank.model.MTBBeftnTransRes;

public interface MTBBeftnTransResRepository extends CrudRepository<MTBBeftnTransRes, Integer> {
    Optional<MTBBeftnTransRes> findTopByOrderByIdAsc();

    List<MTBBeftnTransRes> findAllByIsOutwardStatusPendingAndCreateTimeAfterAndUpdateTimeBefore(
            boolean isOutwardStatusPending, OffsetDateTime createTime, OffsetDateTime updateTime);

    Optional<MTBBeftnTransRes> findById(Long id);
}
