package org.wangqian.introduction.tools.utils;

import java.util.Random;

public class RandomUtil {

	/**
	 * 生成一个n位的随机数字符串
	 * 
	 * @param n
	 * @return
	 */
	public static String getRandomNum(int length) {
		String str = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(9);// [1,9)
			sb.append(str.charAt(number + 1));
		}
		return sb.toString();
	}
}
