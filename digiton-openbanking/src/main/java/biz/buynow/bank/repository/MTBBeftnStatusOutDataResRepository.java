package biz.buynow.bank.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import biz.buynow.bank.model.MTBBeftnStatusOutDataRes;

public interface MTBBeftnStatusOutDataResRepository extends CrudRepository<MTBBeftnStatusOutDataRes, Integer> {
    Optional<MTBBeftnStatusOutDataRes> findAllByReturnStatAndCreateTimeBefore(String returnStat,
            OffsetDateTime createTime);
}
