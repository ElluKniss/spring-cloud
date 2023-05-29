package com.dw;

import java.io.*;

public class CustomClassLoader extends ClassLoader {

    @lombok.SneakyThrows
    @Override
    public Class<?> loadClass(String name) {

        byte[] bytes = null;
        try (InputStream is = new FileInputStream("F:/Main.class")) {
            try {
                bytes = cypher(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Class<?> aClass = defineClass("com.dw.Main", bytes, 0, bytes.length);
        return aClass;

        //读取指定位置的class文件
        //解密
    }

    byte[] cypher(InputStream is) throws IOException {
        int b;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((b = is.read()) != -1) {
            //取反
            //1111 1111
            bos.write(b ^ 0xff);
        }

        return bos.toByteArray();
    }

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
//        FileInputStream fis = new FileInputStream("E:/Main.class");
//        FileOutputStream fos = new FileOutputStream("F:/Main.class");
//        int b;
//        while ((b = fis.read()) != -1) {
//            //取反
//            //1111 1111
//            fos.write(b ^ 0xff);
//        }
        Class<?> aClass = new CustomClassLoader().loadClass("123");
        Object o = aClass.newInstance();
        System.out.println(o);



    }
}
