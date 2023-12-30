package com.example.demodb.dto;

import com.example.demodb.model.Address;
import com.example.demodb.model.Department;
import com.example.demodb.model.Employee;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UpdateEmployeeRequest {

    @NotBlank
    private String name;
    @Min(18)
    @Max(60)
    private int age;

    private Department department;
    private Address address;
    private Long updatedOn;
    public Employee to() {

//        Employee employee = new Employee();
//        employee.setName(this.to().getName()); // returns void
//        employee.setDepartment(this.to().getDepartment());
//        employee.setAddress(this.to().getAddress());
//        employee.setAge(this.to().getAge());
//        employee.setUpdatedOn(System.currentTimeMillis());
        Employee employee= Employee.builder()
                .name(this.name)
                .age(this.age)
                .address(this.address)
                .department(this.department)
                .updatedOn(this.updatedOn)
                .id(UUID.randomUUID().toString())
                .build();
        return employee;
    }
}
