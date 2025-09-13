package com.stronger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author stronger
 * @version release-1.0.0
 * @class DemoTest.class
 * @department Platform R&D
 * @date 2025/9/11
 * @desc do what?
 */
public class DemoTest {

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            try{

                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:2881/test?user=root&password=Cola@2025");
                System.out.println(connection.getAutoCommit());
                Statement sm = connection.createStatement();
                //新建表 t_meta_form
                sm.executeUpdate("CREATE TABLE t_meta_form (name varchar(36) , id int)");
                //插入数据
                sm.executeUpdate("insert into t_meta_form values ('an','1')");
                //查询数据，并输出结果
                ResultSet rs = sm.executeQuery("select * from t_meta_form");
                while (rs.next()) {
                    String name = rs.getString("name");
                    String id = rs.getString("id");
                    System.out.println(name + ','+ id);
                }
                //删除表
                sm.executeUpdate("drop table t_meta_form");

            }catch(SQLException se){
                System.out.println("error!");
                se.printStackTrace() ;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
