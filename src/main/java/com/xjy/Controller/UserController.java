package com.xjy.Controller;

import com.xjy.View.MainView;
import com.xjy.util.Print;

/**
 * @Author 云
 * @create 2023/6/13 19:27
 */
public class UserController {
    public static void mainController(String choose) {
        switch (choose) {
            case "1":
                //跳转到登录视图
                MainView.loginView();
                break;
            case "2":
                //跳转到注册视图
                MainView.registerView();
                break;
            default:
        }
    }

    //主页面的跳转
    public static void manageController(String choose) {
        switch (choose) {
            case "1":
                Print.print("即将进入用户管理页面......");
                MainView.userManagementView();
                break;
            case "2":
                Print.print("即将进入部门管理页面......");
                MainView.departmentManageView();
                break;
            case "3":
                Print.print("即将进入职位管理页面......");
                MainView.positionManageView();
                break;
            case "4":
                Print.print("即将进入公告管理页面......");
                MainView.noticeManageView();
                break;
            case "5":
                Print.print("即将退出系统.....");
                MainView.mainView();
                break;
            default:

        }
    }

    //用户管理页面的跳转
    public static void userInfo(String choose) {
        switch (choose) {
            case "1":
                Print.print("即将进入添加用户信息页面......");
                MainView.insertUserView();
                break;
            case "2":
                Print.print("即将进入删除用户信息页面......");
                MainView.deleteUserView();
                break;
            case "3":
                Print.print("即将进入修改用户信息页面......");
                MainView.updateUserView();
                break;
            case "4":
                Print.print("即将进入查询用户信息页面......");
                MainView.selectUserView();
                break;
            case "5":
                Print.print("即将返回主页面...");
                MainView.userView();
                break;
            default:
        }
    }

    //    用户查询页面的跳转
    public static void selectUserController(String choose) {
        switch (choose) {
            case "1":
                MainView.selectAllUser();
                break;
            case "2":
                MainView.selectUserByNameLike();
                break;
            case "3":
                MainView.selectUserByIdCardLike();
                break;
            case "4":
                MainView.selectUserByGenderLike();
            case "5":
                MainView.selectUserByPhoneLike();
                break;
            case "6":
                MainView.selectUserByPositionNameLike();
                break;
            case "7":
                MainView.selectUserByrDepartmentNameLike();
                break;
            case "8":
                MainView.selectUserByUserType();
                break;
            case "9":
                MainView.userManagementView();
            default:
        }
    }

    //部门管理页面的跳转
    public static void departInfo(String choose) {
        switch (choose) {
            case "1":
                Print.print("即将进入添加部门信息页面......");
                MainView.insertDepartmentView();
                break;
            case "2":
                Print.print("即将进入删除部门信息页面......");
                MainView.deleteDepartmentView();
                break;
            case "3":
                Print.print("即将进入修改部门信息页面......");
                MainView.updateDepartmentView();
                break;
            case "4":
                Print.print("即将进入查询部门信息页面......");
                MainView.selectDepartmentView();
                break;
            case "5":
                Print.print("即将返回上一级......");
                MainView.userView();
                break;
            default:
        }
    }

    //   部门查询的跳转
    public static void selectDepartmentController(String choose) {
        switch (choose) {
            case "1":
                MainView.selectAllDepartment();
                break;
            case "2":
                MainView.selectDepartmentByNameLike();
                break;
            case "3":
                MainView.departmentManageView();
                break;
            default:
        }
    }

//    职位管理页面跳转
    public static void positionInfo(String choose) {
        switch (choose) {
            case "1":
                Print.print("即将进入添加职位信息页面......");
                MainView.insertPositionView();
                break;
            case "2":
                Print.print("即将进入删除职位信息页面......");
                MainView.deletePositionView();
                break;
            case "3":
                Print.print("即将进入修改职位信息页面......");
                MainView.updatePositionView();
                break;
            case "4":
                Print.print("即将进入查询职位信息页面......");
                MainView.selectPositionView();
                break;
            case "5":
                Print.print("即将返回上一级......");
                MainView.userView();
                break;
            default:
        }
    }
    //   职位查询的跳转
    public static void selectPositionController(String choose) {
        switch (choose) {
            case "1":
                MainView.selectAllPosition();
                break;
            case "2":
                MainView.selectPositionByNameLike();
                break;
            case "3":
                MainView.positionManageView();
                break;
            default:
        }
    }


    //公告管理页面的跳转
    public static void noticeInfo(String choose) {
        switch (choose) {
            case "1":
                Print.print("即将进入添加公告信息页面......");
                MainView.insertNoticeView();
                break;
            case "2":
                Print.print("即将进入删除公告信息页面......");
                MainView.deleteNoticeView();
                break;
            case "3":
                Print.print("即将进入修改公告信息页面......");
                MainView.updateNoticeView();
                break;
            case "4":
                Print.print("即将进入查询公告信息页面......");
                MainView.selectNoticeView();
                break;
            case "5":
                Print.print("即将返回上一级......");
                MainView.userView();
                break;
            default:
        }
    }

    //   公告查询的跳转
    public static void selectNoticeController(String choose) {
        switch (choose) {
            case "1":
                MainView.selectAllNotice();
                break;
            case "2":
                MainView.selectNoticeByNameLike();
                break;
            case "3":
                MainView.selectNoticeByContentLike();
                break;
            case "4":
                MainView.noticeManageView();
                break;
            default:
        }
    }

}

