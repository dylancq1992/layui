package com.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.vo.TreeNodeVo;

public class JsonTreeFactory {

    
    
    /**
     *  包装成树形结构 (全部属性)
     * @param tree
     * @return
     * @throws Exception
     */
    public static List factorTree(List tree) throws Exception{
        if(tree != null) {
            List t_list = new ArrayList();
            Map map =new HashMap();
            for(Object o:tree) {
                Class clazz = o.getClass();
                Field id = clazz.getDeclaredField("id");
                if(!id.isAccessible())  {
                    id.setAccessible(true);
                }
                String lId = (String) id.get(o);
                map.put(lId,o);
            }
 
            for(Object o:map.keySet()) {
            	String cId = (String)o;
                Object obj = map.get(cId);
                Class clazz = obj.getClass();
                Field pId = clazz.getDeclaredField("parentId");
                if(!pId.isAccessible())  {
                    pId.setAccessible(true);
                }
                String id = (String)pId.get(obj);
                if(StringUtils.isBlank(id)) {
                    t_list.add(obj);
                } else {
                    Object object = map.get(id);
                    Class clazz1 = object.getClass();
                    Field children = clazz1.getDeclaredField("children");
                    if(!children.isAccessible())  {
                        children.setAccessible(true);
                    }
                    List list = (List)children.get(object);
                    if(CollectionUtils.isEmpty(list)){
                        list=new ArrayList();
                    }
                    list.add(obj);
                    children.set(object,list);
                }
            }
            return t_list;
        }
        return null;
    }
}
