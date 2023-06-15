package com.xjy.dao;

import com.xjy.pojo.Department;
import com.xjy.pojo.Position;
import com.xjy.util.DBUtil;
import org.omg.PortableServer.POA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 云
 * @create 2023/6/14 17:31
 */
public class PositionDaoImpl implements PositionDao {
    @Override
    public void insert(Position position) {
        String sql = "insert into position(positionname) values(?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, position.getPositionName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Position position) {
        String sql = "delete from position where positionname=?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, position.getPositionName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Position position) {
        String sql = "update position set positionname=? where positionid=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, position.getPositionName());
            ps.setInt(2, position.getPositionId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Position> listAll() {
        List<Position> positionList = new ArrayList<>();
        String sql = "select * from position";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Position position = new Position(rs.getInt("positionid"), rs.getString("positionname"));
                positionList.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionList.size() == 0 ? null : positionList;
    }

    @Override
    public List<Position> getPositionByNameLike(String key) {
        List<Position> positionList = new ArrayList<>();
        String sql = "select * from position where positionname like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Position position = new Position(rs.getInt("positionid"), rs.getString("positionname"));
                positionList.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionList.size() == 0 ? null : positionList;
    }


    @Override
    public Position getPositionById(int positionId) {
        String sql = "select * from position where positionid= ?";
        Position position = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, positionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                position = new Position(rs.getInt("positionid"), rs.getString("positionname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return position;
    }

    @Override
    public Position getPositionByName(String PositionName) {
        String sql = "select * from position where positionname = ?";
        Position position = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, PositionName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                position = new Position(rs.getInt("positionid"), rs.getString("positionname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return position;
    }
}
