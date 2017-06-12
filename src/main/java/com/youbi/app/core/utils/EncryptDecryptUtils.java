package com.youbi.app.core.utils;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.Security;

public class EncryptDecryptUtils {
    private static String strDefaultKey = "DEZHOUJIAOTONGGUANLI";
    /**
     * 加密工具
     **/
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    /**
     * 默认构造方法, 使用默认秘钥
     */
    public EncryptDecryptUtils() throws Exception {
        this(strDefaultKey);
    }

    public EncryptDecryptUtils(String strKey) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(strKey.getBytes());

        encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);

    }

    /**
     *加密字符串
     */
    public String encrypt(String strIn) throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    /**
     * 加密字节数组
     */
    public byte[] encrypt(byte[] arrB) throws Exception{
        return encryptCipher.doFinal(arrB);
    }

    /**
     * 解密字符串
     */
    public String decrypt(String strIn) throws Exception{
        try {
            return new String(decrypt(hexStr2ByteArr(strIn)));
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 解密字节数组
     */
    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    /**
     *byte数组转换为16进制字符串
     * @param arrB
     * @return
     * @throws Exception
     */
    public String byteArr2HexStr(byte[] arrB)throws Exception{
        int iLen = arrB.length;
        //每个byte用两个字符才能表示 ，所以字符串长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++){
            int intTmp = arrB[i];
            //把负数转换为正数
            while (intTmp < 0){
                intTmp = intTmp + 256;
            }
            //小于OF的数需要在前面补0
            if(intTmp < 16){
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转换为byte数组 和byteArr2HexStr 互为可逆
     */
    public byte[] hexStr2ByteArr(String strIn) throws Exception{
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        //两个字符表示一个字节, 所以字节数组长度是字符串上的一半
        byte[] arrOut = new byte[iLen / 2];
        for(int i = 0; i < iLen; i = i + 2){
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }


    /**
     * 从指定字符串生成秘钥, 密钥所需的字节数组长度为8位 不足8位后面补0,超出8位只取8位
     */
    private Key getKey(byte[] arrBTmp) throws Exception {
        //创建一个空的8位字节数组(默认为0)
        byte[] arrB = new byte[8];
        //将原字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        //生成mi生成秘钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

        return key;
    }
}

