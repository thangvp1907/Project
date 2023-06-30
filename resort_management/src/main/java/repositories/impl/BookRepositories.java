package repositories.impl;

import models.Book;
import repositories.IBaseRepositories;
import utils.ConnectData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositories implements IBaseRepositories<Book> {
    String findAll = "select * from book where datediff(curdate(),date_out) <= 0 order by date_in, date_out limit 10 offset ?";
    String findById = "select * from book where book.id like concat('%',?,'%') and datediff(curdate(),date_out) <= 0 order by date_in, date_out";
    String findByCondition = "select * from book where book.id like concat('%',?,'%') and datediff(curdate(),date_out) <= 0 order by date_in, date_out limit 10 offset ?";
    String updateById = "update book set customer_name = ?, facility_name = ?, companion = ?, date_in = ?, date_out = ? where id = ?";
    String createNew = "insert into book value (?,?,?,?,?,?)";
    String deleteOject="delete from book where id = ?";
    String countPages = "select count(*) from book";

    @Override
    public List<Book> findByCondition(String id) {
        List<Book> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findById)){
            st.setString(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getDate(5), rs.getDate(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Book> findByCondition(String id, int index) {
        List<Book> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findByCondition)){
            st.setString(1,id);
            st.setInt(2,index);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getDate(5), rs.getDate(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Book> findAll(int i) {
        List<Book> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findAll)){
            st.setInt(1,i);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getDate(5), rs.getDate(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public void create(Book book) {
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(createNew)){
            st.setString(1,book.getId());
            st.setString(2,book.getCustomer_name());
            st.setString(3,book.getFacility_name());
            st.setInt(4,book.getCompanion());
            st.setDate(5,book.getDate_in());
            st.setDate(6,book.getDate_out());
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
    public void update(Book book) {
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(updateById)){
            st.setString(1,book.getCustomer_name());
            st.setString(2,book.getFacility_name());
            st.setInt(3,book.getCompanion());
            st.setDate(4,book.getDate_in());
            st.setDate(5,book.getDate_out());
            st.setString(6,book.getId());
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
