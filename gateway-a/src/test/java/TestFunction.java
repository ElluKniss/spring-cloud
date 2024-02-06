import com.dw.util.FunctionCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class TestFunction {

    private static final int RUNNING    = -1 << Integer.SIZE-3; // 线程池接受新任务并会处理阻塞队列中的任务
    private static final int SHUTDOWN   =  0 << 29; // 线程池不接受新任务，但会处理阻塞队列中的任务
    private static final int STOP       =  1 << 29; // 线程池不接受新的任务且不会处理阻塞队列中的任务，并且会中断正在执行的任务
    private static final int TIDYING    =  2 << 29; // 所有任务都执行完成，且工作线程数为0，将调用terminated方法
    private static final int TERMINATED =  3 << 29;

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList randoms = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            randoms.add(random.nextInt(100));
        }

//        randoms.forEach(e -> System.out.print(e + " "));
//        List odd = FunctionCommon.odd(randoms);
//        System.out.println();
//        odd.forEach(e -> System.out.print(e + " "));
        List test = FunctionCommon.test(randoms, o -> o%2 ==0);
        test.forEach(e -> System.out.print(e + " "));
        List test1 = FunctionCommon.test(randoms, FunctionCommon::integerWithSingular);
        test1.forEach(e -> System.out.print(e + " "));

        System.out.println();

        System.out.println(RUNNING+"====="+SHUTDOWN+"======"+ STOP+"======"+ TIDYING+"======"+ TERMINATED);

    }
}
