package com.tamakiako.smart4j.framework;

import com.tamakiako.smart4j.framework.annotation.Action;
import com.tamakiako.smart4j.framework.annotation.Autowired;
import com.tamakiako.smart4j.framework.annotation.Controller;
import com.tamakiako.smart4j.framework.bean.View;

@Controller
public class ControllerTest {

    @Autowired
    private ServiceTest serviceTest;

    @Action(value = "get:/hi")
    public Object sayHi(){
        return new Object();
    }
}
