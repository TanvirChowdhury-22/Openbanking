package biz.buynow.bank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import biz.buynow.bank.model.MTBBeftnTransReq;

public interface MTBBeftnTransReqRepository extends CrudRepository<MTBBeftnTransReq, Integer> {
    Optional<MTBBeftnTransReq> findByUniqueID(String uniqueID);

}
