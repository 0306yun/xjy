package com.xjy.dao;

import com.xjy.pojo.Notice;
import com.xjy.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 云
 * @create 2023/6/14 17:36
 */
public class NoticeDaoImpl implements NoticeDao {
    @Override
    public void insert(Notice notice) {
        String sql="insert into notice(noticeid,noticename,noticecontent) values(?,?,?)";
        try(
                Connection c= DBUtil.getConnection();
                PreparedStatement ps=c.prepareStatement(sql)
        ){
            ps.setInt(1,notice.getNoticeId());
            ps.setString(2,notice.getNoticeName());
            ps.setString(3,notice.getNoticeContent());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Notice notice) {
        String sql = "delete from notice where noticename=?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, notice.getNoticeName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Notice notice) {
        String sql = "update notice set noticecontent=? where noticename=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, notice.getNoticeName());
            ps.setString(2,notice.getNoticeContent());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notice> listAll() {
        List<Notice> noticeList = new ArrayList<>();
        String sql = "select * from notice";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notice notice = new Notice(rs.getInt(1), rs.getString(2),rs.getString(3));
                noticeList.add(notice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noticeList.size() == 0 ? null : noticeList;
    }

    @Override
    public List<Notice> getNoticeByNameLike(String key) {
        List<Notice>noticeList = new ArrayList<>();
        String sql = "select * from notice where noticename like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notice notice = new Notice(rs.getInt(1), rs.getString(2),rs.getString(3));
                noticeList.add(notice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noticeList.size() == 0 ? null : noticeList;
    }

    @Override
    public List<Notice> getNoticeByContentLike(String key) {
        List<Notice>noticeList = new ArrayList<>();
        String sql = "select * from notice where noticecontent like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notice notice = new Notice(rs.getInt(1), rs.getString(2),rs.getString(3));
                noticeList.add(notice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noticeList.size() == 0 ? null : noticeList;
    }

    @Override
    public Notice getNoticeById(int noticeId) {
        Notice notice = null;
        String sql = "select * from notice where noticeid=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, noticeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                notice = new Notice();
                notice.setNoticeId(rs.getInt("noticeid"));
                notice.setNoticeName(rs.getString("noticename"));
                notice.setNoticeContent(rs.getString("noticecontent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notice;
    }

    @Override
    public Notice getNoticeByName(String noticeName) {
        String sql = "select * from notice where noticename = ?";
        Notice notice = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, noticeName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                notice = new Notice(rs.getInt("noticeid"),rs.getString("noticename"),rs.getString("noticecontent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notice;
    }

    @Override
    public Notice getNoticeByContent(String noticeContent) {
        String sql = "select * from notice where noticecontent = ?";
        Notice notice = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, noticeContent);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                notice = new Notice(rs.getInt("noticeid"),rs.getString("noticename"),rs.getString("noticecontent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notice;
    }
}
