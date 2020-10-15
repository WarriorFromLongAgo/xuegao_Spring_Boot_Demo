package com.xuegao.springboot_tool.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：RedPackage
 * <br/> @Description：spring-boot-seckill   spring-boot-seckill spring-boot-seckill spring-boot-seckill
 * <br/> @author：xuegao
 * <br/> @date：2020/10/15 20:58
 */
public class RedPackage {

    // 红包。其实使用了 延迟消息的思想
    // neety 时间轮实现延迟任务
    public static void main(String[] args) {
        List<Integer> amountList = RedPackage(10000, 5);
        for (Integer amount : amountList) {
            System.out.println("搶到金額：" + new BigDecimal(amount).divide(new BigDecimal(100)) + "元");
        }
    }

    public static List<Integer> RedPackage(int TotalAmount, Integer TotalPeople) {
        List<Integer> amountList = new ArrayList<>();
        Integer restAmount = TotalAmount;//剩餘金額
        Integer restPeople = TotalPeople;//剩餘人數
        Random random = new Random();
        for (int i = 0; i < TotalPeople - 1; i++) {
            System.out.println(restPeople);
            //隨機範圍：[1,剩餘人均金額的兩倍]
            int amount = random.nextInt(restAmount / restPeople * 2 - 1) + 1;
            restAmount -= amount;
            restPeople--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }
}