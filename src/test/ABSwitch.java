package test;

/**
 * 功能描述
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/9/4
 **/
public class ABSwitch {
    public static void main(String[] args) {
        int a = 5;
        int b = 7;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        Printer.println(String.format("a=%s,b=%s", a, b));
    }
}
