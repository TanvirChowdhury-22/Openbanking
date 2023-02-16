package com.mtbl.bank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class OpenBankingMTBStatementUtil {
    public static String NO_OF_ROWS = "noOfRows";
    public static String NO_OF_ROWS_SPECIFIED = "noOfRowsSpecified";
    public static String RES_CODE = "resCode";
    public static String RES_MSG = "resMsg";
    public static String CURRENCY_NAME = "currencyName";
    public static String CURRENT_BALANCE = "currentBalance";
    public static String DEPOSIT = "deposit";
    public static String DESCRIPTION = "description";
    public static String NARRATION = "narration";
    public static String TRANSACTION_DATE = "transactionDate";
    public static String TRANSACTION_TYPE = "transactionType";
    public static String WITHDRAWAL = "withdrawal";
    public static String TRANSACTION_LIST = "transactionlist";

    public static MTBOnlineStmtResJson processStatement(String resp) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(resp);
        MTBOnlineStmtResJson statementResponse = new MTBOnlineStmtResJson();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String jsonKey = entry.getKey();

            if (jsonKey.equals(NO_OF_ROWS)) {
                String jsonValue = entry.getValue().toString();
                statementResponse.setNoOfRows(jsonValue);
            } else if (jsonKey.equals(NO_OF_ROWS_SPECIFIED)) {
                String jsonValue = entry.getValue().toString();
                statementResponse.setNoOfRowsSpecified(jsonValue);
            } else if (jsonKey.equals(RES_CODE)) {
                String jsonValue = entry.getValue().toString();
                statementResponse.setResCode(jsonValue);
            } else if (jsonKey.equals(RES_MSG)) {
                String jsonValue = entry.getValue().toString();
                statementResponse.setResMsg(jsonValue);
            } else if (jsonKey.equals(TRANSACTION_LIST)) {
                List<MTBOnlineStmtTrnxListJson> transactionListEntry = new ArrayList<MTBOnlineStmtTrnxListJson>();
                if (entry.getValue() != null) {
                    @SuppressWarnings("unchecked")
                    List<Object> list = (List<Object>) entry.getValue();
                    transactionListEntry = processStatementDataObject(list);
                }
                statementResponse.setTransactionList(transactionListEntry);
            }
        }

        return statementResponse;
    }

    public static List<MTBOnlineStmtTrnxListJson> processStatementDataObject(List<Object> list) {
        List<MTBOnlineStmtTrnxListJson> transactionListEntry = new ArrayList<MTBOnlineStmtTrnxListJson>();
        for (Object object : list) {
            if (object instanceof Map) {
                MTBOnlineStmtTrnxListJson transactionList = new MTBOnlineStmtTrnxListJson();
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String jsonKey = entry.getKey();
                    String jsonValue = entry.getValue().toString();
                    if (jsonKey.equals(CURRENCY_NAME)) {
                        transactionList.setCurrencyName(jsonValue);
                    } else if (jsonKey.equals(CURRENT_BALANCE)) {
                        transactionList.setCurrentBalance(jsonValue);
                    } else if (jsonKey.equals(DEPOSIT)) {
                        transactionList.setDeposit(jsonValue);
                    } else if (jsonKey.equals(DESCRIPTION)) {
                        transactionList.setDescription(jsonValue);
                    } else if (jsonKey.equals(NARRATION)) {
                        transactionList.setNarration(jsonValue);
                    } else if (jsonKey.equals(TRANSACTION_DATE)) {
                        try {
                            Date transactionDate;
                            transactionDate = new SimpleDateFormat("dd/MM/yy").parse(jsonValue);
                            transactionList.setTransactionDate(transactionDate);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else if (jsonKey.equals(TRANSACTION_TYPE)) {
                        transactionList.setTransactionType(jsonValue);
                    } else if (jsonKey.equals(WITHDRAWAL)) {
                        transactionList.setWithdrawal(jsonValue);
                    }
                }
                transactionListEntry.add(transactionList);
            }
        }
        return transactionListEntry;
    }
}
