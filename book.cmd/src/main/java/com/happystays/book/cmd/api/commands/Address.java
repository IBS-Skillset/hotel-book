package com.happystays.book.cmd.api.commands;

import lombok.Data;

@Data
public class Address {
    private String addressLine;
    private String cityName;
    private String postalCode;
    private String countryCode;
}
