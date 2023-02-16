package biz.buynow.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import biz.buynow.bank.model.DisburseRequest;
import biz.buynow.bank.model.RecipientUser;

public interface RecipientUserRepository extends CrudRepository<RecipientUser, Integer> {
    Optional<RecipientUser> findTopByOrderByIdAsc();

    Optional<RecipientUser> findTopByStatusOrderByIdAsc(Integer status);

    Optional<RecipientUser> findById(Long id);

    List<RecipientUser> findByDisburseRequest(DisburseRequest disburseRequest);

    Optional<RecipientUser> findByDisburseRequestAndUserId(DisburseRequest disburseRequest, String userId);

}
