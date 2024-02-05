package com.dw.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunctionCommon {

    public static List<Integer> odd(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        ArrayList<Integer> odds = new ArrayList<>();
        for (int ele : list) {
            if (ele % 2 != 0) odds.add(ele);
        }
        return odds;
    }

    public static List<Integer> even(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        ArrayList<Integer> evens = new ArrayList<>();
        for (int ele : list) {
            if (ele % 2 == 0) evens.add(ele);
        }
        return evens;
    }

    //判断集合
    public static List<Integer> test(List<Integer> list, Predicate<Integer> predicate) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer e : list) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static boolean integerWithSingular (Integer haha){
        return haha % 2 != 0;
    }

    public static void main(String[] args) {
        //broker镜像实体，
        //producer消息生产者
        //exchange交换机，通过binding绑定队列

        //指定队列queue
        //consumer消费者
        //channel信道
        //
        Predicate<Integer> p = s -> s>2;
        List<Integer> test = test(Arrays.asList(1, 2, 3, 4, 5), p);
        System.out.println(test);


    }

}
