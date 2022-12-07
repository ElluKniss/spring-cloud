package com.dw.factory.absfactory;

public class Test {

    public static void main(String[] args)
    {
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractPA pa = factory1.productA();
        AbstractPB pb = factory1.productB();
        pa.produceA();
        pb.produceB();

        AbstractFactory factory2 = new ConcreteFactory2();
        pa = factory2.productA();
        pb = factory2.productB();
        pa.produceA();
        pb.produceB();
    }
}
