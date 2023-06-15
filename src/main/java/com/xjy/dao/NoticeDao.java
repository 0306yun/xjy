package com.xjy.dao;

import com.xjy.pojo.Notice;

import java.util.List;

/**
 * @Author 云
 * @create 2023/6/14 17:35
 */
public interface NoticeDao {
    /**添加一条公告信息
     * @param notice 公告
     */
    void insert(Notice notice);

    /**删除公告
     * @param notice 公告
     */
    void delete(Notice notice);

    /**更新公告
     * @param notice 公告
     */
    void update(Notice notice);

    /**根据公告号号获取指定的信息
     * @param noticeId 公告号
     * @return 公告信息
     */
    Notice getNoticeById(int noticeId);

    /**根据公告名获取指定公告信息
     * @param noticeName 公告名
     * @return 公告信息
     */
    Notice getNoticeByName(String noticeName);

    /**根据公告内容获取指定公告信息
     * @param noticeContent 公告内容
     * @return 公告信息
     */
    Notice getNoticeByContent(String noticeContent);

    /**查询所有公告
     * @return 所有公告信息
     */
    List<Notice> listAll();

    /**根据公告名模糊查询
     * @param key 公告名
     * @return
     */
    List<Notice> getNoticeByNameLike(String key);

    /**根据公告内容模糊查询
     * @param key 公告内容
     * @return
     */
    List<Notice> getNoticeByContentLike(String key);
}
