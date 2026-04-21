package com.example.service;

import com.example.dao.GoodsDao;
import com.example.model.Goods;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoodsServiceTest3 {
    @Mock
    private GoodsDao goodsDao;//模拟GoodsDao
    @InjectMocks
    private GoodsService goodsService;//注入GoodsDao

    @Test
    void getGoodsById() throws SQLException {
        //准备测试数据
        Goods expGoods = new Goods(21,"意大利芝士饼干");
        //模拟行为
        when(goodsDao.getGoodsById(21)).thenReturn(expGoods);
        //执行测试
        Goods outGoods = goodsService.getGoodsById(21);
        //验证结果
        assertEquals("意大利芝士饼干",outGoods.getName());

    }
}