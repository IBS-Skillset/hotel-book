package com.happystays.book.common.dto.commandmodel;

import com.happystays.book.common.dto.commonmodel.Address;
import lombok.Data;

@Data
public class UserInfo {
    private String prefix;
    private String givenName;
    private String surName;
    private String phoneNumber;
    private String email;
    private Address address;
}
