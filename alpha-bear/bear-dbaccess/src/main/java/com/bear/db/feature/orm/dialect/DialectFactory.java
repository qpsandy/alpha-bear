package com.bear.db.feature.orm.dialect;

import org.apache.ibatis.session.Configuration;

/**
 * 数据库方言工厂,产生方言对象
 * 
 * @author StarZou
 * @since 2014年5月18日 下午1:32:23
 **/
public class DialectFactory {

    public static String dialectClass = null;

    public static AbstractDialect buildDialect(Configuration configuration) {
        if (dialectClass == null) {
            synchronized (DialectFactory.class) {
                if (dialectClass == null) {
                    dialectClass = configuration.getVariables().getProperty("dialectClass");
                }
            }
        }
        AbstractDialect dialect = null;
        try {
            dialect = (AbstractDialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("请检查 mybatis-config.xml 中  dialectClass 是否配置正确?");
        }
        return dialect;
    }
}
