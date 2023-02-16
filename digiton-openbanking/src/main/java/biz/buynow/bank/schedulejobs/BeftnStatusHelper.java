package biz.buynow.bank.schedulejobs;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mtbl.bank.MTBBeftnStatusOutResJson;
import com.mtbl.bank.MTBBeftnStatusOutDataResJson;
import com.mtbl.bank.MTBBeftnStatusResJson;
import com.mtbl.bank.MTBBeftnStatusReqJson;

import biz.buynow.bank.constant.BusinessConstant;
import biz.buynow.bank.model.InWordBeftnInfo;
import biz.buynow.bank.model.MTBBeftnStatusOutRes;
import biz.buynow.bank.model.MTBBeftnStatusOutDataRes;
import biz.buynow.bank.model.MTBBeftnStatusReq;
import biz.buynow.bank.model.MTBBeftnStatusRes;
import biz.buynow.bank.model.MTBBeftnTransRes;
import biz.buynow.bank.model.RecipientUser;
import biz.buynow.bank.repository.MTBBeftnTransResRepository;
import biz.buynow.bank.repository.RecipientUserRepository;
import biz.buynow.bank.repository.MTBBeftnStatusOutResRepository;
import biz.buynow.bank.repository.MTBBeftnStatusOutDataResRepository;
import biz.buynow.bank.repository.MTBBeftnStatusResRepository;
import biz.buynow.bank.repository.MTBBeftnStatusReqRepository;
import biz.buynow.bank.repository.ResponseDumpJsonRepository;
import biz.buynow.bank.repository.ServerStatusRepository;

public class BeftnStatusHelper {

    private String openBankingUri = BusinessConstant.OPEN_BANKING_URI;

    public MTBBeftnStatusResRepository mtblBeftnResponseRepository;

    public MTBBeftnStatusOutResRepository mtblBeftnOutwardResponseRepository;

    public MTBBeftnStatusOutDataResRepository mtblBeftnResponseDataEntryRepository;

    public MTBBeftnStatusReqRepository beftnStatusRequestRepository;

    public ResponseDumpJsonRepository responseDumpJsonRepository;

    public MTBBeftnTransResRepository mtblBeftnInfoResponseRepository;

    public ServerStatusRepository serverStatusRepository;

    public RecipientUserRepository recipientUserRepository;

    BeftnStatusHelper(MTBBeftnStatusResRepository mtblBeftnResponseRepository,
            MTBBeftnStatusOutResRepository mtblBeftnOutwardResponseRepository,
            MTBBeftnStatusOutDataResRepository mtblBeftnResponseDataEntryRepository,
            MTBBeftnStatusReqRepository beftnStatusRequestRepository,
            ResponseDumpJsonRepository responseDumpJsonRepository,
            MTBBeftnTransResRepository mtblBeftnInfoResponseRepository, ServerStatusRepository serverStatusRepository,
            RecipientUserRepository recipientUserRepository) {

        this.mtblBeftnResponseRepository = mtblBeftnResponseRepository;
        this.mtblBeftnOutwardResponseRepository = mtblBeftnOutwardResponseRepository;
        this.mtblBeftnResponseDataEntryRepository = mtblBeftnResponseDataEntryRepository;
        this.beftnStatusRequestRepository = beftnStatusRequestRepository;
        this.responseDumpJsonRepository = responseDumpJsonRepository;
        this.mtblBeftnInfoResponseRepository = mtblBeftnInfoResponseRepository;
        this.serverStatusRepository = serverStatusRepository;
        this.recipientUserRepository = recipientUserRepository;
    }

    public void checkForMtbBeftnStatusByDateAndTime() {
        OffsetDateTime currentTime = OffsetDateTime.now();
        OffsetDateTime passedDays = currentTime.minusDays(5);
        OffsetDateTime passedHours = currentTime.minusHours(5);

        List<MTBBeftnTransRes> infoResponseList = mtblBeftnInfoResponseRepository
                .findAllByIsOutwardStatusPendingAndCreateTimeAfterAndUpdateTimeBefore(true, passedDays, passedHours);
        for (MTBBeftnTransRes mtbBeftnTransRes : infoResponseList) {
            OffsetDateTime creationTime = mtbBeftnTransRes.getCreateTime();
            OffsetDateTime updatedTime = mtbBeftnTransRes.getUpdateTime();
            OffsetDateTime stopCheckingTime = OffsetDateTime.now();
            if ((Duration.between(creationTime, stopCheckingTime).toDays() <= 5)
                    && (Duration.between(updatedTime, stopCheckingTime).toHours() >= 5)) {
                checkForMtbBeftnStatus(mtbBeftnTransRes);
            }
            mtbBeftnTransRes.setUpdateTime(OffsetDateTime.now());
            mtblBeftnInfoResponseRepository.save(mtbBeftnTransRes);
        }
    }

    public void checkForMtbBeftnStatus(MTBBeftnTransRes mtbBeftnTransRes) {
        InWordBeftnInfo mtblInWordBeftnInfo = mtbBeftnTransRes.getInwordbeftninfo();
        String edrTraceNo = mtblInWordBeftnInfo.getEdrTraceno();
        System.out.println("CHECK BEFTN OUTWARD STATUS");
        MTBBeftnStatusReqJson mtblBeftnStatusRequestJson = new MTBBeftnStatusReqJson(edrTraceNo, "", "", "Pathao");
        System.out.println("" + mtblBeftnStatusRequestJson.toString() + "");

        MTBBeftnStatusReq mtblBeftnStatusRequest = processBeftnStatusRequestSave(mtblBeftnStatusRequestJson);
        mtblBeftnStatusRequest = DisbursementSaveRequest.saveBeftnStatusRequest(beftnStatusRequestRepository,
                mtblBeftnStatusRequest);

        String jsonToProcess = DisbursementRequestGenerator.sendJsonRequestForBEFTNOutwardStatusCheck(openBankingUri,
                mtblBeftnStatusRequestJson.toString());
        MTBBeftnStatusResJson mtblBeftnStatusResJson = DisbursementRequestGenerator
                .processJsonRequestForBEFTNOutwardStatusCheck(jsonToProcess, mtblBeftnStatusRequest.getId(),
                        responseDumpJsonRepository);
        String resCode = mtblBeftnStatusResJson.getResCode();
        if (resCode.equals(BusinessConstant.SERVER_DOWN_ERROR_CODE)) {
            DisbursementSaveResponse.saveServerFailureStatus(serverStatusRepository);
        } else {
            mtblParseOutwardResponseJson(mtblBeftnStatusResJson, mtbBeftnTransRes);
            mtbBeftnTransRes.setUpdateTime(OffsetDateTime.now());
            mtblBeftnInfoResponseRepository.save(mtbBeftnTransRes);
        }
    }

    private MTBBeftnStatusReq processBeftnStatusRequestSave(MTBBeftnStatusReqJson mtblBeftnStatusRequestJson) {
        MTBBeftnStatusReq mtblBeftnStatusRequest = ScheduleUtils
                .convertMTBLBeftnStatusRequestJsonToMTBLBeftnStatusRequest(mtblBeftnStatusRequestJson);
        return mtblBeftnStatusRequest;
    }

    public void mtblParseOutwardResponseJson(MTBBeftnStatusResJson mtblBeftnResponseJson,
            MTBBeftnTransRes mtblBeftnInfoResponse) {
        MTBBeftnStatusOutResJson mtblBeftnOutwardResponseJson = mtblBeftnResponseJson.getBeftnOutwardResponse();
        MTBBeftnStatusRes mtblBeftnStatusRes = ScheduleUtils
                .convertMTBBeftnStatusResJsonToMTBBeftnStatusRes(mtblBeftnResponseJson);

        MTBBeftnStatusOutRes mtblBeftnOutwardResponse = ScheduleUtils
                .convertMTBBeftnStatusOutResJsonToMTBBeftnStatusOutResponse(mtblBeftnOutwardResponseJson,
                        mtblBeftnStatusRes);
        mtblBeftnStatusRes.setMTBLBeftnOutwardStatus(mtblBeftnOutwardResponse);
        mtblBeftnStatusRes.setMTBLBeftnTransferResponse(mtblBeftnInfoResponse);
        mtblBeftnStatusRes = mtblBeftnResponseRepository.save(mtblBeftnStatusRes);
        mtblBeftnOutwardResponse = mtblBeftnOutwardResponseRepository.save(mtblBeftnOutwardResponse);

        processMTBLBeftnResponseDataEntryList(mtblBeftnOutwardResponseJson, mtblBeftnOutwardResponse,
                mtblBeftnStatusRes.getMTBLBeftnTransferResponse());
    }

    public void processMTBLBeftnResponseDataEntryList(MTBBeftnStatusOutResJson mtblBeftnOutwardResponseJson,
            MTBBeftnStatusOutRes mtblBeftnOutwardResponse, MTBBeftnTransRes mtbBeftnTransRes) {
        List<MTBBeftnStatusOutDataRes> mtblBeftnResponseDataEntryList = new ArrayList<MTBBeftnStatusOutDataRes>();
        List<MTBBeftnStatusOutDataResJson> mtblBeftnResponseDataEntryJsonList = mtblBeftnOutwardResponseJson
                .getBeftnResponseDataJsonList();
        for (MTBBeftnStatusOutDataResJson mtblBeftnResponseDataEntryJson : mtblBeftnResponseDataEntryJsonList) {
            MTBBeftnStatusOutDataRes mtblBeftnResponseDataEntry = ScheduleUtils
                    .convertMTBLBeftnResponseDataEntryJsonToMTBLBeftnResponseDataEntry(mtblBeftnOutwardResponse,
                            mtblBeftnResponseDataEntryJson);
            if (mtblBeftnResponseDataEntry.getReturnStat().equals("not returned")) {
                mtbBeftnTransRes.setOutwardStatusPending(false);
                mtblBeftnInfoResponseRepository.save(mtbBeftnTransRes);
                makePaymentStatusCompleted(mtbBeftnTransRes);
            }

            mtblBeftnResponseDataEntryRepository.save(mtblBeftnResponseDataEntry);
            mtblBeftnResponseDataEntryList.add(mtblBeftnResponseDataEntry);
        }
        mtblBeftnOutwardResponseRepository.save(mtblBeftnOutwardResponse);
    }

    private void makePaymentStatusCompleted(MTBBeftnTransRes mtbBeftnTransRes) {
        Long id = mtbBeftnTransRes.getRecipientUser();
        Optional<RecipientUser> recipientUserOptional = recipientUserRepository.findById(id);
        if (recipientUserOptional.isPresent()) {
            Integer completedPaymentStatus = BusinessConstant.PAYMENT_STATUS.COMPLETED.ordinal();
            recipientUserOptional.get().setPaymentStatus(completedPaymentStatus);
            recipientUserRepository.save(recipientUserOptional.get());
        }
    }
}
