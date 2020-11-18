/**
 * @author : xue.teng
 * @date 2020/11/18
 * @since
 **/
package com.dark.learn.starter;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;

@Controller
@RequestMapping("/hikari")
@Slf4j
public class HikariOperatorController {
    @Autowired
    private HikariDataSource dataSource;

    /**
     * 使用jdbc添加
     */
    @RequestMapping("/add")
    public void add(){

        String sql="insert into test_name(nick_name)values(?)";
        Connection con=null;
        PreparedStatement preparedStatement=null;
        try {

            con= dataSource.getConnection();
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,"张三");
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @RequestMapping("/update")
    public void update(){
        String sql="update  test_name set nick_name=? where  id=?";
        Connection con=null;
        PreparedStatement preparedStatement=null;
        try {
            con= dataSource.getConnection();
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,"李四");
            preparedStatement.setInt(2,1);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @RequestMapping("/select")
    public void select(){
        String sql="select nick_name from test_name where id=?";
        Connection con=null;
        PreparedStatement preparedStatement=null;
        ResultSet result=null;
        try {
            con= dataSource.getConnection();
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            result=preparedStatement.executeQuery();
            while(result.next()) {
                System.out.print("昵称："+result.getString("nick_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                result.close();//关闭结果集
                preparedStatement.close();//关闭数据库操作
                con.close();//关闭数据库连接
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
