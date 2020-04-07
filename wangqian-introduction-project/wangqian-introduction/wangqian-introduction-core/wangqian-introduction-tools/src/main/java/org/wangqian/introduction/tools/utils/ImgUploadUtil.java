package org.wangqian.introduction.tools.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Random;

@Configuration
public class ImgUploadUtil {
    private static GetEnvironmentUtil getEnvironmentUtil;

    @Autowired
    public void setGetEnvironmentUtil(GetEnvironmentUtil getEnvironmentUtil) {
        ImgUploadUtil.getEnvironmentUtil = getEnvironmentUtil;
    }

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    public static String uploadImg(MultipartFile file) {
        FileOutputStream imgOut = null;
        String resultPath = null;
        try {
            String imgUploadPath = getEnvironmentUtil.getEnv("imgUploadPath");
            //查看文件加是否存在，不存在就新建
            File dir = new File(imgUploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 获取原文件名称
            String fileName = file.getOriginalFilename();
            //获取文件名后缀
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //生成新文件名
            String imgName = DateUtil.format(new Date(), "yyyyMMddkkmmssSSS") + "-" +
                    (new Random().nextInt(89) + 10) + "-" + suffix;

            //将图片写入文件夹下
            imgOut = new FileOutputStream(new File(dir, imgName));
            imgOut.write(file.getBytes());// 返回一个字节数组文件的内容

            //返回的图片路径
            resultPath = "img/trade/" + imgName;

            return resultPath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != imgOut) {
                try {
                    imgOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultPath;
    }

    /**
     * 图片下载
     * img/trade/20190909153340924-78-.jpg
     *
     * @param path 图片路径
     * @return
     */
    public static byte[] downloadImg(String path) {
        FileInputStream fileInputStream = null;
        try {
            String imgUploadPath = getEnvironmentUtil.getEnv("imgUploadPath");
            //查看文件加是否存在，不存在就新建
            File dir = new File(imgUploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 获取原文件名称
            String fileName = path;
            String prefix = "img/trade";
            if (fileName.contains(prefix)) {
                fileName = fileName.substring(fileName.indexOf(prefix) + prefix.length());
            }

            //获取图片文件
            fileInputStream = new FileInputStream(new File(dir, fileName));
            FastByteArrayOutputStream read = IoUtil.read(fileInputStream);
            byte[] bytes = read.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(fileInputStream);
        }
        return null;
    }

    /**
     * 获取图片base64
     *
     * @param path 文件地址
     * @return
     */
    public static String downloadImgBase64(String path) {
        byte[] bytes = downloadImg(path);
        return Base64.encode(bytes);
    }

    /**
     * 获取文件名后缀
     *
     * @param path 文件地址
     * @return
     */
    public static String getSuffix(String path) {
        if (StringUtils.isBlank(path)) {
            return "";
        }
        return path.substring(path.lastIndexOf("."));
    }
}
