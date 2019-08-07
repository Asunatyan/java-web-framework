package com.tamakiako.smart4j.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 返回的数据对象
 *
 * 会将该对象写入HTTPServletResponse对象中
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Data {
    /**
     * 数据模型
     */
    private Object model;
}
