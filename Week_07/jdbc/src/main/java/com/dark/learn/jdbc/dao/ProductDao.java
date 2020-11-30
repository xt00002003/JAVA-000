package com.dark.learn.jdbc.dao;

import com.dark.learn.jdbc.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDao {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    int insertBatch(List<Product> record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}