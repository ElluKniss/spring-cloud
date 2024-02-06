package com.dw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        short s1 = 1;
        s1 +=  1;
        System.out.println("Hello world!");
        Integer[] aa ={1,2,3,4,5,6};

        int[] bb = {4,5,6};
        List<Integer> ints = Arrays.asList(aa);
        ArrayList arrayList = new ArrayList<>(ints);
        arrayList.remove(3);
    }
}