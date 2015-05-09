package com.springapp.mvc.provider;


import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author phoenix
 */
public class PersonProvider {
    public String personAgeRange(final Map<String, Object> parameter) {
        SQL a = new SQL() {
            {
                SELECT("*");
                FROM("Person");
                if (parameter.containsKey("ageGt"))
                    WHERE("age > #{ageGt}");
                if (parameter.containsKey("ageLt"))
                    WHERE("age < #{ageLt}");

            }
        };
        return a.toString();
    }
}
