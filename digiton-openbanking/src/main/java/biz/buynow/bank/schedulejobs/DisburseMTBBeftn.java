package biz.buynow.bank.schedulejobs;

import com.mtbl.bank.MTBBeftnTransReqJson;
import com.mtbl.bank.MTBBeftnTransResJson;
import com.mtbl.bank.MTBInWordBeftnInfoJson;

import biz.buynow.bank.constant.BusinessConstant;
import biz.buynow.bank.model.InWordBeftnInfo;
import biz.buynow.bank.model.MTBBeftnTransReq;
import biz.buynow.bank.model.MTBBeftnTransRes;
import biz.buynow.bank.model.RecipientUser;
import biz.buynow.bank.model.UserBank;
import biz.buynow.bank.repository.MTBBeftnTransReqRepository;
import biz.buynow.bank.repository.MTBBeftnTransResRepository;
import biz.buynow.bank.repository.MTBInWordBeftnInfoRepository;
import biz.buynow.bank.repository.RecipientUserRepository;
import biz.buynow.bank.repository.ResponseDumpJsonRepository;
import biz.buynow.bank.repository.ServerStatusRepository;

public class DisburseMTBBeftn {

    private String openBankingUri = BusinessConstant.OPEN_BANKING_URI;

    public MTBBeftnTransResRepository mtblBeftnInfoResponseRepository;

    public MTBBeftnTransReqRepository beftnInfoRequestRepository;

    public ResponseDumpJsonRepository responseDumpJsonRepository;

    public MTBInWordBeftnInfoRepository mtblInWordBeftnInfoRepository;

    public RecipientUserRepository recipientUserRepository;

    public ServerStatusRepository serverStatusRepository;

    DisburseMTBBeftn(MTBBeftnTransResRepository mtblBeftnInfoResponseRepository,
            MTBBeftnTransReqRepository beftnInfoRequestRepository,
            ResponseDumpJsonRepository responseDumpJsonRepository,
            MTBInWordBeftnInfoRepository mtblInWordBeftnInfoRepository, RecipientUserRepository recipientUserRepository,
            ServerStatusRepository serverStatusRepository) {

        this.mtblBeftnInfoResponseRepository = mtblBeftnInfoResponseRepository;
        this.beftnInfoRequestRepository = beftnInfoRequestRepository;
        this.responseDumpJsonRepository = responseDumpJsonRepository;
        this.mtblInWordBeftnInfoRepository = mtblInWordBeftnInfoRepository;
        this.recipientUserRepository = recipientUserRepository;
        this.serverStatusRepository = serverStatusRepository;
    }

    public void processBeftnPayment(RecipientUser recipientUser, UserBank userBank)
            throws UniqueIDGenMaxLimitException {
        String uniqueID = getAUniqueIDForBEFTN();
        System.out.println("BEFTN");
        MTBBeftnTransReqJson mtblBeftnInfoRequestJson = new MTBBeftnTransReqJson("PUSH", recipientUser.getAmount(),
                userBank.getBankAccNo(), recipientUser.getComments(), userBank.getBankRouting(),
                userBank.getAccountHoldersName(), "Pathao", uniqueID);
        System.out.println("" + mtblBeftnInfoRequestJson.toString() + "");

        MTBBeftnTransReq mtblBeftnInfoRequest = processMTBLBeftnInfoRequest(mtblBeftnInfoRequestJson);
        DisbursementSaveRequest.saveBeftnInfoRequest(beftnInfoRequestRepository, mtblBeftnInfoRequest);

        // generate and send JSON request
        String jsonToProcess = DisbursementRequestGenerator.sendJsonRequestForBEFTN(openBankingUri,
                mtblBeftnInfoRequestJson.toString());

        if (!jsonToProcess.isEmpty()) {
            MTBBeftnTransResJson mtblBeftnInfoResponseJson = DisbursementRequestGenerator.processJsonRequestForBEFTN(
                    jsonToProcess, recipientUser, userBank.getId(), mtblBeftnInfoRequest.getId(),
                    responseDumpJsonRepository);

            String resCode = mtblBeftnInfoResponseJson.getResCode();
            if (resCode.equals(BusinessConstant.SERVER_DOWN_ERROR_CODE)) {
                DisbursementSaveResponse.saveServerFailureStatus(serverStatusRepository);
            } else {
                InWordBeftnInfo mtblInWordBeftnInfo = processResponseForBEFTN(recipientUser, userBank.getId(),
                        mtblBeftnInfoResponseJson);
                // save data in database
                // we need to get updated mtblInWordBeftnInfo with saved database table id
                mtblInWordBeftnInfo = DisbursementSaveResponse.saveResponseForInWordBEFTNInfo(mtblInWordBeftnInfoRepository,
                        mtblInWordBeftnInfo);
                // process nested object
                MTBBeftnTransRes mtblBeftnInfoResponse = processMTBLBeftnInfoResponse(mtblBeftnInfoResponseJson,
                        mtblInWordBeftnInfo);
                DisbursementSaveResponse.saveResponseForBEFTN(recipientUserRepository, mtblInWordBeftnInfoRepository,
                        mtblBeftnInfoResponseRepository, recipientUser, userBank.getId(), mtblBeftnInfoResponse);
            }
        } else {
            // TODO SHOW ERROR MESSAGE TO USER
        }
        // process JSON object

    }

    private MTBBeftnTransReq processMTBLBeftnInfoRequest(MTBBeftnTransReqJson mtblBeftnInfoRequestJson) {
        MTBBeftnTransReq mtblBeftnInfoRequest = ScheduleUtils
                .convertMTBLBeftnInfoRequestJsonToMTBLBeftnInfoRequest(mtblBeftnInfoRequestJson);
        return mtblBeftnInfoRequest;
    }

    private MTBBeftnTransRes processMTBLBeftnInfoResponse(MTBBeftnTransResJson mtblBeftnInfoResponseJson,
            InWordBeftnInfo mtblInWordBeftnInfo) {
        MTBBeftnTransRes mtblBeftnInfoResponse = ScheduleUtils
                .convertMTBLBeftnInfoResponseJsonToBeftnInfoResponse(mtblBeftnInfoResponseJson, mtblInWordBeftnInfo);

        return mtblBeftnInfoResponse;
    }

    private InWordBeftnInfo processResponseForBEFTN(RecipientUser recipientUser, Long userBankId,
            MTBBeftnTransResJson mtblBeftnInfoResponseJson) {
        MTBInWordBeftnInfoJson mtblInWordBeftnInfoJson = mtblBeftnInfoResponseJson.getInwordbeftninfo();
        InWordBeftnInfo mtblInWordBeftnInfo = ScheduleUtils
                .convertMTBLBeftnInWordInfoJsonToBeftnInWordInfo(mtblInWordBeftnInfoJson);
        return mtblInWordBeftnInfo;
    }

    private String getAUniqueIDForBEFTN() throws UniqueIDGenMaxLimitException {
        String uniqueID = CustomRandomGenerator.generateRandomUniqueId();
        int tryCount = 0;
        while (tryCount < BusinessConstant.MAX_RETRY_UNIQUEID_GEN
                && beftnInfoRequestRepository.findByUniqueID(uniqueID).isPresent()) {
            uniqueID = CustomRandomGenerator.generateRandomUniqueId();
            tryCount++;
        }
        if (tryCount == BusinessConstant.MAX_RETRY_UNIQUEID_GEN) {
            throw new UniqueIDGenMaxLimitException("Max Limit Excedded");
        }
        return uniqueID;
    }
}
