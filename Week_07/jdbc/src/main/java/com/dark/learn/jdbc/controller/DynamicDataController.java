/**
 * @author teng.xue
 * @date 2020/12/1
 * @since
 **/
package com.dark.learn.jdbc.controller;

import com.dark.learn.jdbc.conf.CustomDataSource;
import com.dark.learn.jdbc.conf.DataSourceType;
import com.dark.learn.jdbc.entity.Product;
import com.dark.learn.jdbc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@RequestMapping("/dynamic")
@Controller
@Slf4j
public class DynamicDataController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/insertRecord")
    @ResponseBody
    @CustomDataSource(DataSourceType.FIRST)
    public String insertRecord() {
        Product product=null;
        Long startTime=System.currentTimeMillis();
        for (int i=0;i<1;i++){
            product=new Product();
            product.setName("测试商品"+i);
            product.setPrice(20000L);
            product.setStock(100);
            product.setVersion(1);
            product.setCreateTime(new Date());
            product.setUpdateTime(new Date());
            productService.insert(product);
        }
        Long endTime=System.currentTimeMillis();
        Long spendTime=endTime-startTime;
        System.out.println("insertRecord-》花费的时间"+spendTime);
        return "花费的时间"+spendTime;
    }


}
