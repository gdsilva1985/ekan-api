package br.com.ekan.api.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetail {

    private String timeStamp;
    private String status;
    private String title;
    private String message;
    private String developerMessage;

    public ErrorDetail() {
    }

    public ErrorDetail(Builder builder) {
        timeStamp = builder.timeStamp;
        status = builder.status;
        title = builder.title;
        message = builder.message;
        developerMessage = builder.developerMessage;
    }

    public static class Builder {
        private String timeStamp;
        private String status;
        private String title;
        private String message;
        private String developerMessage;

        public Builder timeStamp(String x) {
            this.timeStamp = x;
            return this;
        }
        public Builder status(String x) {
            this.status = x;
            return this;
        }
        public Builder title(String x) {
            this.title = x;
            return this;
        }
        public Builder message(String x) {
            this.message = x;
            return this;
        }
        public Builder developerMessage(String x) {
            this.developerMessage = x;
            return this;
        }

        public ErrorDetail build() {
            return new ErrorDetail(this);
        }
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
