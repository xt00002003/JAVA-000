/**
 * @author teng.xue
 * @date 2020/11/29
 * @description 测试不同方式插入数据库数据的时间
 * @since
 **/
package com.dark.learn.jdbc.controller;

import com.dark.learn.jdbc.entity.Product;
import com.dark.learn.jdbc.service.ProductService;
import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;

@RequestMapping("/jdbc")
@Controller
@Slf4j
public class JDBCController {

    @Autowired
    private ProductService productService;

    /**
     * 测试单次插入的时间
     * 花费的时间3322775
     * @return
     */
    @RequestMapping("/insertRecord")
    @ResponseBody
    public String insertRecord() {
        Product product=null;
        Long startTime=System.currentTimeMillis();
        for (int i=0;i<1000000;i++){
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

    /**
     * 测试批量插入的时间
     * insertBatch-》花费的时间57378
     * @return
     */
    @RequestMapping("/insertBatch")
    @ResponseBody
    public String insertBatch() {
        Product product=null;
        Long startTime=System.currentTimeMillis();
        List<Product> productList=null;
        for(int j=0;j<99;j++){
            productList=new ArrayList<>(10000);
            for (int i=0;i<10000;i++){
                product=new Product();
                product.setName("测试商品"+i);
                product.setPrice(20000L);
                product.setStock(100);
                product.setVersion(1);
                product.setCreateTime(new Date());
                product.setUpdateTime(new Date());
                productList.add(product);

            }
            productService.insertBatch(productList);
        }
        Long endTime=System.currentTimeMillis();
        Long spendTime=endTime-startTime;
        System.out.println("insertBatch-》花费的时间"+spendTime);
        return "花费的时间"+spendTime;
    }

    /**
     * 先把对象放入集合，然后使用多线程去插入
     * TODO
     * @return
     */
    @RequestMapping("/insertBatch2")
    @ResponseBody
    public String insertBatch2() {
        Product product = null;
        Long startTime = System.currentTimeMillis();
        List<Product> productList = null;
        Map<Integer, List<Product>> productMap = new ConcurrentHashMap<>(100);
        for (int j = 0; j < 99; j++) {
            productList = new ArrayList<>(10000);
            for (int i = 0; i < 10000; i++) {
                product = new Product();
                product.setName("测试商品" + i);
                product.setPrice(20000L);
                product.setStock(100);
                product.setVersion(1);
                product.setCreateTime(new Date());
                product.setUpdateTime(new Date());
                productList.add(product);

            }
            productMap.put(j, productList);
//            productService.insertBatch(productList);
        }
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        productMap.forEach((index, items) -> {
            final ListenableFuture<Integer> listenableFuture = service.submit(() -> {
                log.info("顺序是:" + index);
                Integer result = productService.insertBatch(items);
                return result;
            });
            Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer integer) {
                    log.info("执行的结果是:"+integer);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    log.error(throwable.getMessage());
                }
            });
        });







        Long endTime=System.currentTimeMillis();
        Long spendTime=endTime-startTime;
        System.out.println("insertBatch-》花费的时间"+spendTime);
        return "花费的时间"+spendTime;
    }

}
