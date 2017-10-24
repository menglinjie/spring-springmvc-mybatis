package com.mlj.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

public class ImageUtil {
    public static byte[] getInstance(int width, int height, int num, String number) throws Exception {
        byte[] codeArr = null;
         /*
         * 一,绘图
         */
        //step1,内存映像对象(画布)
        BufferedImage image =
                new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //step2,获得画笔
        Graphics g = image.getGraphics();

        //step3,给画笔设置颜色
        Random r = new Random();
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

        //step4,给画布设置背景颜色
        g.fillRect(0, 0, width, height);

        //step5,重新给笔设置颜色
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font(null, Font.ITALIC, height - height / 5));

        //step6,将验证码转换成图片
        g.drawString(number, width / 10, height - height / 5);
        //step7,加一些干扰线
        for (int i = 0; i < 8; i++) {
            g.setColor(new Color(r.nextInt(255),
                    r.nextInt(255), r.nextInt(255)));
            g.drawLine(r.nextInt(width), r.nextInt(height),
                    r.nextInt(width), r.nextInt(height));
        }
        /*
         * 二,压缩图片并输出
         */
        //1.字节数组输出流，向字节数组中输出信息
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //2.压缩图片
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);

        //图片中的二进制信息输出到内存中
        encoder.encode(image);

        codeArr = baos.toByteArray();

        return codeArr;
    }


    //获取随机数
    public static String getNumber(int num) {
        String number = "";
        String chars = "0123456789";
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            number += chars.charAt(
                    r.nextInt(chars.length()));
        }
        return number;
    }
}
