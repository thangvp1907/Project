package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Employee {
    private String id, name, address;
    private Date dob;
    private int phone;
    private int gender;
    private int degree, position;
    private int salary;
}
