package com.example.demodb.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Employee {
    private String id; // which is given to the employee
    private String name;
    private int age;
    private Department department;
    private Address address;
    private Long createdOn;
    private Long updatedOn;


}
