package biz.buynow.bank.schedulejobs;

import biz.buynow.bank.constant.BusinessConstant;
import biz.buynow.bank.model.MTBBeftnTransRes;
import biz.buynow.bank.model.InWordBeftnInfo;
import biz.buynow.bank.model.MTBtoMTBTransRes;
import biz.buynow.bank.model.RecipientUser;
import biz.buynow.bank.model.ServerStatus;
import biz.buynow.bank.repository.MTBBeftnTransResRepository;
import biz.buynow.bank.repository.MTBInWordBeftnInfoRepository;
import biz.buynow.bank.repository.MTBtoMTBFundTransferResponseRepository;
import biz.buynow.bank.repository.RecipientUserRepository;
import biz.buynow.bank.repository.ServerStatusRepository;

public class DisbursementSaveResponse {

    static public InWordBeftnInfo saveResponseForInWordBEFTNInfo(
            MTBInWordBeftnInfoRepository mtblInWordBeftnInfoRepository, InWordBeftnInfo mtblInWordBeftnInfo) {
        mtblInWordBeftnInfo = mtblInWordBeftnInfoRepository.save(mtblInWordBeftnInfo);
        return mtblInWordBeftnInfo;
    }

    static public void saveResponseForBEFTN(RecipientUserRepository recipientUserRepository,
            MTBInWordBeftnInfoRepository mtblInWordBeftnInfoRepository,
            MTBBeftnTransResRepository mtblBeftnInfoResponseRepository, RecipientUser recipientUser, Long userBankId,
            MTBBeftnTransRes mtblBeftnInfoResponse) {

        mtblBeftnInfoResponse.setRecipientUser(recipientUser.getId());
        mtblBeftnInfoResponseRepository.save(mtblBeftnInfoResponse);

        if (mtblBeftnInfoResponse.getResCode().equals("000") && mtblBeftnInfoResponse.getResMsg().equals("SUCCESS")) {
            Integer successfulStatus = BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.SUCCESSFUL.ordinal();
            recipientUser.setStatus(successfulStatus);
            recipientUser.setDisbursedBankId(userBankId.toString());
        } else {
            Integer failedStatus = BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.FAILED.ordinal();
            recipientUser.setStatus(failedStatus);
        }
        if (!mtblBeftnInfoResponse.isOutwardStatusPending()) {
            Integer completedPaymentStatus = BusinessConstant.PAYMENT_STATUS.COMPLETED.ordinal();
            recipientUser.setPaymentStatus(completedPaymentStatus);
        } else {
            Integer pendingPaymentStatus = BusinessConstant.PAYMENT_STATUS.BEFTN_CONFIRMATION_PENDING.ordinal();
            recipientUser.setPaymentStatus(pendingPaymentStatus);
        }
        recipientUserRepository.save(recipientUser);
    }

    static public void saveMTBLToMTBFundTransferResponse(RecipientUserRepository recipientUserRepository,
            MTBtoMTBFundTransferResponseRepository mtbtoMTBFundTransferResponseRepository, RecipientUser recipientUser,
            Long userBankId, MTBtoMTBTransRes mtbtoMTBFundTransferResponse) {

        mtbtoMTBFundTransferResponse.setRecipientUser(recipientUser.getId());
        mtbtoMTBFundTransferResponseRepository.save(mtbtoMTBFundTransferResponse);

        if (mtbtoMTBFundTransferResponse.getResCode().equals("000")
                && mtbtoMTBFundTransferResponse.getResMsg().equals("O.K.")) {
            Integer successfulStatus = BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.SUCCESSFUL.ordinal();
            Integer completedPaymentStatus = BusinessConstant.PAYMENT_STATUS.COMPLETED.ordinal();
            recipientUser.setStatus(successfulStatus);
            recipientUser.setDisbursedBankId(userBankId.toString());
            recipientUser.setPaymentStatus(completedPaymentStatus);
        } else {
            Integer failedStatus = BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.FAILED.ordinal();
            Integer pendingPaymentStatus = BusinessConstant.PAYMENT_STATUS.PENDING.ordinal();
            recipientUser.setStatus(failedStatus);
            recipientUser.setPaymentStatus(pendingPaymentStatus);
        }
        recipientUserRepository.save(recipientUser);
    }

    static public void saveServerFailureStatus(ServerStatusRepository serverStatusRepository) {
        ServerStatus serverStatus = new ServerStatus();
        serverStatus.setBank(BusinessConstant.BANK.MTBL.toString());
        serverStatus.setDown(true);
        serverStatusRepository.save(serverStatus);
    }
}
