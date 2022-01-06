package demo;

import java.io.FilePermission;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * 该类必须放到Package中,否则读取jar时解析不到方法
 */
public class DoTxtOperateDemo {

    /**
     * 执行该方法,正常返回
     */
    public void doOperate2() {
        Boolean result = AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
            @Override
            public Boolean run() {
                operateProperty();
                return true;
            }
        });
        System.out.println("执行结果: " + result);
    }

    /**
     * 执行该方法,抛出异常
     * java.security.AccessControlException: access denied ("java.io.FilePermission" "C:/1.txt" "read")
     */
    public void doOperate1() {
        operateProperty();
    }

    public void operateProperty() {
        FilePermission permission = new FilePermission("C:/1.txt", "read");
        AccessController.checkPermission(permission);
        System.out.println("[C:/1.txt]有[read]权限");
    }
}
