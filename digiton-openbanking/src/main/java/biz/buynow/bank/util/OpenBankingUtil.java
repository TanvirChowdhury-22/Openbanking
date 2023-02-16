package biz.buynow.bank.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import biz.buynow.bank.model.TokenHandler;

public class OpenBankingUtil {
    
    public static String ACCESS_TOKEN = "access_token";
    public static String EXPIRES_IN = "expires_in";
    
    public static Map<String, String> getToken(String openBankingUri, String openBankingusername, String openBankingPassword,
            String openBankingGrantType) {
        Map<String, String> tokenValueMap = new HashMap<>();
        try {
            String uri = openBankingUri + "/auth/login";
            System.out.println(uri);
            HttpHeaders httpHeaders = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            String jsonToPost = "{ \"username\": \"" + openBankingusername + "\", \"password\": \""
                    + openBankingPassword + "\", \"grant_type\" : \"" + openBankingGrantType + "\" }";
            HttpEntity<String> request = new HttpEntity<String>(jsonToPost, httpHeaders);
            System.out.println(request);
            ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
            String json = result.getBody();
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootNode = mapper.readTree(json);

            String token = rootNode.get(ACCESS_TOKEN).asText();
            String expiresIn = rootNode.get(EXPIRES_IN).asText();
            tokenValueMap.put(ACCESS_TOKEN, token);
            tokenValueMap.put(EXPIRES_IN, expiresIn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tokenValueMap;
    }

//    public static HttpHeaders generateHeader(String openBankingUri, String openBankingusername,
//            String openBankingPassword, String openBankingGrantType) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        String token = getToken(openBankingUri, openBankingusername, openBankingPassword, openBankingGrantType);
//
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        // httpHeaders.set("Accept", "application/json");
//        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        httpHeaders.set("Authorization", "Bearer " + token);
//        return httpHeaders;
//    }

    // https://spring.io/blog/2018/03/06/using-spring-security-5-to-integrate-with-oauth-2-secured-services-such-as-facebook-and-github
    private static ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution)
                    throws IOException {
                request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                request.getHeaders().setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                request.getHeaders().add("Authorization", "Bearer " + accessToken);
                return execution.execute(request, bytes);
            }
        };
        return interceptor;
    }

    public static RestTemplate getBearerRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        TokenHandler tokenHandler = TokenHandler.getInstance();
        String accessToken = tokenHandler.getTokenValue();
        restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));

        return restTemplate;
    }

//    public ResponseEntity<String> verifyOrder(String orderNumber, String openBankingUri, String openBankingusername,
//            String openBankingPassword, String openBankingGrantType) {
//        ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        try {
//            Map<String, String> tokenValueMap = getToken(openBankingUri, openBankingusername, openBankingPassword, openBankingGrantType);
//            String token = "";
//            if (tokenValueMap.containsKey(OpenBankingUtil.ACCESS_TOKEN)
//                    && tokenValueMap.containsKey(OpenBankingUtil.EXPIRES_IN)) {
//                token = tokenValueMap.get(OpenBankingUtil.ACCESS_TOKEN);
//            }
//            String uri = openBankingUri + "/VerifyOrder";
//            HttpHeaders httpHeaders = new HttpHeaders();
//            RestTemplate restTemplate = new RestTemplate();
//
//            MultiValueMap<String, String> postValueMap = new LinkedMultiValueMap<>();
//            httpHeaders.add("X_API_TOKEN", token);
//            postValueMap.add("order_number", orderNumber);
//
//            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(postValueMap, httpHeaders);
//            result = restTemplate.postForEntity(uri, request, String.class);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }
}
