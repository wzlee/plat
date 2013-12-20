package com.eaglec.plat.aop;
/**
 * Session中User的类型
 * 
 * @author Xiadi
 * @since 2013-9-25
 */
public enum SessionType {
	/**
	 * 两者任意一个
	 */
	OR,

	/**
	 * 会员用户
	 */
	USER,

	/**
	 * 管理员
	 */
	MANAGER
}
