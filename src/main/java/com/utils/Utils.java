/** 
 * @(#)Utils.java 2015-11-13
 * 
 * Copyright (c) 1995-2015 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */
package com.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;  

import javax.imageio.ImageIO;

/** 
 * @author LJ-silver 
 */  
public class Utils {  
    /** 
     * 从ip的字符串形式得到字节数组形式 
     *  
     * @param ip 
     *            字符串形式的ip 
     * @return 字节数组形式的ip 
     */  
    public static byte[] getIpByteArrayFromString(String ip) {  
        byte[] ret = new byte[4];  
        java.util.StringTokenizer st = new java.util.StringTokenizer(ip, ".");  
        try {  
            ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);  
            ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);  
            ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);  
            ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);  
        } catch (Exception e) {  
        }  
        return ret;  
    }  
  
  
    /** 
     * 对原始字符串进行编码转换，如果失败，返回原始的字符串 
     *  
     * @param s 
     *            原始字符串 
     * @param srcEncoding 
     *            源编码方式 
     * @param destEncoding 
     *            目标编码方式 
     * @return 转换编码后的字符串，失败返回原始字符串 
     */  
    public static String getString(String s, String srcEncoding,  
            String destEncoding) {  
        try {  
            return new String(s.getBytes(srcEncoding), destEncoding);  
        } catch (UnsupportedEncodingException e) {  
            return s;  
        }  
    }  
  
    /** 
     * 根据某种编码方式将字节数组转换成字符串 
     *  
     * @param b 
     *            字节数组 
     * @param encoding 
     *            编码方式 
     * @return 如果encoding不支持，返回一个缺省编码的字符串 
     */  
    public static String getString(byte[] b, String encoding) {  
        try {  
            return new String(b, encoding);  
        } catch (UnsupportedEncodingException e) {  
            return new String(b);  
        }  
    }  
  
    /** 
     * 根据某种编码方式将字节数组转换成字符串 
     *  
     * @param b 
     *            字节数组 
     * @param offset 
     *            要转换的起始位置 
     * @param len 
     *            要转换的长度 
     * @param encoding 
     *            编码方式 
     * @return 如果encoding不支持，返回一个缺省编码的字符串 
     */  
    public static String getString(byte[] b, int offset, int len,  
            String encoding) {  
        try {  
            return new String(b, offset, len, encoding);  
        } catch (UnsupportedEncodingException e) {  
            return new String(b, offset, len);  
        }  
    }  
  
    /** 
     * @param ip 
     *            ip的字节数组形式 
     * @return 字符串形式的ip 
     */  
    public static String getIpStringFromBytes(byte[] ip) {  
        StringBuffer sb = new StringBuffer();  
        sb.append(ip[0] & 0xFF);  
        sb.append('.');  
        sb.append(ip[1] & 0xFF);  
        sb.append('.');  
        sb.append(ip[2] & 0xFF);  
        sb.append('.');  
        sb.append(ip[3] & 0xFF);  
        return sb.toString();  
    }  
    
  
  
}
