package com.wangqian.utils.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * <p>ClassName: ImageProcessUtil</p>
 * <p>Description: 图像处理工具
 * 调整图片大小, 支持等比例压缩, 支持GIF
 * 采用平滑处理, 图片处理后清晰度高
 * </p>
 *
 * @author wangqian
 * @date 2018-01-23 17:55
 */
public class ImageProcessUtil {

    private enum ImageType {
        PNG("PNG"),
        JPG("JPG"),
        JPEG("JPEG"),
        GIF("GIF");
        private String title;

        private ImageType(String title) {
            this.title = title;
        }
    }


    /**
     * <方法描述>
     */
    public static void main(String[] args) {
        File file1 = new File("G:\\c.jpg");
        File file2 = new File("G:\\c1test.jpg");

        try {
            if (!file2.exists()) {
                file2.createNewFile();
            }

            String formatName = file1.getName().substring(file1.getName().lastIndexOf(".") + 1);
            InputStream input = new FileInputStream(file1);
            OutputStream output = new FileOutputStream(file2);
            ImageProcess(input, output, formatName, 400, 400, true);
            input.close();
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 图片处理
     * <方法描述>
     *
     * @param input      输入流
     * @param output     输出流
     * @param width      目标宽度
     * @param height     目标长度
     * @param proportion 是否等比例
     * @return 图像处理结果
     */
    public static boolean ImageProcess(InputStream input, OutputStream output, String formatName, int width, int height, boolean proportion) {
        return ImageProcess(input, output, formatName, width, height, 0, 0, proportion);
    }

    /**
     * 图片处理
     * <方法描述>
     *
     * @param input      输入流
     * @param output     输出流
     * @param width      目标宽度
     * @param height     目标长度
     * @param x          起始坐标x
     * @param y          起始坐标y
     * @param proportion 是否等比例
     * @return 图像处理结果
     */
    public static boolean ImageProcess(InputStream input, OutputStream output, String formatName, int width, int height, int x, int y, boolean proportion) {
        if (input == null) {
            throw new IllegalArgumentException("input == null!");
        }
        if (output == null) {
            throw new IllegalArgumentException("output == null!");
        }
        try {
            //gif格式图片处理
            if (ImageType.GIF.title.equalsIgnoreCase(formatName)) {
                GifDecoder decoder = new GifDecoder();
                AnimatedGifEncoder ag = new AnimatedGifEncoder();
                if (decoder.read(input) == 0) {
                    //获取gif每一帧
                    BufferedImage frame;
                    ag.start(output);
                    int delay; //每一帧延迟时间
                    for (int i = 0; i < decoder.getFrameCount(); i++) {
                        frame = decoder.getFrame(i);
                        frame = ImageCompress(frame, width, height, x, y, proportion);
                        frame = cutImage(frame, width, height);
                        //每一帧的延迟时间
                        delay = decoder.getDelay(i);
                        ag.addFrame(frame);
                        ag.setDelay(delay);
                    }
                    ag.closeStream = false;
                    return ag.finish();
                }
                //gif读取失败
                return false;
            }
            //图片压缩
            BufferedImage image = ImageCompress(ImageIO.read(input), width, height, x, y, proportion);
            //图片裁剪
            image = cutImage(image, width, height);

            return ImageIO.write(image, formatName, output);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 图片压缩<方法描述>
     *
     * @param image      BufferedImage
     * @param width      目标宽度
     * @param height     目标长度
     * @param x          起始坐标x
     * @param y          起始坐标y
     * @param proportion 是否等比例
     * @return BufferedImage
     */
    private static BufferedImage ImageCompress(BufferedImage image, int width, int height, int x, int y, boolean proportion) {
        if (image == null) {
            return null;
        }
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        if (width == 0 || height == 0) {
            return image;
        }

        //如果原图宽高小于目标宽高不压缩
        if (imageWidth < width && imageHeight < height) {
            return image;
        }

        //是否等比例压缩
        if (proportion) {
            double scale1 = (imageWidth + 0.0001) / (width + 0.0001);
            double scale2 = (imageHeight + 0.0001) / (height + 0.0001);
            double scale = scale1 < scale2 ? scale1 : scale2;
            width = (int) (((double) imageWidth) / scale);
            height = (int) (((double) imageHeight) / scale);
        }

        BufferedImage tagImage = new BufferedImage(width, height, image.getType());

        //使用平滑优先处理图片
        tagImage.getGraphics().drawImage(
                image.getScaledInstance(width, height, Image.SCALE_SMOOTH), x, y, null);

        return tagImage;

    }

    /**
     * 图片裁剪
     * <方法描述>
     *
     * @param image  BufferedImage
     * @param width  裁剪宽度
     * @param height 裁剪长度
     * @return BufferedImage
     */
    private static BufferedImage cutImage(BufferedImage image, int width, int height) {
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();
        if (width == 0 || height == 0) {
            throw new IllegalArgumentException("width == " + width + " height == " + height + " 无法裁剪!");
        }
        double scale = (width + 0.0001) / (height + 0.0001);
        if (imgWidth < width || imgHeight < height) {
            if (imgWidth < imgHeight) {
                width = imgWidth;
                height = (int) (imgWidth / scale);
                height = height > imgHeight ? imgHeight : height;
            } else {
                height = imgHeight;
                width = (int) (height * scale);
                width = width > imgWidth ? imgWidth : width;
            }
        }
        return image.getSubimage((imgWidth - width) / 2, (imgHeight - height) / 2, width, height);
    }

}
