import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = (new BufferedReader(new FileReader("Test.txt")));

        // 使用 PushbackReader 读取字符
        PushbackReader pr = new PushbackReader(reader);
        char c = (char)pr.read();
        System.out.println("read first char: " + c);

        // 把读取的字符放回
        pr.unread((int)c);
        System.out.println("unread:" + c);

        // 再次读取
        System.out.println("read again first char is " + (char) pr.read());
    }
}
