package repositories.impl;

import models.Facility;
import models.FacilityDto;
import repositories.IBaseRepositories;
import utils.ConnectData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacilityRepositories implements IBaseRepositories<FacilityDto> {
    String findAll = "select * from facility limit 10 offset ?";
    String findById = "select * from facility where facility.id like concat('%',?,'%')";
    String findByCondition = "select * from facility where facility.id like concat('%',?,'%') limit 10 offset ?";
    String updateById = "update facility set period = ?, area = ?, max_person = ?, price = ?, type = ?, floor = ?, pool_area=? where id = ?";
    String createNew = "insert into facility value (?,?,?,?,?,?,?,?)";
    String deleteOject = "delete from facility where id =?";
    String countUsedTimes = "call facility_management.count_used_times(?)";
    String countPages = "select count(*) from facility";

    @Override
    public List<FacilityDto> findByCondition(String id) {
        List<FacilityDto> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findById)){
            st.setString(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new FacilityDto(
                        new Facility(rs.getString(1), rs.getInt(2), rs.getInt(3),
                                rs.getInt(4), rs.getInt(5), rs.getInt(6),
                                rs.getInt(7), rs.getInt(8))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<FacilityDto> findByCondition(String id, int index) {
        List<FacilityDto> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findByCondition)){
            st.setString(1,id);
            st.setInt(2,index);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new FacilityDto(
                        new Facility(rs.getString(1), rs.getInt(2), rs.getInt(3),
                                rs.getInt(4), rs.getInt(5), rs.getInt(6),
                                rs.getInt(7), rs.getInt(8))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<FacilityDto> findAll(int i) {
        List<FacilityDto> list = new ArrayList<>();
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(findAll)){
            st.setInt(1,i);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new FacilityDto(
                        new Facility(rs.getString(1), rs.getInt(2), rs.getInt(3),
                                rs.getInt(4), rs.getInt(5), rs.getInt(6),
                                rs.getInt(7), rs.getInt(8))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public void create(FacilityDto facility) {
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(createNew)){
            st.setString(1,facility.getId());
            st.setInt(2,facility.getPeriod());
            st.setInt(3,facility.getArea());
            st.setInt(4,facility.getMax_person());
            st.setInt(5,facility.getPrice());
            st.setInt(6,facility.getType());
            st.setInt(7,facility.getFloor());
            st.setInt(8,facility.getPool_area());
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
    public void update(FacilityDto facility) {
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(updateById)){
            st.setInt(1,facility.getPeriod());
            st.setInt(2,facility.getArea());
            st.setInt(3,facility.getMax_person());
            st.setInt(4,facility.getPrice());
            st.setInt(5,facility.getType());
            st.setInt(6,facility.getFloor());
            st.setInt(7,facility.getPool_area());
            st.setString(8,facility.getId());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getUsedTimes(String id) {
        int times = 0;
        try(Connection con = ConnectData.getConnect();
            PreparedStatement st = con.prepareStatement(countUsedTimes)){
            st.setString(1,id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                times = rs.getInt(2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return times;
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
