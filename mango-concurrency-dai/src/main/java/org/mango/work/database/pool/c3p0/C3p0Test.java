package org.mango.work.database.pool.c3p0;

import junit.framework.TestCase;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class C3p0Test extends TestCase {


    public void testCon(){
        try {
            C3p0ConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


/*
    public void testConByJDBC() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.getConnection("jdbc:mysql://localhost:3306/mango","root","root");
    }
*/


}
