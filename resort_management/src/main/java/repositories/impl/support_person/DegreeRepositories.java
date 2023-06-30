package repositories.impl.support_person;

import models.support_person.Degree;
import repositories.ITypeRepositories;
import utils.ConnectData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DegreeRepositories implements ITypeRepositories<Degree> {
    String findAll = "select * from employee_degree";
    @Override
    public List<Degree> findAll() {
        List<Degree> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findAll)){
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Degree(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

}
