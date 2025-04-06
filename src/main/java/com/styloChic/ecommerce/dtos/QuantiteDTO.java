package com.styloChic.ecommerce.dtos;

public class QuantiteDTO {

    private String operation;

    public QuantiteDTO() {
    }

    public QuantiteDTO(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
