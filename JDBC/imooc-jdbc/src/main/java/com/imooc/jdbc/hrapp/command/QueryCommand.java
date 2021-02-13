package com.imooc.jdbc.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class QueryCommand implements Command {
    @Override
    public void execute() {
        System.out.println("请输入部门名称：");
        Scanner in = new Scanner(System.in);
        String pdname = in.nextLine();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1. 加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
            // 2. 创建数据库连接
            conn = DriverManager.getConnection(url, "root", "52Baobao.");
            // 3. 创建`Statement对象
            stmt = conn.createStatement();
            // 结果集
            System.out.println("select * from employee where dname='" + pdname + "'");
            rs = stmt.executeQuery("select * from employee where dname='" + pdname + "'");
            // 4. 遍历查询结果
            // rs.next()返回布尔值，代表是否存在下一条记录
            // 如果有，返回true，同时结果集提取下一条记录
            // 如果没有，返回false，循环就会停止
            while (rs.next()) {
                Integer eno = rs.getInt(1); // JDBC中字段索引从1开始，而非0
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 5. 关闭连接释放资源
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }
}
