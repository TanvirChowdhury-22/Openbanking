package biz.buynow.bank.schedulejobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import biz.buynow.bank.constant.BusinessConstant;

import biz.buynow.bank.model.ServerStatus;
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

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

@Component
public class PendingDisbursementProcessor {
    static String DIGITON_BANK_ACC = "1311000476883";
    static String MTBL_ACCOUNT_PREFIX = "145";

    @Autowired
    private RecipientUserRepository recipientUserRepository;

    @Autowired
    private MTBBeftnTransResRepository mtblBeftnInfoResponseRepository;

    @Autowired
    private MTBInWordBeftnInfoRepository mtblInWordBeftnInfoRepository;

    @Autowired
    private MTBtoMTBFundTransferResponseRepository mtbtoMTBFundTransferResponseRepository;

    @Autowired
    private MTBBeftnStatusResRepository mtblBeftnResponseRepository;

    @Autowired
    private MTBBeftnStatusOutResRepository mtblBeftnOutwardResponseRepository;

    @Autowired
    private MTBBeftnStatusOutDataResRepository mtblBeftnResponseDataEntryRepository;

    @Autowired
    private MTBtoMTBFundTransferRequestRepository mtbtoMTBFundTransferRequestRepository;

    @Autowired
    private MTBBeftnTransReqRepository beftnInfoRequestRepository;

    @Autowired
    private MTBBeftnStatusReqRepository beftnStatusRequestRepository;

    @Autowired
    private ResponseDumpJsonRepository responseDumpJsonRepository;

    @Autowired
    private ServerStatusRepository serverStatusRepository;

    // @Scheduled(cron = "0 0/10 * * * *")
    @Transactional
    @Scheduled(fixedDelay = 10000) // in milliseconds
    public void scheduledTask() {
        System.out.println("calling scheduled task.");
        Boolean isServerActiveBoolean = isServerActive();
        if (isServerActiveBoolean) {
            ScheduleHelper scheduleHelper = new ScheduleHelper(recipientUserRepository, mtblBeftnInfoResponseRepository,
                    mtblInWordBeftnInfoRepository, mtbtoMTBFundTransferResponseRepository, mtblBeftnResponseRepository,
                    mtblBeftnOutwardResponseRepository, mtblBeftnResponseDataEntryRepository,
                    mtbtoMTBFundTransferRequestRepository, beftnInfoRequestRepository, beftnStatusRequestRepository,
                    responseDumpJsonRepository, serverStatusRepository);
            scheduleHelper.getPendingTransReq();

            BeftnStatusHelper beftnStatusHelper = new BeftnStatusHelper(mtblBeftnResponseRepository,
                    mtblBeftnOutwardResponseRepository, mtblBeftnResponseDataEntryRepository,
                    beftnStatusRequestRepository, responseDumpJsonRepository, mtblBeftnInfoResponseRepository,
                    serverStatusRepository, recipientUserRepository);
            beftnStatusHelper.checkForMtbBeftnStatusByDateAndTime();
        }
    }

    public Boolean isServerActive() {
        Boolean isServerActiveBoolean = true;
        OffsetDateTime currentTime = OffsetDateTime.now();
        Optional<ServerStatus> serverStatusOptional = serverStatusRepository
                .findTopByBankOrderByIdDesc(BusinessConstant.BANK.MTBL.toString());

        isServerActiveBoolean = isServerActiveExtracted(isServerActiveBoolean, currentTime, serverStatusOptional);
        return isServerActiveBoolean;
    }

    private Boolean isServerActiveExtracted(Boolean isServerActiveBoolean, OffsetDateTime currentTime,
            Optional<ServerStatus> serverStatusOptional) {
        Long downTimeDuration = 0L;
        if (serverStatusOptional.isPresent()) {
            OffsetDateTime downTime = serverStatusOptional.get().getDownTime();
            downTimeDuration = Duration.between(downTime, currentTime).toMinutes();
            if (downTimeDuration < BusinessConstant.MAXIMUM_DOWNTIME_DURATION_IN_MINUTES) {
                isServerActiveBoolean = false;
            }
        }
        return isServerActiveBoolean;
    }
}
