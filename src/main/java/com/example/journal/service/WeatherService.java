package com.example.journal.service;

import com.example.journal.apiResponse.WeatherResponse;
import com.example.journal.appCache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey ;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city,WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }
       else{
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(appCache.APP_CACHE.get("weather_api").replace("<CITY>", city).replace("<API_KEY>", apiKey), HttpMethod.GET, null, WeatherResponse.class, city, apiKey);
            System.out.println(response.getBody().toString());
            if(response.getBody() != null){
                redisService.set("weather_of_" + city, response.getBody(), 300L);
            }
            return response.getBody();
        }
    }
}
