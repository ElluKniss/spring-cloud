package com.dw.local;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefineThreadLocal<T> {
    private T initVal;
    private static final int HASHCODE_INIT = 0;
    private static final int HASH_INCREMENT = 0x61c88647;

    public final int threadLocalHashCode = nextCode();

    private int nextCode(){
        return HASHCODE_INIT+ HASH_INCREMENT;
    }

    // 直接定义数组无法扩容自定义hash
    private static final Map<Thread, DefineArray> localMap = new ConcurrentHashMap<>();

    public DefineThreadLocal() {
    }

    public DefineThreadLocal(T initVal) {
        this.initVal = initVal;
    }



    public void set(T t) {
        Thread thread = Thread.currentThread();
        // 获取当前线程维护的数组
        DefineArray defineArray = localMap.get(thread);
        if (null != defineArray) {
            defineArray.set(this, t);
        }else{
            localMap.put(thread, new DefineArray(this, t));
        }
    }

    public T get(){
        Thread thread = Thread.currentThread();
        DefineArray defineArray = localMap.get(thread);
        DefineArray.DEntry node = defineArray.getNode(this);
        if(null != node){
            return (T)node.value;
        }
        return null;
    }

    public void remove(){
        Thread thread = Thread.currentThread();
        DefineArray defineArray = localMap.get(thread);
        if(null != defineArray){
            defineArray.remove(this);
        }
    }
}
