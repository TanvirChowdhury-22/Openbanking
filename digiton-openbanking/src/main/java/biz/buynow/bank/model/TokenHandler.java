package biz.buynow.bank.model;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import biz.buynow.bank.constant.BusinessConstant;
import biz.buynow.bank.util.OpenBankingUtil;

public class TokenHandler {
    private static Long MIN_TOKEN_REQ_INTERVAL = 15L; // in case of any failure we wont make new requests within this
                                                      // amount of seconds.
    private static Long EXPIRE_BEFORE_OFFSET = 20L;

    private static final TokenHandler INSTANCE = new TokenHandler();
    private static String tokenValue = "";
    private static Long expiresIn = 0L;
    private static ZonedDateTime tokenUpdatedOn = ZonedDateTime.now();

    private static String openBankingUri = BusinessConstant.OPEN_BANKING_URI;
    private static String openBankingusername = BusinessConstant.OPEN_BANKING_USERNAME;
    private static String openBankingPassword = BusinessConstant.OPEN_BANKING_PASSWORD;
    private static String openBankingGrantType = BusinessConstant.OPEN_BANKING_GRANT_TYPE;

    private TokenHandler() {
    }

    public static TokenHandler getInstance() {
        return INSTANCE;
    }

    public static String getTokenValue() {
        if (isTokenExpired()) {
            try {
                Map<String, String> tokenValueMap = OpenBankingUtil.getToken(openBankingUri, openBankingusername,
                        openBankingPassword, openBankingGrantType);
                String incomingToken = "";
                Long expiresIn = 480L;

                if (tokenValueMap.containsKey(OpenBankingUtil.ACCESS_TOKEN)
                        && tokenValueMap.containsKey(OpenBankingUtil.EXPIRES_IN)) {
                    incomingToken = tokenValueMap.get(OpenBankingUtil.ACCESS_TOKEN);
                    String expiresInStr = tokenValueMap.get(OpenBankingUtil.EXPIRES_IN);
                    expiresIn = Long.valueOf(expiresInStr);
                }

                if (incomingToken.isEmpty()) {
                    expiresIn = MIN_TOKEN_REQ_INTERVAL;
                }
                setTokenValue(incomingToken, expiresIn);
            } catch (Exception ex) {
                System.out.println(ex);
                setTokenValue("", MIN_TOKEN_REQ_INTERVAL);
            }
        }
        return tokenValue;
    }

    private static Boolean isTokenExpired() {
        Long timeDiff = ChronoUnit.SECONDS.between(getTokenUpdatedOn(), ZonedDateTime.now());
        if (timeDiff > EXPIRE_BEFORE_OFFSET) {
            timeDiff -= EXPIRE_BEFORE_OFFSET;
        }
        if (timeDiff >= TokenHandler.getExpiresIn()) {
            return true;
        }
        return false;
    }

    public static void setTokenValue(String tokenValue, Long expiresIn) {
        TokenHandler.tokenValue = tokenValue;
        TokenHandler.tokenUpdatedOn = ZonedDateTime.now();
        TokenHandler.setExpiresIn(expiresIn);
    }

    public static ZonedDateTime getTokenUpdatedOn() {
        return tokenUpdatedOn;
    }

    public static void setTokenUpdatedOn(ZonedDateTime tokenUpdatedOn) {
        TokenHandler.tokenUpdatedOn = tokenUpdatedOn;
    }

    public static Long getExpiresIn() {
        return expiresIn;
    }

    public static void setExpiresIn(Long expiresIn) {
        TokenHandler.expiresIn = expiresIn;
    }

}
