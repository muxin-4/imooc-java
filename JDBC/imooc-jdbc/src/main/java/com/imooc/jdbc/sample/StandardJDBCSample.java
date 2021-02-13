package com.imooc.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 标准JDBC操作五步骤
 */
public class StandardJDBCSample {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://localhost/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";

        try {
            //1.记载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.创建数据库连接
            conn = DriverManager.getConnection(
                    url,
                    "root",
                    "52Baobao."
            );
            //3.创建Statement对象
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee");
            //4.遍历查询结果
            while (rs.next()) {
                Integer eno = rs.getInt(1); // eno
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null && conn.isClosed() == false) {
                    //5.关闭连接，释放资源
                    conn.close();
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }
}
