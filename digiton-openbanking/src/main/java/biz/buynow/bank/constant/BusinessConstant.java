package biz.buynow.bank.constant;

public class BusinessConstant {

    public static String OPEN_BANKING_URI = "http://localhost:8086/ob_uat";
    public static String OPEN_BANKING_USERNAME = "";
    public static String OPEN_BANKING_PASSWORD = "";
    public static String OPEN_BANKING_GRANT_TYPE = "client_credentials";
    public static String SERVER_DOWN_ERROR_CODE = "101";

    public static Long MAXIMUM_DOWNTIME_DURATION_IN_MINUTES = 60L;

    public static enum BANK {
        MTBL
    }

    public static enum DISBURSE_REQUEST_STATUS {
        PENDING, READY, INPROGRESS, FINISHED
    }

    public static enum DISBURSE_REQUEST_RESULT {
        PENDING, READY, FAILED, PART_FAILED, SUCCESSFUL
    }

    public static enum RECIPIENT_USER_DISBURSE_STATUS {
        PENDING, READY, INPROGRESS, CONNECTION_FAILED, INVALID_API_RESPONSE, INVALID_BANK, FAILED, SUCCESSFUL
    }

    public static enum REQ_TYPE_JSON {
        ADD_BENEFICIARY, BEFTN_INFO, BEFTN_STATUS, MTB_TRANSFER, ONLINE_STATEMENT, DISBURSE_REQUEST
    }

    public static enum PAYMENT_STATUS {
        PENDING, BEFTN_CONFIRMATION_PENDING, COMPLETED
    }

    public static int MAX_RETRY_TRANSACTIONID_GEN = 3;

    public static boolean DISABLE_SECURITY_CHECK = true;

    public static int MAX_RETRY_UNIQUEID_GEN = 3;

    public static Long MAX_TIME_TOKEN = 480L; // in seconds

}
