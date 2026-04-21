package com.example.dao;

import com.example.model.Goods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class GoodsDaoTest3 {

    @Test
    public void testGetGoodsById() throws SQLException {

        String excepted="意大利芝士";
        String actual=new GoodsDao().getGoodsById(21).getName();
        Assertions.assertEquals(excepted,actual);
    }
    @Test
    public void getCountOfGoodsByTypeID() throws SQLException {
        int expected=148;
        int actual=new GoodsDao().getCountOfGoodsByTypeID(0);
        Assertions.assertEquals(expected,actual);

    }
    @Test
    public void getCountOfGoodsByTypeID2() throws SQLException {
        int expected2=10;
        int actual2=new GoodsDao().getCountOfGoodsByTypeID(1);
        Assertions.assertEquals(expected2,actual2);
    }
    @Test
    public void getCountOfGoodsByTypeID3() throws SQLException {
        int expected=148;
        int actual=new GoodsDao().getCountOfGoodsByTypeID(0);
        Assertions.assertEquals(expected,actual);
    }
    static Stream<Arguments> data(){
        return Stream.of(
               Arguments.of(0,148) ,
                Arguments.of(1,20)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void getCountOfGoodsByTypeID4(int input,int exp) throws SQLException {
        Assertions.assertEquals(exp,new GoodsDao().getCountOfGoodsByTypeID(input));
    }
}