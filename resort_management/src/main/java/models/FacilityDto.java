package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repositories.impl.FacilityRepositories;

@Getter @Setter @NoArgsConstructor
public class FacilityDto {
    private FacilityRepositories repositories = new FacilityRepositories();
    private String id;
    private int period;
    private int area, max_person, price;
    private int type, floor;
    private int pool_area;
    private int times;

    public FacilityDto(Facility facility) {
        id = facility.getId();
        period = facility.getPeriod();
        area = facility.getArea();
        max_person = facility.getMax_person();
        price = facility.getPrice();
        type = facility.getType();
        floor = facility.getFloor();
        pool_area = facility.getPool_area();
        times = repositories.getUsedTimes(id);
    }
}
