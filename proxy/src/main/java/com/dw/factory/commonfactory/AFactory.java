package com.dw.factory.commonfactory;

public class AFactory implements IFactory{
    @Override
    public IProduct createProduct() {
        return new AProduct();
    }
}
