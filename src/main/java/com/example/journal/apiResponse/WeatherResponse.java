package com.example.journal.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

        private Current current;

    @Data
    @Getter
    @Setter
    public class Current {

        private Integer temperature;

        @JsonProperty("weather_code")
        private Integer weatherCode;


        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;


        private Integer feelslike;


    }
}
