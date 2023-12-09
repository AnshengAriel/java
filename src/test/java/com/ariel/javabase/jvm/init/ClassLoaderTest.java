package com.ariel.javabase.jvm.init;

import org.junit.Test;
import sun.misc.Launcher;

import java.net.URL;

/**
 * 类加载器分为引导类加载器（null/bootstrap）、扩展类加载器（ExtClassLoader）以及系统类加载器（AppClassLoader）
 * String等核心类库由引导类加载器加载，引导类加载器由cpp语言实现，无法获取其对象实例，因此返回null
 * ClassLoaderTest等我们平时写的代码由系统类加载器加载
 * 其他的由扩展类加载器加载
 */
public class ClassLoaderTest {

    @Test
    public void getClassLoader() {
        ClassLoader classLoader = Launcher.getLauncher().getClassLoader();
        System.out.println("classLoader = " + classLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("classLoader.getParent() = " + classLoader.getParent()); // sun.misc.Launcher$ExtClassLoader@677327b6
        System.out.println("classLoader.getParent().getParent() = " + classLoader.getParent().getParent()); // null

        System.out.println("ClassLoaderTest.class.getClassLoader() = " + ClassLoaderTest.class.getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("String.class.getClassLoader() = " + String.class.getClassLoader()); // null
    }

    @Test
    public void loadUrls() {
        // 引导类加载器管理的目录
        System.out.println("---------------------Bootstrap----------------------");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println("url = " + url);
        }

        // 扩展类加载器管理的目录，可以手动放入
        System.out.println("--------------------ext-----------------------");
        String property = System.getProperty("java.ext.dirs");
        for (String url : property.split(";")) {
            System.out.println(url);
        }

    }
}
