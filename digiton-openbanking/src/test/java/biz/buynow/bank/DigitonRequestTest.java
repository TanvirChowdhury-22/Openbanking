package biz.buynow.bank;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import biz.buynow.bank.model.DisburseRequestJson;
import biz.buynow.bank.model.RecipientUserJson;
import biz.buynow.bank.model.UserBankJson;
import biz.buynow.bank.util.DisburseJsonParserUtils;

@SpringBootTest
class DigitonRequestTest {

    @Test
    void contextLoads() {
    }

    @Test
    void digitonDisbursementAndUserInfoTest() {
        String jsonStr = "{\n" + "\"clientRef\":\"er87t6349t\",\n" + "\"dataSecurity\":\"erti7359385t\",\n"
                + "\"data\": [ {\n" + "\"userId\":\"01637836\",\n" + "\"amount\":10000,\n"
                + "\"comments\":\"Test first data\",\n" + "\"currency\":\"BDT\",\n" + "\"bankData\" : [ {\n"
                + "\"bankAccNo\": \"1234\",\n" + "\"bankName\": \"DBBL\",\n" + "\"bankBranch\": \"Mirpur\",\n"
                + "\"bankRouting\": \"123456789\"\n" + "}, {\n" + "\"bankAccNo\": \"1235\",\n"
                + "\"bankName\": \"Janata Bank\",\n" + "\"bankBranch\": \"Sylhet\",\n"
                + "\"bankRouting\": \"111111111\"\n" + "}\n" + "]\n" + "}, {\n" + "\"userId\":\"01637836\",\n"
                + "\"amount\":2000,\n" + "\"comments\":\"test second data\",\n" + "\"currency\":\"BDT\",\n"
                + "\"bankData\" : [ {\n" + "\"bankAccNo\": \"5454\",\n" + "\"bankName\": \"BRAC\",\n"
                + "\"bankBranch\": \"BANANI\",\n" + "\"bankRouting\": \"2323sdsd\"\n" + "}\n" + "]\n" + "}\n" + "]\n"
                + "}";

        DisburseRequestJson userInfo = DisburseJsonParserUtils.processClient(jsonStr);
        Assert.isTrue(userInfo.getClientRef().equals("er87t6349t"), "Invalid ClientRef");
        Assert.isTrue(userInfo.getDataSecurity().equals("erti7359385t"), "Invalid DataSecurity");

        List<RecipientUserJson> data = userInfo.getRecipientUserJsonList();
        Assert.isTrue(data.size() == 2, "Invalid Number of Data Counts");

        RecipientUserJson data1 = data.get(0);
        Assert.isTrue(data1.getUserId().equals("01637836"), "Invalid UserId");
        Assert.isTrue(data1.getAmount().equals("10000"), "Invalid Amount");
        Assert.isTrue(data1.getComments().equals("Test first data"), "Invalid Comments");
        Assert.isTrue(data1.getCurrency().equals("BDT"), "Invalid Currency");

        List<UserBankJson> bankData = data1
                .getUserBankJsonList();
        Assert.isTrue(bankData.size() == 2, "Invalid Number of Data Counts");

        UserBankJson bankData1 = bankData.get(0);
        Assert.isTrue(bankData1.getBankAccNo().equals("1234"), "Invalid BankAccNo");
        Assert.isTrue(bankData1.getBankName().equals("DBBL"), "Invalid BankName");
        Assert.isTrue(bankData1.getBankBranch().equals("Mirpur"), "Invalid BankBranch");
        Assert.isTrue(bankData1.getBankRouting().equals("123456789"), "Invalid BankRouting");

        UserBankJson bankData2 = bankData.get(1);
        Assert.isTrue(bankData2.getBankAccNo().equals("1235"), "Invalid BankAccNo");
        Assert.isTrue(bankData2.getBankName().equals("Janata Bank"), "Invalid BankName");
        Assert.isTrue(bankData2.getBankBranch().equals("Sylhet"), "Invalid BankBranch");
        Assert.isTrue(bankData2.getBankRouting().equals("111111111"), "Invalid BankRouting");

        RecipientUserJson data2 = data.get(1);
        Assert.isTrue(data2.getUserId().equals("01637836"), "Invalid UserId");
        Assert.isTrue(data2.getAmount().equals("2000"), "Invalid Amount");
        Assert.isTrue(data2.getComments().equals("test second data"), "Invalid Comments");
        Assert.isTrue(data2.getCurrency().equals("BDT"), "Invalid Currency");

        List<UserBankJson> bankDataTwo = data2
                .getUserBankJsonList();
        Assert.isTrue(bankDataTwo.size() == 1, "Invalid Number of Data Counts");

        UserBankJson bankDataTwo1 = bankDataTwo.get(0);
        Assert.isTrue(bankDataTwo1.getBankAccNo().equals("5454"), "Invalid BankAccNo");
        Assert.isTrue(bankDataTwo1.getBankName().equals("BRAC"), "Invalid BankName");
        Assert.isTrue(bankDataTwo1.getBankBranch().equals("BANANI"), "Invalid BankBranch");
        Assert.isTrue(bankDataTwo1.getBankRouting().equals("2323sdsd"), "Invalid BankRouting");
    }
}
