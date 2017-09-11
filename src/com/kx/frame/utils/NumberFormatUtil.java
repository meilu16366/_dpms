package com.kx.frame.utils;

import java.text.NumberFormat;

public class NumberFormatUtil {

	/**
	 * 保留小数
	 * @param num 数字
	 * @param digits 位数
	 * @return 保留的数字字符串
	 */
	public static String format(Number num,int digits) {
		NumberFormat fmat = NumberFormat.getNumberInstance();
		fmat.setMaximumFractionDigits(digits);
		fmat.setMinimumFractionDigits(digits);
		return fmat.format(num);
	}
    
}
