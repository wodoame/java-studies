package assessment;

import java.util.concurrent.CompletableFuture;

public class Main {
     static void main(String[] args) {
         // Supplier has the method get() that returns a value of type T
         // Runnable has the method run() that does not return a value
         // Consumer has the method accept(T t) that takes a value of type T and returns void
         // Function has the method apply(T t) that takes a value of type T and returns a value of type R
         // Predicate has the method test(T t) that takes a value of type T and returns a boolean

         // thenRun, thenAccept, thenApply, thenCompose, thenCombine
         var future = CompletableFuture.runAsync(()->{
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         });
         future.thenRun(()-> System.out.println("Task completed"));
         CompletableFuture.supplyAsync(()-> 1).thenAccept((j)-> System.out.println("Hey"));
 }
}