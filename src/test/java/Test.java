import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("开启第一个线程");
            }
        };
        new Thread(runnable).start();


        Runnable run = () -> {
            System.out.println("开启第二个线程");
        };

        new Thread(run).start();
        //遍历集合元素首字母大写然后拼接get后遍历输出
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream()
                .map(str -> {
                    return "get" + str.substring(0, 1).toUpperCase() + str.substring(1);
                })
                .collect(ArrayList::new, List::add, List::addAll)
                .forEach(str -> System.out.println(str));
    }
}
