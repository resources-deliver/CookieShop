package com.example.service;

import com.example.dao.GoodsDao;
import com.example.model.Goods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoodsServiceTest5 {

    @Mock
    private GoodsDao goodsDao;

    @InjectMocks
    private GoodsService goodsService;

    @Test
    void selectGoodsByTypeID() throws SQLException {
        Goods goods1=new Goods(1,"À¶Ý®µ°¸â");
        Goods goods2=new Goods(2,"²ÝÝ®µ°¸â");
        List<Goods> exp= Arrays.asList(goods1,goods2);
        when(goodsDao.selectGoodsByTypeID(1,1,5)).thenReturn(exp);
        List<Goods> actual=goodsService.selectGoodsByTypeID(1,1,5);
        Assertions.assertEquals(2,actual.size());
    }
}