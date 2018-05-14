package com.kong.support.toolboxes;

public class StringTool {
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

    public static String toString(byte[] bytes){
        return new String(bytes);
    }
}
