package me.phx.config;

import me.phx.model.DeviceType;
import me.phx.model.HouseSize;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author phoenix
 */
@MapperScan("me.phx.mybatis.mapper")
@Configuration
public class MybatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        String resources = "mybatis-config.xml";
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(new ClassPathResource(resources));
        sqlSessionFactory.setDataSource(dataSource);
//        sqlSessionFactory.setTypeAliasesPackage("me.phx.model");
//        TypeHandler<DeviceType> typeEnumHandler = new EnumOrdinalTypeHandler<>(DeviceType.class);
//        TypeHandler<HouseSize> sizeEnumHandler = new EnumOrdinalTypeHandler<>(HouseSize.class);
//        TypeHandler<?>[] handlers = Arrays.asList(
//                typeEnumHandler,
//                sizeEnumHandler
//        ).toArray(new TypeHandler<?>[]{});
//        sqlSessionFactory.setTypeHandlers(new TypeHandler[]{typeEnumHandler, sizeEnumHandler});
//        Resource[] mapperLocations = new Resource[] {
//                new ClassPathResource("me/phx/mybatis/mapper/PersonMapper.xml")
//        };
//        sqlSessionFactory.setMapperLocations(mapperLocations);

        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
