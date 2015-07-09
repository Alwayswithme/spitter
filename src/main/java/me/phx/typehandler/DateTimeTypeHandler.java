package me.phx.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.*;

/**
 * @author ye
 */
@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class DateTimeTypeHandler extends BaseTypeHandler<DateTime> {

    public static final DateTimeZone TIME_ZONE = DateTimeZone.getDefault();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
            return;
        }
        ps.setTimestamp(i, null);
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        return ts == null ? null : new DateTime(ts.getTime(), TIME_ZONE);
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        return ts == null ? null : new DateTime(ts.getTime(), TIME_ZONE);
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        return ts == null ? null : new DateTime(ts.getTime(), TIME_ZONE);
    }
}
