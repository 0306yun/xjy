package com.xjy.dao;

import com.xjy.pojo.Department;

import java.util.List;

/**
 * @Author 云
 * @create 2023/6/14 17:18
 */
public interface DepartmentDao {
    /**添加一条部门信息
     * @param department 部门
     */
    void insert(Department department);

    /**删除部门
     * @param department 部门
     */
    void delete(Department department);

    /**更新部门
     * @param department 部门
     */
    void update(Department department);

    /**根据部门号获取指定的信息
     * @param departmentId 部门号
     * @return 部门信息
     */
    Department getDepartmentById(int departmentId);

    /**根据部门名获取指定部门信息
     * @param departmentName 部门名
     * @return 部门信息
     */
    Department getDepartmentByName(String departmentName);

    /**查询所有部门信息
     * @return 所有部门
     */
    List<Department> listAll();


    /**根据部门名模糊查询
     * @param key 部门名
     * @return department
     */
    List<Department> getDepartmentByNameLike(String key);

}
