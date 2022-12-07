package com.dw.local;

import java.lang.ref.WeakReference;

public class DefineArray {

    static class DEntry extends WeakReference<DefineThreadLocal<?>> {
        Object value;

        public DEntry(DefineThreadLocal<?> key, Object obj) {
            super(key);
            this.value = obj;
        }
    }

    // 初始容量
    private static final int INITIAL_CAPACITY = 4;
    // 元素数量
    private int size;
    // 元素节点
    private DEntry[] table;
    // 扩容因子
    private int threshold;

    //初始化1
    DefineArray(DefineThreadLocal<?> key, Object t) {
        table = new DEntry[INITIAL_CAPACITY];
        //计算下标
        int index = key.threadLocalHashCode & (INITIAL_CAPACITY - 1);
        table[index] = new DEntry(key, t);
        size = 1;
        threshold = INITIAL_CAPACITY;
    }

    /**
     * 根据local获取节点
     */
    DEntry getNode(DefineThreadLocal<?> key) {
        // 计算下标
        int index = key.threadLocalHashCode & (table.length - 1);

        DEntry e = table[index];
        // 有值并且没有被覆盖
        if (null != e && e.get() == key) {
            return table[index];
        }
        //冲突或者节点为空(删除节点后有rehash,此处不会存在空)
        return getNodeAfter(index, e, key);
    }

    private DEntry getNodeAfter(int index, DEntry e, DefineThreadLocal<?> key) {

        //由于采取线性寻址,发生hash冲突的节点必定连续,只要往后遍历到空结束
        while (null != e) {
            DefineThreadLocal<?> k = e.get();
            if (null == k) {
                //探测清除
                expungeStaleEntry(index);
            }
            if (key == k) {
                return e;
            } else{
                index = nextIndex(index, table.length);
            }
            e = table[index];
        }

        return null;
    }

    /**
     * 发现key==null需要进行探测清理，遍历直到Entry节点为空，返回该位置
     * @param staleSlot key为空的位置
     */
    private int expungeStaleEntry(int staleSlot) {
        //清空当前位置
        table[staleSlot].value = null;
        table[staleSlot] = null;
        size--;

        DEntry e;
        int i;
        int len = table.length;
        for (i = nextIndex(staleSlot, len);
             (e = table[i] )!= null; i = nextIndex(i, len)){
            DefineThreadLocal<?> k = e.get();
            if (null == k){
                e.value = null;
                table[i] = null;
                size--;
            }else {
                //当前位置节点转移到rehash后的位置
                int hash = k.threadLocalHashCode & (len - 1);
                if(hash != i){
                    table[i] = null;
                    while (table[hash] != null)
                        hash = nextIndex(hash, len);
                    table[hash] = e;
                }
            }
        }
        return i;
    }

    void set(DefineThreadLocal<?> key, Object o) {
        int len = table.length;
        int index = key.threadLocalHashCode & (len - 1);
        //for循环定义一个节点;判断节点;下一个节点,赋值index
        for (DEntry e = table[index]; e != null; e = table[index = nextIndex(index, len)]) {
            DefineThreadLocal<?> k = e.get();
            //判断下标位置是否为空,不为空,判断是否属于覆盖行为
            if (k == key) {
                e.value = o;
            }
            if (null == k) {

                // 防止后续改为弱引用出现内存泄露
            }
            //,否则继续完后遍历
        }
        //直到有空的位置
        table[index] = new DEntry(key, o);
        size++;
        //检查是否需要扩容
        if (size >= threshold * 3 / 4) {
            System.out.println("扩容1次");
            resize();
        }
    }

    /**
     * 计算下个下标
     *
     * @param i 当前位置
     * @return 下标
     */
    public int nextIndex(int i, int len) {
        return i + 1 >= len ? 0 : i + 1;
    }

    void resize() {
        //2倍扩容
        DEntry[] oldDe = table;
        int oldLen = oldDe.length;
        int newLen = oldLen * 2;
        DEntry[] newTable = new DEntry[newLen];

        //初始话size
        int count = 0;
        for (DEntry node : oldDe) {
            if (null != node) {
                //计算下标
                int i = node.get().threadLocalHashCode & (newLen - 1);
                //找到空位置
                while (null != newTable[i])
                    //需要用新数组长度
                    i = nextIndex(i, newLen);
                newTable[i] = node;
                count++;
            }
        }
        //赋值新的数组
        table = newTable;
        //更新size
        size = count;
        //更新扩容阈值基数
        threshold = newLen;
    }

    /**
     * 当前local
     *
     * @param key 删除
     */
    public void remove(DefineThreadLocal<?> key) {
        DEntry[] tab = table;
        int len = tab.length;
        //hash获取下标
        int i = key.threadLocalHashCode & (len - 1);
        //遍历
        for (DEntry e = tab[i]; null != e; e = tab[i = nextIndex(i, len)]) {
            DefineThreadLocal<?> k = e.get();
            //确定是要删除的节点
            if (key == k) {
                e.clear();
                //探测清理
                //ex:[3]位置有值,插入计算新元素,位置相同,线性寻址找到位置[4];当[3]被删除后,如果此时更新[4]的元素,更具hash值找到[3]位置,操作变为新增
                expungeStaleEntry(i);
                return;
            }
        }
    }

    /**
     * 删除节点后,需要rehash该节点后所有连续节点
     * 先去计算hash值,检查对应的位置;如果为空直接插入,
     * 如果不为空,往后寻址
     * 并将原先的置空
     *
     * @param slot 起始位置
     */
    public void rehash(int slot) {
        int len = table.length;
        int hash = 0;
        while (table[slot] != null){
            //操作该节点
            hash = (table[slot].get().threadLocalHashCode & (len - 1));
            //找空位置
            while(table[hash] != null){
                hash = nextIndex(hash, len);
            }
            table[hash] = table[slot];
            table[slot] = null;
            slot = nextIndex(slot, len);
        }
    }
}
