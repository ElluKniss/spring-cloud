package com.dw.util;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.function.Predicate;


public class AESBase64Util {

    public static String encrypt(String sSrc, String sKey) throws Exception {
        if(validateKey(sKey,s -> s.length() == 16)){
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
            //CBC模式需要配置偏移量，设置一个向量，达到密码唯一性，增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec("wNSOYIB1k1DjY5lA".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return Base64.encodeBase64String(encrypted);
        }

        return null;

    }

    // 解密
    public static String decrypt(String sSrc, String sKey) throws Exception {
        try {

            if(!validateKey(sKey,s -> s.length() == 16)){
                return null;
            }

            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //CBC模式需要配置偏移量，设置这个后，不会出来同一个明文加密为同一个密文的问题，达到密文唯一性
            IvParameterSpec iv = new IvParameterSpec("wNSOYIB1k1DjY5lA".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decodeBase64(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }


    public static boolean validateKey(String p, Predicate<String> predicate){
        return predicate.test(p);
    }


    public static void main(String[] args) throws Exception {

        byte[] bytes = "wNSOYIB1k1DjY5lA".getBytes();
        System.out.println(bytes);
//        String DeString = AESBase64Util.encrypt("{\"ip\":\"114.255.201.224\"}", "19dbae994b164fd9");
//        System.out.println("解密后的字串是：" + DeString);
    }
}
