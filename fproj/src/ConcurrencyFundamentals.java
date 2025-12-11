// A thread is an independent path of execution in a program.
// The main method execution is a thread.
// the .start() method can only be called once per thread instance.
// An IllegalThreadStateException is thrown if call it more than once.
// Multiple threads can run concurrently in a program, effectively speeding up the program.
// Creating a large number of threads can lead to a high memory (heap) usage.
// Therefore, the Executor framework is preferred for managing thread pools.
// A thread pool is just a collection of threads that can be reused to execute tasks.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyFundamentals {

}

class ThreadExample extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running");
    }

    void main() {
        ThreadExample thread = new ThreadExample();
        thread.start(); // Start the thread
    }

    void usingTheExecutorFramework() {
        ExecutorService ex =  Executors.newFixedThreadPool(3);
    }
}