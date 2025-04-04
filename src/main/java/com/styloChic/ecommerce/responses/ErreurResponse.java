package com.styloChic.ecommerce.responses;


public class ErreurResponse {
    private String message;
    public ErreurResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
