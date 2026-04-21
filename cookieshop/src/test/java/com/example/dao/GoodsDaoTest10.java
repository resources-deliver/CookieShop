package com.example.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GoodsDaoTest10 {
    private GoodsDao goodsDao;

    @BeforeEach
    void setUp() {
        //初始化工作，准备工作
        goodsDao=new GoodsDao();
    }

    @AfterEach
    void tearDown() {
        //收尾工作
    }

    @Test
    void getCountOfGoodsByTypeID() throws SQLException {
        int exp=148;
        int actual=goodsDao.getCountOfGoodsByTypeID(0);
        Assertions.assertEquals(exp,actual);
    }
    @Test
    void getCountOfGoodsByTypeID2() throws SQLException {
        int exp=20;
        int actual=goodsDao.getCountOfGoodsByTypeID(1);
        Assertions.assertEquals(exp,actual);
    }
    static Stream<Arguments> data(){
        return Stream.of(
                Arguments.of(0,148),
                Arguments.of(1,20)
        );
    }
    @ParameterizedTest
    @MethodSource("data")
    void getCountOfGoodsByTypeID3(int input,int exp) throws SQLException{
        Assertions.assertEquals(exp,goodsDao.getCountOfGoodsByTypeID(input));

    }
}