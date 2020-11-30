package com.dark.learn.jdbc.service.impl;

import com.dark.learn.jdbc.dao.ProductDao;
import com.dark.learn.jdbc.entity.Product;
import com.dark.learn.jdbc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * project_name: jdbc
 * package: com.dark.learn.jdbc.service.impl
 * describe: 实现类
 *
 * @author : xue.teng
 * creat_date: 2020-11-30
 * creat_time: 15:32
 **/
@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return productDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Product record) {
        return productDao.insert(record);
    }

    @Override
    public int insertSelective(Product record) {
        return productDao.insertSelective(record);
    }

    @Override
    public int insertBatch(List<Product> record) {
        return productDao.insertBatch(record);
    }

    @Override
    public Product selectByPrimaryKey(Long id) {
        return productDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Product record) {
        return productDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        return productDao.updateByPrimaryKeySelective(record);
    }
}
