package com.tamakiako.smart4j.framework.helper;

import com.tamakiako.smart4j.framework.annotation.Action;
import com.tamakiako.smart4j.framework.bean.Handler;
import com.tamakiako.smart4j.framework.bean.Request;
import com.tamakiako.smart4j.framework.util.ArrayUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 */
public final class ControllerHelper {
    /**
     * 用于存放请求与处理器的映射关系(简称 Action Map)
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            for (Class<?> cls : controllerClassSet) {
                Method[] methods = cls.getMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        //是否带有action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            //从action获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            //action--->请求的路径get:xxxxx
                            String mapping = action.value();
                            String[] array = mapping.split(":");
                            //验证URL匹配规则
                            if (mapping.matches("\\w+:/\\w*")) {
                                if (array != null && array.length == 2) {
                                    //请求的方法
                                    String Method = array[0];
                                    //请求的路径
                                    String path = array[1];
                                    //封装request对象
                                    Request request = new Request(Method, path);

                                    //封装handler对象
                                    Handler handler = new Handler(cls, method);

                                    ACTION_MAP.put(request, handler);
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    /**
     * 获取hander
     */
    public static Handler getHandler(String requestMethod, String RequestPath) {
        Request request = new Request(requestMethod, RequestPath);
        return ACTION_MAP.get(request);
    }
}

