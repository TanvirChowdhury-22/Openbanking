package biz.buynow.bank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import biz.buynow.bank.model.MTBtoMTBTransReq;

public interface MTBtoMTBFundTransferRequestRepository extends CrudRepository<MTBtoMTBTransReq, Integer> {
    Optional<MTBtoMTBTransReq> findByUniqueTxnId(String uniqueTxnId);
}
