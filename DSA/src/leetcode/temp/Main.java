package leetcode.temp;


import java.util.PriorityQueue;

public class Main {
    static void main() {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(1);
        minHeap.offer(2);
        minHeap.offer(3);
        minHeap.offer(4);
        minHeap.offer(5);
        minHeap.offer(6);
        System.out.println(minHeap.peek());
    }
}
