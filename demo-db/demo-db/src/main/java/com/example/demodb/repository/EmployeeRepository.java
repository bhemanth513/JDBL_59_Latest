package com.example.demodb.repository;

import com.example.demodb.model.Address;
import com.example.demodb.model.Department;
import com.example.demodb.model.Employee;
import com.example.demodb.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private Connection connection;

    EmployeeRepository(@Value("${db_url}") String url,
                       @Value("${db_username}") String username,
                       @Value("${db_password}") String password) throws SQLException {
        connectToDB(url, username, password);
        createEmployeeTable();
    }

    private void createEmployeeTable() throws SQLException {
        String sql = "CREATE TABLE if not exists employee(id varchar(40) primary key, name varchar(30), age int, department varchar(20), address varchar(256))";
        Statement statement = this.connection.createStatement();
        statement.execute(sql);
    }

    private void connectToDB(String url, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url,username,password);
    }

    public Employee create(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (id, name, age, department, address) VALUES ('" +
                employee.getId() + "' , '" + employee.getName() + "' , '" + employee.getAge() + "' , '" +
                employee.getDepartment().name() + "' , '" + employee.getAddress().toString() + "')";

        Statement statement = this.connection.createStatement();
        statement.execute(sql);
        return employee;
    }

    public Employee create2(Employee employee) throws SQLException {

        // static queries
        String sql = "insert into employee (id, name, age, department, address) VALUES (?, ?, ?, ?, ?) ";

        PreparedStatement statement = this.connection.prepareStatement(sql);

        statement.setString(1, employee.getId());
        statement.setString(2, employee.getName());
        statement.setInt(3, employee.getAge());
        statement.setString(4, employee.getDepartment().name());
        statement.setString(5, employee.getAddress().toString());
        statement.execute();

        return employee;
    }

    public Employee get(String id) {

        return null;
    }

    public List<Employee> get() throws SQLException {
        String sql = "select * from employee";
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql); // executeQuery - we should use this whenever we are using select

        List<Employee> employeeList = new ArrayList<>();
        while(resultSet.next()){
//            String id = resultSet.getString(1);
//
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            Department department = Department.valueOf(resultSet.getString("department"));
            Address address = Address.fromString(resultSet.getString("address"));

            Employee employee = Employee.builder()
                    .id(id)
                    .name(name)
                    .department(department)
                    .address(address)
                    .age(age)
                    .build();

            employeeList.add(employee);
        }
        return employeeList;
    }

    public Employee update(String id, Employee employee) {
       return null;
    }

    public Employee delete(String id) {
        return null;
    }
}
