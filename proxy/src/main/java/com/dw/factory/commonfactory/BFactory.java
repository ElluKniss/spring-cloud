package com.dw.factory.commonfactory;

public class BFactory implements IFactory{
    @Override
    public IProduct createProduct() {
        return new BProduct();
    }
}
