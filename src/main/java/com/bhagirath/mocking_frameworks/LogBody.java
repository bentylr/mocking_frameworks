package com.bhagirath.mocking_frameworks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogBody {

    private String input;
    private String message;
    private String exceptionMessage;
    private String stackTrace;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogBody logBody = (LogBody) o;
        return Objects.equals(input, logBody.input) &&
                Objects.equals(message, logBody.message) &&
                Objects.equals(exceptionMessage, logBody.exceptionMessage) &&
                Objects.equals(stackTrace, logBody.stackTrace);
    }

    @Override
    public int hashCode() {

        return Objects.hash(input, message, exceptionMessage, stackTrace);
    }
}
