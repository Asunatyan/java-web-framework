package com.tamakiako.smart4j.framework.helper;

import com.tamakiako.smart4j.framework.annotation.Autowired;
import com.tamakiako.smart4j.framework.util.ReflectionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class IocHelper {
    static {
        //获取所有的Bean类与Bean实例直接的映射关系(简称 BeanMap)
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtils.isNotEmpty(Collections.singleton(beanMap))) {
            //遍历BeanMap
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //从beanMap中获取bean类与bean的实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取Bean定义的所有成员变量( Bean File)
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    //遍历Bean Field
                    for (Field beanField : beanFields) {
                        //获取说有bean中的属性是否有我们写的autowired注解
                        if (beanField.isAnnotationPresent(Autowired.class)) {
                            //在bean Map中获取Bean Field 对应的实例
                            Object beanFieldInstance = beanMap.get(beanField.getType());
                            if (beanFieldInstance != null) {
                                //通过放射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
