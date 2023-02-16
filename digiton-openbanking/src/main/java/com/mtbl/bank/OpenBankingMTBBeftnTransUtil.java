package com.mtbl.bank;

import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class OpenBankingMTBBeftnTransUtil {
    public static String RES_CODE = "resCode";
    public static String API_MSG = "apiMsg";
    public static String RES_MSG = "resMsg";
    public static String IN_WORD_BEFTN_INFO = "inwordbeftninfo";
    public static String CBSF_TRN_NUM = "cbsftrnNum";
    public static String CBSF_JOURNAL_NO = "cbsfjournalNo";
    public static String EDR_TRACE_NO = "edrTraceno";
    public static String MSG = "msg";
    public static String MSG_CODE = "msgCode";
    public static String ORIGINAL_TRACE_NO = "originalTraceno";
    public static String STLM_DATE = "stlmDate";

    public static MTBBeftnTransResJson processBeftnInfo(String resp) {
        MTBBeftnTransResJson beftnInfoResponse = new MTBBeftnTransResJson();
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(resp);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String jsonKey = entry.getKey();
            if (jsonKey.equals(RES_CODE)) {
                String jsonValue = entry.getValue().toString();
                beftnInfoResponse.setResCode(jsonValue);
            } else if (jsonKey.equals(IN_WORD_BEFTN_INFO)) {
                @SuppressWarnings("unchecked")
                Map<String, Object> mapData = (Map<String, Object>) entry.getValue();
                MTBInWordBeftnInfoJson inwordbeftninfo = inWordBeftnInfo(mapData);
                beftnInfoResponse.setInwordbeftninfo(inwordbeftninfo);
            } else if (jsonKey.equals(API_MSG)) {
                String jsonValue = entry.getValue().toString();
                beftnInfoResponse.setApiMsg(jsonValue);
            } else if (jsonKey.equals(RES_MSG)) {
                String jsonValue = entry.getValue().toString();
                beftnInfoResponse.setResMsg(jsonValue);
            }
        }
        return beftnInfoResponse;
    }

    private static MTBInWordBeftnInfoJson inWordBeftnInfo(Map<String, Object> mapData) {
        MTBInWordBeftnInfoJson inWordBeftnInfo = new MTBInWordBeftnInfoJson();
        for (Map.Entry<String, Object> entry : mapData.entrySet()) {
            String jsonKey = entry.getKey();
            if (jsonKey.equals(CBSF_JOURNAL_NO)) {
                String jsonValue = entry.getValue().toString();
                inWordBeftnInfo.setCbsfjournalNo(jsonValue);
            } else if (jsonKey.equals(CBSF_TRN_NUM)) {
                String jsonValue = entry.getValue().toString();
                inWordBeftnInfo.setCbsftrnNum(jsonValue);
            } else if (jsonKey.equals(EDR_TRACE_NO)) {
                String jsonValue = entry.getValue().toString();
                inWordBeftnInfo.setEdrTraceno(jsonValue);
            } else if (jsonKey.equals(MSG)) {
                String jsonValue = entry.getValue().toString();
                inWordBeftnInfo.setMsg(jsonValue);
            } else if (jsonKey.equals(MSG_CODE)) {
                String jsonValue = entry.getValue().toString();
                inWordBeftnInfo.setMsgCode(jsonValue);
            } else if (jsonKey.equals(ORIGINAL_TRACE_NO)) {
                String jsonValue = entry.getValue().toString();
                inWordBeftnInfo.setOriginalTraceno(jsonValue);
            } else if (jsonKey.equals(STLM_DATE)) {
                String jsonValue = entry.getValue().toString();
                inWordBeftnInfo.setStlmDate(jsonValue);
            }
        }
        return inWordBeftnInfo;
    }

}
