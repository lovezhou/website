package com.jessrun.common.web.jstl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.jessrun.platform.util.reflect.ReflectUtils;


public class GridBodyTag extends BodyTagSupport{
    
    private static String KEY_CODE = "code";
    private static String KEY_VAL = "val";
    public static String GRID_ITEM_DATATYPE_DATE = "date";
    public static String GRID_ITEM_DATATYPE_DATETIME = "datetime";
    public static String GRID_ITEM_DATATYPE_NUMBER = "number";
    public static String GRID_ITEM_DATATYPE_SUM = "sum";
    
    private List confList; //配置的规则list
    
    private Object dataObj; //vo数据对象
    
    private Map confCodeMap; //存放code

    private int i = 0 ; //集合当前索引
    
    private int size ; //集合大小
    
//    private String var ;
    
    @Override
    public int doAfterBody() throws JspException {
        i++;
        if(i >= size) {//循环结束
            i=0;
            return SKIP_BODY;
        }
        Object object = confList.get(i);
        setAttribute(object);
        return EVAL_BODY_AGAIN;//循环标签体
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

    @Override
    public int doStartTag() throws JspException {
        size = confList.size();
        if(size <= 0){
            return super.doEndTag();
        }
        //组装codemap
        this.confCodeMap = createCodeMap(confList);
        
        Object object = confList.get(i);
        setAttribute(object);
        return EVAL_BODY_BUFFERED;
    }

    
    public List getConfList() {
        return confList;
    }

    
    public void setConfList(List confList) {
        this.confList = confList;
    }

    
    public Object getDataObj() {
        return dataObj;
    }

    
    public void setDataObj(Object dataObj) {
        this.dataObj = dataObj;
    }
    
    private void setAttribute(Object object){
        try {
            String vcode = String.valueOf(ReflectUtils.getValueByFieldName(object, "itemVCode"));
            String dataType = String.valueOf(ReflectUtils.getValueByFieldName(object, "dataType"));
            pageContext.setAttribute(KEY_CODE,vcode);
            Object obj = null;
            if(dataType.equals(GRID_ITEM_DATATYPE_DATE)){//日期类型
                obj = ReflectUtils.getValueByFieldName(dataObj,vcode);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if(obj != null){
                    obj = dateFormat.format(obj);
                }
            }else if(dataType.equals(GRID_ITEM_DATATYPE_DATETIME)){//时间类型
                obj = ReflectUtils.getValueByFieldName(dataObj,vcode);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if(obj != null){
                    obj = dateFormat.format(obj);
                }
            }else if(dataType.equals(GRID_ITEM_DATATYPE_NUMBER)){//数字
                obj = ReflectUtils.getValueByFieldName(dataObj,vcode);
                if(obj != null && Integer.valueOf(obj.toString()) == 0){
                    obj = null;
                }
            }else if(dataType.equals(GRID_ITEM_DATATYPE_SUM)){//合计
//                PropertyDescriptor pd = new PropertyDescriptor(vcode, dataObj.getClass());
                Method rM = dataObj.getClass().getMethod(vcode+"Sum", Map.class);
                obj = rM.invoke(dataObj,this.confCodeMap);
                if(obj != null && Integer.valueOf(obj.toString()) == 0){
                    obj = null;
                }
            }else{
                obj = ReflectUtils.getValueByFieldName(dataObj,vcode);
            }
            pageContext.setAttribute(KEY_VAL,obj);
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
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Map createCodeMap(List confGridList){
        Map confCodeMap = new HashMap();
        try {
            for (int i = 0; i < confGridList.size(); i++) {
                Object object = confGridList.get(i);
                String vcode = String.valueOf(ReflectUtils.getValueByFieldName(object, "itemVCode"));
                confCodeMap.put(vcode, object);
            }
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
        return confCodeMap;
    }
    
//    
//    public String getVar() {
//        return var;
//    }
//
//    
//    public void setVar(String var) {
//        this.var = var;
//    }
    
}
