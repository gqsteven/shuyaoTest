package com.shuyao.dynamicdatasource;

/**
 * 增加多数据源，在此配置
 *
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-04 22:26:02
 */
public enum DataSourceContext {
    FIRST("first"),
    SECOND("second");

    private String name;

    DataSourceContext(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
