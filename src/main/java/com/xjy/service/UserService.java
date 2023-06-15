package com.xjy.service;

import com.xjy.View.Application;
import com.xjy.View.MainView;
import com.xjy.dao.*;
import com.xjy.pojo.Department;
import com.xjy.pojo.Notice;
import com.xjy.pojo.Position;
import com.xjy.pojo.User;
import com.xjy.util.Print;

import java.util.List;
import java.util.Scanner;

/**
 * @Author 云
 * @create 2023/6/13 19:33
 */
public class UserService {
    private static UserDao userDao;
    private static DepartmentDao departmentDao;
    private static PositionDao positionDao;
    private static NoticeDao noticeDao;
    private static Scanner sc = new Scanner(System.in);

    static {
        userDao = new UserDaoImpl();
        departmentDao = new DepartmentDaoImpl();
        positionDao = new PositionDaoImpl();
        noticeDao = new NoticeDaoImpl();

    }


    //登录验证
    public void login(User inputUser, String RandomConfirmCode, String inputConfirmCode) {
        // 用户名不存在 登录失败
        if (userDao.getUserByName(inputUser.getUserName()) == null) {
            Print.print("用户名不存在，请重新登录!");
            MainView.loginView();
        } else {
            //验证码错误,登录失败
            if (!RandomConfirmCode.equalsIgnoreCase(inputConfirmCode)) {
                Print.print("验证码输入错误，请重新登录!");
                MainView.loginView();
            }
            User user = userDao.getUserByName(inputUser.getUserName());
            if (user.getUserName().equals(inputUser.getUserName())
                    && user.getPassWord().equals(inputUser.getPassWord())) {
                Application.currentUser = user;
                //登录成功 进入用户页面
                Print.print("登录成功,正在进入用户页面......");
                MainView.userView();
            } else {
                //登录失败 回到登录页面
                Print.print("用户名或密码不正确，请重新登录!");
                MainView.loginView();
            }
        }
    }

    //注册验证
    public void register(User registerUser) {
        // 遍历用户集合
        // 用户名不存在--注册
        //存在--重新注册
        if (userDao.getUserByName(registerUser.getUserName()) == null) {
            userDao.insert(registerUser);
            Print.print("注册成功,即将跳转到主页面,请稍后......");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            MainView.mainView();
        } else {
            Print.print("注册失败，用户名已存在，请重新注册！");
            MainView.registerView();
        }
    }

    //添加用户验证
    public static void insertUser(User insertUser) {
        //添加用户存在，添加失败
        //不存在，添加成功
        if (userDao.getUserByName(insertUser.getUserName()) != null) {
            Print.print("该用户已存在，请重新添加!");
            MainView.insertUserView();
        } else {
            userDao.insert(insertUser);
            Print.print("用户添加成功！");
            Application.currentUser = insertUser;
            Print.print("是否继续进行添加用户操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("正在进入添加用户信息页面......");
                MainView.insertUserView();
            } else if (answer.equalsIgnoreCase("N")) {
                Print.print("正在返回上一级......");
                MainView.userManagementView();
            }
        }
    }

    //    删除用户验证
    public static void deleteUser(User deleteUser) {
//        用户存在，删除
//        不存在，删除失败
        if (deleteUser == null) {
            Print.print("删除失败,该用户不存在,请重新选择用户删除！");
            MainView.deleteUserView();
        } else if (userDao.getUserByName(deleteUser.getUserName()).getUserName() != null) {
            userDao.delete(deleteUser);
            Print.print("删除成功!");
            Print.print("是否继续进行删除用户操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("正在进入删除用户信息页面.....");
                MainView.deleteUserView();
            } else if (answer.equalsIgnoreCase("N")) {
                Print.print("正在返回上一级.....");
                MainView.userManagementView();
            }
        }
    }


    //  修改用户验证
    public static void updateUser(User updateUser) {
//    要进行修改的用户名不存在，修改失败
        //        用户名存在，修改信息
        if (userDao.getUserByName(updateUser.getUserName()) == null) {
            Print.print("该用户不存在，请重新选择用户进行修改！");
            MainView.updateUserView();
        } else {
            if (userDao.getUserByName(updateUser.getUserName()) != null) {
                userDao.update(updateUser);
                Print.print("修改成功！");
                Print.print("是否继续进行修改用户操作(Y/N)?");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    Print.print("正在进入修改用户信息页面.....");
                    MainView.updateUserView();
                } else if (answer.equalsIgnoreCase("N")) {
                    Print.print("正在返回上一级.....");
                    MainView.userManagementView();
                }
            }
        }
    }

    //用户名模糊字段查询
    public static void selectUserByName(List<User> userList) {
        Print.print("查询到的用户信息如下：");
        for (User user : userList) {
            System.out.println(user);
        }
        Print.print("是否继续进行模糊查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行模糊查询");
            MainView.selectUserByNameLike();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectUserView();
        }
    }


//    身份证号模糊查询
public static void selectUserByIdCard(List<User> userList) {
    Print.print("查询到的用户信息如下：");
    for (User user : userList) {
        System.out.println(user);
    }
    Print.print("是否继续进行模糊查询操作(Y/N)?");
    String answer = sc.nextLine();
    if (answer.equalsIgnoreCase("Y")) {
        Print.print("继续进行模糊查询");
        MainView.selectUserByIdCardLike();
    } else {
        Print.print("正在返回上一页......");
        MainView.selectUserView();
    }
}
//    性别模糊查询
public static void selectUserByGender(List<User> userList) {
    Print.print("查询到的用户信息如下：");
    for (User user : userList) {
        System.out.println(user);
    }
    Print.print("是否继续进行模糊查询操作(Y/N)?");
    String answer = sc.nextLine();
    if (answer.equalsIgnoreCase("Y")) {
        Print.print("继续进行模糊查询");
        MainView.selectUserByGenderLike();
    } else {
        Print.print("正在返回上一页......");
        MainView.selectUserView();
    }
}
//    手机号模糊查询
    public static void selectUserByPhone(List<User> userList) {
        Print.print("查询到的用户信息如下：");
        for (User user : userList) {
            System.out.println(user);
        }
        Print.print("是否继续进行模糊查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行模糊查询");
            MainView.selectUserByPhoneLike();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectUserView();
        }
    }
//    职位模糊查询
        public static void selectUserByPositionName(List<User> userList) {
            Print.print("查询到的用户信息如下：");
            for (User user : userList) {
                System.out.println(user);
            }
            Print.print("是否继续进行模糊查询操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行模糊查询");
                MainView.selectUserByPositionNameLike();
            } else {
                Print.print("正在返回上一页......");
                MainView.selectUserView();
            }
        }
//    部门模糊查询
            public static void selectUserByDepartmentName(List<User> userList) {
                Print.print("查询到的用户信息如下：");
                for (User user : userList) {
                    System.out.println(user);
                }
                Print.print("是否继续进行模糊查询操作(Y/N)?");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    Print.print("继续进行模糊查询");
                    MainView.selectUserByrDepartmentNameLike();
                } else {
                    Print.print("正在返回上一页......");
                    MainView.selectUserView();
                }
            }
    //        用户状态模糊字段查询
    public static void selectUserByType(List<User> userList) {
        Print.print("查询到的用户信息如下：");
        for (User user : userList) {
            System.out.println(user);
        }
        Print.print("是否继续进行模糊查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行模糊查询");
            MainView.selectUserByUserType();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectUserView();
        }
    }

    //        查询所有用户
    public static void selectUserShow(List<User> userList) {
        for (User user : userList) {
            System.out.println(user);
        }
        Print.print("是否继续进行所有用户信息查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行查询");
            MainView.selectAllUser();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectUserView();
        }
    }

    //    添加部门信息验证
    public static void insertDepartment(Department insertDepartment) {
        if (departmentDao.getDepartmentById(insertDepartment.getDepartmentId()) != null ||
                departmentDao.getDepartmentByName(insertDepartment.getDepartmentName()) != null) {
            Print.print("该部门已存在,请重新添加！");
            MainView.insertDepartmentView();
        } else {
            departmentDao.insert(insertDepartment);
            Print.print("部门添加成功");
            Application.currentDepartment=insertDepartment;
            Print.print("是否继续进行部门添加操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行添加");
                MainView.insertDepartmentView();
            } else {
                Print.print("正在返回上一页......");
                MainView.departmentManageView();
            }
        }
    }

    //    删除部门验证
    public static void deleteDepartment(Department deleteDepartment) {
//        找不到--删除失败
//    存在--删除
        if (departmentDao.getDepartmentById(deleteDepartment.getDepartmentId()) == null
                && departmentDao.getDepartmentByName(deleteDepartment.getDepartmentName()) == null) {
            Print.print("该部门不存在，请重新删除！");
            MainView.deleteDepartmentView();
        } else {
            departmentDao.delete(deleteDepartment);
            Print.print("部门删除成功");
            Print.print("是否继续进行部门删除操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行删除");
                MainView.deleteDepartmentView();
            } else {
                Print.print("正在返回上一页......");
                MainView.departmentManageView();
            }
        }

    }

    //    修改部门验证
//    部门名不存在--失败
//    部门名存在--成功
    public static void updateDepartment(Department updateDepartment) {
        if (departmentDao.getDepartmentById(updateDepartment.getDepartmentId()) == null
                && departmentDao.getDepartmentByName(updateDepartment.getDepartmentName()) == null) {
            Print.print("该部门不存在，请重新选择部门进行修改！");
            MainView.updateDepartmentView();
        } else {
            departmentDao.update(updateDepartment);
            Print.print("部门修改成功");
            Print.print("是否继续进行部门修改操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行修改");
                MainView.updateDepartmentView();
            } else {
                Print.print("正在返回上一页......");
                MainView.departmentManageView();
            }
        }
    }

    //    查询所有部门
    public static void selectDepartmentShow(List<Department> departmentList ) {
        for (Department department : departmentList) {
            System.out.println(department);
        }
        Print.print("是否继续进行所有部门信息查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行查询");
            MainView.selectAllDepartment();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectDepartmentView();
        }
    }

    //    部门名模糊查询
    public static void selectDepartmentByNameLike(List<Department> departmentList) {
        Print.print("查询到的部门信息如下：");
        for (Department department : departmentList) {
            System.out.println(department);
        }
        Print.print("是否继续进行模糊查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行模糊查询");
            MainView.selectDepartmentByNameLike();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectDepartmentView();
        }
    }

    //    添加职位信息验证
    public static void insertPosition(Position insertPosition) {
        if (positionDao.getPositionById(insertPosition.getPositionId()) != null ||
                positionDao.getPositionByName(insertPosition.getPositionName()) != null) {
            Print.print("该职位已存在,请重新添加！");
            MainView.insertDepartmentView();
        } else {
            positionDao.insert(insertPosition);
            Print.print("职位添加成功");
            Application.currentPosition=insertPosition;
            Print.print("是否继续进行部门添加操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行添加");
                MainView.insertPositionView();
            } else {
                Print.print("正在返回上一页......");
                MainView.positionManageView();
            }
        }
    }

    //    删除职位验证
    public static void deletePosition(Position deletePosition) {
//        找不到--删除失败
//    存在--删除
        if (departmentDao.getDepartmentById(deletePosition.getPositionId()) == null
                && departmentDao.getDepartmentByName(deletePosition.getPositionName()) == null) {
            Print.print("该职位不存在，请重新删除！");
            MainView.deletePositionView();
        } else {
            positionDao.delete(deletePosition);
            Print.print("职位删除成功");
            Print.print("是否继续进行职位删除操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行删除");
                MainView.deletePositionView();
            } else {
                Print.print("正在返回上一页......");
                MainView.positionManageView();
            }
        }

    }

    //    修改职位验证
//    部门名不存在--失败
//    部门名存在--成功
    public static void updatePosition(Position updatePosition) {
        if (positionDao.getPositionById(updatePosition.getPositionId()) == null
                && departmentDao.getDepartmentByName(updatePosition.getPositionName()) == null) {
            Print.print("该职位不存在，请重新选择职位进行修改！");
            MainView.updatePositionView();
        } else {
            positionDao.update(updatePosition);
            Print.print("职位修改成功");
            Print.print("是否继续进行职位修改操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行修改");
                MainView.updatePositionView();
            } else {
                Print.print("正在返回上一页......");
                MainView.positionManageView();
            }
        }
    }

    //    查询所有职位
    public static void selectPositionShow(List<Position> positionList){
        for (Position position : positionList) {
            System.out.println(position);
        }
        Print.print("是否继续进行所有职位信息查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行查询");
            MainView.selectAllPosition();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectPositionView();
        }
    }

    //    职位名模糊查询
    public static void selectPositionByNameLike(List<Position> positionList) {
        Print.print("查询到的职位信息如下：");
        for (Position position : positionList) {
            System.out.println(position);
        }
        Print.print("是否继续进行模糊查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行模糊查询");
            MainView.selectPositionByNameLike();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectPositionView();
        }
    }

    //    添加公告信息验证
    public static void insertNotice(Notice insertNotice) {
        if (noticeDao.getNoticeById(insertNotice.getNoticeId()) != null ||
                noticeDao.getNoticeByName(insertNotice.getNoticeName()) != null) {
            Print.print("该公告已存在,请重新添加！");
            MainView.insertNoticeView();
        } else {
            noticeDao.insert(insertNotice);
            Print.print("职位添加成功");
            Application.currentNotice=insertNotice;
            Print.print("是否继续进行公告添加操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行添加");
                MainView.insertNoticeView();
            } else {
                Print.print("正在返回上一页......");
                MainView.noticeManageView();
            }
        }
    }

    //    删除公告验证
    public static void deleteNotice(Notice deleteNotice) {
//        找不到--删除失败
//    存在--删除
        if (noticeDao.getNoticeById(deleteNotice.getNoticeId()) == null
                && noticeDao.getNoticeByName(deleteNotice.getNoticeName()) == null) {
            Print.print("该公告不存在，请重新删除！");
            MainView.deleteNoticeView();
        } else {
            noticeDao.delete(deleteNotice);
            Print.print("公告删除成功");
            Print.print("是否继续进行公告删除操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行删除");
                MainView.deleteNoticeView();
            } else {
                Print.print("正在返回上一页......");
                MainView.noticeManageView();
            }
        }

    }

    //    修改公告验证
//    公告名不存在--失败
//    公告名存在--成功
    public static void updateNotice(Notice updateNotice) {
        if (noticeDao.getNoticeByName(updateNotice.getNoticeName()) == null) {
            Print.print("该公告不存在，请重新选择公告进行修改！");
            MainView.updateNoticeView();
        } else {
            noticeDao.update(updateNotice);
            Print.print("修改成功");
            Print.print("是否继续进行修改操作(Y/N)?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                Print.print("继续进行修改");
                MainView.updateNoticeView();
            } else {
                Print.print("正在返回上一页......");
                MainView.noticeManageView();
            }
        }
    }

    //    查询所有公告
    public static void selectNoticeShow(List<Notice> noticeList){
        for (Notice notice : noticeList) {
            System.out.println(notice);
        }
        Print.print("是否继续进行所有公告信息查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行查询");
            MainView.selectAllNotice();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectNoticeView();
        }
    }

    //    公告名模糊查询
    public static void selectNoticeByNameLike(List<Notice> noticeList) {
        Print.print("查询到的公告信息如下：");
        for (Notice notice : noticeList) {
            System.out.println(notice);
        }
        Print.print("是否继续进行模糊查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行模糊查询");
            MainView.selectNoticeByNameLike();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectNoticeView();
        }
    }
    //    公告内容模糊查询
    public static void selectNoticeByContentLike(List<Notice> noticeList){
        Print.print("查询到的公告信息如下：");
        for (Notice notice : noticeList) {
            System.out.println(notice);
        }
        Print.print("是否继续进行模糊查询操作(Y/N)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            Print.print("继续进行模糊查询");
            MainView.selectNoticeByContentLike();
        } else {
            Print.print("正在返回上一页......");
            MainView.selectNoticeView();
        }
    }
}




