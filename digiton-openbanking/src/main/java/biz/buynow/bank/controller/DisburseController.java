package biz.buynow.bank.controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import biz.buynow.bank.constant.BusinessConstant;
import biz.buynow.bank.model.DisburseRequest;
import biz.buynow.bank.model.DisburseRequestJson;
import biz.buynow.bank.model.MTBBeftnTransRes;

import biz.buynow.bank.model.RecipientUser;
import biz.buynow.bank.model.RecipientUserJson;
import biz.buynow.bank.model.UserBank;
import biz.buynow.bank.model.UserBankJson;
import biz.buynow.bank.repository.UserBankRepository;

import biz.buynow.bank.repository.RecipientUserRepository;
import biz.buynow.bank.repository.DisburseRequestRepository;
import biz.buynow.bank.repository.MTBBeftnTransResRepository;
import biz.buynow.bank.util.DisburseJsonParserUtils;

@Controller
@Transactional
public class DisburseController {
    static String DIGITON_BANK_ACC = "0510320000083";

    static String DIGITON_PRESHARED_KEY = "UBDGJDNKFISHDFkjkjhfjkdhgjfh1233257683423";

    @Autowired
    private DisburseRequestRepository disburseRequestRepository;

    @Autowired
    private RecipientUserRepository recipientUserRepository;

    @Autowired
    private UserBankRepository userBankRepository;

    @Autowired
    private MTBBeftnTransResRepository mtblBeftnInfoResponseRepository;

    @GetMapping("/trigger_disburse_request/{disburse_request_id}")
    public ResponseEntity<String> changeDisburseReqStatus(
            @PathVariable(name = "disburse_request_id") Long disburseRequestId) {
        Integer pendingDisburseStatus = BusinessConstant.DISBURSE_REQUEST_STATUS.PENDING.ordinal();
        Integer readyDisburseStatus = BusinessConstant.DISBURSE_REQUEST_STATUS.READY.ordinal();
        Optional<DisburseRequest> disburseRequestOptional = disburseRequestRepository.findById(disburseRequestId);
        if (disburseRequestOptional.isPresent()) {
            if (disburseRequestOptional.get().getStatus() == pendingDisburseStatus
                    || disburseRequestOptional.get().getStatus() == readyDisburseStatus) {
                disburseRequestOptional.get().setStatus(readyDisburseStatus);
                disburseRequestRepository.save(disburseRequestOptional.get());

                List<RecipientUser> recipientUserList = disburseRequestOptional.get().getRecipientUserList();
                for (RecipientUser recipientUser : recipientUserList) {
                    Integer pendingStatus = BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.PENDING.ordinal();
                    Integer readyStatus = BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.READY.ordinal();

                    if (recipientUser.getStatus() == pendingStatus) {
                        recipientUser.setStatus(readyStatus);
                        recipientUserRepository.save(recipientUser);
                    }
                }
            }
        }
        ResponseEntity<String> responseEntity = ResponseEntity.ok().build();
        return responseEntity;
    }

    @GetMapping("/trigger_recipient_user/{recipient_user_id}")
    public ResponseEntity<String> changePendingStatus(@PathVariable(name = "recipient_user_id") Long recipientUserId) {
        Integer pendingStatus = BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.PENDING.ordinal();
        Integer readyStatus = BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.READY.ordinal();

        Optional<RecipientUser> recipientUserOptional = recipientUserRepository.findById(recipientUserId);
        if (recipientUserOptional.isPresent()) {
            if (recipientUserOptional.get().getStatus() == pendingStatus) {
                recipientUserOptional.get().setStatus(readyStatus);
                recipientUserRepository.save(recipientUserOptional.get());
            }
        }
        ResponseEntity<String> responseEntity = ResponseEntity.ok().build();
        return responseEntity;
    }

    @GetMapping("/trigger_beftn_check/{mtbl_beftn_info_response_id}")
    public ResponseEntity<String> changeStatus(
            @PathVariable(name = "mtbl_beftn_info_response_id") Long beftnInfoResponseId) {

        Optional<MTBBeftnTransRes> beftnInfoResponseOptional = mtblBeftnInfoResponseRepository
                .findById(beftnInfoResponseId);
        if (beftnInfoResponseOptional.isPresent()) {
            if (beftnInfoResponseOptional.get().isOutwardStatusPending() == false) {
                beftnInfoResponseOptional.get().setOutwardStatusPending(true);
                mtblBeftnInfoResponseRepository.save(beftnInfoResponseOptional.get());
            }
        }
        ResponseEntity<String> responseEntity = ResponseEntity.ok().build();
        return responseEntity;
    }

    @Transactional
    @PostMapping("/inittransactions")
    public @ResponseBody String processDisbursementReq(@RequestBody String jsonString) throws Exception {
        Long disburseId = processDisburseRequestJson(jsonString);

        return disburseId.toString();
    }

    boolean verifySecurityCode(DisburseRequestJson digitonRequest) {
        if (BusinessConstant.DISABLE_SECURITY_CHECK) {
            return true;
        }
        String dataSecurityToCheck = "";
        String dataSecurity = digitonRequest.getDataSecurity();
        List<RecipientUserJson> userDataList = digitonRequest.getRecipientUserJsonList();
        for (RecipientUserJson recipientUserJson : userDataList) {
            String userId = recipientUserJson.getUserId();
            String amount = recipientUserJson.getAmount();
            dataSecurityToCheck += userId;
            dataSecurityToCheck += amount;
            List<UserBankJson> bankDataList = recipientUserJson.getUserBankJsonList();
            for (UserBankJson userBankJson : bankDataList) {
                String bankAccNo = userBankJson.getBankAccNo();
                String bankRouting = userBankJson.getBankRouting();
                dataSecurityToCheck += bankAccNo;
                dataSecurityToCheck += bankRouting;
            }
        }

        dataSecurityToCheck += DIGITON_PRESHARED_KEY;
        String dataSecurityHashToCheck = HashGenerator.getSHA256(dataSecurityToCheck);

        if (!dataSecurityHashToCheck.equals(dataSecurity)) {
            System.out.println(dataSecurityHashToCheck);
            return false;
        }
        return true;
    }

    @Transactional
    private Long processDisburseRequestJson(String jsonString) throws Exception {
        BigDecimal totalAmount = new BigDecimal(0);
        DisburseRequestJson digitonRequest = DisburseJsonParserUtils.processClient(jsonString);

        if (!verifySecurityCode(digitonRequest)) {
            System.out.println("Invalid Security Code");
            return -1L;
        }
        DisburseRequest disburseRequest = convertDisburseRequestJsonToDisburseRequest(digitonRequest);
        disburseRequest.setStatus(BusinessConstant.DISBURSE_REQUEST_STATUS.PENDING.ordinal());
        disburseRequest.setResult(BusinessConstant.DISBURSE_REQUEST_RESULT.PENDING.ordinal());
        disburseRequest.setTotalAmount(totalAmount);

        Optional<DisburseRequest> disburseRequestOptional = disburseRequestRepository
                .findByClientRefAndDataSecurityAndCreateTimeAfter(disburseRequest.getClientRef(),
                        disburseRequest.getDataSecurity(), OffsetDateTime.now().minusHours(5));

        if (!disburseRequestOptional.isPresent()) {
            disburseRequest = disburseRequestRepository.save(disburseRequest);
            totalAmount = processRecipientUserList(digitonRequest, disburseRequest);
            disburseRequest.setTotalAmount(totalAmount);
            disburseRequest = disburseRequestRepository.save(disburseRequest);
        } else {
            throw new Exception("Duplicate Disbursement Request.");
        }
        disburseRequest = disburseRequestRepository.save(disburseRequest);
        return disburseRequest.getId();

    }

    private BigDecimal processRecipientUserList(DisburseRequestJson digitonRequest, DisburseRequest disburseRequest) {
        BigDecimal totalAmount = new BigDecimal(0);
        List<RecipientUser> recipientUserList = new ArrayList<RecipientUser>();
        List<RecipientUserJson> recipientUserJsonList = digitonRequest.getRecipientUserJsonList();
        for (RecipientUserJson recipientUserJson : recipientUserJsonList) {
            RecipientUser recipientUser = convertRecipientUserJsonToRecipientUser(recipientUserJson, disburseRequest);
            recipientUser.setStatus(BusinessConstant.RECIPIENT_USER_DISBURSE_STATUS.PENDING.ordinal());
            recipientUser.setPaymentStatus(BusinessConstant.PAYMENT_STATUS.PENDING.ordinal());
            recipientUserRepository.save(recipientUser);

            Double userAmount = Double.valueOf(recipientUser.getAmount());
            totalAmount = totalAmount.add(BigDecimal.valueOf(userAmount));
            recipientUserList.add(recipientUser);
            processUserBankList(recipientUserJson, recipientUser);
        }
        disburseRequest.setRecipientUserList(recipientUserList);
        disburseRequestRepository.save(disburseRequest);
        return totalAmount;
    }

    private void processUserBankList(RecipientUserJson recipientUserJson, RecipientUser recipientUser) {
        List<UserBank> userBankList = new ArrayList<UserBank>();
        List<UserBankJson> userBankJsonList = recipientUserJson.getUserBankJsonList();
        for (UserBankJson userBankJson : userBankJsonList) {
            UserBank userBank = convertUserBankJsonToUserBank(recipientUser, userBankJson);
            userBankRepository.save(userBank);
            userBankList.add(userBank);
        }
        recipientUser.setUserBankList(userBankList);
        recipientUserRepository.save(recipientUser);
    }

    private UserBank convertUserBankJsonToUserBank(RecipientUser recipientUser, UserBankJson userBankJson) {
        UserBank userBank = new UserBank();
        userBank.setBankAccNo(userBankJson.getBankAccNo());
        userBank.setAccountHoldersName(userBankJson.getAccountHoldersName());
        userBank.setBankName(userBankJson.getBankName());
        userBank.setBankBranch(userBankJson.getBankBranch());
        userBank.setBankRouting(userBankJson.getBankRouting());
        userBank.setRecipientUser(recipientUser);
        return userBank;
    }

    private DisburseRequest convertDisburseRequestJsonToDisburseRequest(DisburseRequestJson digitonRequest) {
        DisburseRequest disburseRequest = new DisburseRequest();
        disburseRequest.setClientRef(digitonRequest.getClientRef());
        disburseRequest.setDataSecurity(digitonRequest.getDataSecurity());
        disburseRequest.setStartDateFromString(digitonRequest.getStartDate());
        disburseRequest.setEndDateFromString(digitonRequest.getEndDate());
        disburseRequest.setRecurFreq(digitonRequest.getRecurFreq());
        disburseRequest.setRecurDate(digitonRequest.getRecurDate());
        return disburseRequest;
    }

    private RecipientUser convertRecipientUserJsonToRecipientUser(RecipientUserJson recipientUserJson,
            DisburseRequest disburseRequest) {
        RecipientUser recipientUser = new RecipientUser();
        recipientUser.setUserId(recipientUserJson.getUserId());
        recipientUser.setAmount(recipientUserJson.getAmount());
        recipientUser.setComments(recipientUserJson.getComments());
        recipientUser.setCurrency(recipientUserJson.getCurrency());
        recipientUser.setDisburseRequest(disburseRequest);
        return recipientUser;
    }
}
