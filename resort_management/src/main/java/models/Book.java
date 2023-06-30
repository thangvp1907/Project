package models;

import lombok.*;

import java.sql.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    private String id;
    private String customer_name;
    private String facility_name;
    private int companion;
    private Date date_in, date_out;
}
