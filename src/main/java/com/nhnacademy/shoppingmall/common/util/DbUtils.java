package com.nhnacademy.shoppingmall.common.util;


import com.mysql.cj.xdevapi.Schema;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.Duration;

public class DbUtils {
    public DbUtils(){
        throw new IllegalStateException("Utility class");
    }

    private static final DataSource DATASOURCE;

    static {
        BasicDataSource basicDataSource = new BasicDataSource();

        try {
            basicDataSource.setDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //todo#1-1 {ip},{database},{username},{password} 설정
        basicDataSource.setUrl("jdbc:mysql://133.186.241.167:3306/nhn_academy_17");
        basicDataSource.setUsername("nhn_academy_17");
        basicDataSource.setPassword("e(kbT!u88]ReeHwL");


        //todo#1-2 initialSize, maxTotal, maxIdle, minIdle 은 모두 5로 설정합니다.
        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxTotal(5);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMinIdle(5);

        //todo#1-3 Validation Query를 설정하세요
        basicDataSource.setMaxWait(Duration.ofSeconds(5));
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setTestOnBorrow(false);

        //todo#1-4 적절히 변경하세요
        DATASOURCE = basicDataSource;

    }

    public static DataSource getDataSource(){
        return DATASOURCE;
    }

}
