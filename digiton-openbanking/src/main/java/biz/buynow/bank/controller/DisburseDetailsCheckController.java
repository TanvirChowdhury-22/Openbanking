package biz.buynow.bank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import biz.buynow.bank.model.DisburseRequest;
import biz.buynow.bank.model.RecipientUser;
import biz.buynow.bank.repository.DisburseRequestRepository;
import biz.buynow.bank.repository.RecipientUserRepository;

@RestController
public class DisburseDetailsCheckController {

    @Autowired
    private DisburseRequestRepository disburseRequestRepository;

    @Autowired
    private RecipientUserRepository recipientUserRepository;

    @JsonIgnoreProperties
    @GetMapping("/disbursement-details/{disburse_request_id}")
    public List<RecipientUser> disbursementDetails(@PathVariable(name = "disburse_request_id") Long disburseRequestId) {
        Optional<DisburseRequest> disburseRequestOptional = disburseRequestRepository.findById(disburseRequestId);
        List<RecipientUser> recipientUserList = new ArrayList<RecipientUser>();
        if (disburseRequestOptional.isPresent()) {
            DisburseRequest disburseRequest = disburseRequestOptional.get();
            recipientUserList = recipientUserRepository.findByDisburseRequest(disburseRequest);
            for (RecipientUser recipientUser : recipientUserList) {
                recipientUser.setDisburseRequest(null);
                recipientUser.setUserBankList(null);
            }
            System.out.println(recipientUserList);
        }
        return recipientUserList;
    }

    @GetMapping("/checkPaymentStatus/{disburseRequestId}/{userId}")
    public Optional<RecipientUser> userPaymentStatus(@PathVariable("disburseRequestId") Long disburseRequestId,
            @PathVariable("userId") String userId) {
        Optional<DisburseRequest> disburseRequestOptional = disburseRequestRepository.findById(disburseRequestId);
        Optional<RecipientUser> recipientUserOptional = Optional.empty();
        if (disburseRequestOptional.isPresent()) {
            DisburseRequest disburseRequest = disburseRequestOptional.get();
            recipientUserOptional = recipientUserRepository.findByDisburseRequestAndUserId(disburseRequest, userId);
            if (recipientUserOptional.isPresent()) {
                recipientUserOptional.get().setDisburseRequest(null);
                recipientUserOptional.get().setUserBankList(null);
            }
        }
        return recipientUserOptional;
    }
}
