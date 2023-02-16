package biz.buynow.bank.schedulejobs;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import biz.buynow.bank.constant.BusinessConstant;

import biz.buynow.bank.model.RecipientUser;
import biz.buynow.bank.model.UserBank;
import biz.buynow.bank.repository.MTBBeftnTransReqRepository;
import biz.buynow.bank.repository.MTBBeftnTransResRepository;
import biz.buynow.bank.repository.MTBBeftnStatusOutResRepository;
import biz.buynow.bank.repository.MTBBeftnStatusOutDataResRepository;
import biz.buynow.bank.repository.MTBBeftnStatusResRepository;
import biz.buynow.bank.repository.MTBBeftnStatusReqRepository;
import biz.buynow.bank.repository.MTBInWordBeftnInfoRepository;
import biz.buynow.bank.repository.MTBtoMTBFundTransferRequestRepository;
import biz.buynow.bank.repository.MTBtoMTBFundTransferResponseRepository;
import biz.buynow.bank.repository.RecipientUserRepository;
import biz.buynow.bank.repository.ResponseDumpJsonRepository;
import biz.buynow.bank.repository.ServerStatusRepository;

@Service
public class ScheduleHelper {

    static String MTBL_ACCOUNT_PREFIX = "145";
    static String DIGITON_BANK_ACC = "1311000476883";

    public RecipientUserRepository recipientUserRepository;

    public MTBBeftnTransResRepository mtblBeftnInfoResponseRepository;

    public MTBInWordBeftnInfoRepository mtblInWordBeftnInfoRepository;

    public MTBtoMTBFundTransferResponseRepository mtbtoMTBFundTransferResponseRepository;

    public MTBBeftnStatusResRepository mtblBeftnResponseRepository;

    public MTBBeftnStatusOutResRepository mtblBeftnOutwardResponseRepository;

    public MTBBeftnStatusOutDataResRepository mtblBeftnResponseDataEntryRepository;

    public MTBtoMTBFundTransferRequestRepository mtbtoMTBFundTransferRequestRepository;

    public MTBBeftnTransReqRepository beftnInfoRequestRepository;

    public MTBBeftnStatusReqRepository beftnStatusRequestRepository;

    public ResponseDumpJsonRepository responseDumpJsonRepository;

    public ServerStatusRepository serverStatusRepository;

    ScheduleHelper(RecipientUserRepository recipientUserRepository,
            MTBBeftnTransResRepository mtblBeftnInfoResponseRepository,
            MTBInWordBeftnInfoRepository mtblInWordBeftnInfoRepository,
            MTBtoMTBFundTransferResponseRepository mtbtoMTBFundTransferResponseRepository,
            MTBBeftnStatusResRepository mtblBeftnResponseRepository,
            MTBBeftnStatusOutResRepository mtblBeftnOutwardResponseRepository,
            MTBBeftnStatusOutDataResRepository mtblBeftnResponseDataEntryRepository,
            MTBtoMTBFundTransferRequestRepository mtbtoMTBFundTransferRequestRepository,
            MTBBeftnTransReqRepository beftnInfoRequestRepository,
            MTBBeftnStatusReqRepository beftnStatusRequestRepository,
            ResponseDumpJsonRepository responseDumpJsonRepository, ServerStatusRepository serverStatusRepository) {

        this.recipientUserRepository = recipientUserRepository;
        this.mtblInWordBeftnInfoRepository = mtblInWordBeftnInfoRepository;
        this.mtblBeftnInfoResponseRepository = mtblBeftnInfoResponseRepository;
        this.mtbtoMTBFundTransferResponseRepository = mtbtoMTBFundTransferResponseRepository;
        this.mtblBeftnResponseRepository = mtblBeftnResponseRepository;
        this.mtblBeftnOutwardResponseRepository = mtblBeftnOutwardResponseRepository;
        this.mtblBeftnResponseDataEntryRepository = mtblBeftnResponseDataEntryRepository;
        this.mtbtoMTBFundTransferRequestRepository = mtbtoMTBFundTransferRequestRepository;
        this.beftnInfoRequestRepository = beftnInfoRequestRepository;
        this.beftnStatusRequestRepository = beftnStatusRequestRepository;
        this.responseDumpJsonRepository = responseDumpJsonRepository;
        this.serverStatusRepository = serverStatusRepository;
    }

    public void getPendingTransReq() {
        System.out.println("pending...");
        Integer readyStatus = BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.READY.ordinal();

        Optional<RecipientUser> recipientUserOptional = recipientUserRepository
                .findTopByStatusOrderByIdAsc(readyStatus);
        processTransfer(recipientUserOptional);
    }

    public void processTransfer(Optional<RecipientUser> recipientUserOptional) {
        if (recipientUserOptional.isPresent()) {
            System.out.println("Id = " + recipientUserOptional.get().getUserId() + " amount = "
                    + recipientUserOptional.get().getAmount());

            List<UserBank> userBankList = recipientUserOptional.get().getUserBankList();
            boolean mtblStatus = false;
            try {
                mtblStatus = executeMTBTransferIfAvailable(recipientUserOptional.get(), userBankList);
            } catch (TransactionMaxLimitException transactionMaxLimitException) {
                transactionMaxLimitException.printStackTrace();
            }
            executeBeftnTransfer(recipientUserOptional, userBankList, mtblStatus);
        }
    }

    public void executeBeftnTransfer(Optional<RecipientUser> recipientUserOptional, List<UserBank> userBankList,
            boolean mtblStatus) {
        if (!mtblStatus) {
            for (UserBank userBank : userBankList) {
                System.out.println("AccountHoldersName = " + userBank.getAccountHoldersName() + " bankName = "
                        + userBank.getBankName());
                try {
                    DisburseMTBBeftn disburseMTBBeftn = new DisburseMTBBeftn(mtblBeftnInfoResponseRepository,
                            beftnInfoRequestRepository, responseDumpJsonRepository, mtblInWordBeftnInfoRepository,
                            recipientUserRepository, serverStatusRepository);
                    disburseMTBBeftn.processBeftnPayment(recipientUserOptional.get(), userBank);
                } catch (UniqueIDGenMaxLimitException uniqueIDGenMaxLimitException) {
                    uniqueIDGenMaxLimitException.printStackTrace();
                }
                break;
            }
        }
    }

    public boolean executeMTBTransferIfAvailable(RecipientUser recipientUser, List<UserBank> userBankList)
            throws TransactionMaxLimitException {
        for (UserBank userBank : userBankList) {
            String bankRoutingNumber = userBank.getBankRouting();
            if (bankRoutingNumber.startsWith(MTBL_ACCOUNT_PREFIX)) {
                DisburseMTBtoMTB disbursementMtbToMtb = new DisburseMTBtoMTB(mtbtoMTBFundTransferRequestRepository,
                        recipientUserRepository, responseDumpJsonRepository, mtbtoMTBFundTransferResponseRepository,
                        serverStatusRepository);
                disbursementMtbToMtb.processMTBLToMTBLPayment(recipientUser, userBank);
                return true;
            }
        }
        return false;
    }
}
