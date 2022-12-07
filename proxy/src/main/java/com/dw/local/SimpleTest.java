package com.dw.local;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleTest {

    private static final DefineThreadLocal<String> tl = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl2 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl3 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl4 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl5 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl6 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl7 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl8 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl9 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl10 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl11 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl12 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl13 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl14 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl15 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl16 = new DefineThreadLocal<>();
    private static final DefineThreadLocal<String> tl17 = new DefineThreadLocal<>();
//    private static final DefineThreadLocal<Long> tl2 = new DefineThreadLocal<>(1L);

    public static void main(String[] args) {

        AtomicBoolean flag = new AtomicBoolean(true);
        AtomicInteger i = new AtomicInteger(0);

        while (flag.get()) {
            int andAdd = i.getAndAdd(1);
            new Thread(() -> {
                tl.set("dw : " + Thread.currentThread().getName());
                tl2.set("dw2 : " + Thread.currentThread().getName());
                tl3.set("dw3 : " + Thread.currentThread().getName());
                tl4.set("dw4 : " + Thread.currentThread().getName());
                tl.remove();
                tl2.remove();
                tl3.remove();
                tl4.set("daiwei");
//                tl5.set("dw5 : " + Thread.currentThread().getName());
//                tl6.set("dw6 : " + Thread.currentThread().getName());
//                tl7.set("dw7 : " + Thread.currentThread().getName());
//                tl8.set("dw8 : " + Thread.currentThread().getName());
//                tl9.set("dw9 : " + Thread.currentThread().getName());
//                tl10.set("dw10 : " + Thread.currentThread().getName());
//                tl11.set("dw11 : " + Thread.currentThread().getName());
//                tl12.set("dw12 : " + Thread.currentThread().getName());
//                tl13.set("dw13 : " + Thread.currentThread().getName());
//                tl14.set("dw14 : " + Thread.currentThread().getName());
//                tl15.set("dw15 : " + Thread.currentThread().getName());
//                tl16.set("dw16 : " + Thread.currentThread().getName());
//                tl17.set("dw17 : " + Thread.currentThread().getName());
//                tl2.set(21+ Thread.currentThread().getId());
                System.out.println(andAdd + tl.get());
                System.out.println(andAdd + tl2.get());
                System.out.println(andAdd + tl3.get());
                System.out.println(andAdd + tl4.get());
//                System.out.println(andAdd + "-" + tl2.get());
            }).start();

            if (andAdd == 0) {
                flag.set(false);
            }
        }

    }
}
