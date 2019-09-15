package com.perseus.pus.dto;

import javax.validation.constraints.Size;

public class EmailDTO {
    private long id;

    @Size(max = 100)
    private String mail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
