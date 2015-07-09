package me.phx.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.*;

import java.sql.*;

/**
 * @author ye
 */
public class LocalTimeTypeHandler extends BaseTypeHandler<LocalTime> {

    public static final DateTimeZone TIME_ZONE = DateTimeZone.getDefault();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalTime parameter, JdbcType jdbcType) throws SQLException {
        parameter.getMillisOfDay();
        if (parameter != null) {
            DateTime dateTime = new LocalDate(new Instant(0)).toDateTime(parameter);
            ps.setTime(i, new Time(dateTime.toDate().getTime()));
            return;
        }
        ps.setTimestamp(i, null);
    }

    @Override
    public LocalTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Time time = rs.getTime(columnName);
        return time == null ? null : new LocalTime(time);
    }

    @Override
    public LocalTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Time time = rs.getTime(columnIndex);
        return time == null ? null : new LocalTime(time);
    }

    @Override
    public LocalTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Time time = cs.getTime(columnIndex);
        return time == null ? null : new LocalTime(time);
    }
}
