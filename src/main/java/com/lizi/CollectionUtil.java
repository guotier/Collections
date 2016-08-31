package com.lizi;

import org.apache.commons.beanutils.PropertyUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by guotie on 2016/8/31.
 */
public class CollectionUtil {

    /**
     * 提取集合中的对象的一个属性(通过Getter函数), 组合成List.
     *
     * @param collection 来源集合.
     * @param propertyName 要提取的属性名.
     */
    public static List extractToList(final Collection collection, final String propertyName) {
        List list = new ArrayList(collection.size());

        try {
            for (Object obj : collection) {
                Object property = PropertyUtils.getProperty(obj, propertyName);
                if(property != null){list.add(property);}
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected Checked Exception.",e);
        }

        return list;
    }

    /**
     * see extractToList(final Collection collection, final String propertyName) 元素不重复
     * @param collection
     * @param propertyName
     * @return
     */
    public static List extractToListUnRepeat(final Collection collection, final String propertyName) {
        List list = new ArrayList();

        try {
            for (Object obj : collection) {
                Object property = PropertyUtils.getProperty(obj, propertyName);
                if(null != property && !list.contains(property)){list.add(property);}
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected Checked Exception.",e);
        }

        return list;
    }

    public static List extractToListWithSeparator(final Collection<String> collection, final String separator){
        List list = new ArrayList();

        try {
            for (String obj : collection) {
                if(obj.contains(separator)){
                    list.add(obj.substring(0,obj.indexOf(separator)));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected Checked Exception.",e);
        }

        return list;
    }

    /**
     * 搜索集合中的对象的一个属性propertyName == value 只返回第一个匹配的对象
     * @param collection
     * @param propertyName
     * @param value
     * @return
     */
    public static Object findTheOne(final Collection collection, final String propertyName, final Object value){
        for (Object obj : collection) {
            try{
                if(value.equals(PropertyUtils.getProperty(obj, propertyName))){
                    return obj;
                }
            } catch (Exception e){
                throw new RuntimeException("Unexpected Checked Exception.",e);
            }
        }
        return null;
    }

    /**
     * 搜索集合中的对象的一个属性propertyName == value 返回全部匹配的对象
     * @param collection
     * @param propertyName
     * @param value
     * @return
     */
    public static List findTheOnes(final Collection collection,  final String propertyName, final Object value){
        List list = new ArrayList();

        for (Object obj : collection) {
            try{
                if(value.equals(PropertyUtils.getProperty(obj, propertyName))){
                    list.add(obj);
                }
            } catch (Exception e){
                throw new RuntimeException("Unexpected Checked Exception.",e);
            }
        }
        return list;
    }

    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static Boolean isEmpty(final Collection collection){
        return (collection == null || collection.isEmpty());
    }

    /**
     * 集合中的属性转换成字符串
     * @param collection
     * @param propertyName
     * @param cha
     * @return
     */
    public static String toSplitString(final Collection collection, final String propertyName, final String cha){
        StringBuffer result = new StringBuffer();
        for (Object obj : collection) {
            try{
                result.append(PropertyUtils.getProperty(obj, propertyName).toString());
                result.append(cha);
            } catch (Exception e){
                throw new RuntimeException("Unexpected Checked Exception.",e);
            }
        }
        return result.substring(0,result.length() - cha.length());
    }

    public static String toSplitString(final Collection collection, final String propertyName){
        return toSplitString(collection, propertyName, ";");
    }

    /**
     * toSplitStringOfSimpleCollection
     * 集合中的属性转换成字符串
     * @param collection
     * @param cha
     * @return
     */
    public static String toSplitStringOSC(final Collection collection, final String cha){
        StringBuffer result = new StringBuffer();
        for (Object obj : collection) {
            try{
                result.append(obj.toString());
                result.append(cha);
            } catch (Exception e){
                throw new RuntimeException("Unexpected Checked Exception.",e);
            }
        }
        return result.substring(0, result.length() - cha.length());
    }

    //toSplitStringOfSimpleCollection
    public static String toSplitStringOSC(final Collection collection){
        return toSplitStringOSC(collection, ";");
    }

    public static List<String> join(final List<String> src, final String separator){
        List<String> dst = new ArrayList<String>();
        for(String obj: src){
            dst.add(obj + separator);
        }
        return dst;
    }

    public static Integer sum(final Collection collection, final String propertyName){
        Integer result = 0;
        for (Object obj : collection) {
            try{
                if(PropertyUtils.getProperty(obj, propertyName) instanceof Integer){
                    result += (Integer) PropertyUtils.getProperty(obj, propertyName);
                }else if(PropertyUtils.getProperty(obj, propertyName) instanceof Long){
                    result += ((Long) PropertyUtils.getProperty(obj, propertyName)).intValue();
                }
            } catch (Exception e){
                throw new RuntimeException("Unexpected Checked Exception.",e);
            }
        }
        return result;
    }

    public static Long mulitiply(final Collection collection, final String propertyName1, final String propertyName2){
        Long result = 0l;
        for(Object obj : collection){
            try{
                Long value1 = 0l;
                Long value2 = 0l;
                if(PropertyUtils.getProperty(obj, propertyName1) instanceof Integer){
                    value1 = ((Integer) PropertyUtils.getProperty(obj, propertyName1)).longValue();
                }else if(PropertyUtils.getProperty(obj, propertyName1) instanceof Long){
                    value1 = (Long) PropertyUtils.getProperty(obj, propertyName1);
                }

                if(PropertyUtils.getProperty(obj, propertyName2) instanceof Integer){
                    value2 = ((Integer) PropertyUtils.getProperty(obj, propertyName2)).longValue();
                }else if(PropertyUtils.getProperty(obj, propertyName2) instanceof Long){
                    value2 = (Long) PropertyUtils.getProperty(obj, propertyName2);
                }
                result += value1 * value2;
            } catch (Exception e){
                throw new RuntimeException("Unexpected Checked Exception.",e);
            }
        }
        return result;
    }
}
