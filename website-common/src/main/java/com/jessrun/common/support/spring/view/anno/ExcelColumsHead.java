package com.jessrun.common.support.spring.view.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumsHead {
	String defaultColumHeadName();
	String columHeadCode();
	int sort() default 0;
	boolean isTotal() default false;
	
}
