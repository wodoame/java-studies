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
8. To find the kth largest or kth smallest element in an unsorted array you can use quickselect
