package biz.buynow.bank.schedulejobs;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mtbl.bank.MTBBeftnTransResJson;
import com.mtbl.bank.MTBBeftnStatusResJson;
import com.mtbl.bank.MTBOnlineStmtResJson;
import com.mtbl.bank.MTBtoMTBTransResJson;
import com.mtbl.bank.OpenBankingMTBBeftnTransUtil;
import com.mtbl.bank.OpenBankingMTBBeftnStatusUtil;
import com.mtbl.bank.OpenBankingMTBStatementUtil;

import biz.buynow.bank.constant.BusinessConstant;
import biz.buynow.bank.model.RecipientUser;
import biz.buynow.bank.model.ResponseDumpJson;
import biz.buynow.bank.repository.ResponseDumpJsonRepository;
import biz.buynow.bank.util.DisburseJsonParserUtils;
import biz.buynow.bank.util.OpenBankingUtil;

public class DisbursementRequestGenerator {

    private static String FAIL_OVER_JSON = "{\"resCode\": \"101\", \"resMsg\": \"Server Error\"}";

    static public String sendJsonRequestForBEFTNOutwardStatusCheck(String openBankingUri, String jsonToSend) {
        ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        String uri = openBankingUri + "/MTBOpenBank/checkBeftnOutwardStatus";
        RestTemplate restTemplate = OpenBankingUtil.getBearerRestTemplate();
        HttpEntity<String> request = new HttpEntity<>(jsonToSend);
        try {
            result = restTemplate.postForEntity(uri, request, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return FAIL_OVER_JSON;
        }
        printHttpBodyAndStatusCode(result);

        String json = "";
        if (result.getStatusCode() == HttpStatus.OK) {
            json = result.getBody();
        }
        return json;
    }

    static public MTBBeftnStatusResJson processJsonRequestForBEFTNOutwardStatusCheck(String json, Long requestId,
            ResponseDumpJsonRepository responseDumpJsonRepository) {
        ResponseDumpJson responseDumpJson = new ResponseDumpJson();
        responseDumpJson.setFullJson(json);
        responseDumpJson.setReqType(BusinessConstant.REQ_TYPE_JSON.BEFTN_STATUS.ordinal());
        responseDumpJson.setRequestId(requestId);
        responseDumpJsonRepository.save(responseDumpJson);

        MTBBeftnStatusResJson mtblBeftnResponseJson = OpenBankingMTBBeftnStatusUtil.processMtbBeftnStatusRes(json);
        System.out.println(mtblBeftnResponseJson.toString());
        return mtblBeftnResponseJson;
    }

    static public String sendJsonRequestForMTBLToMTBL(String openBankingUri, String jsonToPost) {
        ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        String uri = openBankingUri + "/Txn/doMTBFundTransfer";
        RestTemplate restTemplate = OpenBankingUtil.getBearerRestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(jsonToPost);
        try {
            result = restTemplate.postForEntity(uri, request, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return FAIL_OVER_JSON;
        }
        printHttpBodyAndStatusCode(result);

        String json = "";
        if (result.getStatusCode() == HttpStatus.OK) {
            json = result.getBody();
        }
        return json;
    }

    static public MTBtoMTBTransResJson processJsonRequestForMTBLToMTBL(String json, RecipientUser recipientUser,
            Long userBankId, Long requestId, ResponseDumpJsonRepository responseDumpJsonRepository) {
        ResponseDumpJson responseDumpJson = new ResponseDumpJson();
        responseDumpJson.setFullJson(json);
        responseDumpJson.setReqType(BusinessConstant.REQ_TYPE_JSON.MTB_TRANSFER.ordinal());
        responseDumpJson.setRequestId(requestId);
        responseDumpJsonRepository.save(responseDumpJson);

        MTBtoMTBTransResJson mtbtoMTBFundTransferResponseJson = DisburseJsonParserUtils.processMTBtoMTBResponse(json);
        System.out.println(mtbtoMTBFundTransferResponseJson.toString());
        return mtbtoMTBFundTransferResponseJson;
    }

    static public String sendJsonRequestForBEFTN(String openBankingUri, String jsonToSend) {
        ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        String uri = openBankingUri + "/Txn/serDoBEFTN";
        RestTemplate restTemplate = OpenBankingUtil.getBearerRestTemplate();
        HttpEntity<String> request = new HttpEntity<>(jsonToSend);
        try {
            result = restTemplate.postForEntity(uri, request, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return FAIL_OVER_JSON;
        }
        printHttpBodyAndStatusCode(result);

        String json = "";
        if (result.getStatusCode() == HttpStatus.OK) {
            json = result.getBody();
        }
        return json;
    }

    private static void printHttpBodyAndStatusCode(ResponseEntity<String> result) {
        System.out.println(result.getBody());
        System.out.println(result.getStatusCode());
    }

    static public MTBBeftnTransResJson processJsonRequestForBEFTN(String json, RecipientUser recipientUser,
            Long userBankId, Long requestId, ResponseDumpJsonRepository responseDumpJsonRepository) {

        ResponseDumpJson responseDumpJson = new ResponseDumpJson();
        responseDumpJson.setFullJson(json);
        responseDumpJson.setReqType(BusinessConstant.REQ_TYPE_JSON.BEFTN_INFO.ordinal());
        responseDumpJson.setRequestId(requestId);
        responseDumpJsonRepository.save(responseDumpJson);

        MTBBeftnTransResJson mtblBeftnInfoResponseJson = OpenBankingMTBBeftnTransUtil.processBeftnInfo(json);
        System.out.println(mtblBeftnInfoResponseJson.toString());
        return mtblBeftnInfoResponseJson;
    }

    static public String sendJsonRequestForOnlineStatement(String openBankingUri, String jsonToSend) {
        ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        String uri = openBankingUri + "/MTBOpenBank/OnlineAccountStatement";
        RestTemplate restTemplate = OpenBankingUtil.getBearerRestTemplate();
        HttpEntity<String> request = new HttpEntity<>(jsonToSend);
        try {
            result = restTemplate.postForEntity(uri, request, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return FAIL_OVER_JSON;
        }
        printHttpBodyAndStatusCode(result);

        String json = "";
        if (result.getStatusCode() == HttpStatus.OK) {
            json = result.getBody();
        }
        return json;
    }

    static public MTBOnlineStmtResJson processJsonRequestForOnlineStatement(String json, Long requestId,
            ResponseDumpJsonRepository responseDumpJsonRepository) {

        ResponseDumpJson responseDumpJson = new ResponseDumpJson();
        responseDumpJson.setFullJson(json);
        responseDumpJson.setReqType(BusinessConstant.REQ_TYPE_JSON.ONLINE_STATEMENT.ordinal());
        responseDumpJson.setRequestId(requestId);
        responseDumpJsonRepository.save(responseDumpJson);

        MTBOnlineStmtResJson mtblOnlineStatementResponseJson = OpenBankingMTBStatementUtil.processStatement(json);
        System.out.println(mtblOnlineStatementResponseJson.toString());

        return mtblOnlineStatementResponseJson;
    }
}
