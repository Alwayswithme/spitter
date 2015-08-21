package me.phx.mybatis;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @author ye
 */
public class TypeAliasPlugin extends PluginAdapter {

    private FullyQualifiedJavaType typeAlias;

    public TypeAliasPlugin() {
        super();
        typeAlias = new FullyQualifiedJavaType("org.apache.ibatis.type.Alias");
    }

    /**
     * This plugin is always valid - no properties are required
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }


    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        addTypeAlias(topLevelClass, introspectedTable);
        return true;
    }

    private void addTypeAlias(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType(typeAlias);

        String aliasStr = String.format("@Alias(\"%s\")", topLevelClass.getClass().getSimpleName());
        topLevelClass.addAnnotation(aliasStr);
    }
}
