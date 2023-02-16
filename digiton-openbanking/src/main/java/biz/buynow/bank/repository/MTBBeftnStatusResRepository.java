package biz.buynow.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import biz.buynow.bank.model.MTBBeftnStatusRes;

public interface MTBBeftnStatusResRepository extends CrudRepository<MTBBeftnStatusRes, Integer> {
    Optional<MTBBeftnStatusRes> findByOrderByIdAsc();

    List<MTBBeftnStatusRes> findById(Long id);

}
