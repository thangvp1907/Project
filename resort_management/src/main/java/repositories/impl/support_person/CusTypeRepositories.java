package repositories.impl.support_person;

import models.support_person.Type;
import repositories.ITypeRepositories;
import utils.ConnectData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CusTypeRepositories implements ITypeRepositories<Type> {
    String findAll = "select * from customertype";
    @Override
    public List<Type> findAll() {
        List<Type> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findAll)){
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Type(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
