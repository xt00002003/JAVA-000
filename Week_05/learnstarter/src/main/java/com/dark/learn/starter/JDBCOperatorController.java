package com.dark.learn.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;

/**
 * project_name: learnstarter
 * package: com.dark.learn.starter
 * describe: jdbc操作示例
 *
 * @author : xue.teng
 * creat_date: 2020-11-18
 * creat_time: 16:56
 **/
@Controller
@RequestMapping("/jdbc")
@Slf4j
public class JDBCOperatorController {
    @Autowired
    private DataSourceProperties dataSourceProperties;

    /**
     * 使用jdbc添加
     */
    @RequestMapping("/add")
    public void add() {
        String driverName = dataSourceProperties.getDriverClassName();
        String sql = "insert into test_name(nick_name)values(?)";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword());
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "张三");
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @RequestMapping("/update")
    public void update() {
        String driverName = dataSourceProperties.getDriverClassName();
        String sql = "update  test_name set nick_name=? where  id=?";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword());
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "李四");
            preparedStatement.setInt(2, 1);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @RequestMapping("/select")
    public void select() {
        String driverName = dataSourceProperties.getDriverClassName();
        String sql = "select nick_name from test_name where id=?";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword());
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                System.out.print("昵称：" + result.getString("nick_name"));

            }
            result.close();//关闭结果集
            preparedStatement.close();//关闭数据库操作
            con.close();//关闭数据库连接
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                result.close();//关闭结果集
                preparedStatement.close();//关闭数据库操作
                con.close();//关闭数据库连接
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @RequestMapping("/batch")
    public void batch() {
        String driverName = dataSourceProperties.getDriverClassName();
        String sql = "insert into test_name(nick_name)values(?)";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword());
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement(sql);
            for (int i = 0; i < 100; i++) {
                preparedStatement.setString(1, "张三" + i);
                preparedStatement.addBatch();
            }
            int[] result = preparedStatement.executeBatch();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


}
