package com.dw.factory.commonfactory;

/**
 * 工厂方法模式：只有一个抽象产品类，具体工厂只能创建一个产品实例
 */
public interface IFactory {
    IProduct createProduct();
}
