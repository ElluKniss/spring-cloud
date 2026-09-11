package com.dw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dw.domain.StockSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StockSummaryMapper extends BaseMapper<StockSummary> {

    /**
     * 根据物料名称 + 规格查询库存
     */
    @Select("SELECT * FROM t_stock_summary WHERE product_name = #{productName} AND model = #{model}")
    StockSummary selectByProductNameAndModel(@Param("productName") String productName,
                                             @Param("model") String model);

    /**
     * 带乐观锁更新
     */
    @Update("UPDATE t_stock_summary SET " +
            "total_in = #{totalIn}, " +
            "total_out = #{totalOut}, " +
            "current_stock = #{currentStock}, " +
            "last_in_date = #{lastInDate}, " +
            "last_out_date = #{lastOutDate}, " +
            "version = version + 1 " +
            "WHERE id = #{id} AND version = #{version}")
    int updateByIdWithVersion(StockSummary stock);
}
