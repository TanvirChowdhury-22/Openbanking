package biz.buynow.bank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import biz.buynow.bank.model.MTBOnlineStmtTrnxList;

public interface MTBStmtTrnxListRepository extends CrudRepository<MTBOnlineStmtTrnxList, Integer> {
    // TODO WRITE A FUNCTION TO FIND DUPLICATE TRANSACTION
    Optional<MTBOnlineStmtTrnxList> findByCurrencyNameAndCurrentBalanceAndDepositAndDescriptionAndNarrationAndTransactionTypeAndWithdrawal(
            String currencyName, String currentBalance, String deposit, String description, String narration,
            String transactionType, String withdrawal);
}
