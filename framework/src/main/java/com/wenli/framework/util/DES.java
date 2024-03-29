package com.wenli.framework.util;

import android.text.TextUtils;
import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加密介绍
 * DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
 * 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DES {
    //测试
    public static void main(String args[]) {
        //待加密内容
        String str = "result";
        String password = "12345678";
        byte[] result = DES.encrypt(str.getBytes(),password);
        System.out.println("加密后："+new String(result));
        //直接将如上内容解密
        try {
            byte[] decryResult = DES.decrypt(result, password);
            System.out.println("解密后："+new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static String encryptToServer(String content) {
        try {
            byte[] result = DES.encrypt(content.getBytes(), "12345678");
            String temp = new String(Base64.encode(result, Base64.DEFAULT), "utf-8");
            return java.net.URLEncoder.encode(temp, "utf-8");
        } catch (Exception ex) {
            return "{result:false}";
        }
    }

    public static String decryptFromServer(String content) {
        String ss = "";
        try {
            byte[] temp = Base64.decode(content, Base64.DEFAULT);
            byte[] result = DES.decrypt(temp, "12345678");
            ss = new String(result, "utf-8");
            return ss;
        } catch (Exception ex) {
            LogUtil.e("-----ex=" + ex.getMessage());
            if (!TextUtils.isEmpty(ss)) {
                return ss;
            }
            return "{result:false,from:localdata}";
        }

    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
}
