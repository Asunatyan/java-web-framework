package com.tamakiako.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类操作工具类
 *
 * 1.加载类
 * 2.获取指定包名下的所有类
 * 3.获取类加载器
 *
 */
public final class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        //获取当前线程中的Classloader即可
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * <p>
     * 加载类需要提供类名和是否初始化标志(是否执行类的静态代码块)
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls = null;
        try {
            /**
             * Class使用给定的类加载器返回与具有给定字符串名称的类或接口关联的对象。
             * 给定类或接口的完全限定名称（以相同的格式返回getName），此方法尝试查找，加载和链接类或接口。
             * 指定的类加载器用于加载类或接口。如果参数 loader为null，则通过引导类加载器加载该类。
             * 只有在 initialize参数为true且之前尚未初始化时，才会初始化类。
             */
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("类加载失败", e);
        }
        return cls;
    }

    /**
     * 获取指定包名下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        //URL resource = getClassLoader().getResource(packageName.replace(".", "/"));getResources<====>getResource
        try {
            //this.getClass().getResources()获取的是当前路径
            //this.getClass().getResources("/")获取的是根路径
            //getClassLoader().getResources()获取的是根路径
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.endsWith("file")) {
                        String packagePath = url.getPath().replaceAll("%20", "");
                        addClass(classSet, packagePath, packageName);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> entries = jarFile.entries();
                                while (entries.hasMoreElements()) {
                                    JarEntry jarEntry = entries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }

                        }
                    }
                }
            }


        } catch (IOException e) {
            LOGGER.error("获取 class set 失败", e);
            throw new RuntimeException(e);
        }

        return classSet;
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls= loadClass(className, false);
        classSet.add(cls);
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                //是一个文件并且是.clss结尾的文件或者是一个文件夹都返回true要不然就是过滤掉
                return (file.isFile() && file.getName().endsWith(".class") || file.isDirectory());
            }
        });

        for (File file : files) {
            //当前文件不是一个文件夹
            String fileName = file.getName();
            if (file.isFile()) {
                String ClassName = fileName.substring(0, fileName.lastIndexOf("."));//获取类名
                if (StringUtils.isNotEmpty(packageName)) {
                    ClassName = packageName + "." + ClassName;//全类名
                }
                doAddClass(classSet, ClassName);
            }else {
                //当前文件是一个文件夹
                String subPackagePath = fileName;
                if (StringUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);//递归调用当前的方法是文件才不会调用文件夹就递归
            }
        }
    }

    public static void main(String[] args) {
        Set<Class<?>> classSet = ClassUtil.getClassSet("com.tamakiako.smart4j");
    }

}




























