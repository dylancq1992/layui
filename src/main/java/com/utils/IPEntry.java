/** 
 * @(#)IPEntry.java 2015-11-13
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

/**
 * 一条IP范围记录，不仅包括国家和区域，也包括起始IP和结束IP
 * @date 2018-7-9 下午5:09:48
 * @author wanghongwei
 *
 */
public class IPEntry {  
    public String beginIp;  
    public String endIp;  
    public String country;  
    public String area;  
  
    /** 
     * 构造函数 
     */  
  
    public IPEntry() {  
        beginIp = endIp = country = area = "";  
    }  
  
    public String toString() {  
        return this.area + "  " + this.country + "IP范围:" + this.beginIp + "-"  
                + this.endIp;  
    }  
  
}  
