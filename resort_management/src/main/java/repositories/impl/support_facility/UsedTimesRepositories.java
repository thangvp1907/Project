package repositories.impl.support_facility;

import models.support_facility.UsedTimes;
import repositories.ITypeRepositories;
import utils.ConnectData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsedTimesRepositories implements ITypeRepositories<UsedTimes> {
    String findAll = "select * from facility_times";
    @Override
    public List<UsedTimes> findAll() {
        List<UsedTimes> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findAll)){
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new UsedTimes(rs.getString(1),rs.getInt(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
