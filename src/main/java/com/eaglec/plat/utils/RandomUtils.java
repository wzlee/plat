package com.eaglec.plat.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomUtils {
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String numberChar = "0123456789";

	public static String generateMixString(int length) {

		StringBuffer sb = new StringBuffer();

		Random random = new Random();

		for (int i = 0; i < length; i++) {

			sb.append(allChar.charAt(random.nextInt(letterChar.length())));

		}

		return sb.toString();

	}

	public static String generateNumberString(int length) {

		StringBuffer sb = new StringBuffer();

		Random random = new Random();

		for (int i = 0; i < length; i++) {

			sb.append(numberChar.charAt(random.nextInt(numberChar.length())));

		}

		return sb.toString();

	}

	public static String generateUpperString(int length) {

		return generateMixString(length).toUpperCase();

	}

	// 随机产生一个10位的随机数
	public static String generateRandomNumber() {
		String s = "";
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int n = random.nextInt(36);
			if (n >= 0 && n <= 9) {
				s += String.valueOf(n);
			} else {
				n = n - 10;
				s += (char) (97 + n);
			}
		}
		return s;
	}
	
	/**
	 * 生成指定范围内(0~size)，不重复的指定数量的随机数
	 * @author Xiadi
	 * @since 2013-10-17 
	 *
	 * @param size 指定最大数值
	 * @param count 随机产生个数  count <= size
	 * @return 
	 */
	public static Integer[] generateNotRepeatRandomNumber(int size, int count) {
		Integer[] ret = {};
		if(count > size) {
			try {
				throw new Exception("count is larger than size. ");
			} catch (Exception e) {
				e.printStackTrace();
				return ret;
			}
		}
		Set<Integer> nums = new HashSet<Integer>();
		while (nums.size() < count) {
			nums.add((new Random()).nextInt(size));
		}
		return nums.toArray(ret);
	}
	
	public static void main(String[] args){
		System.out.println(generateMixString(10));
	}
}
