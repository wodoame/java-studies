Some Leetcode Tips:
1. To get the last digit of an integer you can use the formula `x % 10`
2. To concatenate a digit `y` to an integer `x` , you can use the formula `x * 10 + y`.
   For example to turn 5 to 51, we can do 5*10 + 1. 
3. You can use an array of length 26 to store values relating to each letter of the alphabet
    using the formula `arr[x - 'a']`, where `x` is a letter.
    The value relating to 'a' occupies position 0, that relating to 'b' occupies position 1 and so on.
4. For a linked list, you can create a dummy node `dummy = new ListNode()`
    to prevent an edge case of working with an empty list. To begin from the `real` first node, you'll return `dummy.next`.  
    Otherwise you'd have to be checking `if currentNode == null`. (I hope it makes sense)
5. For a rotated sorted array example `[4, 5, 6, 1, 2, 3]`, the condition to check if an element is in the left sorted half is `arr[i] > arr[right]` where `right = arr.length - 1`. Explanation: an element in the left sorted half will always be greater than every element in the right sorted half and `arr[right]` is the greatest element in the right sorted half. Conversely, to check if an element is in the left sorted half the condition will be `arr[i] < arr[left]` where left is `left = 0`.
6. You can reverse an array in-place by swapping opposite elements. That is swapping `arr[i]` and `arr[arr.length - 1 - i]`.
   With this you can reverse a sub-array `reverse(arr, start, end)`.  
7. You can iterate through all elements of a 2d array by using a single for loop by applying some clever math
  For example study this code that flattens a 2d array.
```java
public static int[] flatten(int[][] arr) {
    int m = arr.length; // Number of rows
    int n = arr[0].length; // Number of columns in each row 
    int numElements = m * n;
    int[] res = new int[numElements];
    // NOTE:: 
    // The formula i % n is the actual index of an element in its row
    // The formula i / n is the row the element belongs to

    for (int i = 0; i < numElements; i++)res[i] = arr[i / n][i % n]; 
    return res;
  }
```
8. To find the kth largest or kth smallest element in an unsorted array you can use quickselect. Or more simply you can use a min-heap or a max-heap.
9. When sorting a list of values based on another property (say their frequency), try to see if a comparator helps in the sorting. 
10. You will need to solve some questions multiple times before the intuition becomes second nature. So don't worry if you don't remember how you solved a question previously: just learn it again.
11. When solving a graph problem, you can convert a list of edges into an adjacency list in order to solve it more easily. 
12. When solving graph problems that deal with relationships, you can try to model the problem in terms of incoming and outgoing edges. So you can store the number of incoming and outgoing edges for each node in a hash map (Or better still in an array serving as a hash map).
13. When an array is used as hash map the execution time of the algorithm is usually better due to the static nature of the array.
14. Dynamic programming problems usually follow a recurrence relation (e.g dp[i] = dp[i-1] + dp[i-2]) so try to think of that. Recursion and memoization also work too.
15. When solving dynamic programming problems, try to start from the goal and work your way backwards.
Every value in your `dp` array will represent the result it takes to reach the goal.
