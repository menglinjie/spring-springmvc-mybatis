package com.mlj.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Test {

    @org.junit.Test
    public void testMd5() {
        String pass = "111";
        try {

            String pass1 = Md5Util.getEncryptedPwd(pass);
            String pass2 = Md5Util.getEncryptedPwd(pass);
            Boolean b =Md5Util.validPasswd(pass,pass1);
            System.out.println(b);

            System.out.println(pass1);
            System.out.println(pass2);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
