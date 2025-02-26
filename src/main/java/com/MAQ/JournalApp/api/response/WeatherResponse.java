package com.MAQ.JournalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    private Current current;

    public class Current{
        @JsonProperty("temp_c")
        private double temperature;

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getFeelslike() {
            return feelslike;
        }

        public void setFeelslike(double feelslike) {
            this.feelslike = feelslike;
        }

        @JsonProperty("feelslike_c")
        private double feelslike;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}




