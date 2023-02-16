package biz.buynow.bank.repository;

import org.springframework.data.repository.CrudRepository;

import biz.buynow.bank.model.UserBank;

public interface UserBankRepository extends CrudRepository<UserBank, Integer> {

}
