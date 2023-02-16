package biz.buynow.bank.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import biz.buynow.bank.constant.BusinessConstant;
import biz.buynow.bank.model.TokenHandler;
import biz.buynow.bank.util.OpenBankingUtil;

@Controller
public class OpenBankAPIController {

    private String openBankingUri = BusinessConstant.OPEN_BANKING_URI;
    private String openBankingusername = BusinessConstant.OPEN_BANKING_USERNAME;
    private String openBankingPassword = BusinessConstant.OPEN_BANKING_PASSWORD;
    private String openBankingGrantType = BusinessConstant.OPEN_BANKING_GRANT_TYPE;

    @GetMapping("/check-token")
    public String verifyOrder(Model model) throws Exception {
        System.out.println("XXXXXXXXXXXXXXXXX");

        Map<String, String> tokenValueMap = OpenBankingUtil.getToken(openBankingUri, openBankingusername,
                openBankingPassword, openBankingGrantType);
        String token = "";
        if (tokenValueMap.containsKey(OpenBankingUtil.ACCESS_TOKEN)
                && tokenValueMap.containsKey(OpenBankingUtil.EXPIRES_IN)) {
            token = tokenValueMap.get(OpenBankingUtil.ACCESS_TOKEN);
        }
        TokenHandler tokenHandler = TokenHandler.getInstance();
        tokenHandler.setTokenValue(token, 120L);
        model.addAttribute("token", token);
        return "x";

    }

    @GetMapping("/print-token")
    public String verifytoken(Model model) throws Exception {

        TokenHandler tokenHandler = TokenHandler.getInstance();
        System.out.println(tokenHandler.getTokenValue());
        model.addAttribute("token", tokenHandler.getTokenValue());
        return "x";

    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model, Object NullPointerException, Object JsonMappingException,
            Object JsonProcessingException) {
        if (ex.equals(NullPointerException)) {
            model.addAttribute("description", "No value present");
        } else if (ex.equals(JsonMappingException) || ex.equals(JsonProcessingException)) {
            model.addAttribute("description", "Error response from api");
        }
        ex.printStackTrace();
        return "error";
    }
}
