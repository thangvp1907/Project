package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Facility {
    private String id;
    private int period;
    private int area, max_person, price;
    private int type, floor;
    private int pool_area;
    
}
