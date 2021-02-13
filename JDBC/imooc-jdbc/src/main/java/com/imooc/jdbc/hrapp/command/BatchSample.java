package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * JDBC中的事务控制
 */
public class BatchSample {
    // 标准方式插入若干数据
    private static void tc1() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            long startTime = new Date().getTime();
            conn = DbUtils.getConnection();
            // JDBC默认使用自动提交模式
            conn.setAutoCommit(false); // 关闭自动提交
            String sql = "insert into employee(eno, ename,salary, dname) values(?,?,?,?)";
            for (int i = 100000; i < 200000; i++) {
//                if(i == 1005) {
//                    throw new RuntimeException("插入失败");
//                }

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.setString(2, "员工" + i);
                pstmt.setFloat(3, 4000f);
                pstmt.setString(4, "市场部");
                pstmt.executeUpdate();
            }
            conn.commit(); // 提交数据
            long endTime = new Date().getTime();
            System.out.println("tc1()执行时长：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            DbUtils.closeConnection(null, pstmt, conn);
        }
    }

    // 批处理插入若干数据
    private static void tc2() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            long startTime = new Date().getTime();
            conn = DbUtils.getConnection();
            // JDBC默认使用自动提交模式
            conn.setAutoCommit(false); // 关闭自动提交
            String sql = "insert into employee(eno, ename,salary, dname) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            for (int i = 200000; i < 300000; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "员工" + i);
                pstmt.setFloat(3, 4000f);
                pstmt.setString(4, "市场部");
//                pstmt.executeUpdate();
                pstmt.addBatch(); // 将参数加入批处理任务
            }
            pstmt.executeBatch(); // 执行批处理任务
            conn.commit(); // 提交数据
            long endTime = new Date().getTime();
            System.out.println("tc2()执行时长：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            DbUtils.closeConnection(null, pstmt, conn);
        }
    }

    public static void main(String[] args) {
        tc1();
        tc2();
    }
}
