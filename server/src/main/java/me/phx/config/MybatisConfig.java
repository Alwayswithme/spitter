package me.phx.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Set;

/**
 * @author phoenix
 */
@MapperScan("me.phx.mybatis.mapper")
@Configuration
@Slf4j
public class MybatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setFailFast(true);
        sqlSessionFactory.setTypeAliasesPackage("me.phx.model");
        sqlSessionFactory.setTypeHandlersPackage("me.phx.mybatis.handler");

        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getObject().getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<>();
        resolverUtil.find(new ResolverUtil.IsA(Enum.class), "me.phx.model");
        Set<Class<? extends Class<?>>> typeSet = resolverUtil.getClasses();
        for(Class<?> type : typeSet){
            typeHandlerRegistry.register(type, new EnumOrdinalTypeHandler(type));
            if (log.isDebugEnabled()) {
                log.debug("Scanned type: '" + type + "' use enum ordinal type handler");
            }
        }

        return sqlSessionFactory.getObject();
    }

}
