package org.mango.work.database.pool.c3p0;

import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/1 0001.
 * c3p0连接池的使用
 */
public class C3p0ConnectionManager {

    private static final String DRIVER_CLASS = "driverClass";
    private static final String URL = "url";
    private static DataSource ds = null;


    static {
          initDB();
    }

    private static final void initDB() {
        Properties c3p0Properties = new Properties();
        try {
            InputStream im = C3p0ConnectionManager.class.getResourceAsStream("/c3p0.properties");
            c3p0Properties.load(C3p0ConnectionManager.class.getResourceAsStream("/c3p0.properties"));
        } catch (IOException e) {
             e.printStackTrace();
        }

        String driverClass = c3p0Properties.getProperty(DRIVER_CLASS);
        if(driverClass != null){
            try {
                Class.forName(driverClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Properties jdbcPropes = new Properties();
        Properties c3p0Propes = new Properties();
        for(Object key:c3p0Properties.keySet()){
            String skey = (String)key;
            if(skey.startsWith("c3p0.")){
                c3p0Propes.put(skey, c3p0Properties.getProperty(skey));
            }else{
                jdbcPropes.put(skey, c3p0Properties.getProperty(skey));
            }
        }

        try {
            String url = c3p0Properties.getProperty(URL);
            //建立连接
            DataSource unPool = DataSources.unpooledDataSource(url,jdbcPropes);
            ds = DataSources.pooledDataSource(unPool,c3p0Propes);
       } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection() throws SQLException {
        final Connection conn = ds.getConnection();
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return conn;
    }
}

