package com.example.demodb.dto;

import com.example.demodb.model.Address;
import com.example.demodb.model.Department;
import com.example.demodb.model.Employee;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@ToString
@Builder
public class CreateEmployeeRequest {

    @NotBlank
    private String name;
    @Min(18)
    @Max(60)
    private int age;
    @NonNull
    private Department department;
    private Address address;
    private Long createdOn;
    private Long updatedOn;

    // conversion from dto --> model
    public Employee to() {

//        Employee employee = new Employee();
//        employee.setName(this.name); // returns void
//        employee.setDepartment(this.department);
//        employee.setAddress(this.address);
//        employee.setAge(this.age);
//
//        employee.setCreatedOn(System.currentTimeMillis());
//        employee.setUpdatedOn(System.currentTimeMillis());
//        employee.setId(UUID.randomUUID().toString());
   //     return employee;
        Employee employee= Employee.builder()
                            .name(this.name)
                            .age(this.age)
                            .address(this.address)
                            .createdOn(this.createdOn)
                            .department(this.department)
                            .updatedOn(this.updatedOn)
                            .id(UUID.randomUUID().toString())
                            .build();
          return employee;
    }
}