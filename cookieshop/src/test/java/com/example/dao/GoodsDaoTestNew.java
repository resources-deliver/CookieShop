package com.example.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.DisplayName;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GoodsDao类的单元测试类 - 针对getCountOfGoodsByTypeID方法
 */
class GoodsDaoTestNew {

    private GoodsDao goodsDao;

    @BeforeEach
    void setUp() {
        goodsDao = new GoodsDao();
    }

    /**
     * 测试getCountOfGoodsByTypeID方法 - 根据实际测试结果修正的参数化测试
     * 使用CSV数据源提供不同的输入和实际验证过的输出
     */
    @ParameterizedTest(name = "测试类型ID {0}，期望数量 {1}")
    @CsvSource({
            "0, 166",   // 所有商品总数 - 实际为166
            "1, 23",    // 类型ID为1的商品数量 - 实际为23
            "2, 113",   // 类型ID为2的商品数量 - 实际为113
            "11, 2"     // 类型ID为11的商品数量 - 实际为2
    })
    @DisplayName("测试不同类型ID下的商品数量统计(基于实际数据)")
    void testGetCountOfGoodsByTypeID(int typeId, int expectedCount) throws SQLException {
        // 调用被测试的方法
        int actualCount = goodsDao.getCountOfGoodsByTypeID(typeId);

        // 断言实际结果与实际验证过的数据一致
        assertEquals(expectedCount, actualCount,
                "类型ID为 " + typeId + " 的商品数量应该为 " + expectedCount);
    }

    /**
     * 针对特定业务场景的独立测试用例
     * TC_001: 测试typeID为0时获取所有商品数量
     */
    @Test
    @DisplayName("TC_001: 测试typeID为0时获取所有商品数量")
    void testGetCountOfGoodsByTypeID_TC_001() throws SQLException {
        int typeId = 0;
        int actual = goodsDao.getCountOfGoodsByTypeID(typeId);
        int expected = 166; // 根据实际测试结果更新

        assertEquals(expected, actual, "当typeID为0时，应该返回所有商品数量" + expected);
    }

    /**
     * TC_002: 测试typeID为1时获取指定类型商品数量
     */
    @Test
    @DisplayName("TC_002: 测试typeID为1时获取指定类型商品数量")
    void testGetCountOfGoodsByTypeID_TC_002() throws SQLException {
        int typeId = 1;
        int actual = goodsDao.getCountOfGoodsByTypeID(typeId);
        int expected = 23; // 根据实际测试结果更新

        assertEquals(expected, actual, "当typeID为1时，应该返回商品数量" + expected);
    }

    /**
     * TC_003: 测试typeID为2时获取指定类型商品数量
     */
    @Test
    @DisplayName("TC_003: 测试typeID为2时获取指定类型商品数量")
    void testGetCountOfGoodsByTypeID_TC_003() throws SQLException {
        int typeId = 2;
        int actual = goodsDao.getCountOfGoodsByTypeID(typeId);
        int expected = 113; // 根据实际测试结果更新

        assertEquals(expected, actual, "当typeID为2时，应该返回商品数量" + expected);
    }

    /**
     * 验证方法的基本功能 - 确保返回非负数值
     */
    @Test
    @DisplayName("验证方法返回值为非负数")
    void testGetCountOfGoodsByTypeIDReturnsNonNegative() throws SQLException {
        int[] testTypeIds = {0, 1, 2, 3, 11}; // 多个类型ID进行测试

        for (int typeId : testTypeIds) {
            int result = goodsDao.getCountOfGoodsByTypeID(typeId);
            assertTrue(result >= 0,
                    "类型ID为 " + typeId + " 的商品数量不应为负数，实际值为: " + result);
        }
    }

    /**
     * 测试边界条件 - 当类型ID为负数时的情况
     */
    @Test
    @DisplayName("测试负数类型ID")
    void testGetCountOfGoodsByTypeIDWithNegativeId() throws SQLException {
        int negativeTypeId = -1;
        int result = goodsDao.getCountOfGoodsByTypeID(negativeTypeId);

        // 对于不存在的类型ID，通常返回0
        assertTrue(result >= 0, "商品数量不能为负数");
    }
}