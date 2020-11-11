package week3;

import java.util.concurrent.CompletableFuture;

/**
 * project_name: learn-architecture
 * package: week3
 * describe: 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author : xue.teng
 * creat_date: 2020-11-10
 * creat_time: 19:01
 **/
public class Homework03_1 {
    public static void main(String[] args) throws Exception{
        //使用CompletableFuture 实现获取结果
        CompletableFuture<Integer> future =CompletableFuture.supplyAsync(()->{
            int i=0;
            i++;
            return i;
        });
        Integer result=future.get();
        System.out.println("获取的结果是："+result);
        System.out.println("程序结束");
    }
}
