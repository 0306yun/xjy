package com.xjy.dao;

import com.xjy.pojo.Department;
import com.xjy.pojo.Position;

import java.util.List;

/**
 * @Author 云
 * @create 2023/6/14 17:28
 */
public interface PositionDao {
    /**插入一条职位信息
     * @param position 职位信息
     */
    void insert(Position position);

    /**删除职位信息
     * @param position 职位信息
     */
    void delete(Position position);

    /**修改职位信息
     * @param position 职位信息
     */
    void update(Position position);

    /**根据职位号获取指定的信息
     * @param positionId 职位号
     * @return 职位信息
     */
    Position getPositionById(int positionId);

    /**根据职位名获取指定职位信息
     * @param positionName 部门名
     * @return 职位信息
     */
    Position getPositionByName(String positionName);

    /**查看所有 职位信息
     * @return 所有职位信息
     */
    List<Position> listAll();


    /**根据职位名模糊性查询
     * @param key 职位名称
     * @return 职位信息
     */
    List<Position> getPositionByNameLike(String key);

}
