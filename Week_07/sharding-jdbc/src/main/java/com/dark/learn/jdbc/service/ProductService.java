package com.dark.learn.jdbc.service;

import com.dark.learn.jdbc.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductService {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    int insertBatch(List<Product> record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}