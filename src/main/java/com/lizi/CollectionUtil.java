package com.lizi;

import org.apache.commons.beanutils.PropertyUtils;

import java.util.Collection;

/**
 * Created by guotie on 2016/8/31.
 */
public class CollectionUtil {

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
