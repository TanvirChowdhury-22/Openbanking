package biz.buynow.bank.schedulejobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.mtbl.bank.MTBBeftnTransReqJson;
import com.mtbl.bank.MTBBeftnTransResJson;
import com.mtbl.bank.MTBBeftnStatusOutResJson;
import com.mtbl.bank.MTBBeftnStatusOutDataResJson;
import com.mtbl.bank.MTBBeftnStatusResJson;
import com.mtbl.bank.MTBBeftnStatusReqJson;
import com.mtbl.bank.MTBInWordBeftnInfoJson;
import com.mtbl.bank.MTBOnlineStmtResJson;
import com.mtbl.bank.MTBOnlineStmtTrnxListJson;
import com.mtbl.bank.MTBOnlineStmtReqJson;
import com.mtbl.bank.MTBtoMTBTransReqJson;
import com.mtbl.bank.MTBtoMTBTransResJson;

import biz.buynow.bank.model.MTBBeftnTransReq;
import biz.buynow.bank.model.MTBBeftnTransRes;
import biz.buynow.bank.model.MTBOnlineStmtRes;
import biz.buynow.bank.model.MTBOnlineStmtTrnxList;
import biz.buynow.bank.model.MTBOnlineStmtReq;
import biz.buynow.bank.model.MTBBeftnStatusOutRes;
import biz.buynow.bank.model.MTBBeftnStatusRes;
import biz.buynow.bank.model.MTBBeftnStatusOutDataRes;
import biz.buynow.bank.model.MTBBeftnStatusReq;
import biz.buynow.bank.model.InWordBeftnInfo;
import biz.buynow.bank.model.MTBtoMTBTransReq;
import biz.buynow.bank.model.MTBtoMTBTransRes;

public class ScheduleUtils {

    static public MTBtoMTBTransRes convertMTBtoMTBFundTransferResponseJsonToMTBtoMTBFundTransferResponse(
            MTBtoMTBTransResJson mtbtoMTBFundTransferResponseJson) {
        MTBtoMTBTransRes mtbtoMTBFundTransferResponse = new MTBtoMTBTransRes();
        mtbtoMTBFundTransferResponse.setCbsCode(mtbtoMTBFundTransferResponseJson.getCbsCode());
        mtbtoMTBFundTransferResponse.setCbsJournalNo(mtbtoMTBFundTransferResponseJson.getCbsJournalNo());
        mtbtoMTBFundTransferResponse.setCbsResDate(mtbtoMTBFundTransferResponseJson.getCbsResDate());
        mtbtoMTBFundTransferResponse.setCbsTxnID(mtbtoMTBFundTransferResponseJson.getCbsTxnID());
        mtbtoMTBFundTransferResponse.setLogId(mtbtoMTBFundTransferResponseJson.getLogId());
        mtbtoMTBFundTransferResponse.setResCode(mtbtoMTBFundTransferResponseJson.getResCode());
        mtbtoMTBFundTransferResponse.setResMsg(mtbtoMTBFundTransferResponseJson.getResMsg());
        return mtbtoMTBFundTransferResponse;
    }

    static public MTBBeftnStatusOutDataRes convertMTBLBeftnResponseDataEntryJsonToMTBLBeftnResponseDataEntry(
            MTBBeftnStatusOutRes mtblBeftnOutwardResponse,
            MTBBeftnStatusOutDataResJson mtblBeftnResponseDataEntryJson) {
        MTBBeftnStatusOutDataRes mtblBeftnResponseDataEntry = new MTBBeftnStatusOutDataRes();
        mtblBeftnResponseDataEntry.setAmount(mtblBeftnResponseDataEntryJson.getAmount());
        mtblBeftnResponseDataEntry.setAmountSpecified(mtblBeftnResponseDataEntryJson.getAmountSpecified());
        mtblBeftnResponseDataEntry.setBbAcknoledged(mtblBeftnResponseDataEntryJson.getBbAcknoledged());
        mtblBeftnResponseDataEntry.setChannelId(mtblBeftnResponseDataEntryJson.getChannelId());
        mtblBeftnResponseDataEntry.setCompAccountNum(mtblBeftnResponseDataEntryJson.getCompAccountNum());
        mtblBeftnResponseDataEntry.setCompEntryDesc(mtblBeftnResponseDataEntryJson.getCompEntryDesc());
        mtblBeftnResponseDataEntry.setCompId(mtblBeftnResponseDataEntryJson.getCompId());
        mtblBeftnResponseDataEntry.setCompName(mtblBeftnResponseDataEntryJson.getCompName());
        mtblBeftnResponseDataEntry.setDfiAccountNo(mtblBeftnResponseDataEntryJson.getDfiAccountNo());
        mtblBeftnResponseDataEntry.setFileGenerated(mtblBeftnResponseDataEntryJson.getFileGenerated());
        mtblBeftnResponseDataEntry.setIndividualId(mtblBeftnResponseDataEntryJson.getIndividualId());
        mtblBeftnResponseDataEntry.setOriginalTraceNumber(mtblBeftnResponseDataEntryJson.getOriginalTraceNumber());
        mtblBeftnResponseDataEntry.setReceive_bank(mtblBeftnResponseDataEntryJson.getReceive_bank());
        mtblBeftnResponseDataEntry.setReceiverName(mtblBeftnResponseDataEntryJson.getReceiverName());
        mtblBeftnResponseDataEntry.setRetReason(mtblBeftnResponseDataEntryJson.getRetReason());
        mtblBeftnResponseDataEntry.setReturnDate(mtblBeftnResponseDataEntryJson.getReturnDate());
        mtblBeftnResponseDataEntry.setReturnStat(mtblBeftnResponseDataEntryJson.getReturnStat());
        mtblBeftnResponseDataEntry.setTranDate(mtblBeftnResponseDataEntryJson.getTranDate());
        mtblBeftnResponseDataEntry.setMTBLBeftnOutwardStatus(mtblBeftnOutwardResponse);
        return mtblBeftnResponseDataEntry;
    }

    static public InWordBeftnInfo convertMTBLBeftnInWordInfoJsonToBeftnInWordInfo(
            MTBInWordBeftnInfoJson mtblInWordBeftnInfoJson) {
        InWordBeftnInfo mtblInWordBeftnInfo = new InWordBeftnInfo();
        mtblInWordBeftnInfo.setCbsfjournalNo(mtblInWordBeftnInfoJson.getCbsfjournalNo());
        mtblInWordBeftnInfo.setCbsftrnNum(mtblInWordBeftnInfoJson.getCbsftrnNum());
        mtblInWordBeftnInfo.setEdrTraceno(mtblInWordBeftnInfoJson.getEdrTraceno());
        mtblInWordBeftnInfo.setMsg(mtblInWordBeftnInfoJson.getMsg());
        mtblInWordBeftnInfo.setMsgCode(mtblInWordBeftnInfoJson.getMsgCode());
        mtblInWordBeftnInfo.setOriginalTraceno(mtblInWordBeftnInfoJson.getOriginalTraceno());
        mtblInWordBeftnInfo.setStlmDate(mtblInWordBeftnInfoJson.getStlmDate());
        return mtblInWordBeftnInfo;
    }

    static public MTBBeftnTransRes convertMTBLBeftnInfoResponseJsonToBeftnInfoResponse(
            MTBBeftnTransResJson mtblBeftnInfoResponseJson, InWordBeftnInfo mtblInWordBeftnInfo) {
        MTBBeftnTransRes mtblBeftnInfoResponse = new MTBBeftnTransRes();
        mtblBeftnInfoResponse.setApiMsg(mtblBeftnInfoResponseJson.getApiMsg());
        mtblBeftnInfoResponse.setResCode(mtblBeftnInfoResponseJson.getResCode());
        mtblBeftnInfoResponse.setResMsg(mtblBeftnInfoResponseJson.getResMsg());
        mtblBeftnInfoResponse.setInwordbeftninfo(mtblInWordBeftnInfo);
        return mtblBeftnInfoResponse;
    }

    static public MTBBeftnStatusRes convertMTBBeftnStatusResJsonToMTBBeftnStatusRes(
            MTBBeftnStatusResJson mtblBeftnResponseJson) {
        MTBBeftnStatusRes mtblBeftnResponse = new MTBBeftnStatusRes();
        mtblBeftnResponse.setLogId(mtblBeftnResponseJson.getLogId());
        mtblBeftnResponse.setLogIdSpecified(mtblBeftnResponseJson.getLogIdSpecified());
        mtblBeftnResponse.setResCode(mtblBeftnResponseJson.getResCode());
        mtblBeftnResponse.setResMsg(mtblBeftnResponseJson.getResMsg());
        return mtblBeftnResponse;
    }

    static public MTBBeftnStatusOutRes convertMTBBeftnStatusOutResJsonToMTBBeftnStatusOutResponse(
            MTBBeftnStatusOutResJson mtblBeftnOutwardResponseJson, MTBBeftnStatusRes mtblBeftnResponse) {
        MTBBeftnStatusOutRes mtblBeftnOutwardResponse = new MTBBeftnStatusOutRes();
        mtblBeftnOutwardResponse.setStatus(mtblBeftnOutwardResponseJson.getStatus());
        mtblBeftnOutwardResponse.setStatusCode(mtblBeftnOutwardResponseJson.getStatusCode());
        mtblBeftnOutwardResponse.setMessage(mtblBeftnOutwardResponseJson.getMessage());
        mtblBeftnOutwardResponse.setMTBLBeftnStatusResponse(mtblBeftnResponse);
        return mtblBeftnOutwardResponse;
    }

    static public MTBtoMTBTransReq convertMTBtoMTBFundTransferRequestJsonToMTBtoMTBFundTransferRequest(
            MTBtoMTBTransReqJson mtbtoMTBFundTransferRequestJson) {
        MTBtoMTBTransReq mtbtoMTBFundTransferRequest = new MTBtoMTBTransReq();
        mtbtoMTBFundTransferRequest.setAmount(mtbtoMTBFundTransferRequestJson.getAmount());
        mtbtoMTBFundTransferRequest.setCreditAccount(mtbtoMTBFundTransferRequestJson.getCreditAccount());
        mtbtoMTBFundTransferRequest.setDebitAccount(mtbtoMTBFundTransferRequestJson.getDebitAccount());
        mtbtoMTBFundTransferRequest.setRemarks(mtbtoMTBFundTransferRequestJson.getRemarks());
        mtbtoMTBFundTransferRequest.setTransactionChannelId(mtbtoMTBFundTransferRequestJson.getTransactionChannelId());
        mtbtoMTBFundTransferRequest.setUniqueTxnId(mtbtoMTBFundTransferRequestJson.getUniqueTxnId());

        return mtbtoMTBFundTransferRequest;
    }

    static public MTBBeftnTransReq convertMTBLBeftnInfoRequestJsonToMTBLBeftnInfoRequest(
            MTBBeftnTransReqJson mtblBeftnInfoRequestJson) {
        MTBBeftnTransReq mtblBeftnInfoRequest = new MTBBeftnTransReq();
        mtblBeftnInfoRequest.setAmount(mtblBeftnInfoRequestJson.getAmount());
        mtblBeftnInfoRequest.setChannelId(mtblBeftnInfoRequestJson.getChannelId());
        mtblBeftnInfoRequest.setDfiAccountno(mtblBeftnInfoRequestJson.getDfiAccountno());
        mtblBeftnInfoRequest.setDscrptn(mtblBeftnInfoRequestJson.getDscrptn());
        mtblBeftnInfoRequest.setEftTrnsType(mtblBeftnInfoRequestJson.getEftTrnsType());
        mtblBeftnInfoRequest.setReceiveBank(mtblBeftnInfoRequestJson.getReceiveBank());
        mtblBeftnInfoRequest.setReceiverName(mtblBeftnInfoRequestJson.getReceiverName());
        mtblBeftnInfoRequest.setUniqueID(mtblBeftnInfoRequestJson.getUniqueID());

        return mtblBeftnInfoRequest;
    }

    static public MTBBeftnStatusReq convertMTBLBeftnStatusRequestJsonToMTBLBeftnStatusRequest(
            MTBBeftnStatusReqJson mtblBeftnStatusRequestJson) {
        MTBBeftnStatusReq mtblBeftnStatusRequest = new MTBBeftnStatusReq();
        mtblBeftnStatusRequest.setChannelId(mtblBeftnStatusRequestJson.getChannelId());
        mtblBeftnStatusRequest.setFromDate(mtblBeftnStatusRequestJson.getFromDate());
        mtblBeftnStatusRequest.setToDate(mtblBeftnStatusRequestJson.getToDate());
        mtblBeftnStatusRequest.setTraceNo(mtblBeftnStatusRequestJson.getTraceNo());

        return mtblBeftnStatusRequest;
    }

    static public MTBOnlineStmtReq convertMTBLStmtRequestJsonToMTBLStmtRequest(
            MTBOnlineStmtReqJson mtblOnlineStatusRequestJson) {
        MTBOnlineStmtReq mtblOnlineStatusRequest = new MTBOnlineStmtReq();
        mtblOnlineStatusRequest.setAccNo(mtblOnlineStatusRequestJson.getAccNo());
        mtblOnlineStatusRequest.setChannelId(mtblOnlineStatusRequestJson.getChannelId());
        mtblOnlineStatusRequest.setFromDate(mtblOnlineStatusRequestJson.getFromDate());
        mtblOnlineStatusRequest.setNooftrn(mtblOnlineStatusRequestJson.getNooftrn());
        mtblOnlineStatusRequest.setToDate(mtblOnlineStatusRequestJson.getToDate());

        return mtblOnlineStatusRequest;
    }

    static public MTBOnlineStmtRes convertMTBLOnlineStmtResJsonToMTBLOnlineStmtRes(
            MTBOnlineStmtResJson mtblOnlineStatementResponseJson) {
        MTBOnlineStmtRes mtblOnlineStatementResponse = new MTBOnlineStmtRes();
        mtblOnlineStatementResponse.setNoOfRows(mtblOnlineStatementResponseJson.getNoOfRows());
        mtblOnlineStatementResponse.setNoOfRowsSpecified(mtblOnlineStatementResponseJson.getNoOfRowsSpecified());
        mtblOnlineStatementResponse.setResCode(mtblOnlineStatementResponseJson.getResCode());
        mtblOnlineStatementResponse.setResMsg(mtblOnlineStatementResponseJson.getResMsg());

        return mtblOnlineStatementResponse;
    }

    static public MTBOnlineStmtTrnxList convertMTBLStmtTrnListJsonToMTBLStmtTrnList(
            MTBOnlineStmtTrnxListJson mtblOnlineStatementTransactionListJson) {
        MTBOnlineStmtTrnxList mtblOnlineStatementTransactionList = new MTBOnlineStmtTrnxList();
        mtblOnlineStatementTransactionList.setCurrencyName(mtblOnlineStatementTransactionListJson.getCurrencyName());
        mtblOnlineStatementTransactionList
                .setCurrentBalance(mtblOnlineStatementTransactionListJson.getCurrentBalance());
        mtblOnlineStatementTransactionList.setDeposit(mtblOnlineStatementTransactionListJson.getDeposit());
        mtblOnlineStatementTransactionList.setDescription(mtblOnlineStatementTransactionListJson.getDescription());
        mtblOnlineStatementTransactionList.setNarration(mtblOnlineStatementTransactionListJson.getNarration());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String transactionDateStr = dateFormat.format(mtblOnlineStatementTransactionListJson.getTransactionDate());
        mtblOnlineStatementTransactionList.setTransactionDate(transactionDateStr);
        mtblOnlineStatementTransactionList
                .setTransactionType(mtblOnlineStatementTransactionListJson.getTransactionType());
        mtblOnlineStatementTransactionList.setWithdrawal(mtblOnlineStatementTransactionListJson.getWithdrawal());

        return mtblOnlineStatementTransactionList;
    }
}
