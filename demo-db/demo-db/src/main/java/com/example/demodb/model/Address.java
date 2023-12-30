package com.example.demodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Address {
    private String houseNo;

    private String streetName;

    private String city;

    private String state;

    private String country;

    private String pinCode;

    public static Address fromString(String address) {
        return new Address();
    }
}
