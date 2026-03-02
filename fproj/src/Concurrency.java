import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Concurrency{
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new MyTask());
        executor.submit(new MyTask());
        executor.submit(new MyTask());

        executor.shutdown();
    }
}

class Counter {
    AtomicInteger count = new AtomicInteger();

     void increment() {
        count.incrementAndGet();
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Running " + Thread.currentThread().getName());
    }
}