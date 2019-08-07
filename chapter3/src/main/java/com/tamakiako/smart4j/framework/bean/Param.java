package com.tamakiako.smart4j.framework.bean;

import com.tamakiako.smart4j.framework.util.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 *
 * 1. 将HTTPServletRequest对象中获取的请求参数,初始化到Param中
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数值
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     */
    public Map<String,Object> getMap(){
        return paramMap;
    }
}


























