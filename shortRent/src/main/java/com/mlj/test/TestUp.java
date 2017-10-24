package com.mlj.test;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

public class TestUp {
//
//    @Test
//    public void test1(){
//        String a = request.getRequestURI();
//        System.out.println(a);
//    }

    //    @RequestMapping(value = "/up")
//    public void img_upload_compress(@RequestParam("picture") MultipartFile picture,
//                                    HttpServletRequest request,
//                                    HttpServletResponse response) {
//        //如果文件不为空，写入上传路径
//        if (!picture.isEmpty()) {
//            System.out.println(request.getServletContext().getRealPath("/image/"));
//            //上传文件路径
//            String path = request.getServletContext().getRealPath("/image/");
//            //上传文件名
//            String filename = picture.getOriginalFilename();
//            File filepath = new File(path, filename);
//            //判断路径是否存在，如果不存在就创建一个
//            if (!filepath.getParentFile().exists()) {
//                filepath.getParentFile().mkdirs();
//            }
//            //将上传文件保存到一个目标文件当中
//            try {
//                // 先尝试压缩并保存图片
//                Thumbnails.of(picture.getInputStream()).scale(1f).outputQuality(0.25f).toFile(new File(path + File.separator + filename));
//               // picture.transferTo(new File(path + File.separator + filename));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            String filename = picture.getOriginalFilename(); // 图片名
//        System.out.println(request.getRequestURL());
//        File dest = new File(filename,"http://localhost:8080/sr/"); // 保存位置
//
//        try {
//            // 先尝试压缩并保存图片
//            Thumbnails.of(picture.getInputStream()).scale(1f).outputQuality(0.25f).toFile(dest);
//        } catch (IOException e) {
//            try {
//                // 失败了再用springmvc自带的方式
//                picture.transferTo(dest);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
}
