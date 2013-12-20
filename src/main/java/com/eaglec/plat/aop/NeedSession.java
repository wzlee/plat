package com.eaglec.plat.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用户Session注解，只能用于方法<br/>
 * 默认为value = SessionType.USER
 * 
 * @author Xiadi
 * @since 2013-9-10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedSession {
	/**
	 * Session中用户的类型<br/>
	 * 默认 USER
	 * @author Xiadi
	 * @since 2013-9-10 
	 *
	 * @return
	 */
	SessionType value() default SessionType.USER;
}
