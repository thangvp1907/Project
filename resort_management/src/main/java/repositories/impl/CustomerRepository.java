package repositories.impl;

import models.Customer;
import repositories.IBaseRepositories;
import utils.ConnectData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements IBaseRepositories<Customer> {
    String findAll = "select * from customers limit 10 offset ?";
    String findById = "select * from customers where customers.id like concat('%',?,'%')";
    String findByCondition = "select * from customers where customers.id like concat('%',?,'%') limit 10 offset ?";
    String updateById = "update customers set name = ?, address = ?, dob = ?, phone = ?, genderID = ?, cusTypeID = ? where id = ?";
    String createNew = "insert into customers value (?,?,?,?,?,?,?)";
    String deleteOject="delete from customers where id =?";
    String countPages = "select count(*) from customers";

    @Override
    public List<Customer> findAll(int index) {
        List<Customer> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findAll)){
            st.setInt(1,index);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getInt(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Customer> findByCondition(String id) {
        List<Customer> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findById)){
        st.setString(1,id);
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            list.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                    rs.getInt(5), rs.getString(6), rs.getString(7)));
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Customer> findByCondition(String id, int index) {
        List<Customer> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findByCondition)){
            st.setString(1,id);
            st.setInt(2,index);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getInt(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public void create(Customer customer) {
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(createNew)){
            st.setString(1,customer.getId());
            st.setString(2,customer.getName());
            st.setString(3,customer.getAddress());
            st.setDate(4,customer.getDob());
            st.setInt(5,customer.getPhone());
            st.setString(6,customer.getGender());
            st.setString(7,customer.getCusType());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(deleteOject)){
            st.setString(1,id);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(updateById)){
            st.setString(1,customer.getName());
            st.setString(2,customer.getAddress());
            st.setDate(3,customer.getDob());
            st.setInt(4,customer.getPhone());
            st.setString(5,customer.getGender());
            st.setString(6,customer.getCusType());
            st.setString(7,customer.getId());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int countItem() {
        int pages = 0;
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(countPages)){
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                pages = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pages;
    }
}
