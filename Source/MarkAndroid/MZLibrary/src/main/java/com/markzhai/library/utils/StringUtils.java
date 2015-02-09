package com.markzhai.library.utils;

/**
 * Created by marktlzhai on 2015/1/7.
 */
public class StringUtils {

	/**
	 * 判断是否为null
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * 检查手机号
	 */
	public static boolean checkPhone(String mobile) {
		if (isEmpty(mobile)) {
			return false;
		}

		if (!NumberUtils.isNumber(mobile)) {
			return false;
		}

		int length = mobile.length();
		if (length != 11) {
			return false;
		}
		return true;
	}

	/**
	 * 检查验证码
	 */
	public static boolean checkVerifycode(String verifyCode) {
		if (isEmpty(verifyCode)) {
			return false;
		}

		if (!NumberUtils.isNumber(verifyCode)) {
			return false;
		}

		if (verifyCode.length() != 4) {
			return false;
		}
		return true;
	}
}
