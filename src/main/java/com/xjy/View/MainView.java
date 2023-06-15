package com.xjy.View;

import com.xjy.Controller.UserController;
import com.xjy.dao.*;
import com.xjy.pojo.Department;
import com.xjy.pojo.Notice;
import com.xjy.pojo.Position;
import com.xjy.pojo.User;
import com.xjy.service.UserService;
import com.xjy.util.Print;
import com.xjy.util.StringUtil;
import java.util.List;
import java.util.Scanner;

/**
 * @Author 云
 * @create 2023/6/13 19:28
 */
public class MainView {
    public static Scanner sc = new Scanner(System.in);
    private static UserService userService = new UserService();
    public static UserDao userDao = new UserDaoImpl();
    public static DepartmentDao departmentDao = new DepartmentDaoImpl();
    public static PositionDao positionDao = new PositionDaoImpl();
    public static NoticeDao noticeDao = new NoticeDaoImpl();

    //    系统页面
    public static void mainView() {
        Print.print("欢迎来到员工管理系统");
        Print.print("1.登录");
        Print.print("2.注册");
        String choose = sc.nextLine();
        UserController.mainController(choose);
    }

    //    登录页面
    public static void loginView() {
        Print.print("请输入用户名");
        String inputUsername = sc.nextLine();
        Print.print("请输入密码");
        String inputPassword = sc.nextLine();
        String RandomConfirmCode = StringUtil.getRandomStr(4);
        Print.print("请输入验证码,验证码为：" + RandomConfirmCode);
        String inputConfirmCode = sc.nextLine();
        User inputUser = new User(1, inputUsername, inputPassword, null, null, null, null, null, null);
        userService.login(inputUser, RandomConfirmCode, inputConfirmCode);
    }

    //    注册页面
    public static void registerView() {
        Print.print("请输入用户名");
        String registerUsername = sc.nextLine();
        Print.print("请输入密码");
        String registerPassword = sc.nextLine();
        String RandomConfirmCode = StringUtil.getRandomStr(4);
        Print.print("请输入验证码,验证码为：" + RandomConfirmCode);
        String registerConfirmCode = sc.nextLine();
        Print.print("请输入电话号码");
        String registerPhone = sc.nextLine();
        Print.print("请输入性别");
        String registerGender = sc.nextLine();
        Print.print("请输入所属部门名");
        String registerDepartmentName = sc.nextLine();
        Print.print("请输入所在职位名");
        String registerPositionName = sc.nextLine();
        Print.print("请输入身份");
        String registerUserType = sc.nextLine();
        User registerUSer = new User(0, registerUsername, registerPassword, registerConfirmCode, registerPhone, registerGender, registerDepartmentName,
                registerPositionName, registerUserType);
        userService.register(registerUSer);
    }

    //    用户页面
    public static void userView() {
        Print.print("该用户为:" + userDao.getUserByName(Application.currentUser.getUserName()).getUserType() + ",请选择你要访问的功能.");
        Print.print("1.用户管理");
        Print.print("2.部门管理");
        Print.print("3.职位管理");
        Print.print("4.公告管理");
        Print.print("5.退出系统");
        String choose = sc.nextLine();
        UserController.manageController(choose);
    }

    //    用户管理页面
    public static void userManagementView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加用户信息");
        Print.print("2.删除用户信息");
        Print.print("3.修改用户信息");
        Print.print("4.查询用户信息");
        Print.print("5.返回主页面");
        String choose = sc.nextLine();
        UserController.userInfo(choose);
    }

    //    添加用户页面
    public static void insertUserView() {
        Print.print("请输入要添加的用户id");
        Integer insertUserId = Integer.parseInt(sc.nextLine());
        Print.print("请输入要添加的用户名");
        String insertUserName = sc.nextLine();
        Print.print("请输入要添加的用户密码");
        String insertUserPassword = sc.nextLine();
        Print.print("请输入要添加的用户身份证号");
        String insertIdCard = sc.nextLine();
        Print.print("请输入要添加的用户电话");
        String insertPhone = sc.nextLine();
        Print.print("请输入要添加的用户性别");
        String insertGender = sc.nextLine();
        Print.print("请输入要添加的用户所属部门名");
        String insertDepartmentId = sc.nextLine();
        Print.print("请输入要添加的用户所在职位名");
        String insertPositionId = sc.nextLine();
        Print.print("请输入要添加的用户身份");
        String insertUserType = sc.nextLine();
        User insertUserInfo = new User(insertUserId, insertUserName, insertUserPassword,
                insertIdCard, insertPhone, insertGender, insertDepartmentId, insertPositionId, insertUserType);
        UserService.insertUser(insertUserInfo);
    }

    //    删除用户
    public static void deleteUserView() {
        Print.print("请输入要删除的员工名");
        String deleteUserName = sc.nextLine();
        User deleteUserInfo = userDao.getUserByName(deleteUserName);
        UserService.deleteUser(deleteUserInfo);
    }

    //    编辑用户
    public static void updateUserView() {
        Print.print("请输入要修改信息的用户名");
        String existUserName = sc.nextLine();
        Print.print("请输入修改后的用户密码");
        String updateUserPassword = sc.nextLine();
        Print.print("请输入修改后的用户电话");
        String updatePhone = sc.nextLine();
        Print.print("请输入修改后的用户所属部门名");
        String updateDepartmentId = sc.nextLine();
        Print.print("请输入修改后的用户所在职位名");
        String updatePositionId = sc.nextLine();
        Print.print("请输入修改后的用户身份");
        String updateUserType = sc.nextLine();
        User user = userDao.getUserByName(existUserName);
        User updateUserInfo = new User(user.getUserId(), existUserName, updateUserPassword,
                user.getIdCard(), updatePhone, user.getGender(), updateDepartmentId, updatePositionId, updateUserType);
        UserService.updateUser(updateUserInfo);

    }

    //    查询用户
    public static void selectUserView() {
        Print.print("1.所有用户信息查询");
        Print.print("2.用户名模糊查询");
        Print.print("3.身份证号模糊查询");
        Print.print("4.性别模糊查询");
        Print.print("5.手机号模糊查询");
        Print.print("6.职位模糊查询");
        Print.print("7.部门模糊查询");
        Print.print("8.用户状态模糊查询");
        Print.print("9.返回上一级");
        String choose = sc.nextLine();
        UserController.selectUserController(choose);
    }

    public static void selectUserByNameLike() {
        Print.print("请输入用户名模糊字段");
        String key = sc.nextLine();
        List<User> userList = userDao.listByNameLike(key);
        UserService.selectUserByName(userList);
    }

public static void selectUserByIdCardLike(){
    Print.print("请输入用户身份证号模糊字段");
    String key = sc.nextLine();
    List<User> userList = userDao.listByUserIdCardLike(key);
    UserService.selectUserByIdCard(userList);
}

    public static void selectUserByGenderLike(){
        Print.print("请输入用户性别模糊字段");
        String key = sc.nextLine();
        List<User> userList = userDao.listByUserGenderLike(key);
        UserService.selectUserByGender(userList);
    }
    public static void selectUserByPhoneLike(){
        Print.print("请输入用户手机号模糊字段");
        String key = sc.nextLine();
        List<User> userList = userDao.listByUserPhoneLike(key);
        UserService.selectUserByPhone(userList);
    }

    public static void selectUserByPositionNameLike(){
        Print.print("请输入用户职位名模糊字段");
        String key = sc.nextLine();
        List<User> userList = userDao.listByUserPositionNameLike(key);
        UserService.selectUserByPositionName(userList);
    }

    public static void selectUserByrDepartmentNameLike(){
        Print.print("请输入用户部门名模糊字段");
        String key = sc.nextLine();
        List<User> userList = userDao.listByUserDepartmentNameLike(key);
        UserService.selectUserByDepartmentName(userList);
    }

    public static void selectUserByUserType() {
        Print.print("请输入用户类型模糊字段");
        String key = sc.nextLine();
        List<User> userList = userDao.listByUserTypeLike(key);
        UserService.selectUserByType(userList);
    }

    public static void selectAllUser() {
        Print.print("所有用户信息如下：");
        List<User> userList = userDao.listAll();
        UserService.selectUserShow(userList);
    }

    //    部门管理页面
    public static void departmentManageView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加部门信息");
        Print.print("2.删除部门信息");
        Print.print("3.修改部门信息");
        Print.print("4.查询部门信息");
        Print.print("5.返回主页面");
        String choose = sc.nextLine();
        UserController.departInfo(choose);
    }

    //    添加部门页面
    public static void insertDepartmentView() {
        Print.print("1.添加部门号");
        Integer insertDepartmentId = Integer.parseInt(sc.nextLine());
        Print.print("2.添加部门名");
        String insertDepartmentName = sc.nextLine();
        Department insertDepartmentInfo = new Department(insertDepartmentId, insertDepartmentName);
        UserService.insertDepartment(insertDepartmentInfo);

    }

    //删除部门
    public static void deleteDepartmentView() {
        Print.print("请输入要删除的部门名");
        String deleteDepartmentName = sc.nextLine();
        Department deleteDepartmentInfo = departmentDao.getDepartmentByName(deleteDepartmentName);
        UserService.deleteDepartment(deleteDepartmentInfo);
    }

    //    修改部门
    public static void updateDepartmentView() {
        Print.print("请输入你要修改的部门号");
  Integer existDepartmentId=Integer.parseInt(sc.nextLine());
        Print.print("请输入修改后的部门名称");
        String updateDepartmentName = sc.nextLine();
        Department updateUserInfo = new Department(existDepartmentId, updateDepartmentName);
        UserService.updateDepartment(updateUserInfo);
    }

    //查询部门
    public static void selectDepartmentView() {
        Print.print("1.所有部门信息查询");
        Print.print("2.部门名模糊查询");
        Print.print("3.返回上一级");
        String choose = sc.nextLine();
        UserController.selectDepartmentController(choose);
    }
//    查询所有部门
    public static void selectAllDepartment(){
        Print.print("所有部门信息如下：");
        List<Department> departmentList = departmentDao.listAll();
        UserService.selectDepartmentShow(departmentList);

    }
//    部门名模糊查询
public static void selectDepartmentByNameLike(){
    Print.print("请输入部门名模糊字段");
    String key = sc.nextLine();
    List<Department> departmentList = departmentDao.getDepartmentByNameLike(key);
    UserService.selectDepartmentByNameLike(departmentList);

}
    //    职位管理页面
    public static void positionManageView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加职位信息");
        Print.print("2.删除职位信息");
        Print.print("3.修改职位信息");
        Print.print("4.查询职位信息");
        Print.print("5.返回主页面");
        String choose = sc.nextLine();
        UserController.positionInfo(choose);
    }

    public static void insertPositionView() {
        Print.print("1.添加职位号");
        Integer insertPositionId = Integer.parseInt(sc.nextLine());
        Print.print("2.添加职位名");
        String insertPositionName = sc.nextLine();
        Position insertPositionInfo = new Position(insertPositionId, insertPositionName);
        UserService.insertPosition(insertPositionInfo);
    }

    public static void deletePositionView() {
        Print.print("请输入要删除的职位名");
        String deletePositionName = sc.nextLine();
        Position deletePositionInfo = positionDao.getPositionByName(deletePositionName);
        UserService.deletePosition(deletePositionInfo);
    }

    public static void updatePositionView() {
        Print.print("请输入你要修改的职位号");
        Integer existPositionId=Integer.parseInt(sc.nextLine());
        Print.print("请输入修改后的职位名称");
        String updatePositionName = sc.nextLine();
        Position updatePositionInfo = new Position(existPositionId, updatePositionName);
        UserService.updatePosition(updatePositionInfo);
    }

    public static void selectPositionView() {
        Print.print("1.所有职位信息查询");
        Print.print("2.职位名模糊查询");
        Print.print("3.返回上一级");
        String choose = sc.nextLine();
        UserController.selectPositionController(choose);
    }
    public static void selectAllPosition(){
        Print.print("所有职位信息如下：");
        List<Position> positionList = positionDao.listAll();
        UserService.selectPositionShow(positionList);
    }

    public static void selectPositionByNameLike(){
        Print.print("请输入职位名模糊字段");
        String key = sc.nextLine();
        List<Position> positionList = positionDao.getPositionByNameLike(key);
        UserService.selectPositionByNameLike(positionList);
    }

    //公告管理页面
    public static void noticeManageView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加公告信息");
        Print.print("2.删除公告信息");
        Print.print("3.修改公告信息");
        Print.print("4.查询公告信息");
        Print.print("5.返回主页面");
        String choose = sc.nextLine();
        UserController.noticeInfo(choose);
    }

    public static void insertNoticeView() {
        Print.print("1.添加公告号");
        Integer insertNoticeId = Integer.parseInt(sc.nextLine());
        Print.print("2.添加公告名");
        String insertNoticeName = sc.nextLine();
        Print.print("添加公告内容");
        String insertNoticeContent=sc.nextLine();
        Notice insertNoticeInfo = new Notice(insertNoticeId, insertNoticeName,insertNoticeContent);
        UserService.insertNotice(insertNoticeInfo);
    }

    public static void deleteNoticeView() {
        Print.print("请输入要删除的公告名");
        String deleteNoticeName = sc.nextLine();
        Notice deleteNoticeInfo = noticeDao.getNoticeByName(deleteNoticeName);
        UserService.deleteNotice(deleteNoticeInfo);
    }

    public static void updateNoticeView() {
        Print.print("请输入你要修改的公告名称");
       String existNoticeName=sc.nextLine();
        Print.print("请输入修改后的公告名称");
        String updateNoticeName = sc.nextLine();
        Print.print("请输入要修改后的公告内容");
        String updateNoticeContent=sc.nextLine();
        Notice notice=noticeDao.getNoticeByName(existNoticeName);
        Notice updateNoticeInfo = new Notice(notice.getNoticeId(), updateNoticeName,updateNoticeContent);
        UserService.updateNotice(updateNoticeInfo);
    }

    public static void selectNoticeView() {
        Print.print("1.所有公告信息查询");
        Print.print("2.公告名模糊查询");
        Print.print("3.公告名模糊查询");
        Print.print("4.返回上一级");
        String choose = sc.nextLine();
        UserController.selectNoticeController(choose);
    }

    public static void selectAllNotice(){
        Print.print("所有公告信息如下：");
        List<Notice> noticeList = noticeDao.listAll();
        UserService.selectNoticeShow(noticeList);
    }

    public static void selectNoticeByNameLike(){
        Print.print("请输入公告名模糊字段");
        String key = sc.nextLine();
        List<Notice> noticeList = noticeDao.getNoticeByNameLike(key);
        UserService.selectNoticeByNameLike(noticeList);
    }
    public static void selectNoticeByContentLike(){
        Print.print("请输入公告信息模糊字段");
        String key = sc.nextLine();
        List<Notice> noticeList = noticeDao.getNoticeByContentLike(key);
        UserService.selectNoticeByContentLike(noticeList);
    }
}
