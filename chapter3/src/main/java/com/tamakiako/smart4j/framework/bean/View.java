package com.tamakiako.smart4j.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
/**
 * 返回视图对象(有两种情况)
 * 1. jsp
 * 2. date数据
 */
public class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String,Object> model;


}
