package com.mtbl.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class OpenBankingMTBBeftnStatusUtil {

    public static String COMP_ACCOUNT_NUM = "compAccountNum";
    public static String COMP_ID = "compId";
    public static String COMP_NAME = "compName";
    public static String COMP_ENTRY_DESC = "compEntryDesc";
    public static String CHANNEL_ID = "channelId";
    public static String DFI_ACCOUNT_INFO = "dfiAccountno";
    public static String AMOUNT = "amount";
    public static String AMOUNT_SPECIFIED = "amountSpecified";

    public static String RECIEVER_NAME = "receiverName";
    public static String INDIVIDUAL_ID = "individualId";
    public static String RECIEVE_BANK = "receive_bank";
    public static String ORIGINAL_TRACE_NUMBER = "originalTraceNumber";
    public static String FILE_GENERATED = "fileGenerated";
    public static String BB_ACKNOLEDGED = "BBAcknoledged";
    public static String TRAN_DATE = "tranDate";
    public static String RETURN_STAT = "returnStat";
    public static String RETURN_DATE = "returnDate";
    public static String RET_REASON = "retReason";

    public static String LOG_ID = "logId";
    public static String LOG_ID_SPECIFIED = "logIdSpecified";
    public static String STATUS = "status";
    public static String STATUS_CODE = "statusCode";
    public static String MESSAGE = "message";
    public static String RES_CODE = "resCode";
    public static String RES_MSG = "resMsg";
    public static String BEFTN_OUTWARD_RESPONSE = "beftnOutwardResponse";
    public static String DATA = "data";

    public static MTBBeftnStatusResJson processMtbBeftnStatusRes(String resp) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(resp);
        MTBBeftnStatusResJson beftnResponse = new MTBBeftnStatusResJson();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String jsonKey = entry.getKey();
            if (jsonKey.equals(BEFTN_OUTWARD_RESPONSE)) {
                @SuppressWarnings("unchecked")
                Map<String, Object> mapData = (Map<String, Object>) entry.getValue();
                MTBBeftnStatusOutResJson beftnOutwardResponse = beftnOutwardResponse(mapData);
                beftnResponse.setBeftnOutwardResponse(beftnOutwardResponse);
            } else if (jsonKey.equals(LOG_ID)) {
                String jsonValue = entry.getValue().toString();
                beftnResponse.setLogId(jsonValue);
            } else if (jsonKey.equals(LOG_ID_SPECIFIED)) {
                String jsonValue = entry.getValue().toString();
                beftnResponse.setLogIdSpecified(jsonValue);
            } else if (jsonKey.equals(RES_CODE)) {
                String jsonValue = entry.getValue().toString();
                beftnResponse.setResCode(jsonValue);
            } else if (jsonKey.equals(RES_MSG)) {
                String jsonValue = entry.getValue().toString();
                beftnResponse.setResMsg(jsonValue);
            }
        }
        return beftnResponse;
    }

    public static MTBBeftnStatusOutResJson beftnOutwardResponse(Map<String, Object> mapData) {
        MTBBeftnStatusOutResJson beftnOutwardResponse = new MTBBeftnStatusOutResJson();

        for (Map.Entry<String, Object> entry : mapData.entrySet()) {
            String jsonKey = entry.getKey();
            if (jsonKey.equals(STATUS)) {
                String jsonValue = entry.getValue().toString();
                beftnOutwardResponse.setStatus(jsonValue);
            } else if (jsonKey.equals(STATUS_CODE)) {
                String jsonValue = entry.getValue().toString();
                beftnOutwardResponse.setStatusCode(jsonValue);
            } else if (jsonKey.equals(MESSAGE)) {
                if (entry.getValue() != null) {
                    String jsonValue = entry.getValue().toString();
                    beftnOutwardResponse.setMessage(jsonValue);
                }
            } else if (jsonKey.equals(DATA)) {
                @SuppressWarnings("unchecked")
                List<Object> list = (List<Object>) entry.getValue();
                List<MTBBeftnStatusOutDataResJson> beftnResponseDataList = processBeftnDataObject(list);
                beftnOutwardResponse.setBeftnResponseDataJsonList(beftnResponseDataList);
            }
        }
        return beftnOutwardResponse;
    }

    public static List<MTBBeftnStatusOutDataResJson> processBeftnDataObject(List<Object> list) {
        List<MTBBeftnStatusOutDataResJson> beftnResponseDataList = new ArrayList<MTBBeftnStatusOutDataResJson>();
        for (Object object : list) {
            if (object instanceof Map) {
                MTBBeftnStatusOutDataResJson beftnResponseDataEntry = new MTBBeftnStatusOutDataResJson();
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String jsonKey = entry.getKey();
                    String jsonValue = entry.getValue().toString();
                    if (jsonKey.equals(COMP_ACCOUNT_NUM)) {
                        beftnResponseDataEntry.setCompAccountNum(jsonValue);
                    } else if (jsonKey.equals(COMP_ID)) {
                        beftnResponseDataEntry.setCompId(jsonValue);
                    } else if (jsonKey.equals(COMP_NAME)) {
                        beftnResponseDataEntry.setCompName(jsonValue);
                    } else if (jsonKey.equals(COMP_ENTRY_DESC)) {
                        beftnResponseDataEntry.setCompEntryDesc(jsonValue);
                    } else if (jsonKey.equals(CHANNEL_ID)) {
                        beftnResponseDataEntry.setChannelId(jsonValue);
                    } else if (jsonKey.equals(DFI_ACCOUNT_INFO)) {
                        beftnResponseDataEntry.setDfiAccountNo(jsonValue);
                    } else if (jsonKey.equals(AMOUNT)) {
                        beftnResponseDataEntry.setAmount(jsonValue);
                    } else if (jsonKey.equals(AMOUNT_SPECIFIED)) {
                        beftnResponseDataEntry.setAmountSpecified(jsonValue);
                    } else if (jsonKey.equals(RECIEVER_NAME)) {
                        beftnResponseDataEntry.setReceiverName(jsonValue);
                    } else if (jsonKey.equals(INDIVIDUAL_ID)) {
                        beftnResponseDataEntry.setIndividualId(jsonValue);
                    } else if (jsonKey.equals(RECIEVE_BANK)) {
                        beftnResponseDataEntry.setReceive_bank(jsonValue);
                    } else if (jsonKey.equals(ORIGINAL_TRACE_NUMBER)) {
                        beftnResponseDataEntry.setOriginalTraceNumber(jsonValue);
                    } else if (jsonKey.equals(FILE_GENERATED)) {
                        beftnResponseDataEntry.setFileGenerated(jsonValue);
                    } else if (jsonKey.equals(BB_ACKNOLEDGED)) {
                        beftnResponseDataEntry.setBbAcknoledged(jsonValue);
                    } else if (jsonKey.equals(TRAN_DATE)) {
                        beftnResponseDataEntry.setTranDate(jsonValue);
                    } else if (jsonKey.equals(RETURN_STAT)) {
                        beftnResponseDataEntry.setReturnStat(jsonValue);
                    } else if (jsonKey.equals(RETURN_DATE)) {
                        beftnResponseDataEntry.setReturnDate(jsonValue);
                    } else if (jsonKey.equals(RET_REASON)) {
                        beftnResponseDataEntry.setRetReason(jsonValue);
                    }
                }
                beftnResponseDataList.add(beftnResponseDataEntry);
            }
        }
        return beftnResponseDataList;
    }
}
