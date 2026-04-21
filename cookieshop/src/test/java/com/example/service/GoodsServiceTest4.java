package com.example.service;

import com.example.dao.GoodsDao;
import com.example.model.Goods;
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
class GoodsServiceTest4 {

    @Mock
    private GoodsDao goodsDao;
    @InjectMocks
    private GoodsService goodsService;

    @Test
    void selectGoodsByTypeID() throws SQLException {
        Goods goods1=new Goods(45,"√µπÂ Ê‹Ω¿Ÿ");
        Goods goods2=new Goods(53,"∞Î Ï÷• ø");
        List<Goods> expGoods = Arrays.asList(goods1,goods2);
        when(goodsDao.selectGoodsByTypeID(11,1,5)).thenReturn(expGoods);
        List<Goods> outGoods=goodsService.selectGoodsByTypeID(11,1,5);
        assertEquals(2,outGoods.size());
    }
}