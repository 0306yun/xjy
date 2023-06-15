package com.xjy.dao;

import com.xjy.pojo.Department;
import com.xjy.pojo.User;
import com.xjy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 云
 * @create 2023/6/14 17:20
 */
public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public void insert(Department department) {
        String sql = "insert into department(departmentname) values(?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, department.getDepartmentName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Department department) {
        String sql = "delete from department where departmentname=?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, department.getDepartmentName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department department) {
        String sql = "update department set departmentname=? where departmentid=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, department.getDepartmentName());
            ps.setInt(2, department.getDepartmentId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        Department department = null;
        String sql = "select * from department where departmentid=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                department = new Department();
                department.setDepartmentId(rs.getInt("departmentid"));
                department.setDepartmentName(rs.getString("departmentname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        String sql = "select * from department where departmentname = ?";
        Department department = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, departmentName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                department = new Department(rs.getInt("departmentid"),rs.getString("departmentname") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }


    @Override
    public List<Department> listAll() {
        List<Department> departmentList = new ArrayList<>();
        String sql = "select * from department";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department(rs.getInt("departmentid"), rs.getString("departmentname"));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList.size() == 0 ? null : departmentList;
    }

    @Override
    public List<Department> getDepartmentByNameLike(String key) {
        List<Department> departmentList = new ArrayList<>();
        String sql = "select * from department  where departmentname like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department(rs.getInt("departmentid"), rs.getString("departmentname"));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList.size() == 0 ? null : departmentList;
    }
}
