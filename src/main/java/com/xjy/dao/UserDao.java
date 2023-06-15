package com.xjy.dao;

import com.xjy.pojo.User;

import java.util.List;

/**
 * @Author 云
 * @create 2023/6/14 15:11
 */
public interface UserDao {
    /**插入一条用户数据
     * @param user 用户数据
     */
    void insert(User user);

    /**删除一条用户数据
     * @param user 用户数据
     */
    void delete(User user);

    /**修改一条用户数据
     * @param user 用户数据
     */
    void update(User user);

    /**根据用户id获取指定的用户数据
     * @param userId 用户id
     * @return
     */
    User getUserById(int userId);

    /**根据用户名获取指定的用户数据
     * @param userName 用户名
     * @return
     */
    User getUserByName(String userName);

    /**根据用户所属部门名获取指定的用户数据
     * @param departmentName 部门名
     * @return
     */
    User getUserBydDepartmentName(String departmentName);

    /**根据用户所在职位名获取指定的用户数据
     * @param positionName 职位名
     * @return
     */
    User getUserByPositionName(String positionName);

    /**根据用户类型模糊查询
     * @param key 用户类型
     * @return
     */
    List<User> listByUserTypeLike(String key);

    /**根据用户性别模糊查询
     * @param key 用户性别
     * @return
     */
    List<User> listByUserGenderLike(String key);

    /**根据用户身份证号模糊查询
     * @param key
     * @return
     */
    List<User> listByUserIdCardLike(String key);

    /**根据用户手机号模糊查询
     * @param key
     * @return
     */
    List<User> listByUserPhoneLike(String key);

    /**  根据用户职位名模糊查询
     * @param key
     * @return
     */
    List<User> listByUserPositionNameLike(String key);

    /**根据用户部门名模糊查询
     * @param key
     * @return
     */
    List<User> listByUserDepartmentNameLike(String key);
    /**根据用户名模糊查询
     * @param key 用户名
     * @return
     */
    List<User> listByNameLike(String key);
    /**
     * 默认查询所有用户信息
     *
     * @return 所有用户信息
     */
    List<User> listAll();

    /**获取该用户类型
     * @param userType 用户类型
     * @return
     */

}