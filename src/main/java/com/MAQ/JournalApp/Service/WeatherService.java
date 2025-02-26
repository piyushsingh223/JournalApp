package com.MAQ.JournalApp.Service;

import com.MAQ.JournalApp.Cache.AppCache;
import com.MAQ.JournalApp.Entity.User;
import com.MAQ.JournalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String api_key;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache app_cache;

    public WeatherResponse getWeather(String city){
        String finalAPI=app_cache.appcache.get(AppCache.keys.Weather_API_KEY.toString()).replace("<city>",city).replace("<api_key>",api_key);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }

//    public WeatherResponse dummypostcallservice(String city){
//        String finalAPI=API.replace("CITY",city).replace("API_KEY",api_key);
//
//        HttpHeaders httpheader = new HttpHeaders();
//        httpheader.set("key","value");
//
//        User user = User.builder().userName("xyz").passWord("abc").build();
//        HttpEntity<User> httpentity = new HttpEntity<>(user,httpheader);
//
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST,httpentity, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body;
//    }
}
