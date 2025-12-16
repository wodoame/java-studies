// POJO - Plain Old Java Object
// thread.join() makes a thread wait for another thread to finish its execution before continuing.
// thread.isAlive() checks if a thread is currently running.
// thread.sleep(milliseconds) pauses the execution of the current thread for a specified duration.
// thread.start() initiates the execution of a thread.
// synchronized blocks ensure that only one thread can access a particular section of code at a time, preventing race conditions

void main() {
    // Concurrency in Java allows multiple threads to run simultaneously,
    // enabling efficient use of resources and improved performance for tasks that can be parallelized.
    // Key concepts include threads, synchronization, and thread lifecycle management.

    Thread thread1 = new Thread(() -> {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread 1 - Count: " + i);
            try {
                Thread.sleep(500); // Pause for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    });

    Thread thread2 = new Thread(() -> {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread 2 - Count: " + i);
            try {
                Thread.sleep(300); // Pause for 300 milliseconds
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    });

    thread1.start();
    thread2.start();

    try {
        thread1.join(); // Wait for thread1 to finish
        thread2.join(); // Wait for thread2 to finish
    } catch (InterruptedException e) {
        System.out.println(e.getMessage());
    }

    System.out.println("Both threads have finished execution.");

}