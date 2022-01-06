package access_controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 1.java.policy文件路径(jdk 1.7)  D:\Develop\Java\jdk1.7.0_51\jre\lib\security\java.policy
 * 2.项目中的java.policy文件是从jre目录下拷贝过来的,添加了自定义赋权配置,程序运行时需要替换(先备份)掉java.policy原文件
 * 3.在系统C盘新建一个[1.txt]文件
 * 4.需要先执行"mvn install"生成jar包
 * 4.需要先执行"mvn install"生成jar包
 * 4.需要先执行"mvn install"生成jar包,说三遍
 */
public class AccessControllerDemo {

    // 当前项目路径
    private static final String base = System.getProperty("user.dir");
    // jar包路径
    private static final String jar;

    static {
        // 项目名称
        String name = "DoTxtOperate";
        jar = "file:" + base + "\\" + name + "\\target\\" + name + "-1.0.jar";
        // 需要先执行"mvn install"生成jar包
        System.out.println("Jar包路径: " + jar);
    }

    public static void main(String[] args) throws
        MalformedURLException,
        ClassNotFoundException,
        InstantiationException,
        IllegalAccessException,
        NoSuchMethodException,
        InvocationTargetException {
        URL[] urls = new URL[] { new URL(jar) };
        URLClassLoader ll = new URLClassLoader(urls);
        // 包路径必须要有,此处包路径为demo
        Class<?> clazz = ll.loadClass("demo.DoTxtOperateDemo");
        Object obj = clazz.newInstance();

        System.out.println("java.policy文件[codeBase]: \n" + jar.replaceAll("\\\\", "//"));

        // 抛出异常:java.security.AccessControlException: access denied ("java.io.FilePermission" "C:/1.txt" "read")
        /*Method method1 = clazz.getMethod("doOperate1", null);
        method1.invoke(obj, null);*/

        // 正常返回
        Method method2 = clazz.getMethod("doOperate2", null);
        method2.invoke(obj, null);

        System.out.println();
    }
}
