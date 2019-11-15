package org.wangqian.introduction.tools.utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES {



    private static String puclickey = "20160918HSWL2018";
    private static String iv = "0123456789ABCDEF";//偏移量字符串必须是16位 当模式是CBC的时候必须设置偏移量
    private static String Algorithm = "AES";
    private static String AlgorithmProvider = "AES/CBC/PKCS5Padding"; //算法/模式/补码方式

    public static byte[] generatorKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm);
        keyGenerator.init(256);//默认128，获得无政策权限后可为192或256
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static IvParameterSpec getIv() throws UnsupportedEncodingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));
        //System.out.println("偏移量："+byteToHexString(ivParameterSpec.getIV()));
        return ivParameterSpec;
    }

    public static byte[] encrypt(String src, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);
        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] cipherBytes = cipher.doFinal(src.getBytes(Charset.forName("utf-8")));
        return cipherBytes;
    }

    public static byte[] decrypt(String src, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);

        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] hexBytes = hexStringToBytes(src);
        byte[] plainBytes = cipher.doFinal(hexBytes);
        return plainBytes;
    }

    /**
     * 将byte转换为16进制字符串
     * @param src
     * @return
     */
    public static String byteToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xff;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串装换为byte数组
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return b;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 加密
     * @param conect
     * @return
     */
    public static String JM(String conect) {
        byte key[] = new byte[0];
        String enc ="" ;
        try {
            key = puclickey.getBytes("utf-8");
            enc = byteToHexString(encrypt(conect, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enc;
    }


    //解密
    public static String decrypt(String conect){
        byte key[] = new byte[0];
        String jm = "";
        try {
            key = puclickey.getBytes("utf-8");
            jm= new String(decrypt(conect, key), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jm;
    }


    public static void main(String[] args) {
        try {
            // byte key[] = generatorKey();
            // 密钥必须是16的倍数
            byte key[] = "20160918HSWL2018".getBytes("utf-8");//hexStringToBytes("0123456789ABCDEF");
            //String src = "范德萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨幅度萨芬送达范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨幅度萨芬的萨芬士大夫的萨芬的萨芬士大夫的撒阿凡达大方点撒范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德萨幅度萨芬的萨芬士大夫的撒啊发的萨芬的撒范德萨范德萨广泛的 和规范化哥废话地方撒范德萨范德萨幅度萨芬 护法国会发但是好地方幅度萨芬的撒士大夫回复第三方给的撒噶似的该公司大哥三安光电萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生萨范德萨范德萨范德萨幅度萨芬的撒范德萨范德萨分倒萨范德萨范德萨范德萨发生";
            String src = "adf6fc30a27f8244e56cd8f578162962956ff6834c73b5f4455ea81d90a3c567e1349684b9e7c6ba02d5a5f64f3c68d9";
            System.out.println("密钥:"+byteToHexString(key));
            System.out.println("原字符串:"+src);

            String enc = byteToHexString(encrypt(src, key));
            System.out.println("加密："+enc);
            System.out.println("解密："+new String(decrypt("1ba688ac1e3db70a9ca025414e205234139309586dc671809846bab7bb5c9da022437b7dddb02068368f3d425ddc305b2418f870ffd02e120e59e81278982cec53107cf92b56fda55911fa9b6844b69fb4b126bb1d9e4cd379a389ec8b3b7405af341c63bd0bc2ead8c89eb7701b8282416b0e04b8295458ba6e2038288a5e64b2dffdbdeb8c9c28637c1400e2448890707319757acc97680035115c8b6fb28a98da92a9dec7f0dd0da415ed9525dc5f2419ba1afbbc43f4d738e4647bc2f0095869df390c9fbbebc468b55f6a24d792c4c671156a95b779eae906739fadc8cc68dc288016302ac970e2b9e26a1ec5013c776e232fda79bc1c2f6588bb85a8b9fa35b63560111b3c778c928f86cfcd3ac3f3f5c241b45959ec99b314e79e320592ed0dc9c28a10c8feb012d5e19601cc0f264131892e02edacaaf2b1f6cf7b7e3bdd6e0cfa59150b1fee18c3b2ddb88464d5cc80f2f1a10f1ed809919e7547a39a8898c09cd7b2c8f550343035633ce40eb9cb56fe0072239cc3eee0e7734f997ad2587b4ad2adfac208a762f1e3a74f499757564fa310da7e92e6b04ff525796c90dde61cb190a0149bd2d08004671014e2e9d863b3d1f8884b691ec2862dfdee3c12f75b930584963287eaa1aed1c31d08afed09a70d614c1d893f8e7e810df0606d420633ecf5790180328ba2b78a65e44e1334366cb8e339ea5f7f5d70dfcd65b350657240316d8da22ca482ea4aeeb3b3c42647ae9e6a1b298d344ecf6a16034cd449610946d654289eb63309b60e9019b65c78c9d3f68abc280179a4541da80c8bac7fd9e791e2edb517f4aaeb0c5d9e373de4dd6ea67693fddbb04dbe8dbb8258846a31ecb67120fbd02e0f5ece7b132d75fe54a55ae9378edcb09582bdf4a5242f4f65a68331c615f6f634016371826dbe3e6b4423155fbbe02f6785942241513294d0587d2ff70f06fcb4b40f7502af83b3cd64676da5202056fca00c09c3254a2d4c20c15384be294d9487b8a47e08ffdafc464e627e9ba9ddf7eecd7ea78615d8014224fcc8023d12bb00ed503907a456d5b2a08cd8fd32434ae8d358f74edf180a133a4e9182a8b7f37e8cf752ca7ff17c152ed8d0c1ff1f3c2497f61c655933fe7a7a904274a1ffbc2d05d84cfdc6a41b232ad6598aeb362c8ff39894f3cef24069e7eaf9410f83456406988266b6151f4521a0798152d2273a90d2790270ae9b8ed9e23b44b6b1ba1e32686eb2b2f814f1363475ddd2f21cab2771ba7ebadb4625fcef8ff90e1e9640b04dcc624a63803108edee29aebcbfbae980d89d915546e6f45a9cbf81edb1426e0f3411538a91940ee7245392e4377293bd3bee62258cf7a1f82c907a10c36b0a336b7dc3431a77839d55229fb05a7356d9ccd0815e7a04bfdd3e2fc0fe4269b210097ad39c20ec35219df15685f843e04279da98ec53687a05c45cd15116d874c0356396b822ddbdc26ba68bb656e1de86a62c6286f82cf630efef8314e7b4b5ef05a0dc4c239c6b63002742853f0a7282a3883d23f57e9bb7dced2ab824c8540f9e87f2ccebd60caf6f30eb121b689a28f0d137e59b8bf3b2b5e00a5af418bb448e5349ceeb36a74c02964095db9cb4e816600faf080b5a4a9ae47229f53f25b4ef2d7c9e126a4fc0725b90625204adb461d0d5b3fd7dfd46cbf74b1d1908544d3ba3fbc62fba73c8b0a84fadd2c02fd2e235fb1d30d8ea67fc8961e5051470bce428d030be2feb52ad787ed35562b4b98e02bdfb1ce5ed42164d878ae1bd2d2ee22c60766f13129c35aee55fa0efb06b206d08d62062a79cbbcaf97343e6dffe03b339d42447f935e578b0da9f280bc94addc2b779f1fdd32afd94918d612368d7583f7ff903d79759a80c250c793b39e8005011e70c2326bdc130db4f7a2a07087e7ee3f907aed48e1c8517a220d2f154fa459ca4e9e2879f79e7ce9d29681cb3288e12765f1bcb5e3ddc5602c848511332482c8e4c4a508681ea4f739073485fe525c7af97058676c2edf0d7ddc4f9d3f3f40ef0f3ad6c35b2596489eba435b25232e36f69028e4af82d0102997fdefeae6b629b4b851bac419a5c7f8c99141229631e97fca12e18315db85a0a609c6b0da24c3543427bba2495a49d23c79e1926e81046d19376e9f7bcc7a936ca48558d1f355cd37ac6492934a04b1dde184ae88c121a89ba324310bc6b6acb591a06a650b30805d6d289879b9239beee995cc2403571273c008e6db3aa1b590dcb23c2ceb425324a2059fb6aebde081fffadc26ed7c6558446e1b28daadac1", key), "utf-8"));



            String jm = AES.JM(src);
            System.out.println("封装jm----------"+jm);

            String decrypt = AES.decrypt(jm);
            System.out.println("封装decrypt---------"+decrypt);


        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}