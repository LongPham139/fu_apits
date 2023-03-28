package com.apits.apitssystembackend.utils;

import org.hibernate.dialect.MySQL57Dialect;

public class MySQLCustom extends MySQL57Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";
    }
}
