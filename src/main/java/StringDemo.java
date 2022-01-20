public class StringDemo {

    String str = "good";

    char[] ch = {'t', 'e', 's', 't'};

    /**
     * String str,str位于change()对应栈帧的局部变量表中:
     * change方法对应字节码如下:
     * public void change(java.lang.String, char[]);
     *   descriptor: (Ljava/lang/String;[C)V
     *   flags: ACC_PUBLIC
     *   Code:
     *     stack=3, locals=3, args_size=3
     *        0: ldc           #7        // String test ok
     *        2: astore_1
     *        3: aload_2
     *        4: iconst_0
     *        5: bipush        98
     *        7: castore
     *        8: return
     *     LineNumberTable:
     *       line 9: 0
     *       line 10: 3
     *       line 11: 8
     *     LocalVariableTable:
     *       Start  Length  Slot  Name   Signature
     *           0       9     0  this   Lcom/unionpay/modular/Main;
     *           0       9     1  str    Ljava/lang/String;
     *           0       9     2    ch   [C
     */
    public void change(String str, char[] ch) {
        // 此处修改的是局部变量str的值(只是没有使用而已),而成员变量str是不变的
        // 可以把局部变量str改成str1,,就好理解了
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringDemo main = new StringDemo();
        main.change(main.str, main.ch);
        // good
        System.out.println(main.str);
        // best
        System.out.println(main.ch);
    }
}
