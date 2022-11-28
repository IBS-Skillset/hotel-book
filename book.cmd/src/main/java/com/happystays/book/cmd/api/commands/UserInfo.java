package com.happystays.book.cmd.api.commands;

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
