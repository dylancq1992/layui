/** 
 * @(#)ImageServlet.java 2015-11-13
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
package com.pub;

/**
 * 验证码
 * @author chenyibing
 * 2017-5-22
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author worm0527 2016-03-22 23:15:54 生成验证码
 */
public class ImageController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	public Color getRandColor(int fc, int bc) {

		Random random = new Random();

		if (fc > 255)
			fc = 255;

		if (bc > 255)
			bc = 255;

		int r = fc + random.nextInt(bc - fc);

		int g = fc + random.nextInt(bc - fc);

		int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 图片的宽度。
		int width = 95;
		// 图片的高度。
		int height = 40;
		// 验证码字符个数
		int codeCount = 4;
		// 验证码干扰线数
		int lineCount = 10;
		// 验证码
		int fontWidth = width / codeCount;// 字体的宽度
		int fontHeight = height - 5;// 字体的高度
		int codeY = height - 8;

		// 图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		// Graphics2D g = buffImg.createGraphics();
		// 设置背景色
		Color bkColor = getRandColor(200, 250);
		g.setColor(bkColor);
		g.fillRect(0, 0, width, height);

		// 设置字体
		// Font font1 = getFont(fontHeight);
		Font font = new Font("华文中宋", Font.BOLD, fontHeight);
		g.setFont(font);
		Random random = new Random();
		// 设置干扰线
		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width);
			int ye = ys + random.nextInt(height);
			g.setColor(getRandColor(1, 255));
			g.drawLine(xs, ys, xe, ye);
		}

		// 添加噪点
		float yawpRate = 0.01f;// 噪声率
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			buffImg.setRGB(x, y, random.nextInt(255));
		}

		String str1 = randomStr(codeCount);// 得到随机字符
		String code = str1;
		HttpSession session=req.getSession(true);  
        session.setAttribute("certCode", code);
		for (int i = 0; i < codeCount; i++) {
			String strRand = str1.substring(i, i + 1);
			Color fontColor = new Color(0,0,0);
			while(Math.abs(fontColor.getRed() - bkColor.getRed())<20 &&
					Math.abs(fontColor.getGreen() - bkColor.getGreen())<20 &&
					Math.abs(fontColor.getBlue() - bkColor.getBlue())<20 ){
				
				fontColor = new Color(0,0,0);
			}
			g.setColor(fontColor);
			// g.drawString(a,x,y);
			// a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处

			g.drawString(strRand, i * fontWidth + 3, codeY);
		}
		
		g.dispose();    //释放g所占用的系统资源  
        ImageIO.write(buffImg,"JPEG",resp.getOutputStream()); //输出图片
	}

	private String randomStr(int n) {
		String str1 = "ABCDEFGHJKMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz234567890";
		String str2 = "";
		int len = str1.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = (Math.random()) * len;
			str2 = str2 + str1.charAt((int) r);
		}
		return str2;
	}

}
