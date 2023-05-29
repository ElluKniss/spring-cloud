package com.dw.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

}
