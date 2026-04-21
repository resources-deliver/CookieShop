package com.example.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class GoodsDaoTest4 {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCountOfGoodsByTypeID() throws SQLException {
        int exp=200;
        int actual=new GoodsDao().getCountOfGoodsByTypeID(0);
        Assertions.assertEquals(exp,actual);
    }

    @Test
    void getCountOfGoodsByTypeID2() throws SQLException {
        int exp=20;
        int actual=new GoodsDao().getCountOfGoodsByTypeID(1);
        Assertions.assertEquals(exp,actual);
    }
}