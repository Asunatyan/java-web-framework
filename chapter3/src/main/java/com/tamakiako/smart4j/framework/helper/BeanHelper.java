package com.tamakiako.smart4j.framework.helper;

import com.sun.jndi.toolkit.ctx.StringHeadTail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public final class BeanHelper {
    /**
     * 定义bean的映射(用于存放Bean类与Bean 的实例映射关系)
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {

    }
}
