package repositories.impl.support_facility;

import models.support_facility.FacilityType;
import repositories.ITypeRepositories;
import utils.ConnectData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacilityTypeRepositories implements ITypeRepositories<FacilityType> {
    String findAll = "select * from facility_type";
    @Override
    public List<FacilityType> findAll() {
        List<FacilityType> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findAll)){
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new FacilityType(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
