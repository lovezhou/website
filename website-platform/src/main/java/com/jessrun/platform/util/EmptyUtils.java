package com.jessrun.platform.util;

import java.util.Collection;


public class EmptyUtils {

    
    /**
     * 判断集合是否不为空
     * @param collection
     * @return true 表示不为空， false表示为空
     */
    public static  boolean isNotEmptyList(Collection<?> collection){
        if(collection == null ){
            return false;
        }else{
           if(collection.size()>0){
               return true ;
           }else{
               return false;
           }
        }
    }
    
    
    /**
     * 判断数组是否不为空
     * @param collection
     * @return true 表示不为空， false表示为空
     */
    public static  boolean isNotEmptyArray(String[] strArray){
        if(strArray == null ){
            return false;
        }else{
           if(strArray.length>0){
               return true ;
           }else{
               return false;
           }
        }
    }
    
    
    
    /**
     * 判断字符串是否不为空
     * @param collection
     * @return true 表示不为空， false表示为空
     */
    public static  boolean isNotEmptyString(String  str){
        if(str == null ){
            return false;
        }else{
           if(str.trim().equals("")){
               return false ;
           }else{
               return true;
           }
        }
    }
    
    
}
