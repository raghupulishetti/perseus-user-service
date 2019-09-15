package com.perseus.pus.dto;

import javax.validation.constraints.Size;

public class PhoneNumberDTO {

    private long id;

    @Size(max = 10)
    private String number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

