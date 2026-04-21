package com.example.dao;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(Parameterized.class)
public class GoodsDaoTest2 {
    int input;
    int exp;
    public GoodsDaoTest2(int input,int exp){
        this.input=input;
        this.exp=exp;
    }
    @Parameters
    public  static Collection<?> data(){
        return Arrays.asList(new Object[][]{
                {0,158},{1,20},{2,103}
        });
    }
    @Test
    public void getCountOfGoodsByTypeID() throws SQLException {
        GoodsDao gd = new GoodsDao();
        Assertions.assertEquals(exp,gd.getCountOfGoodsByTypeID(input));
    }

}