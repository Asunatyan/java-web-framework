package com.tamakiako.smart4j.framework.classutil;

import com.tamakiako.smart4j.framework.helper.BeanHelper;
import com.tamakiako.smart4j.framework.helper.ClassHelper;
import com.tamakiako.smart4j.framework.helper.ControllerHelper;
import com.tamakiako.smart4j.framework.helper.IocHelper;
import com.tamakiako.smart4j.framework.util.ClassUtil;

/**
 * 加载相应的Helper类
 *
 * 1. 实际上,第一次访问类的时候就会加载其static块,这里只是让加载更加集中才写了这个HelpLoder类
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), false);
        }
    }
}
