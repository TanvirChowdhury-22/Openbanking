package biz.buynow.bank;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.mtbl.bank.MTBBeftnTransReqJson;
import com.mtbl.bank.MTBBeftnTransResJson;
import com.mtbl.bank.MTBBeftnStatusOutResJson;
import com.mtbl.bank.MTBBeftnPullBeneficiaryReqJson;
import com.mtbl.bank.MTBBeftnPullBeneficiaryResJson;
import com.mtbl.bank.MTBBeftnStatusOutDataResJson;
import com.mtbl.bank.MTBBeftnStatusResJson;
import com.mtbl.bank.MTBBeftnStatusReqJson;
import com.mtbl.bank.MTBInWordBeftnInfoJson;
import com.mtbl.bank.MTBOnlineStmtResJson;
import com.mtbl.bank.MTBOnlineStmtTrnxListJson;
import com.mtbl.bank.MTBOnlineStmtReqJson;
import com.mtbl.bank.MTBtoMTBTransReqJson;
import com.mtbl.bank.MTBtoMTBTransResJson;
import com.mtbl.bank.OpenBankingMTBBeftnTransUtil;
import com.mtbl.bank.OpenBankingMTBBeftnPullBeneficiaryUtil;
import com.mtbl.bank.OpenBankingMTBBeftnStatusUtil;
import com.mtbl.bank.OpenBankingMTBStatementUtil;

import biz.buynow.bank.util.DisburseJsonParserUtils;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class MTBLOpenbankingTests {

    @Test
    void contextLoads() {
    }

    @Test
    void beftnPullBeneficiaryResponseTest() {
        String inputJsonForPullBeneficiaryResponse = "{ " + "\"resCode\": \"000\"," + "\"resMsg\": \"SUCCESS\"" + "}";
        MTBBeftnPullBeneficiaryResJson mtblBeftnPullBeneficiaryResponse = OpenBankingMTBBeftnPullBeneficiaryUtil
                .processBeftnPullBeneficiary(inputJsonForPullBeneficiaryResponse);
        System.out.println("test*" + mtblBeftnPullBeneficiaryResponse.getResMsg());
        Assert.isTrue(mtblBeftnPullBeneficiaryResponse.getResMsg().equals("SUCCESS"), "ResMsg mismatch");
        Assert.isTrue(mtblBeftnPullBeneficiaryResponse.getResCode().equals("000"), "ResCode mismatch");
    }

    @Test
    void beftnPullBeneficiaryRequestTest() {
        MTBBeftnPullBeneficiaryReqJson beftnPullBeneficiaryRequest = new MTBBeftnPullBeneficiaryReqJson("1271050021443",
                "090060289", "Md. Jahirul I", "Pathao");
        System.out.println("test*" + beftnPullBeneficiaryRequest.getReceiverName());
        System.out.println("test*" + beftnPullBeneficiaryRequest.getReceiveBank());

        Assert.isTrue(beftnPullBeneficiaryRequest.getDfiAccountno().equals("1271050021443"),
                "Credit Account No mismatch");
        Assert.isTrue(beftnPullBeneficiaryRequest.getReceiverName().equals("Md. Jahirul I"), "ReceiverName mismatch");
        Assert.isTrue(beftnPullBeneficiaryRequest.getReceiveBank().equals("090060289"), "ReceiveBank mismatch");
        Assert.isTrue(beftnPullBeneficiaryRequest.getChannelId().equals("Pathao"), "Transaction Channel mismatch");
    }

    @Test
    void beftnInfoRequestTest() {
        MTBBeftnTransReqJson beftnInfoRequest = new MTBBeftnTransReqJson("PUSH", "1.00", "1271050021443",
                "Pathao fund trunsfer", "090060289", "Md. Jahirul I", "Pathao", "2324960011580236");
        System.out.println("test*" + beftnInfoRequest.getEftTrnsType());
        System.out.println("test*" + beftnInfoRequest.getAmount());

        Assert.isTrue(beftnInfoRequest.getEftTrnsType().equals("PUSH"), "Transaction Type mismatch");
        Assert.isTrue(beftnInfoRequest.getAmount().equals("1.00"), "Amount mismatch");
        Assert.isTrue(beftnInfoRequest.getDfiAccountno().equals("1271050021443"), "Credit Account No mismatch");
        Assert.isTrue(beftnInfoRequest.getDscrptn().equals("Pathao fund trunsfer"), "Description mismatch");
        Assert.isTrue(beftnInfoRequest.getReceiveBank().equals("090060289"), "ReceiveBank mismatch");
        Assert.isTrue(beftnInfoRequest.getChannelId().equals("Pathao"), "Transaction Channel mismatch");
        Assert.isTrue(beftnInfoRequest.getReceiverName().equals("Md. Jahirul I"), "ReceiverName mismatch");
        Assert.isTrue(beftnInfoRequest.getUniqueID().equals("2324960011580236"), "UniqueId mismatch");
    }

    @Test
    @Disabled
    void beftnInfoResponseTest() {
        String inputJsonForBeftnInfoResponse = "{" + "        \"resCode\": \"001\","
                + "        \"resMsg\": \"Refund Successful\","
                + "        \"apiMsg\": \"Duplicate message or Unique Id\"," + "        \"inwordbeftninfo\": {"
                + "                \"msgCode\": \"13\","
                + "                \"msg\": \"Duplicate message or Unique Id\","
                + "                \"edrTraceno\": \"\"," + "                \"originalTraceno\": \"\","
                + "                \"st1mDate\": \"\"," + "                \"cbsfjournalNo\": \"755825\","
                + "                \"cbsftrnNum\": \"34038\"" + "        }" + "}";
        MTBBeftnTransResJson mtblBeftnInfoResponse = OpenBankingMTBBeftnTransUtil
                .processBeftnInfo(inputJsonForBeftnInfoResponse);
        System.out.println("test*" + mtblBeftnInfoResponse.getResMsg());
        Assert.isTrue(mtblBeftnInfoResponse.getResMsg().equals("Refund Successful"), "ResMsg mismatch");
        Assert.isTrue(mtblBeftnInfoResponse.getResCode().equals("001"), "ResCode mismatch");

        MTBInWordBeftnInfoJson mtblInWordBeftnInfo = mtblBeftnInfoResponse.getInwordbeftninfo();
        Assert.isTrue(mtblInWordBeftnInfo.getMsgCode().equals("13"), "MsgCode mismatch");
        Assert.isTrue(mtblInWordBeftnInfo.getMsg().equals("Duplicate message or Unique Id"), "Msg mismatch");
        Assert.isTrue(mtblInWordBeftnInfo.getEdrTraceno().equals(""), "EdrTraceno mismatch");
        Assert.isTrue(mtblInWordBeftnInfo.getOriginalTraceno().equals(""), "OrginalTraceno mismatch");
        Assert.isTrue(mtblInWordBeftnInfo.getStlmDate().equals(""), "St1mDate mismatch");
        Assert.isTrue(mtblInWordBeftnInfo.getCbsfjournalNo().equals("755825"), "CbsfjournalNO mismatch");
        Assert.isTrue(mtblInWordBeftnInfo.getCbsftrnNum().equals("34038"), "CbsftrnNum mismatch");
    }

    @Test
    void beftnStatusReqWithTraceIdTest() {
        MTBBeftnStatusReqJson beftnStatusRequestWithTraceId = new MTBBeftnStatusReqJson(
                "202202021452702320258800025879", "", "", "Pathao");
        Assert.isTrue(beftnStatusRequestWithTraceId.getTraceNo().equals("202202021452702320258800025879"),
                "Invalid TraceNo");
        Assert.isTrue(beftnStatusRequestWithTraceId.getFromDate().equals(""), "Invalid FromDate");
        Assert.isTrue(beftnStatusRequestWithTraceId.getToDate().equals(""), "Invalid ToDate");
        Assert.isTrue(beftnStatusRequestWithTraceId.getChannelId().equals("Pathao"), "Invalid ChannelId");
    }

    @Test
    void beftnStatusReqWithDateTest() {
        MTBBeftnStatusReqJson beftnStatusRequestWithDate = new MTBBeftnStatusReqJson("", "2022-02-24", "2022-02-24",
                "Pathao");
        Assert.isTrue(beftnStatusRequestWithDate.getTraceNo().equals(""), "Invalid TraceNo");
        Assert.isTrue(beftnStatusRequestWithDate.getFromDate().equals("2022-02-24"), "Invalid FromDate");
        Assert.isTrue(beftnStatusRequestWithDate.getToDate().equals("2022-02-24"), "Invalid ToDate");
        Assert.isTrue(beftnStatusRequestWithDate.getChannelId().equals("Pathao"), "Invalid ChannelId");
    }

    @Test
    void beftnResponseForStoppedBeftnTest() {
        String jsonStr = "{\n" + "\"logId\": 1009583,\n" + "\"beftnOutwardResponse\": {\n" + "\"status\": \"OK\",\n"
                + "\"statusCode\": \"000\",\n" + "\"data\": [\n" + "{\n" + "\"compAccountNum\": \"1301000042511\",\n"
                + "\"compId\": \"200003922\",\n" + "\"compName\": \"PKERippleLive\",\n"
                + "\"compEntryDesc\": \"REMITTANCE\",\n" + "\"channelId\": \"NRB\",\n"
                + "\"dfiAccountno\": \"20503550201091902\",\n" + "\"amount\": 29584,\n"
                + "\"receiverName\": \"MD MOFAZZAL HOSSIN\",\n" + "\"individualId\": \"1400100785721\",\n"
                + "\"receive_bank\": \"125490108\",\n" + "\"originalTraceNumber\": \"145270781844474\",\n"
                + "\"fileGenerated\": \"Y\",\n" + "\"BBAcknoledged\": \"Y\",\n"
                + "\"tranDate\": \"2021-11-07T00:00:00\",\n" + "\"returnStat\": \"returned\",\n"
                + "\"returnDate\": \"2021-11-08T00:00:00\",\n" + "\"retReason\": \"Payment Stopped\"\n" + "}\n" + "]\n"
                + "},\n" + "\"resCode\": \"000\",\n" + "\"resMsg\": \"Success\"\n" + "}\n";

        MTBBeftnStatusResJson beftnResponse = OpenBankingMTBBeftnStatusUtil.processMtbBeftnStatusRes(jsonStr);
        Assert.isTrue(beftnResponse.getLogId().equals("1009583"), "Invalid LogId");
        Assert.isTrue(beftnResponse.getResCode().equals("000"), "Invalid ResCode");
        Assert.isTrue(beftnResponse.getResMsg().equals("Success"), "Invalid ResMsg");

        MTBBeftnStatusOutResJson mtblBeftnOutwardResponse = beftnResponse.getBeftnOutwardResponse();
        Assert.isTrue(mtblBeftnOutwardResponse.getStatus().equals("OK"), "Invalid Status");
        Assert.isTrue(mtblBeftnOutwardResponse.getStatusCode().equals("000"), "Invalid StatusCode");

        List<MTBBeftnStatusOutDataResJson> beftnResponseDataList = mtblBeftnOutwardResponse
                .getBeftnResponseDataJsonList();
        Assert.isTrue(beftnResponseDataList.size() == 1, "Invalid Number of Data Counts");

        MTBBeftnStatusOutDataResJson mtblBeftnResponseDataEntry = beftnResponseDataList.get(0);
        Assert.isTrue(mtblBeftnResponseDataEntry.getCompAccountNum().equals("1301000042511"), "Invalid CompAccountNum");
        Assert.isTrue(mtblBeftnResponseDataEntry.getCompId().equals("200003922"), "Invalid CompId");
        Assert.isTrue(mtblBeftnResponseDataEntry.getCompName().equals("PKERippleLive"), "Invalid CompName");
        Assert.isTrue(mtblBeftnResponseDataEntry.getCompEntryDesc().equals("REMITTANCE"), "Invalid CompEntryDesc");
        Assert.isTrue(mtblBeftnResponseDataEntry.getChannelId().equals("NRB"), "Invalid ChannelId");
        Assert.isTrue(mtblBeftnResponseDataEntry.getDfiAccountNo().equals("20503550201091902"), "Invalid DfiAccountNo");
        Assert.isTrue(mtblBeftnResponseDataEntry.getAmount().equals("29584"), "Invalid Amount");
        Assert.isTrue(mtblBeftnResponseDataEntry.getReceiverName().equals("MD MOFAZZAL HOSSIN"),
                "Invalid ReceiverName");
        Assert.isTrue(mtblBeftnResponseDataEntry.getIndividualId().equals("1400100785721"), "Invalid IndividualId");
        Assert.isTrue(mtblBeftnResponseDataEntry.getReceive_bank().equals("125490108"), "Invalid Receive_bank");
        Assert.isTrue(mtblBeftnResponseDataEntry.getOriginalTraceNumber().equals("145270781844474"),
                "Invalid OriginalTraceNumber");
        Assert.isTrue(mtblBeftnResponseDataEntry.getFileGenerated().equals("Y"), "Invalid FileGenerated");
        Assert.isTrue(mtblBeftnResponseDataEntry.getBbAcknoledged().equals("Y"), "Invalid BbAcknoledged");
        Assert.isTrue(mtblBeftnResponseDataEntry.getTranDate().equals("2021-11-07T00:00:00"), "Invalid TranDate");
        Assert.isTrue(mtblBeftnResponseDataEntry.getReturnStat().equals("returned"), "Invalid ReturnStat");
        Assert.isTrue(mtblBeftnResponseDataEntry.getReturnDate().equals("2021-11-08T00:00:00"), "Invalid ReturnDate");
        Assert.isTrue(mtblBeftnResponseDataEntry.getRetReason().equals("Payment Stopped"), "Invalid RetReason");
    }

    @Test
    void beftnResponseForSuccessfulBeftnTest() {
        String jsonStr = "{\n" + "\"logId\": 1007944,\n" + "\"beftnOutwardResponse\": {\n" + "\"status\": \"OK\",\n"
                + "\"statusCode\": \"000\",\n" + "\"data\": [\n" + "{\n" + "\"compAccountNum\": \"0100-0210000522\",\n"
                + "\"compId\": \"100000051\",\n" + "\"compName\": \"GulfExchange\",\n"
                + "\"compEntryDesc\": \"REMITTANCE\",\n" + "\"channelId\": \"NRB\",\n"
                + "\"dfiAccountno\": \"20501890202928008\",\n" + "\"amount\": 10235.55,\n"
                + "\"receiverName\": \"md mossaddek hossen\",\n" + "\"individualId\": \"1336698\",\n"
                + "\"receive_bank\": \"125130884\",\n" + "\"fileGenerated\": \"Y\",\n" + "\"BBAcknoledged\": \"Y\",\n"
                + "\"tranDate\": \"2021-10-04T00:00:00\",\n" + "\"returnStat\": \"not returned\"\n" + "}\n" + "]\n"
                + "},\n" + "\"resCode\": \"000\",\n" + "\"resMsg\": \"Success\"\n" + "}\n";

        MTBBeftnStatusResJson beftnResponse = OpenBankingMTBBeftnStatusUtil.processMtbBeftnStatusRes(jsonStr);
        Assert.isTrue(beftnResponse.getLogId().equals("1007944"), "Invalid LogId");
        Assert.isTrue(beftnResponse.getResCode().equals("000"), "Invalid ResCode");
        Assert.isTrue(beftnResponse.getResMsg().equals("Success"), "Invalid ResMsg");

        MTBBeftnStatusOutResJson mtblBeftnOutwardResponse = beftnResponse.getBeftnOutwardResponse();
        Assert.isTrue(mtblBeftnOutwardResponse.getStatus().equals("OK"), "Invalid Status");
        Assert.isTrue(mtblBeftnOutwardResponse.getStatusCode().equals("000"), "Invalid StatusCode");

        List<MTBBeftnStatusOutDataResJson> beftnResponseDataList = mtblBeftnOutwardResponse
                .getBeftnResponseDataJsonList();
        Assert.isTrue(beftnResponseDataList.size() == 1, "Invalid Number of Data Counts");

        MTBBeftnStatusOutDataResJson mtblBeftnResponseDataEntry = beftnResponseDataList.get(0);
        Assert.isTrue(mtblBeftnResponseDataEntry.getCompAccountNum().equals("0100-0210000522"),
                "Invalid CompAccountNum");
        Assert.isTrue(mtblBeftnResponseDataEntry.getCompId().equals("100000051"), "Invalid CompId");
        Assert.isTrue(mtblBeftnResponseDataEntry.getCompName().equals("GulfExchange"), "Invalid CompName");
        Assert.isTrue(mtblBeftnResponseDataEntry.getCompEntryDesc().equals("REMITTANCE"), "Invalid CompEntryDesc");
        Assert.isTrue(mtblBeftnResponseDataEntry.getChannelId().equals("NRB"), "Invalid ChannelId");
        Assert.isTrue(mtblBeftnResponseDataEntry.getDfiAccountNo().equals("20501890202928008"), "Invalid DfiAccountNo");
        Assert.isTrue(mtblBeftnResponseDataEntry.getAmount().equals("10235.55"), "Invalid Amount");
        Assert.isTrue(mtblBeftnResponseDataEntry.getReceiverName().equals("md mossaddek hossen"),
                "Invalid ReceiverName");
        Assert.isTrue(mtblBeftnResponseDataEntry.getIndividualId().equals("1336698"), "Invalid IndividualId");
        Assert.isTrue(mtblBeftnResponseDataEntry.getReceive_bank().equals("125130884"), "Invalid Receive_bank");
        Assert.isTrue(mtblBeftnResponseDataEntry.getFileGenerated().equals("Y"), "Invalid FileGenerated");
        Assert.isTrue(mtblBeftnResponseDataEntry.getBbAcknoledged().equals("Y"), "Invalid BbAcknoledged");
        Assert.isTrue(mtblBeftnResponseDataEntry.getTranDate().equals("2021-10-04T00:00:00"), "Invalid TranDate");
        Assert.isTrue(mtblBeftnResponseDataEntry.getReturnStat().equals("not returned"), "Invalid ReturnStat");
    }

    @Test
    void onlineAccStatementReqTest() {
        MTBOnlineStmtReqJson onlineStatusRequest = new MTBOnlineStmtReqJson("1311000371170", "", "0", "", "Pathao");
        Assert.isTrue(onlineStatusRequest.getAccNo().equals("1311000371170"), "Invalid AccNo");
        Assert.isTrue(onlineStatusRequest.getFromDate().equals(""), "Invalid FromDate");
        Assert.isTrue(onlineStatusRequest.getNooftrn().equals("0"), "Invalid Nooftrn");
        Assert.isTrue(onlineStatusRequest.getToDate().equals(""), "Invalid ToDate");
        Assert.isTrue(onlineStatusRequest.getChannelId().equals("Pathao"), "Invalid ChannelId");
    }

    @Test
    @Disabled
    void onlineAccStatementResponseTest() {
        String jsonStr = "{\n" + "\"noOfRows\": 1,\n" + "\"noOfRowsSpecified\": true,\n" + "\"resCode\": \"000\",\n"
                + "\"resMsg\": \"Request Successful\",\n" + "\"transactionlist\": [\n" + "{\n"
                + "\"currencyName\": \"BDT\",\n" + "\"currentBalance\": \"7884.00\",\n" + "\"deposit\": \"0\",\n"
                + "\"description\": \"MB/FT/1311000371170/0460310080116/personal\",\n"
                + "\"narration\": \"Withdrawl Transfer\",\n" + "\"transactionDate\": \"01/12/21\",\n"
                + "\"transactionType\": \"DR\",\n" + "\"withdrawal\": \"5000.00\"\n" + "},\n" + "{\n"
                + "\"currencyName\": \"BDT\",\n" + "\"currentBalance\": \"7884.00\",\n" + "\"deposit\": \"0\",\n"
                + "\"description\": \"MB/FT/1311000371170/0460310080116/personal\",\n"
                + "\"narration\": \"Withdrawl Transfer\",\n" + "\"transactionDate\": \"01/12/21\",\n"
                + "\"transactionType\": \"DR\",\n" + "\"withdrawal\": \"5000.00\"\n" + "}\n" + "]\n" + "}\n";

        MTBOnlineStmtResJson statementResponse = OpenBankingMTBStatementUtil.processStatement(jsonStr);
        Assert.isTrue(statementResponse.getNoOfRows().equals("1"), "Invalid NoOfRows");
        Assert.isTrue(statementResponse.getNoOfRowsSpecified().equals("true"), "Invalid NoOfRowsSpecified");
        Assert.isTrue(statementResponse.getResCode().equals("000"), "Invalid ResCode");
        Assert.isTrue(statementResponse.getResMsg().equals("Request Successful"), "Invalid ResMsg");

        List<MTBOnlineStmtTrnxListJson> transactionListEntry = statementResponse.getTransactionList();
        Assert.isTrue(transactionListEntry.size() == 2, "Invalid Number of Data Counts");

        MTBOnlineStmtTrnxListJson mtblOnlineStatementTransactionList1 = transactionListEntry.get(0);
        Assert.isTrue(mtblOnlineStatementTransactionList1.getCurrencyName().equals("BDT"), "Invalid CurrencyName");
        Assert.isTrue(mtblOnlineStatementTransactionList1.getCurrentBalance().equals("7884.00"),
                "Invalid CurrentBalance");
        Assert.isTrue(mtblOnlineStatementTransactionList1.getDeposit().equals("0"), "Invalid Deposit");
        Assert.isTrue(mtblOnlineStatementTransactionList1.getDescription()
                .equals("MB/FT/1311000371170/0460310080116/personal"), "Invalid Description");
        Assert.isTrue(mtblOnlineStatementTransactionList1.getNarration().equals("Withdrawl Transfer"),
                "Invalid Narration");
        Assert.isTrue(mtblOnlineStatementTransactionList1.getTransactionDate().equals("01/12/21"),
                "Invalid TransactionDate");
        Assert.isTrue(mtblOnlineStatementTransactionList1.getTransactionType().equals("DR"), "Invalid TransactionType");
        Assert.isTrue(mtblOnlineStatementTransactionList1.getWithdrawal().equals("5000.00"), "Invalid Withdrawal");

        MTBOnlineStmtTrnxListJson mtblOnlineStatementTransactionList2 = transactionListEntry.get(1);
        Assert.isTrue(mtblOnlineStatementTransactionList2.getCurrencyName().equals("BDT"), "Invalid CurrencyName");
        Assert.isTrue(mtblOnlineStatementTransactionList2.getCurrentBalance().equals("7884.00"),
                "Invalid CurrentBalance");
        Assert.isTrue(mtblOnlineStatementTransactionList2.getDeposit().equals("0"), "Invalid Deposit");
        Assert.isTrue(mtblOnlineStatementTransactionList2.getDescription()
                .equals("MB/FT/1311000371170/0460310080116/personal"), "Invalid Description");
        Assert.isTrue(mtblOnlineStatementTransactionList2.getNarration().equals("Withdrawl Transfer"),
                "Invalid Narration");
        Assert.isTrue(mtblOnlineStatementTransactionList2.getTransactionDate().equals("01/12/21"),
                "Invalid TransactionDate");
        Assert.isTrue(mtblOnlineStatementTransactionList2.getTransactionType().equals("DR"), "Invalid TransactionType");
        Assert.isTrue(mtblOnlineStatementTransactionList2.getWithdrawal().equals("5000.00"), "Invalid Withdrawal");
    }

    @Test
    void onlineAccStatementErrorResponse1Test() {
        String jsonStr = "{\n" + "\"noOfRows\": 0,\n" + "\"noOfRowsSpecified\": true,\n" + "\"resCode\": \"001\",\n"
                + "\"resMsg\": \"Unauthorized Access\",\n" + "\"transactionlist\": null\n" + "}\n";

        MTBOnlineStmtResJson statementResponse = OpenBankingMTBStatementUtil.processStatement(jsonStr);
        Assert.isTrue(statementResponse.getNoOfRows().equals("0"), "Invalid NoOfRows");
        Assert.isTrue(statementResponse.getNoOfRowsSpecified().equals("true"), "Invalid NoOfRowsSpecified");
        Assert.isTrue(statementResponse.getResCode().equals("001"), "Invalid ResCode");
        Assert.isTrue(statementResponse.getResMsg().equals("Unauthorized Access"), "Invalid ResMsg");

        List<MTBOnlineStmtTrnxListJson> transactionListEntry = statementResponse.getTransactionList();
        Assert.isTrue(transactionListEntry.size() == 0, "Invalid Number of Data Counts");
    }

    @Test
    void onlineAccStatementErrorResponse2Test() {
        String jsonStr = "{\n" + "\"noOfRows\": 0,\n" + "\"noOfRowsSpecified\": true,\n" + "\"resCode\": \"002\",\n"
                + "\"resMsg\": \"Invalid Account Information\",\n" + "\"transactionlist\": null\n" + "}\n" + "";

        MTBOnlineStmtResJson statementResponse = OpenBankingMTBStatementUtil.processStatement(jsonStr);
        Assert.isTrue(statementResponse.getNoOfRows().equals("0"), "Invalid NoOfRows");
        Assert.isTrue(statementResponse.getNoOfRowsSpecified().equals("true"), "Invalid NoOfRowsSpecified");
        Assert.isTrue(statementResponse.getResCode().equals("002"), "Invalid ResCode");
        Assert.isTrue(statementResponse.getResMsg().equals("Invalid Account Information"), "Invalid ResMsg");

        List<MTBOnlineStmtTrnxListJson> transactionListEntry = statementResponse.getTransactionList();
        Assert.isTrue(transactionListEntry.size() == 0, "Invalid Number of Data Counts");
    }

    @Test
    void mtbToMtbReqTest() {
        MTBtoMTBTransReqJson mtbToMTBFundTransferRequest = new MTBtoMTBTransReqJson("1.00", "1311000476883",
                "2440100101000099", "test", "Pathao", "MTB012230220221747");
        Assert.isTrue(mtbToMTBFundTransferRequest.getAmount().equals("1.00"), "Invalid amount");
        Assert.isTrue(mtbToMTBFundTransferRequest.getCreditAccount().equals("1311000476883"), "Invalid creditAccount");
        Assert.isTrue(mtbToMTBFundTransferRequest.getDebitAccount().equals("2440100101000099"), "Invalid debitAccount");
        Assert.isTrue(mtbToMTBFundTransferRequest.getRemarks().equals("test"), "Invalid remarks");
        Assert.isTrue(mtbToMTBFundTransferRequest.getTransactionChannelId().equals("Pathao"),
                "Invalid transactionChannelId");
        Assert.isTrue(mtbToMTBFundTransferRequest.getUniqueTxnId().equals("MTB012230220221747"), "Invalid uniqueTxnId");
    }

    @Test
    void mtbToMtbResponseTest() {
        String mtbToMtbResponse = """
                {
                         "cbsJournalNo":"3150",
                         "cbsTxnID":"34038",
                         "cbsCode":"08",
                         "cbsResDate":"01-07-2022",
                         "resCode":"000",
                         "resMsg":"O.K.",
                         "logId":"1273711"
                }
                """;
        MTBtoMTBTransResJson mtbToMTBFundTransferResponse = DisburseJsonParserUtils
                .processMTBtoMTBResponse(mtbToMtbResponse);
        System.out.println("test*" + mtbToMTBFundTransferResponse.getResMsg());
        Assert.isTrue(mtbToMTBFundTransferResponse.getCbsJournalNo().equals("3150"), "cbsJournalNo mismatch");
        Assert.isTrue(mtbToMTBFundTransferResponse.getCbsTxnID().equals("34038"), "cbsTxnID mismatch");
        Assert.isTrue(mtbToMTBFundTransferResponse.getCbsCode().equals("08"), "cbsCode mismatch");
        Assert.isTrue(mtbToMTBFundTransferResponse.getCbsResDate().equals("01-07-2022"), "cbsResDate mismatch");
        Assert.isTrue(mtbToMTBFundTransferResponse.getResCode().equals("000"), "resCode mismatch");
        Assert.isTrue(mtbToMTBFundTransferResponse.getResMsg().equals("O.K."), "resMsg mismatch");
        Assert.isTrue(mtbToMTBFundTransferResponse.getLogId().equals("1273711"), "logId mismatch");
    }
}
