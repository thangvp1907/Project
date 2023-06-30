package models;

import lombok.*;

import java.sql.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String name, address;
    private Date dob;
    private int phone;
    private String gender, cusType;

}
