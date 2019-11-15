package org.wangqian.introduction.tools.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * <p>ClassName: IoTool</p>
 * <p>Description: 流工具</p>
 *
 * @author wangqian
 * @date 2018-03-01 16:10
 */
public class IoTool {

    /**
     * 默认的数组大小
     */
    private static final int DEFAULT_ARRAY_SIZE = 1024;

    /**
     * 输入流转字符串读取, 不对输入流进行关闭操作
     *
     * @param in      InputStream 输入流
     * @param charset 字符格式
     * @return String
     * @throws Exception 异常
     */
    public static String InputStreamToString(InputStream in, String charset) throws Exception {
        return new String(InputStreamToBytes(in), charset).trim();
    }


    /**
     * 输入流转字符串读取, 不对输入流进行关闭操作
     *
     * @param in InputStream 输入流
     * @return byte[]
     * @throws Exception 异常
     */
    public static byte[] InputStreamToBytes(InputStream in) throws Exception {

        //变量声明
        ByteArrayOutputStream outArray = new ByteArrayOutputStream();
        byte[] tempArray = new byte[DEFAULT_ARRAY_SIZE];
        int len;
        byte[] data;

        //批量读取数据
        while ((len = in.read(tempArray)) != -1) {
            outArray.write(tempArray, 0, len);
//            tempArray = new byte[DEFAULT_ARRAY_SIZE];
        }

        //排除空数据
        len = 0;
        for (byte b : tempArray) {
            if (b == 0) {
                break;
            }
            len++;
        }

        outArray.write(tempArray, 0, len);
        data = outArray.toByteArray();
        outArray.close();
        return data;
    }

}
