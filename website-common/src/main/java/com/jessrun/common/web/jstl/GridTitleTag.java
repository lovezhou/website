package com.jessrun.common.web.jstl;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.jessrun.platform.util.reflect.ReflectUtils;


public class GridTitleTag extends BodyTagSupport{
    
    private List confList; //对象list

    private int i = 0 ; //集合当前索引
    
    private int size ; //集合大小
    
    private String var; //迭代集合item逻辑名
    
    @Override
    public int doStartTag() throws JspException {
        size = confList.size();
        if(size <= 0){
            return super.doEndTag();
        }
        Object object = confList.get(i);
        try {
            setAttribute(object);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        i++;
        if(i >= size) {//循环结束
            i=0;
            return SKIP_BODY;
        }
        Object object = confList.get(i);
        try {
            setAttribute(object);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return EVAL_BODY_AGAIN;//循环标签体
    }

    
    public List getConfList() {
        return confList;
    }
    
    public void setConfList(List confList) {
        this.confList = confList;
    }

    public int getI() {
        return i;
    }

    
    public void setI(int i) {
        this.i = i;
    }

    
    public int getSize() {
        return size;
    }

    
    public void setSize(int size) {
        this.size = size;
    }

    public String getVar() {
        return var;
    }

    
    public void setVar(String var) {
        this.var = var;
    }

    
    @Override
    public int doEndTag() throws JspException {
        try {
            //输出
            bodyContent.writeOut(pageContext.getOut());
        } catch (Exception e) {
            
        }
        return EVAL_PAGE;
    }
    
    private void setAttribute(Object object) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException{
        Object aliasNameObj = ReflectUtils.getValueByFieldName(object, "aliasName");
        String titleShow = "";
        if(aliasNameObj != null && !"".equals(String.valueOf(aliasNameObj))){
             titleShow = String.valueOf(aliasNameObj);
        }else{
             titleShow = String.valueOf(ReflectUtils.getValueByFieldName(object, "itemName"));
        }
        pageContext.setAttribute(var,titleShow);
    }
    
    
}
