package com.tamakiako.smart4j.framework.bean;

import lombok.*;

/**
 * 封装请求的信息
 *
 * handler和request会组成员一个映射
 * 请求的方法/路径(request)<---->请求的controller/Action(handler)
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Request {
    /**
     * 请求的方法
     */
    private String requesrMethod;

    /**
     * 请求的路径
     */
    private String requestPath;


}
