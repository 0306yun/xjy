package com.xjy.dao;

import com.xjy.pojo.User;
import com.xjy.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 云
 * @create 2023/6/14 15:12
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void insert(User user) {
        String sql = "insert into user(userid,username,password,idcard,phone,gender,positionname,departmentname,usertype) " +
                "values(?,?,?,?,?,?,?,?,?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPassWord());
            ps.setString(4, user.getIdCard());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getGender());
            ps.setString(7, user.getDepartmentName());
            ps.setString(8, user.getPositionName());
            ps.setString(9, user.getUserType());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
            String sql = "delete from user where username=?";
            try (Connection c = DBUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, user.getUserName());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void update(User user) {
        String sql = "update user set password=?,phone=?,departmentname=?,positionname=?,usertype=? where username=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user.getPassWord());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getDepartmentName());
            ps.setString(4, user.getPositionName());
            ps.setString(5, user.getUserType());
            ps.setString(6, user.getUserName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        String sql = "select * from user where userid=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setUserName(rs.getString("password"));
                user.setIdCard(rs.getString("idcard"));
                user.setPhone(rs.getString("phone"));
                user.setGender(rs.getString("gender"));
                user.setPositionName(rs.getString("positionname"));
                user.setDepartmentName(rs.getString("departmentname"));
                user.setUserType(rs.getString("usertype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByName(String userName) {
            String sql = "select * from user where username = ?";
            User user = null;
            try (Connection c = DBUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, userName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                            rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                            rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype") );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        }


    @Override
    public User getUserBydDepartmentName(String departmentName) {
    String sql="select * from user where departmentname=?";
        User user=null;
    try(Connection c=DBUtil.getConnection();
        PreparedStatement ps=c.prepareStatement(sql);
    ){
        ps.setString(1,departmentName);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
         user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                    rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                    rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
        }
    }catch (SQLException e) {
        e.printStackTrace();
    }
    return user;
    }

    @Override
    public User getUserByPositionName(String positionName) {
        String sql="select * from user where positionname=?";
        User user=null;
        try(Connection c=DBUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,positionName);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                        rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> listByUserTypeLike(String key) {
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where usertype like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              User user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                        rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList.size() == 0 ? null : userList;
    }

    @Override
    public List<User> listByNameLike(String key) {
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where username like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                        rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList.size() == 0 ? null : userList;
    }

    @Override
    public List<User> listAll() {
            String sql = "select * from user";
            List<User> userList = new ArrayList<>();
            try (Connection c = DBUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)
            ) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                            rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                            rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
                    userList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return userList.size() == 0 ? null : userList;
    }

    @Override
    public List<User> listByUserGenderLike(String key) {
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where gender like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                        rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList.size() == 0 ? null : userList;
    }

    @Override
    public List<User> listByUserIdCardLike(String key) {
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where idcard like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                        rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList.size() == 0 ? null : userList;
    }

    @Override
    public List<User> listByUserPhoneLike(String key) {
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where phone like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                        rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList.size() == 0 ? null : userList;
    }

    @Override
    public List<User> listByUserPositionNameLike(String key) {
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where positionname like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                        rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList.size() == 0 ? null : userList;
    }

    @Override
    public List<User> listByUserDepartmentNameLike(String key) {
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where departmentname like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("idcard"), rs.getString("phone"), rs.getString("gender"),
                        rs.getString("positionname"), rs.getString("departmentname"),rs.getString("usertype"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList.size() == 0 ? null : userList;
    }
}
