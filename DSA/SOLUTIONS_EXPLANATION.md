# DSA Assignment Solutions Explanation

This document provides a detailed explanation of the three methods implemented in `Assignment1.java` for the DSA Live Session preparation.

---

## 1️⃣ Maximum Subarray Sum: `maxSubarraySum()`

### Algorithm: Kadane's Algorithm

### Method Signature
```java
public static int maxSubarraySum(int[] nums)
```

### Purpose
Finds the contiguous subarray within a one-dimensional array that has the largest sum.

### How It Works

**Kadane's Algorithm** is an optimal solution that uses dynamic programming principles to solve this problem in linear time.

#### Core Concept
The algorithm maintains two variables:
- **`currentSum`**: The maximum sum of the subarray ending at the current position
- **`maxSum`**: The maximum sum found so far across all positions

#### Step-by-Step Process

1. **Initialize**: Start with the first element
   - `maxSum = nums[0]`
   - `currentSum = nums[0]`

2. **Iterate**: For each element from index 1 to n-1:
   - **Decision Point**: At each position, decide whether to:
     - Extend the existing subarray: `currentSum + nums[i]`
     - Start a new subarray: `nums[i]`
   - Choose the maximum: `currentSum = Math.max(nums[i], currentSum + nums[i])`
   - Update the global maximum: `maxSum = Math.max(maxSum, currentSum)`

3. **Return**: The maximum sum found

### Example Walkthrough

**Input**: `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`

| Index | Element | currentSum Calculation | currentSum | maxSum |
|-------|---------|------------------------|------------|--------|
| 0 | -2 | (initial) | -2 | -2 |
| 1 | 1 | max(1, -2+1) = 1 | 1 | 1 |
| 2 | -3 | max(-3, 1-3) = -2 | -2 | 1 |
| 3 | 4 | max(4, -2+4) = 4 | 4 | 4 |
| 4 | -1 | max(-1, 4-1) = 3 | 3 | 4 |
| 5 | 2 | max(2, 3+2) = 5 | 5 | 5 |
| 6 | 1 | max(1, 5+1) = 6 | 6 | 6 |
| 7 | -5 | max(-5, 6-5) = 1 | 1 | 6 |
| 8 | 4 | max(4, 1+4) = 5 | 5 | 6 |

**Output**: `6` (from subarray `[4, -1, 2, 1]`)

### Complexity Analysis
- **Time Complexity**: O(n) - Single pass through the array
- **Space Complexity**: O(1) - Only using two variables

### Edge Cases Handled
- Empty or null array: Throws `IllegalArgumentException`
- Single element: Returns that element
- All negative numbers: Returns the least negative number

---

## 2️⃣ Rotate Array: `rotateArray()`

### Algorithm: Temporary Array Method

### Method Signature
```java
public static void rotateArray(int[] nums, int k)
```

### Purpose
Rotates the array to the right by `k` steps.

### How It Works

This implementation uses a **temporary array** approach (Method 1 from the requirements).

#### Step-by-Step Process

1. **Handle Edge Cases**:
   - Null or empty array: Return immediately
   - Normalize k: `k = k % n` (handles cases where k > array length)
   - If k = 0: No rotation needed, return

2. **Create Temporary Array**:
   - Allocate a new array of size n

3. **Calculate New Positions**:
   - For each element at index `i`, its new position is `(i + k) % n`
   - Copy each element to its new position in the temporary array

4. **Copy Back**:
   - Copy all elements from temporary array back to original array

### Example Walkthrough

**Input**: `nums = [1, 2, 3, 4, 5, 6, 7]`, `k = 3`

| Original Index | Element | New Position (i+3)%7 | New Array |
|----------------|---------|----------------------|-----------|
| 0 | 1 | 3 | [_, _, _, 1, _, _, _] |
| 1 | 2 | 4 | [_, _, _, 1, 2, _, _] |
| 2 | 3 | 5 | [_, _, _, 1, 2, 3, _] |
| 3 | 4 | 6 | [_, _, _, 1, 2, 3, 4] |
| 4 | 5 | 0 | [5, _, _, 1, 2, 3, 4] |
| 5 | 6 | 1 | [5, 6, _, 1, 2, 3, 4] |
| 6 | 7 | 2 | [5, 6, 7, 1, 2, 3, 4] |

**Output**: `[5, 6, 7, 1, 2, 3, 4]`

### Complexity Analysis
- **Time Complexity**: O(n) - Two passes through the array
- **Space Complexity**: O(n) - Temporary array of size n

### Alternative Method 2 (Optimized In-Place - Not Implemented)

The requirements mention implementing a second method with O(1) space. Here's how it would work:

**Reversal Algorithm**:
1. Reverse entire array: `[7, 6, 5, 4, 3, 2, 1]`
2. Reverse first k elements: `[5, 6, 7, 4, 3, 2, 1]`
3. Reverse remaining n-k elements: `[5, 6, 7, 1, 2, 3, 4]`

This achieves O(1) space with O(n) time complexity.

---

## 3️⃣ Move Zeroes: `moveZeroes()`

### Algorithm: Two-Pointer Technique

### Method Signature
```java
public static void moveZeroes(int[] nums)
```

### Purpose
Moves all zeros to the end of the array while maintaining the relative order of non-zero elements, performed in-place.

### How It Works

This implementation uses a **two-pointer approach** for optimal performance.

#### Core Concept
Use a pointer (`nonZeroPos`) to track where the next non-zero element should be placed.

#### Step-by-Step Process

1. **Initialize**: Set `nonZeroPos = 0` (position for next non-zero element)

2. **Iterate**: Loop through the array:
   - If current element is non-zero:
     - Swap it with the element at `nonZeroPos` (only if positions differ - optimization)
     - Increment `nonZeroPos`
   - If current element is zero:
     - Skip it (continue to next element)

3. **Result**: All non-zeros are moved to the front in order, zeros fill the remaining positions

### Example Walkthrough

**Input**: `nums = [0, 1, 0, 3, 12]`

| Step | i | nums[i] | nonZeroPos | Action | Array State |
|------|---|---------|------------|--------|-------------|
| Initial | - | - | 0 | - | [0, 1, 0, 3, 12] |
| 1 | 0 | 0 | 0 | Skip (zero) | [0, 1, 0, 3, 12] |
| 2 | 1 | 1 | 0 | Swap(0,1), nonZeroPos++ | [1, 0, 0, 3, 12] |
| 3 | 2 | 0 | 1 | Skip (zero) | [1, 0, 0, 3, 12] |
| 4 | 3 | 3 | 1 | Swap(1,3), nonZeroPos++ | [1, 3, 0, 0, 12] |
| 5 | 4 | 12 | 2 | Swap(2,4), nonZeroPos++ | [1, 3, 12, 0, 0] |

**Output**: `[1, 3, 12, 0, 0]`

### Why This Works

The algorithm partitions the array into regions:
- **[0, nonZeroPos)**: Contains all non-zero elements processed so far
- **[nonZeroPos, i)**: Contains all zeros encountered so far
- **[i, n)**: Not yet processed

### Optimization Detail

The condition `if (i != nonZeroPos)` before swapping avoids unnecessary swaps when:
- The array has no leading zeros
- We're processing a consecutive sequence of non-zeros

This reduces the total number of write operations.

### Complexity Analysis
- **Time Complexity**: O(n) - Single pass through the array
- **Space Complexity**: O(1) - In-place modification, only using one extra variable
- **Number of Operations**: Minimized by only swapping when necessary

### Edge Cases Handled
- Null or empty array: Throws `IllegalArgumentException`
- No zeros: Array remains unchanged (optimization prevents unnecessary swaps)
- All zeros: Array remains unchanged
- Zeros at the end: Minimal operations due to optimization

---

## Summary Comparison

| Problem | Algorithm | Time | Space | Key Technique |
|---------|-----------|------|-------|---------------|
| Max Subarray Sum | Kadane's | O(n) | O(1) | Dynamic Programming |
| Rotate Array | Temporary Array | O(n) | O(n) | Index Mapping |
| Move Zeroes | Two-Pointer | O(n) | O(1) | Partitioning |

---

## Testing

All three methods are tested in the `main()` method with the examples provided in the problem statements. Run the program to see the output:

```bash
javac Assignment1.java
java Assignment1
```

Expected output will demonstrate each algorithm working correctly on the test cases.

---

**Good luck with your DSA Live Session! 🚀**

