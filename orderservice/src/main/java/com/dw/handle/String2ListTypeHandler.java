package com.dw.handle;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.dw.domain.Product;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class String2ListTypeHandler implements TypeHandler<List<Product>> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<Product> tList, JdbcType jdbcType) throws SQLException {

        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(tList);
        preparedStatement.setString(i, jsonArray.toString());
    }

    @Override
    public List<Product> getResult(ResultSet resultSet, String s) throws SQLException {

        System.out.println("result {}"+ resultSet);
        String ss = resultSet.getString(s);
        System.out.println(ss);
//        JSONObject jsonObject = new JSONObject(ss);
//        System.out.println(jsonObject);

        JSONArray jsonArray = JSONUtil.parseArray(ss);
        System.out.println(jsonArray);


//        jsonArray.toArray(array);

        return JSONUtil.toList(jsonArray, Product.class);
    }

    @Override
    public List<Product> getResult(ResultSet resultSet, int i) throws SQLException {



//        jsonArray.toArray(array);
        return null;
    }

    @Override
    public List<Product> getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
