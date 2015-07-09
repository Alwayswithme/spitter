package me.phx.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.joda.time.LocalDate;

import java.sql.*;

/**
 * @author ye
 */
@MappedJdbcTypes(JdbcType.DATE)
public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setDate(i, new Date(parameter.toDateTimeAtStartOfDay().toDate().getTime()));
            return;
        }
        ps.setTimestamp(i, null);
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Date ts = rs.getDate(columnName);
        return ts == null ? null : new LocalDate(ts.getTime());
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Date ts = rs.getDate(columnIndex);
        return ts == null ? null : new LocalDate(ts.getTime());
    }

    @Override
    public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Date ts = cs.getDate(columnIndex);
        return ts == null ? null : new LocalDate(ts.getTime());
    }
}
