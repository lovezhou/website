package com.jessrun.common.web;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.jessrun.platform.util.SerializeUtils;


public class AjaxUtil {

    
    /**
     * ajax 成功之后 返回的带分页格式的字符串
     * @param rep
     * @param list
     */
    public  static void  success(HttpServletResponse response ,List<?> list,int  total){
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         String data = SerializeUtils.toJson(list);
         String result = "{\"total\":"+total+",\"rows\":"+data+"}";
         try{
             Writer  out =  response.getWriter();
             out.write(result);
         }catch(IOException e){
             e.printStackTrace();
             //TODO　异常处理
         }
    }
}
