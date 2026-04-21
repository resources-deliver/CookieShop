package com.example.service;
import com.example.dao.GoodsDao;
import com.example.model.Goods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * 使用Mockito框架对GoodsService的selectGoodsByTypeID方法进行隔离测试
 */
@ExtendWith(MockitoExtension.class)
class GoodsServiceSelectGoodsByTypeIDTest {
    @Mock
    private GoodsDao goodsDao;
    @InjectMocks
    private GoodsService goodsService;
    private List<Goods> mockGoodsList;
    private Goods mockGood1;
    private Goods mockGood2;
    @BeforeEach
    void setUp() {
        // 准备测试数据
        mockGood1 = new Goods();
        mockGood1.setId(1);
        mockGood1.setName("商品1");
        mockGood2 = new Goods();
        mockGood2.setId(2);
        mockGood2.setName("商品2");
        mockGoodsList = new ArrayList<>();
        mockGoodsList.add(mockGood1);
        mockGoodsList.add(mockGood2);
    }

    /**
     * TC_001: 测试正常情况下按类型ID查询商品列表
     */
    @Test
    void testSelectGoodsByTypeID_NormalCase() throws SQLException {
        // 给定：设置模拟行为
        int typeId = 1;
        int pageNumber = 1;
        int pageSize = 10;
        when(goodsDao.selectGoodsByTypeID(typeId, pageNumber, pageSize))
                .thenReturn(mockGoodsList);
        // 当：调用被测试的方法
        List<Goods> result = goodsService.selectGoodsByTypeID(typeId, pageNumber, pageSize);
        // 那么：验证结果
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("商品1", result.get(0).getName());
        assertEquals("商品2", result.get(1).getName());
        // 验证DAO层方法被正确调用了一次
        verify(goodsDao, times(1)).selectGoodsByTypeID(typeId, pageNumber, pageSize);
    }
    /**
     * TC_002: 测试查询空结果集的情况
     */
    @Test
    void testSelectGoodsByTypeID_EmptyResult() throws SQLException {
        // 给定：设置模拟行为，返回空列表
        int typeId = 999; // 假设不存在的类型ID
        int pageNumber = 1;
        int pageSize = 10;
        when(goodsDao.selectGoodsByTypeID(typeId, pageNumber, pageSize))
                .thenReturn(new ArrayList<>());
        // 当：调用被测试的方法
        List<Goods> result = goodsService.selectGoodsByTypeID(typeId, pageNumber, pageSize);
        // 那么：验证结果
        assertNotNull(result);
        assertTrue(result.isEmpty());
        // 验证DAO层方法被正确调用了一次
        verify(goodsDao, times(1)).selectGoodsByTypeID(typeId, pageNumber, pageSize);
    }

    /**
     * TC_003: 测试SQL异常情况
     */
    @Test
    void testSelectGoodsByTypeID_WithSQLException() throws SQLException {
        // 给定：设置模拟行为，抛出SQLException
        int typeId = 1;
        int pageNumber = 1;
        int pageSize = 10;
        SQLException mockException = new SQLException("数据库连接错误");
        when(goodsDao.selectGoodsByTypeID(typeId, pageNumber, pageSize))
                .thenThrow(mockException);

        // 当：调用被测试的方法
        List<Goods> result = goodsService.selectGoodsByTypeID(typeId, pageNumber, pageSize);

        // 那么：验证返回null（因为方法中有try-catch块）
        assertNull(result);

        // 验证DAO层方法被正确调用了一次
        verify(goodsDao, times(1)).selectGoodsByTypeID(typeId, pageNumber, pageSize);
    }

    /**
     * TC_004: 测试参数传递的准确性
     */
    @Test
    void testSelectGoodsByTypeID_ParameterAccuracy() throws SQLException {
        // 给定：设置模拟行为
        int typeId = 5;
        int pageNumber = 3;
        int pageSize = 8;
        when(goodsDao.selectGoodsByTypeID(eq(typeId), eq(pageNumber), eq(pageSize)))
                .thenReturn(mockGoodsList);

        // 当：调用被测试的方法
        goodsService.selectGoodsByTypeID(typeId, pageNumber, pageSize);

        // 那么：验证参数被正确传递
        verify(goodsDao, times(1)).selectGoodsByTypeID(typeId, pageNumber, pageSize);
    }

    /**
     * TC_005: 测试不同类型的typeID参数
     */
    @Test
    void testSelectGoodsByTypeID_WithDifferentTypeIds() throws SQLException {
        // 给定：设置多种typeID的模拟行为
        when(goodsDao.selectGoodsByTypeID(0, 1, 10)).thenReturn(mockGoodsList);
        when(goodsDao.selectGoodsByTypeID(1, 1, 10)).thenReturn(mockGoodsList.subList(0, 1));

        // 当：测试typeID为0的情况
        List<Goods> result1 = goodsService.selectGoodsByTypeID(0, 1, 10);

        // 那么：验证typeID为0的结果
        assertNotNull(result1);
        assertEquals(2, result1.size());

        // 当：测试typeID为1的情况
        List<Goods> result2 = goodsService.selectGoodsByTypeID(1, 1, 10);

        // 那么：验证typeID为1的结果
        assertNotNull(result2);
        assertEquals(1, result2.size());

        // 验证DAO层方法被调用了两次
        verify(goodsDao, times(1)).selectGoodsByTypeID(0, 1, 10);
        verify(goodsDao, times(1)).selectGoodsByTypeID(1, 1, 10);
    }

    /**
     * TC_006: 验证方法的边界条件处理
     */
    @Test
    void testSelectGoodsByTypeID_BoundaryConditions() throws SQLException {
        // 给定：设置各种边界条件的模拟行为
        when(goodsDao.selectGoodsByTypeID(anyInt(), anyInt(), anyInt()))
                .thenReturn(mockGoodsList);

        // 当：测试边界值
        List<Goods> result1 = goodsService.selectGoodsByTypeID(-1, 1, 10); // 负数typeID
        List<Goods> result2 = goodsService.selectGoodsByTypeID(0, 1, 1);   // 最小page size
        List<Goods> result3 = goodsService.selectGoodsByTypeID(1, 1, 100); // 大page size

        // 那么：验证所有结果都不为null
        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);

        // 验证DAO层方法被调用了三次
        verify(goodsDao, times(3)).selectGoodsByTypeID(anyInt(), anyInt(), anyInt());
    }
}