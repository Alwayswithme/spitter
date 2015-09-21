package me.phx;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

public class LombokPlugin extends PluginAdapter {
    private FullyQualifiedJavaType equalsAndHashCode;
    private FullyQualifiedJavaType noArgsConstructor;
//    private FullyQualifiedJavaType accessors;

    public LombokPlugin() {
        super();
        equalsAndHashCode = new FullyQualifiedJavaType("lombok.EqualsAndHashCode");
        noArgsConstructor = new FullyQualifiedJavaType("lombok.NoArgsConstructor");
//        accessors = new FullyQualifiedJavaType("lombok.experimental.Accessors");
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
        topLevelClass.addImportedType(equalsAndHashCode);
        topLevelClass.addImportedType(noArgsConstructor);
//        topLevelClass.addImportedType(accessors);

        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = false)");
//        topLevelClass.addAnnotation("@Accessors(chain = true)");
    }
}
