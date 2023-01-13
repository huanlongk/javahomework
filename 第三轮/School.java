package work;

import java.sql.*;
import java.time.LocalTime;
import java.util.Scanner;

public class School {
    static Scanner sc = new Scanner(System.in);
    public static  Connection conn;
    private static final String SQL1 = "insert  into student(stu_id,stu_name,stu_sex) values (?,?,?)";
    private static final String SQL2 = "delete from  student where stu_id=?";
    private static final String SQL3 = "UPDATE student SET stu_name = ? , stu_sex = ?WHERE stu_id = ?";
    private static final String SQL4 = " select  * from  student";
    private static final String SQL5 = "insert  into class(class_id,studentnum,class_time) values (?,?,?)";
    private static final String SQL6 = "delete from  class where class_id=?";
    private static final String SQL7 = "UPDATE class SET studentnum = ? , class_time = ?WHERE class_id = ?";
    private static final String SQL8 = " select  * from  class";
    //先设好要用的sql语句，用起来方便
    public static void connetionDatabse() throws Exception {
        String url = "jdbc:mysql://localhost:3306/school? useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "ke20031110";
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
        Statement st = conn.createStatement();
    }
    //连接数据库
    public static void main(String[] args) throws Exception {
        School.connetionDatabse();
        while (true) {
            System.out.println("-----------欢迎来到学籍管理系统-----------------");
            Scanner sc = new Scanner(System.in);
            System.out.println("请选择您要进行的的操作的对象 1.学生 2.班级：");
            int num = sc.nextInt();
            System.out.println("1.添加信息");
            System.out.println("2.删除信息");
            System.out.println("3.修改信息");
            System.out.println("4.查看所有信息");
            System.out.println("其他.退出系统");
            int menue = sc.nextInt();
            if (menue == 1) {
                School.add(num);
            } else if (menue == 2) {
                School.remove(num);
            } else if (menue == 3) {
                School.update(num);
            } else if (menue == 4) {
                School.showAll(num);
            }
            else {
                System.out.println("谢谢使用!");
                break;
            }
        }
        conn.close();
    }
//主程序


    public static void add(int num) throws SQLException {//增加信息
        if (num==1) {
            System.out.println("------------添加学生信息---------------------");
            System.out.println("请输入您要添加的学生学号：");
            String stuId = sc.next();
            System.out.println("请输入您要添加的学生姓名：");
            String stuName = sc.next();
            System.out.println("请输入您要添加的学生性别：");
            String stuSex = sc.next();
            PreparedStatement ps = conn.prepareStatement(SQL1);
            ps.setString(1, stuId);
            ps.setString(2, stuName);
            ps.setString(3, stuSex);
            if (ps.executeUpdate() > 0) {
                System.out.println("添加成功!");
            } else {
                System.out.println("添加失败!");
            }
        }
        if (num==2) {
            System.out.println("------------添加班级信息---------------------");
            System.out.println("请输入您要添加的班级编号：");
            String classId = sc.next();
            System.out.println("请输入您要添加的班级内人数：");
            String stunum = sc.next();
            System.out.println("请输入您要添加的班级的进班时间（准确到小时即可）：");
            int Hour=sc.nextInt();
            LocalTime time= LocalTime.of(Hour,00);
            PreparedStatement ps = conn.prepareStatement(SQL5);
            ps.setString(1, classId);
            ps.setString(2, stunum);
            ps.setTime(3, Time.valueOf(time));
            if (ps.executeUpdate() > 0) {
                System.out.println("添加成功!");
            } else {
                System.out.println("添加失败!");
            }
        }
    }
    public static void remove(int num) throws SQLException {//删除信息
        if(num==1) {
            PreparedStatement ps2 = conn.prepareStatement(SQL2);
            System.out.println("------------删除学生信息---------------------");
            System.out.println("请输入您要删除的学生学号");
            int id = sc.nextInt();
            ps2.setInt(1, id);
            if (ps2.executeUpdate() > 0) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
        }
        else if(num==2){
            PreparedStatement ps2 = conn.prepareStatement(SQL6);
            System.out.println("------------删除班级信息---------------------");
            System.out.println("请输入您要删除班级的编号");
            int id = sc.nextInt();
            ps2.setInt(1, id);
            if (ps2.executeUpdate() > 0) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
        }
    }
    public static void update(int num) throws SQLException {//修改信息
        if(num==1) {
            PreparedStatement ps3 = conn.prepareStatement(SQL3);
            System.out.println("------------修改学生信息---------------------");
            System.out.println("请输入您要修改信息的学生的学号");
            String id3 = sc.next();
            System.out.println("请输入修改后的学生姓名：");
            String stuName3 = sc.next();
            System.out.println("请输入修改后的学生性别：");
            String stuSex3 = sc.next();
            ps3.setString(3, id3);
            ps3.setString(1, stuName3);
            ps3.setString(2, stuSex3);
            if (ps3.executeUpdate() > 0) {
                System.out.println("修改成功！");
            } else {
                System.out.println("修改失败!");
            }
        }
        if(num==2) {
            PreparedStatement ps3 = conn.prepareStatement(SQL7);
            System.out.println("------------修改班级信息---------------------");
            System.out.println("请输入您要修改信息的班级的编号");
            String id3 = sc.next();
            System.out.println("请输入修改后的班级人数：");
            String classnum3 = sc.next();
            System.out.println("请输入您要添加的班级的进班时间（准确到小时即可）：");
            int Hour=sc.nextInt();
            LocalTime time= LocalTime.of(Hour,00);
            ps3.setString(3, id3);
            ps3.setString(1, classnum3);
            ps3.setTime(2, Time.valueOf(time));
            if (ps3.executeUpdate() > 0) {
                System.out.println("修改成功！");
            } else {
                System.out.println("修改失败!");
            }
        }
    }
    public static void showAll(int num) throws SQLException {//查询所有信息
        if (num == 1) {
            PreparedStatement ps4 = conn.prepareStatement(SQL4);
            System.out.println("------------所有学生信息---------------------");
            ResultSet resultSet = ps4.executeQuery();
            System.out.println("------------查询结果---------------------");
            while (resultSet.next()) {
                System.out.println("学号：" + resultSet.getString(1) + "，" + "姓名：" + resultSet.getString(2) + "，" + "性别：" + resultSet.getString(3));
            }
        }
        if (num == 2) {
            PreparedStatement ps4 = conn.prepareStatement(SQL8);
            System.out.println("------------所有班级信息---------------------");
            ResultSet resultSet = ps4.executeQuery();
            System.out.println("------------查询结果---------------------");
            while (resultSet.next()) {
                System.out.println("编号：" + resultSet.getString(1) + "，" + "人数：" + resultSet.getString(2) + "，" + "时间：" + resultSet.getTime(3));
            }
        }
    }
}
