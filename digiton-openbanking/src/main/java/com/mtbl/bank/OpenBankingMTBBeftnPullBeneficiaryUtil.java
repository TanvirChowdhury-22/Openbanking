package com.mtbl.bank;

import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class OpenBankingMTBBeftnPullBeneficiaryUtil {
    public static String RES_CODE = "resCode";
    public static String RES_MSG = "resMsg";

    public static MTBBeftnPullBeneficiaryResJson processBeftnPullBeneficiary(String resp) {
        MTBBeftnPullBeneficiaryResJson beftnPullBeneficiaryResponse = new MTBBeftnPullBeneficiaryResJson();
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(resp);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey().equals(RES_CODE)) {
                beftnPullBeneficiaryResponse.setResCode(entry.getValue().toString());
            } else if (entry.getKey().equals(RES_MSG)) {
                beftnPullBeneficiaryResponse.setResMsg(entry.getValue().toString());
            }
        }
        return beftnPullBeneficiaryResponse;
    }
}
