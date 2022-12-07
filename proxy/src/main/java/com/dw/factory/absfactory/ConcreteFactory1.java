package com.dw.factory.absfactory;

/**
 * 每个具体工厂类可以创建多个具体产品的实例
 */
public class ConcreteFactory1 implements AbstractFactory{
    @Override
    public AbstractPA productA() {
        return new ProductA1();
    }

    @Override
    public AbstractPB productB() {
        return new ProductB1();
    }
}

class ConcreteFactory2 implements AbstractFactory{

    @Override
    public AbstractPA productA() {
        return new ProductA2();
    }

    @Override
    public AbstractPB productB() {
        return new ProductB2();
    }
}
