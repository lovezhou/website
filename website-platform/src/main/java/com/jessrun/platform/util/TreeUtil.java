package com.jessrun.platform.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

/**
 * 
 * 类TreeUtil.java 把数据库中的记录转化为对应的java数据结构
 * @author zmy 2015-8-29 上午1:01:40
 */
public class TreeUtil {

    /**
     * 将数据库的中的树形结构数据转化为对应的java树结构，满足页面js的tree数据结构
     * @param list  要转化的为树的list
     * @param idProperty  Vo的id属性
     * @param pidProperty Vo的pid属性
     * @param childrenProperty Vo的child属性 ，类型一定要为list
     * @param rootValue  根节点的值
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public static List convertToTree( List list ,String idProperty ,String pidProperty,String childrenProperty,Object rootValue) throws Exception{
        if(CollectionUtils.isNotEmpty(list)){
            List _list = new ArrayList();
            Map map = new HashMap();
            //遍历整个list，然后放入map中
            for(Object obj :list){
                Class clazz = obj.getClass();
                Field field = clazz.getDeclaredField(idProperty);
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                Object _id= field.get(obj);
                map.put(_id, obj);
            }
            //遍历key，然后将每个key对应的Object 设置给它的父元素
            for(Object _id :map.keySet()){
                Object c_obj = map.get(_id);
                Class clazz = c_obj.getClass();
                Field field  = clazz.getDeclaredField(pidProperty);
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                Object _pid = field.get(c_obj);
                if(_pid==null||rootValue.equals(_pid)){ //根节点
                    _list.add(c_obj);//根节点加入到list中
                }else{
                   Object p_obj =  map.get(_pid); // 获取对应的父节点
                   Class _clazz = p_obj.getClass();
                   Field _field =_clazz.getDeclaredField(childrenProperty);
                   if(!_field.isAccessible()){
                       _field.setAccessible(true);
                   }
                   List children = (List)_field.get(p_obj);
                   if(CollectionUtils.isEmpty(children)){
                       children = new ArrayList();
                   }
                   children.add(c_obj); 
                   _field.set(p_obj, children);  //将child重新设置给父节点
                }
            }
            return _list;
        }
        return null ;
    }
    
}
