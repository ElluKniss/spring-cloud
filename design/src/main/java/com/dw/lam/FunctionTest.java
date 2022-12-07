package com.dw.lam;


import java.util.Comparator;
import java.util.function.*;

public class FunctionTest {

    /**
     * 消费型接口
     * @param s 入参
     * @param consumer 有入参，没有返回值
     */
    public static void invokeC(String s, Consumer<String> consumer){
        consumer.accept(s);
    }

    /**
     * 供给型接口
     * @param supplier 没有入参，有返回值
     * @return 返回值
     */
    public static <T> T invokeS(Supplier<T> supplier){
        return (T)supplier.get();
    }

    public static char invokeF(Character s, Function<Character, Integer> function, Function<Integer, Character> function2){
        return function2.apply(function.apply(s));
    }

    public static int invokeF2(int x, int y, BiFunction<Integer, Integer, Integer> function){
        return function.apply(x,y);
    }

    public static int invokeF3(int x, int y, Comparator<Integer> comparable){
        return BinaryOperator.maxBy(comparable).apply(x, y);
    }

    public static boolean invokeP(String p, Predicate<String> predicate){
        return predicate.test(p);
    }

    public static void main(String[] args) {
        invokeC("fff", (x) -> System.out.println(x));
        FunctionTest o = invokeS(FunctionTest::new);
        System.out.println(o);

        System.out.println(invokeF('a', x -> Integer.valueOf(x), y -> Character.lowSurrogate(y+1)));

        System.out.println(invokeF2(1,2, (x,y) -> x+y));

        Comparator<Integer> comparable = (x, y) -> x - y;
        int compare = (comparable).compare(1, 2);
        System.out.println(invokeF3(1, 2, (x, y) -> x - y));

        System.out.println(invokeP("美", x -> x.matches("[\u4e00-\u9fa5]")));
    }
}
