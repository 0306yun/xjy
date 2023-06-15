package com.xjy.View;

import com.xjy.pojo.Department;
import com.xjy.pojo.Notice;
import com.xjy.pojo.Position;
import com.xjy.pojo.User;
import com.xjy.util.DBUtil;

/**
 * @Author 云
 * @create 2023/6/10 14:32
 */
public class Application {
    public static User currentUser=null;
    public static Department currentDepartment=null;
    public static Position currentPosition=null;
    public static Notice currentNotice=null;
    public static void main(String[] args) {
        MainView.mainView();
    }
}
