package biz.buynow.bank.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mtbl.bank.MTBOnlineStmtReqJson;
import com.mtbl.bank.MTBOnlineStmtResJson;
import com.mtbl.bank.MTBOnlineStmtTrnxListJson;

import biz.buynow.bank.constant.BusinessConstant;
import biz.buynow.bank.model.MTBOnlineStmtReq;
import biz.buynow.bank.model.MTBOnlineStmtRes;
import biz.buynow.bank.model.MTBOnlineStmtTrnxList;
import biz.buynow.bank.model.ServerStatus;
import biz.buynow.bank.repository.MTBOnlineStmtReqRepository;
import biz.buynow.bank.repository.MTBOnlineStmtResRepository;
import biz.buynow.bank.repository.MTBStmtTrnxListRepository;
import biz.buynow.bank.repository.ResponseDumpJsonRepository;
import biz.buynow.bank.repository.ServerStatusRepository;
import biz.buynow.bank.schedulejobs.DisbursementRequestGenerator;
import biz.buynow.bank.schedulejobs.DisbursementSaveRequest;
import biz.buynow.bank.schedulejobs.ScheduleUtils;

@Controller
public class StatementController {
    static String DIGITON_BANK_ACC = "0510320000083";
    private static String openBankingUri = BusinessConstant.OPEN_BANKING_URI;

    @Autowired
    private ResponseDumpJsonRepository responseDumpJsonRepository;

    @Autowired
    private MTBOnlineStmtReqRepository mtblStmtRequestRepository;

    @Autowired
    private MTBOnlineStmtResRepository mtblStmtResponseRepository;

    @Autowired
    private MTBStmtTrnxListRepository mtblStmtTransactionRepository;

    @Autowired
    private ServerStatusRepository serverStatusRepository;

    @GetMapping("/onlinestatement")
    public ResponseEntity<String> showOnlineStatement() {
        processOnlineStatement("", "", "0");
        ResponseEntity<String> responseEntity = ResponseEntity.ok().build();
        return responseEntity;
    }

    @GetMapping("/onlinestatement-with-date")
    public ResponseEntity<String> showOnlineStatementDate() {
        Instant now = Instant.now(); // current date
        Instant before = now.minus(Duration.ofDays(5));
        Date fromDate = Date.from(before);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date toDate = new Date();
        // todo GET CURRENT DATE
        String toDateStr = dateFormat.format(toDate);
        String fromDateStr = dateFormat.format(fromDate);// todo CONVERT STRING from FROMDATE
        // todo CONVERT STRING from TODATE
        processOnlineStatement(fromDateStr, toDateStr, "0");
        ResponseEntity<String> responseEntity = ResponseEntity.ok().build();
        return responseEntity;
    }

    void processOnlineStatement(String fromDate, String toDate, String nooftrn) {
        System.out.println("ONLINE STATEMENT");
        MTBOnlineStmtReqJson mtblOnlineStmtReqJson = new MTBOnlineStmtReqJson(DIGITON_BANK_ACC, fromDate, nooftrn,
                toDate, "Pathao");
        System.out.println("" + mtblOnlineStmtReqJson.toString() + "");
        MTBOnlineStmtReq mtblOnlineStmtReq = processMTBLStmtRequest(mtblOnlineStmtReqJson);
        DisbursementSaveRequest.saveOnlineStmtReq(mtblStmtRequestRepository, mtblOnlineStmtReq);
        System.out.println(mtblOnlineStmtReq.toString());
        String jsonToProcess = DisbursementRequestGenerator.sendJsonRequestForOnlineStatement(openBankingUri,
                mtblOnlineStmtReqJson.toString());
        if (!jsonToProcess.isEmpty()) {
            MTBOnlineStmtResJson mtblOnlineStatementResponseJson = DisbursementRequestGenerator
                    .processJsonRequestForOnlineStatement(jsonToProcess, mtblOnlineStmtReq.getId(),
                            responseDumpJsonRepository);
            String resCode = mtblOnlineStatementResponseJson.getResCode();
            if (resCode.equals(BusinessConstant.SERVER_DOWN_ERROR_CODE)) {
                ServerStatus serverStatus = new ServerStatus();
                serverStatus.setBank(BusinessConstant.BANK.MTBL.toString());
                serverStatus.setDown(true);
                serverStatusRepository.save(serverStatus);
            } else {
                boolean ifNoOfTrnZero = (!nooftrn.equals("0"));
                mtblParseOnlineStatementResponseJson(mtblOnlineStatementResponseJson, ifNoOfTrnZero);
            }
        } else {
            // TODO SHOW ERROR MESSAGE TO USER
        }
    }

    private void mtblParseOnlineStatementResponseJson(MTBOnlineStmtResJson mtblOnlineStatementResponseJson,
            boolean ifNoOfTrnZero) {
        MTBOnlineStmtRes mtblOnlineStatementResponse = ScheduleUtils
                .convertMTBLOnlineStmtResJsonToMTBLOnlineStmtRes(mtblOnlineStatementResponseJson);
        List<MTBOnlineStmtTrnxListJson> transactionListJson = processMTBLStmtTransaction(
                mtblOnlineStatementResponseJson);
        Long lastTransactionId = saveMTBLStmtTransaction(transactionListJson);
        if (ifNoOfTrnZero) {
            lastTransactionId = -1L;
        }
        mtblOnlineStatementResponse.setLastTransactionId(lastTransactionId);
        mtblOnlineStatementResponse = mtblStmtResponseRepository.save(mtblOnlineStatementResponse);
    }

    private Long saveMTBLStmtTransaction(List<MTBOnlineStmtTrnxListJson> transactionListJson) {
        Long lastTransactionId = -1L;
        for (MTBOnlineStmtTrnxListJson mtblOnlineStatementTransactionListJson : transactionListJson) {
            MTBOnlineStmtTrnxList mtblStmtTransaction = ScheduleUtils
                    .convertMTBLStmtTrnListJsonToMTBLStmtTrnList(mtblOnlineStatementTransactionListJson);
            try {
                Optional<MTBOnlineStmtTrnxList> mtblStmtTransactionOptional = mtblStmtTransactionRepository
                        .findByCurrencyNameAndCurrentBalanceAndDepositAndDescriptionAndNarrationAndTransactionTypeAndWithdrawal(
                                mtblStmtTransaction.getCurrencyName(), mtblStmtTransaction.getCurrentBalance(),
                                mtblStmtTransaction.getDeposit(), mtblStmtTransaction.getDescription(),
                                mtblStmtTransaction.getNarration(), mtblStmtTransaction.getTransactionType(),
                                mtblStmtTransaction.getWithdrawal());
                lastTransactionId = conditionForLastTrxIdSave(lastTransactionId, mtblStmtTransaction,
                        mtblStmtTransactionOptional);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return lastTransactionId;
    }

    private Long conditionForLastTrxIdSave(Long lastTransactionId, MTBOnlineStmtTrnxList mtblStmtTransaction,
            Optional<MTBOnlineStmtTrnxList> mtblStmtTransactionOptional) {
        if (mtblStmtTransactionOptional.isPresent()) {
            if (lastTransactionId == null) {
                lastTransactionId = -1L;
            } else if (lastTransactionId == -1L) {
                lastTransactionId = mtblStmtTransaction.getId();
            }
        } else {
            mtblStmtTransaction = mtblStmtTransactionRepository.save(mtblStmtTransaction);
            if (lastTransactionId == -1L) {
                lastTransactionId = mtblStmtTransaction.getId();
            }
        }
        return lastTransactionId;
    }

    private MTBOnlineStmtReq processMTBLStmtRequest(MTBOnlineStmtReqJson mtblOnlineStatusRequestJson) {
        MTBOnlineStmtReq mtblOnlineStatusRequest = ScheduleUtils
                .convertMTBLStmtRequestJsonToMTBLStmtRequest(mtblOnlineStatusRequestJson);
        return mtblOnlineStatusRequest;
    }

    private List<MTBOnlineStmtTrnxListJson> processMTBLStmtTransaction(
            MTBOnlineStmtResJson mtblOnlineStatementResponseJson) {
        List<MTBOnlineStmtTrnxListJson> transactionListJson = mtblOnlineStatementResponseJson.getTransactionList();
        return transactionListJson;
    }
}
