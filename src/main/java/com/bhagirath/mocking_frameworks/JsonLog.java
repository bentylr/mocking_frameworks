package com.bhagirath.mocking_frameworks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.HashMap;
import java.util.Map;

public class JsonLog {
    private static final ObjectMapper OBJ_MAPPER = new ObjectMapper();
    private Map<String, Object> properties;

    public Map<String, Object> getProperties() {
        return properties;
    }

    private JsonLog(Builder builder) {
        this.properties = builder.properties;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Map<String, Object> properties;

        private Builder() {
            this.properties = new HashMap<>();
        }

        public JsonLog build() {
            return new JsonLog(this);
        }

        public Builder withInput(Object input) {
            this.properties.put("input", toJson(input));
            return this;
        }

        public Builder withMessage(String message) {
            this.properties.put("message", message);
            return this;
        }

        public Builder withExceptionMesage(String message) {
            this.properties.put("exceptionMessage", message);
            return this;
        }

        public Builder withStackTrace(String stackTrace) {
            this.properties.put("stackTrace", stackTrace);
            return this;
        }

    }


    private static String toJson(Object obj) {
        try {
            return OBJ_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
           // return buildJson(objectMap);
        }
        return null;
    }

}
