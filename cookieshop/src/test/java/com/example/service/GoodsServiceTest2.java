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
class GoodsServiceTest2 {
    @Mock
    private GoodsDao goodsDao;
    @InjectMocks
    private GoodsService goodsService;
    /*@BeforeEach
    // 在每个测试方法执行之前初始化模拟对象
    public void setUp() {
        MockitoAnnotations.openMocks(this); // 初始化Mockito注解
    }*/
    @Test
    void selectGoodsByTypeID() throws SQLException {
        Goods goods1 = new Goods(45,"玫瑰舒芙蕾");
        Goods goods2 = new Goods(53,"半熟芝士");
        Goods goods3 = new Goods(181,"1234");
        List<Goods> expGoods = Arrays.asList(goods1,goods2,goods3);

        when(goodsDao.selectGoodsByTypeID(11,1,5)).thenReturn(expGoods);
        List<Goods> outGoods = goodsService.selectGoodsByTypeID(11, 1, 5);
        assertEquals(3,outGoods.size());
    }
}