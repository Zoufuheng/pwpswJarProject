package com.verifycode;

import com.pwpew.utils.VerifyCode;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author miaoyin
 * @date 2020/5/7 - 18:20
 * @commet:
 */
public class VerifyCodeTest {
    @Test
    public void test1() throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage bi = vc.getImage();
        VerifyCode.output(bi, new FileOutputStream("F:/xxx.jpg"));
        System.out.println(vc.getText());
    }
}
