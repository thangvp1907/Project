package repositories.impl.support_facility;

import models.support_facility.Period;
import repositories.ITypeRepositories;
import utils.ConnectData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodRepositories implements ITypeRepositories<Period> {
    String findAll = "select * from period";
    @Override
    public List<Period> findAll() {
        List<Period> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findAll)){
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Period(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
