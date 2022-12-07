package com.dw.factory.commonfactory;

import java.util.Scanner;

public class FactoryTest {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入具体工厂类名");
        String name = scanner.next();
        System.out.println(name);

        Class<?> aClass = Class.forName("com.dw.factory." + name);
        IFactory factory = (IFactory)aClass.newInstance();
        System.out.println(factory);

        IProduct product = factory.createProduct();
        product.specific();
    }
}
