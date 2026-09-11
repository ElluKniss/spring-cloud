package com.dw.handle;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.dw.domain.Product;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class String2ListTypeHandler implements TypeHandler<List<Product>> {

    private static final Logger log = LoggerFactory.getLogger(String2ListTypeHandler.class);

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<Product> tList, JdbcType jdbcType) throws SQLException {
        if (tList == null) {
            preparedStatement.setNull(i, Types.VARCHAR);
            return;
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(tList);
        preparedStatement.setString(i, jsonArray.toString());
    }

    @Override
    public List<Product> getResult(ResultSet resultSet, String s) throws SQLException {
        String json = resultSet.getString(s);
        return parseProductList(json);
    }

    @Override
    public List<Product> getResult(ResultSet resultSet, int i) throws SQLException {
        String json = resultSet.getString(i);
        return parseProductList(json);
    }

    @Override
    public List<Product> getResult(CallableStatement callableStatement, int i) throws SQLException {
        String json = callableStatement.getString(i);
        return parseProductList(json);
    }

    private List<Product> parseProductList(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            JSONArray jsonArray = JSONUtil.parseArray(json);
            return JSONUtil.toList(jsonArray, Product.class);
        } catch (Exception e) {
            log.error("productlist JSON解析失败：{}", json, e);
            return new ArrayList<>();
        }
    }
}
