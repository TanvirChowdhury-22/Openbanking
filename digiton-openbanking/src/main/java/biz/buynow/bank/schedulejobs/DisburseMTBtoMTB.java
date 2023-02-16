package biz.buynow.bank.schedulejobs;

import com.mtbl.bank.MTBtoMTBTransReqJson;
import com.mtbl.bank.MTBtoMTBTransResJson;

import biz.buynow.bank.constant.BusinessConstant;
import biz.buynow.bank.model.MTBtoMTBTransReq;
import biz.buynow.bank.model.MTBtoMTBTransRes;
import biz.buynow.bank.model.RecipientUser;
import biz.buynow.bank.model.UserBank;
import biz.buynow.bank.repository.MTBtoMTBFundTransferRequestRepository;
import biz.buynow.bank.repository.MTBtoMTBFundTransferResponseRepository;
import biz.buynow.bank.repository.RecipientUserRepository;
import biz.buynow.bank.repository.ResponseDumpJsonRepository;
import biz.buynow.bank.repository.ServerStatusRepository;

public class DisburseMTBtoMTB {
    static String DIGITON_BANK_ACC = "1311000476883";

    private String openBankingUri = BusinessConstant.OPEN_BANKING_URI;

    public MTBtoMTBFundTransferRequestRepository mtbtoMTBFundTransferRequestRepository;

    public RecipientUserRepository recipientUserRepository;

    public ResponseDumpJsonRepository responseDumpJsonRepository;

    public MTBtoMTBFundTransferResponseRepository mtbtoMTBFundTransferResponseRepository;

    public ServerStatusRepository serverStatusRepository;

    DisburseMTBtoMTB(MTBtoMTBFundTransferRequestRepository mtbtoMTBFundTransferRequestRepository,
            RecipientUserRepository recipientUserRepository, ResponseDumpJsonRepository responseDumpJsonRepository,
            MTBtoMTBFundTransferResponseRepository mtbtoMTBFundTransferResponseRepository,
            ServerStatusRepository serverStatusRepository) {

        this.mtbtoMTBFundTransferRequestRepository = mtbtoMTBFundTransferRequestRepository;
        this.recipientUserRepository = recipientUserRepository;
        this.responseDumpJsonRepository = responseDumpJsonRepository;
        this.mtbtoMTBFundTransferResponseRepository = mtbtoMTBFundTransferResponseRepository;
        this.serverStatusRepository = serverStatusRepository;
    }

    public void processMTBLToMTBLPayment(RecipientUser recipientUser, UserBank userBank)
            throws TransactionMaxLimitException {
        // TODO GENERATE UNIQUE ID FOR TRANSACTION
        String uniqueTxnId = getAUniqueTransactionIdForMTBL();
        System.out.println("MTBL TO MTBL");
        MTBtoMTBTransReqJson mtbToMTBFundTransferRequestJson = new MTBtoMTBTransReqJson(recipientUser.getAmount(),
                DIGITON_BANK_ACC, userBank.getBankAccNo(), recipientUser.getComments(), "Pathao", uniqueTxnId);
        System.out.println("" + mtbToMTBFundTransferRequestJson.toString() + "");

        MTBtoMTBTransReq mtbtoMTBFundTransferRequest = processMTBLFundTransferRequest(
                mtbToMTBFundTransferRequestJson);

        mtbtoMTBFundTransferRequest = DisbursementSaveRequest
                .saveMTBToMTBFundTransferRequest(mtbtoMTBFundTransferRequestRepository, mtbtoMTBFundTransferRequest);
        // generate and send JSON request
        String jsonToProcess = DisbursementRequestGenerator.sendJsonRequestForMTBLToMTBL(openBankingUri,
                mtbToMTBFundTransferRequestJson.toString());
        if (!jsonToProcess.isEmpty()) {
            MTBtoMTBTransResJson mtbtoMTBFundTransferResponseJson = DisbursementRequestGenerator
                    .processJsonRequestForMTBLToMTBL(jsonToProcess, recipientUser, userBank.getId(),
                            mtbtoMTBFundTransferRequest.getId(), responseDumpJsonRepository);

            String resCode = mtbtoMTBFundTransferResponseJson.getResCode();
            if (resCode.equals(BusinessConstant.SERVER_DOWN_ERROR_CODE)) {
                DisbursementSaveResponse.saveServerFailureStatus(serverStatusRepository);
            } else {
                // process JSON object
                MTBtoMTBTransRes mtbtoMTBFundTransferResponse = processMTBLFundTransferResponse(recipientUser,
                        userBank.getId(), mtbtoMTBFundTransferResponseJson);
                // save data in database
                DisbursementSaveResponse.saveMTBLToMTBFundTransferResponse(recipientUserRepository,
                        mtbtoMTBFundTransferResponseRepository, recipientUser, userBank.getId(),
                        mtbtoMTBFundTransferResponse);
            }
        } else {
            // TODO SHOW ERROR MESSAGE TO USER
        }
    }

    private MTBtoMTBTransReq processMTBLFundTransferRequest(MTBtoMTBTransReqJson mtbtoMTBFundTransferRequestJson) {
        MTBtoMTBTransReq mtbtoMTBFundTransferRequest = ScheduleUtils
                .convertMTBtoMTBFundTransferRequestJsonToMTBtoMTBFundTransferRequest(mtbtoMTBFundTransferRequestJson);
        return mtbtoMTBFundTransferRequest;
    }

    private MTBtoMTBTransRes processMTBLFundTransferResponse(RecipientUser recipientUser, Long userBankId,
            MTBtoMTBTransResJson mtbtoMTBFundTransferResponseJson) {
        MTBtoMTBTransRes mtbtoMTBFundTransferResponse = ScheduleUtils
                .convertMTBtoMTBFundTransferResponseJsonToMTBtoMTBFundTransferResponse(
                        mtbtoMTBFundTransferResponseJson);
        return mtbtoMTBFundTransferResponse;
    }

    private String getAUniqueTransactionIdForMTBL() throws TransactionMaxLimitException {
        String transactionId = CustomRandomGenerator.generateRandomTransactionId();
        int tryCount = 0;
        while (tryCount < BusinessConstant.MAX_RETRY_TRANSACTIONID_GEN
                && mtbtoMTBFundTransferRequestRepository.findByUniqueTxnId(transactionId).isPresent()) {
            transactionId = CustomRandomGenerator.generateRandomTransactionId();
            tryCount++;
        }
        if (tryCount == BusinessConstant.MAX_RETRY_TRANSACTIONID_GEN) {
            throw new TransactionMaxLimitException("Max Limit Excedded");
        }
        return transactionId;
    }
}
