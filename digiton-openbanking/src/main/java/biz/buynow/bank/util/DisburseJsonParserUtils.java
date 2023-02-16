package biz.buynow.bank.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import com.mtbl.bank.MTBtoMTBTransResJson;

import biz.buynow.bank.model.DisburseRequestJson;
import biz.buynow.bank.model.RecipientUserJson;
import biz.buynow.bank.model.UserBankJson;

public class DisburseJsonParserUtils {
    public static String CLIENT_REF = "clientRef";
    public static String DATA_SECURITY = "dataSecurity";
    public static String START_DATE = "startDate";
    public static String END_DATE = "endDate";
    public static String RECUR_FREQ = "recurFreq";
    public static String RECUR_DATE = "recurDate";
    public static String DATA = "data";
    public static String ACC_HOLDERS_NAME = "accountHoldersName";
    public static String USER_ID = "userId";
    public static String AMOUNT = "amount";
    public static String COMMENTS = "comments";
    public static String CURRENCY = "currency";
    public static String BANK_DATA = "bankData";
    public static String BANK_ACC_NO = "bankAccNo";
    public static String BANK_NAME = "bankName";
    public static String BANK_BRANCH = "bankBranch";
    public static String BANK_ROUTING = "bankRouting";
    public static String CBS_JOURNAL_NO = "cbsJournalNo";
    public static String CBS_TXN_ID = "cbsTxnID";
    public static String CBS_CODE = "cbsCode";
    public static String CBS_RES_DATE = "cbsResDate";
    public static String RES_CODE = "resCode";
    public static String RES_MSG = "resMsg";
    public static String LOG_ID = "logId";

    public static DisburseRequestJson processClient(String jsonString) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(jsonString);
        DisburseRequestJson client = new DisburseRequestJson();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String jsonKey = entry.getKey();
            if (jsonKey.equals(CLIENT_REF)) {
                String jsonValue = entry.getValue().toString();
                client.setClientRef(jsonValue);
            } else if (jsonKey.equals(DATA_SECURITY)) {
                String jsonValue = entry.getValue().toString();
                client.setDataSecurity(jsonValue);
            } else if (jsonKey.equals(START_DATE)) {
                String jsonValue = entry.getValue().toString();
                client.setStartDate(jsonValue);
            } else if (jsonKey.equals(END_DATE)) {
                String jsonValue = entry.getValue().toString();
                client.setEndDate(jsonValue);
            } else if (jsonKey.equals(RECUR_FREQ)) {
                if (map.containsKey(START_DATE) && !map.get(START_DATE).toString().trim().isEmpty()
                        && map.containsKey(END_DATE) && !map.get(END_DATE).toString().trim().isEmpty()) {
                    String jsonValue = entry.getValue().toString();
                    client.setRecurFreq(jsonValue);
                }
            } else if (jsonKey.equals(RECUR_DATE)) {
                if (map.containsKey(START_DATE) && !map.get(START_DATE).toString().trim().isEmpty()
                        && map.containsKey(END_DATE) && !map.get(END_DATE).toString().trim().isEmpty()) {
                    String jsonValue = entry.getValue().toString();
                    client.setRecurDate(jsonValue);
                }
            } else if (jsonKey.equals(DATA)) {
                @SuppressWarnings("unchecked")
                List<Object> list = (List<Object>) entry.getValue();
                List<RecipientUserJson> clientDataList = processClientDataObject(list);
                client.setRecipientUserJsonList(clientDataList);
            }
        }
        return client;
    }

    public static MTBtoMTBTransResJson processMTBtoMTBResponse(String jsonString) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(jsonString);
        MTBtoMTBTransResJson client = new MTBtoMTBTransResJson();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String jsonKey = entry.getKey();
            if (jsonKey.equals(CBS_JOURNAL_NO)) {
                String jsonValue = entry.getValue().toString();
                client.setCbsJournalNo(jsonValue);
            } else if (jsonKey.equals(CBS_TXN_ID)) {
                String jsonValue = entry.getValue().toString();
                client.setCbsTxnID(jsonValue);
            } else if (jsonKey.equals(CBS_CODE)) {
                String jsonValue = entry.getValue().toString();
                client.setCbsCode(jsonValue);
            } else if (jsonKey.equals(CBS_RES_DATE)) {
                String jsonValue = entry.getValue().toString();
                client.setCbsResDate(jsonValue);
            } else if (jsonKey.equals(RES_CODE)) {
                String jsonValue = entry.getValue().toString();
                client.setResCode(jsonValue);
            } else if (jsonKey.equals(RES_MSG)) {
                String jsonValue = entry.getValue().toString();
                client.setResMsg(jsonValue);
            } else if (jsonKey.equals(LOG_ID)) {
                String jsonValue = entry.getValue().toString();
                client.setLogId(jsonValue);
            }
        }
        return client;
    }

    public static List<RecipientUserJson> processClientDataObject(List<Object> list) {
        List<RecipientUserJson> clientDataList = new ArrayList<RecipientUserJson>();
        for (Object object : list) {
            if (object instanceof Map) {
                RecipientUserJson clientDataEntry = new RecipientUserJson();
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String jsonKey = entry.getKey();

                    if (jsonKey.equals(USER_ID)) {
                        String jsonValue = entry.getValue().toString();
                        clientDataEntry.setUserId(jsonValue);
                    } else if (jsonKey.equals(AMOUNT)) {
                        String jsonValue = entry.getValue().toString();
                        clientDataEntry.setAmount(jsonValue);
                    } else if (jsonKey.equals(COMMENTS)) {
                        String jsonValue = entry.getValue().toString();
                        clientDataEntry.setComments(jsonValue);
                    } else if (jsonKey.equals(CURRENCY)) {
                        String jsonValue = entry.getValue().toString();
                        clientDataEntry.setCurrency(jsonValue);
                    } else if (jsonKey.equals(BANK_DATA)) {
                        @SuppressWarnings("unchecked")
                        List<Object> data = (List<Object>) entry.getValue();
                        List<UserBankJson> bankDataList = processBankDataObject(data);
                        clientDataEntry.setUserBankJsonList(bankDataList);
                    }
                }
                clientDataList.add(clientDataEntry);
            }
        }
        return clientDataList;
    }

    public static List<UserBankJson> processBankDataObject(List<Object> list) {
        List<UserBankJson> bankDataList = new ArrayList<UserBankJson>();
        for (Object object : list) {
            if (object instanceof Map) {
                UserBankJson bankDataEntry = new UserBankJson();
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String jsonKey = entry.getKey();
                    String jsonValue = entry.getValue().toString();
                    if (jsonKey.equals(BANK_ACC_NO)) {
                        bankDataEntry.setBankAccNo(jsonValue);
                    } else if (jsonKey.equals(ACC_HOLDERS_NAME)) {
                        bankDataEntry.setAccountHoldersName(jsonValue);
                    } else if (jsonKey.equals(BANK_NAME)) {
                        bankDataEntry.setBankName(jsonValue);
                    } else if (jsonKey.equals(BANK_BRANCH)) {
                        bankDataEntry.setBankBranch(jsonValue);
                    } else if (jsonKey.equals(BANK_ROUTING)) {
                        bankDataEntry.setBankRouting(jsonValue);
                    }
                }
                bankDataList.add(bankDataEntry);
            }
        }
        return bankDataList;
    }
}
