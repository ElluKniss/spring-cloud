package com.dw.factory.absfactory;

/**
 * 每个抽象产品派生多个具体产品
 */
public class ProductA1 implements AbstractPA{
    @Override
    public void produceA() {
        System.out.println("productA1");
    }
}

class ProductA2 implements AbstractPA{

    @Override
    public void produceA() {
        System.out.println("productA2");
    }
}

class ProductB1 implements AbstractPB{

    @Override
    public void produceB() {
        System.out.println("productB1");
    }
}

class ProductB2 implements AbstractPB{

    @Override
    public void produceB() {
        System.out.println("productB2");
    }
}
