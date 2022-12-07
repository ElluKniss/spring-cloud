package com.dw.factory.commonfactory;

/**
 * 抽象产品类，派生多个具体产品，只能由对应的工厂创建
 */
public interface IProduct {
    void specific();
}
