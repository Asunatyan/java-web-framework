package com.tamakiako.smart4j.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Handler {
    /**
     * Controller类(请求对应的controller类)
     */
    private Class<?> controllerClass;

    /**
     * Action方法(请求对应的方法)
     */
    private Method actionMethod;


}
