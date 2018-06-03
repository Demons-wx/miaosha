package online.wangxuan.miaosha.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author wangxuan
 * @date 2018/5/30 下午2:54
 */

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass +
                salt.charAt(5) + salt.charAt(4);

        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass +
                salt.charAt(5) + salt.charAt(4);

        return md5(str);
    }

    public static String inputPassToDBPass(String input, String saltDB) {
        String formPass = inputPassToFormPass(input);
        return formPassToDBPass(formPass, saltDB);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDBPass("123456", "asdfghj"));
        System.out.println(inputPassToDBPass("www921105", "1a2b3c4d"));
    }
}
