package me.phx.config;

import me.phx.model.Device;
import me.phx.model.House;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author phoenix
 */
@MapperScan("me.phx.mapper")
@Configuration
public class MybatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        String resources = "mybatis-config.xml";
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
//        sqlSessionFactory.setConfigLocation(new ClassPathResource(resources));
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("me.phx.model");

//        sqlSessionFactory.setTypeHandlers(new TypeHandler[]{typeEnumHandler, sizeEnumHandler});
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getObject().getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        typeHandlerRegistry.register(Device.DeviceType.class, new EnumOrdinalTypeHandler<>(Device.DeviceType.class));
        typeHandlerRegistry.register(House.HouseSize.class, new EnumOrdinalTypeHandler<>(House.HouseSize.class));
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
