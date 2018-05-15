package com.kong.support.toolboxes;

import java.nio.charset.Charset;

/**
 * 字符串工具
 */
public class StringTool {
    /**
     * 将字节数组转成 hex字符串
     * @param bytes
     * @return
     */
    public static String hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b:
                bytes) {
            int i =  b&0xff;
            if (i<0xf){
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString();
    }

    /**
     * 将字节数组转成 字符串
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes){
        return new String(bytes);
    }

    /**
     * 将字节数组转成 指定编码的字符串
     * @param bytes
     * @param charset
     * @return
     */
    public static String toString(byte[] bytes,Charset charset){
        return new String(bytes,charset);
    }

    /**
     * 判断字符串是不是空白的 （null，'' ，'  ' 都是空白的）
     * @param obj
     * @return
     */
    public static boolean isBlank(String obj){
        return obj == null || obj.trim().length() == 0;
    }
    /**
     * 判断字符串是不是 NULL值
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj){
        return obj == null;
    }

    /**
     * 将 字符串 以指定长度分组
     * @param obj
     * @return
     */
    public static String[] groups(String obj,int unitLength){
        if (isNull(obj))
            return null;
        if (unitLength <= 0 )
            unitLength = obj.length();
        int length = obj.length();
        int groupSize = length / unitLength;
        if (length%unitLength > 0)
            groupSize++;
        String[] ret = new String[groupSize];
        for (int i = 0; i < groupSize; i++) {
            int start = i * unitLength;
            int end =  length - start > unitLength?start + unitLength:length;
            ret[i] = obj.substring(start,end);
        }
        return ret;
    }

    /**
     * 连接字符数组为整个字符串
     * @param strings
     * @return
     */
    public static String contact(String... strings){
        if(isNull(strings))
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: strings){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }


    /**
     * 将子字符串放到 源字符串的第 n 个字符之后
     * @param src           源字符串
     * @param joinString    子字符串
     * @param srcIndex  0 放到最前。 源串的位置
     * @return
     */
    public static String join(String src,String joinString,int srcIndex){
        if (isBlank(src))
            return joinString;
        if (isNull(joinString))
            return src;
        if (srcIndex < 0 || srcIndex > src.length()){
            throw new ArrayIndexOutOfBoundsException("src index >= 0 and <= src.length");
        }
        String substring = src.substring(0, srcIndex);
        String substring1 = src.substring(srcIndex);
        return contact(substring,joinString,substring1);
    }
}
