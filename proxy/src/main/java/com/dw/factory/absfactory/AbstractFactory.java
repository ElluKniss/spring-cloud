package com.dw.factory.absfactory;

/**
 * 抽象工厂，派生多个具体工厂类
 */
public interface AbstractFactory {

    AbstractPA productA();

    AbstractPB productB();

}
