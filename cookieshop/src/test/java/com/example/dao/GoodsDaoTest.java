package com.example.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.sql.SQLException;
import java.util.stream.Stream;




class GoodsDaoTest {

    static Stream<Arguments> data(){
        return Stream.of(
               Arguments.of(0,158) ,
                Arguments.of(11,3),
                Arguments.of(2,103)
        );
    }

    //Juint5≤Œ ˝ªØ≤‚ ‘
    @ParameterizedTest
    @MethodSource("data")
    void getCountOfGoodsByTypeID(int input,int exp) throws SQLException {
        GoodsDao gd = new GoodsDao();
        Assertions.assertEquals(exp,gd.getCountOfGoodsByTypeID(input));
    }


}