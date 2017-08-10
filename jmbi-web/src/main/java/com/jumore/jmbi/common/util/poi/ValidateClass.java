/**
 * 
 */
package com.jumore.jmbi.common.util.poi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 用在ExcelExtractor的值对像的方法上，告诉ExcelExtractor，当调用设置方法时，哪些验证方法应该被调用
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValidateClass {
	/**
	 * @return 
	 */
	Class value();
}
