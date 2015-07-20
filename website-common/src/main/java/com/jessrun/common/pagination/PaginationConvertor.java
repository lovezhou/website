/**
 * @(#)PaginationConvertor.java
 *
 * Copyright 2008 jointown, Inc. All rights reserved.
 */

package com.jessrun.common.pagination;

/**
 * 分页转换器。此接口将Pagination对象转换为字符串的html
 * @author  luoyifan
 * @version 1.0,2010-3-15
 */
public interface PaginationConvertor {
	public String convert(Pagination pagination);
}
