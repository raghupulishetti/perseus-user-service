package com.perseus.pus.dto;

import java.util.ArrayList;
import java.util.List;

public class UserContactDataDTO {
    private List<EmailDTO> emails = new ArrayList<>();
    private List<PhoneNumberDTO> phoneNumbers = new ArrayList<>();

    public List<EmailDTO> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailDTO> emails) {
        this.emails = emails;
    }

    public List<PhoneNumberDTO> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberDTO> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
