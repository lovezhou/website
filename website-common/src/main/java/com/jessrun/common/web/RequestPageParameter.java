package com.jessrun.common.web;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jessrun.common.pagination.Pagination;
import com.jessrun.platform.util.JavaBeanUtil;

/***
 * 封装请求的分页参数
 * 类RequestPageParameter.java的实现
 * @author zmy 2014-9-6 下午6:23:17
 */
public class RequestPageParameter {

    /**
     * 将一个 request 中的分页参数 page rows 封装为一个Pagination 对象，  放入 Map中，bean也转化为Map
     * @param bean  将bean转化为map
     * @param request 将page ，rows 参数封装为一个Pagination对象put Map中，用来被分页参数适用
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map convertPageMap(Object bean,HttpServletRequest request) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        Map returnMap = JavaBeanUtil.convertBean(bean);
        String page  = request.getParameter("page");
        String rows  = request.getParameter("rows");
        Pagination pagination = null;
        if(page!=null && rows!=null){
            pagination   = new  Pagination(Integer.parseInt(rows),Integer.parseInt(page));
            returnMap.put("pagination", pagination);
        }
        return returnMap;
    }
}
