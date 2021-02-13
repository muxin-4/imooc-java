package com.imooc.spring.ioc.bookshop.dao;

public class BookDaoOraclelmpl implements BookDao {
    public void insert() {
        System.out.println("向 oracle book 表中插入一条数据");
    }
}
