package me.phx.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ye
 */
public class IdEnumTypeHandler<E extends Enum<E> & IdEnum> extends BaseTypeHandler<E> {

    private Class<E> type;
    private final Map<Integer, E> idEnumMap;

    public IdEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;

        E[] enums = type.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }

        idEnumMap = new HashMap<>(enums.length);
        for (E e : enums) {
            idEnumMap.put(e.getId(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        return rs.wasNull() ? null : convert(i);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        return rs.wasNull() ? null : convert(i);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        return cs.wasNull() ? null : convert(i);
    }

    private E convert(int id) {
        E e = idEnumMap.get(id);
        if (e != null) {
            return e;
        }
        throw new IllegalArgumentException("Cannot convert " + id + " to " + type.getSimpleName() + " by id value.");
    }
}