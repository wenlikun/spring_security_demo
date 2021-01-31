package com.benbird.bencenter.handler;

import com.google.gson.Gson;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/27
 * 描述:
 * @author Admin
 */
public class JsonTypeHandler<T extends Object> extends BaseTypeHandler<T> {

    private static final Gson mapper = new Gson();
    private Class<T> clazz;

    public JsonTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType)
            throws SQLException {
        preparedStatement.setString(i, this.toJson(t));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return this.toObject(resultSet.getString(columnName), clazz);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return this.toObject(resultSet.getString(columnIndex), clazz);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return this.toObject(callableStatement.getString(columnIndex), clazz);
    }

    private String toJson(T object) {
        try {
            return mapper.toJson(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private T toObject(String content, Class<?> clazz) {
        if (content != null && !content.isEmpty()) {
            try {
                return (T) mapper.fromJson(content, clazz);
            } catch (Exception e) {
                System.out.println(content);
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

}
