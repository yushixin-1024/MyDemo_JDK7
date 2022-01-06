import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * InvocationHandler#invoke()调用代理对象的toString()会出现栈溢出
 */
public class ProxyDemo {

    public static void main(String[] args) {

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                // Exception in thread "main" java.lang.StackOverflowError
                // proxy是动态生成的代理类对象[return (String) super.h.invoke(this, $Proxy12.m2, null)]
                // this就是当前动态生成的代理类对象,自调用toString()，所以栈溢出
                // System.out.println(proxy.toString());
                return "test()";
            }
        };

        TestInterface proxy = (TestInterface) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class[] {TestInterface.class}, handler);
        System.out.println(proxy.test());
    }
}

interface TestInterface {
    String test();
}