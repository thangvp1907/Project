package repositories.impl;

import models.Employee;
import repositories.IBaseRepositories;
import utils.ConnectData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositories implements IBaseRepositories<Employee> {
    String findAll = "select * from employee limit 10 offset ?";
    String findById = "select * from employee where employee.id like concat('%',?,'%')";
    String findByCondition = "select * from employee where employee.id like concat('%',?,'%') limit 10 offset ?";
    String updateById = "update employee set name = ?, address = ?, dob = ?, phone = ?, gender = ?, degree = ?, position = ?, salary = ? where id = ?";
    String createNew = "insert into employee value (?,?,?,?,?,?,?,?,?)";
    String deleteOject="delete from employee where id =?";
    String countPages = "select count(*) from employee";

    @Override
    public List<Employee> findByCondition(String id) {
        List<Employee> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findById)){
            st.setString(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Employee> findByCondition(String id, int index) {
        List<Employee> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findByCondition)){
            st.setString(1,id);
            st.setInt(2,index);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Employee> findAll(int i) {
        List<Employee> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findAll)){
            st.setInt(1,i);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public void create(Employee employee) {
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(createNew)){
            st.setString(1,employee.getId());
            st.setString(2,employee.getName());
            st.setString(3,employee.getAddress());
            st.setDate(4,employee.getDob());
            st.setInt(5,employee.getPhone());
            st.setInt(6,employee.getGender());
            st.setInt(7,employee.getDegree());
            st.setInt(8,employee.getPosition());
            st.setInt(9,employee.getSalary());
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
    public void update(Employee employee) {
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(updateById)){
            st.setString(1,employee.getName());
            st.setString(2,employee.getAddress());
            st.setDate(3,employee.getDob());
            st.setInt(4,employee.getPhone());
            st.setInt(5,employee.getGender());
            st.setInt(6,employee.getDegree());
            st.setInt(7,employee.getPosition());
            st.setInt(8,employee.getSalary());
            st.setString(9,employee.getId());
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
