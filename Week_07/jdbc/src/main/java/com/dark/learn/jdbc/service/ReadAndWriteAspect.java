/**
 * @author teng.xue
 * @date 2020/12/6
 * @since
 * @description 用于读写分离的切面
 **/
package com.dark.learn.jdbc.service;

import com.dark.learn.jdbc.conf.CustomDataSource;
import com.dark.learn.jdbc.conf.DataSourceType;
import com.dark.learn.jdbc.conf.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

@Aspect
@Order(1)
@Component
@Slf4j
public class ReadAndWriteAspect {

    @Pointcut("execution(public * com.dark.learn.jdbc.service.impl..*.*(..))")
    public void rwAspect(){

    }

    /**
     * 对于select 开头的方法使用second 数据源
     * 对于其他开头的使用 first 数据源
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("rwAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        //获取
        Method method = signature.getMethod();
        String methodName=method.getName();
        log.info("获取到的service的方法签名是:"+methodName);
        if (ReadAndWriteAspect.matchReadMethod(methodName)){
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.SECOND.name());
        }else {
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.FIRST.name());
        }
        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 这里使用规则约定：约定select开头就是读数据方法
     * @return
     */
    private static boolean  matchReadMethod(String methodName){
        String pattern = "^select.*";
        boolean isMatch = Pattern.matches(pattern, methodName);
        return isMatch;
    }


}
