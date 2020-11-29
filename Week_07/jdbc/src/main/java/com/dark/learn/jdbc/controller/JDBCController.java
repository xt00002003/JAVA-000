/**
 * @author teng.xue
 * @date 2020/11/29
 * @since
 **/
package com.dark.learn.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
@RequestMapping("/jdbc")
@Controller
public class JDBCController {
    @Autowired
    private DataSource dataSource;
    @RequestMapping("/insertRecord")
    @ResponseBody
    public String insertRecord() throws Exception{
        this.dataSource.getConnection();
        return "ok";
    }

}
