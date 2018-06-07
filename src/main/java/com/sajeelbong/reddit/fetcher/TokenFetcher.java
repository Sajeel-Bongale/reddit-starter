package com.sajeelbong.reddit.fetcher;

import com.sajeelbong.reddit.constants.Credentials;
import com.sajeelbong.reddit.entity.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TokenFetcher {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private MongoOperations mongoOperations;

    public String tokenUrl = "https://www.reddit.com/api/v1/access_token";
    public String selfDetails = "https://oauth.reddit.com/api/v1/me";


    public HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
            set("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            set("User-Agent", "java:sajeel:v0.0 (by /u/sajeel)");
        }};
    }

    public HttpEntity<MultiValueMap<String,String>> getBody(HttpHeaders headers){

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "password");
        map.add("username", Credentials.USERNAME);
        map.add("password", Credentials.PASSWORD);

        return new HttpEntity<MultiValueMap<String, String>>(map, headers);

    }

    public String getToken(String url, HttpEntity<MultiValueMap<String, String>> request){
        ResponseEntity<Object> response = restTemplate.postForEntity( url, request , Object.class );
        LinkedHashMap<String, String> responseBody = (LinkedHashMap<String, String>) response.getBody();
        Object key = responseBody.keySet().iterator().next();

        return responseBody.get(key);
    }



    public void getSelfDetails(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + accessToken);
        headers.set("User-Agent", "java:sajeel:v0.0 (by /u/sajeel)");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<SelfDetails> response = restTemplate.exchange(selfDetails, HttpMethod.GET, entity, SelfDetails.class);
        System.out.println(response.getBody().isEmployee());
//        Map linkedHashMap = (LinkedHashMap<String, Object>) response.getBody();
//
//        Set set = linkedHashMap.entrySet();
//        for(Object o : set){
//            Map.Entry e = (Map.Entry) o;
//            Object key = e.getKey();
//            Object value = e.getValue();
//            System.out.println("Key : " + key + ", value : " + value);
//        }
//
//        User user = new User();
//        user.setName((String) linkedHashMap.get("name"));
//        user.setNumberOfFriends((int)linkedHashMap.get("num_friends"));
//        mongoOperations.insert(user);
    }


    public static class SelfDetails{
        public boolean isEmployee() {
            return isEmployee;
        }

        public void setEmployee(boolean employee) {
            isEmployee = employee;
        }

        private boolean isEmployee;


    }
}
