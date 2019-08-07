package com.tamakiako.smart4j.framework;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class DemoTest {
    @Test
    public void test1() throws IOException {
        File file = new File("D:\\work\\nuwa-cbs-web\\java-web-framework\\chapter3\\src\\main\\java\\com\\tamakiako\\smart4j\\framework\\ConfigConstant.java");
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
    }
}
