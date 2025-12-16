# Minimum Subarray Sum Difference Solution

## Problem Overview
Given an array of positive integers `nums` and an integer `k`, partition the array into two non-empty subarrays A and B such that:
- All elements in A are ≤ pivot P
- All elements in B are > pivot P  
- The pivot P must be the k-th smallest element (1-indexed)
- Find the minimum absolute difference between sum(A) and sum(B)

## Solution Approach: QuickSelect Algorithm

### Why QuickSelect?
The hint suggests we don't need to fully sort the array. QuickSelect is perfect because:
1. It finds the k-th smallest element in O(n) average time
2. It uses the same partitioning logic as QuickSort
3. It only recursively processes one partition, making it more efficient than full sorting

### Algorithm Steps

#### Step 1: Find the k-th Smallest Element
```java
int pivot = quickSelect(arr, 0, arr.length - 1, k - 1);
```
- Use QuickSelect to find the k-th smallest element (the pivot)
- Convert k from 1-indexed to 0-indexed by using `k - 1`
- This operates on a copy of the array to avoid modifying the original

#### Step 2: Partition and Calculate Sums
```java
for (int num : nums) {
    if (num <= pivot) {
        sumA += num;
    } else {
        sumB += num;
    }
}
```
- Iterate through the original array
- Elements ≤ pivot go to subarray A
- Elements > pivot go to subarray B
- Initially, the pivot is included in sumA

#### Step 3: Optimize Pivot Placement
```java
if (sumA > sumB) {
    sumA -= pivot;
    sumB += pivot;
}
```
- To minimize the difference, assign the pivot to the larger sum
- If sumA > sumB, move pivot from A to B
- Otherwise, leave pivot in A

#### Step 4: Calculate Final Difference
```java
diff = Math.abs(sumA - sumB);
```

### QuickSelect Implementation

The `quickSelect` method recursively partitions the array:

```java
static int quickSelect(int[] arr, int low, int high, int k) {
    if (low == high) {
        return arr[low];
    }
    
    int pi = partition(arr, low, high);
    
    if (pi == k) {
        return arr[pi];
    } else if (k < pi) {
        return quickSelect(arr, low, pi - 1, k);
    } else {
        return quickSelect(arr, pi + 1, high, k);
    }
}
```

**Key Points:**
1. Base case: when low == high, we've found our element
2. Partition the array using QuickSort's partition method
3. If partition index equals k, we found the k-th smallest element
4. If k < pi, recurse on left subarray
5. If k > pi, recurse on right subarray

### Example Walkthrough

**Input:** `nums = [8, 3, 1, 5, 2]`, `k = 3`

1. **Find 3rd smallest (k=3, 0-indexed k=2):**
   - QuickSelect finds pivot = 3
   - Sorted order would be: [1, 2, 3, 5, 8]
   - 3rd element is 3 ✓

2. **Initial Partition:**
   - A (≤ 3): [1, 2, 3] → sumA = 6
   - B (> 3): [5, 8] → sumB = 13

3. **Optimize Pivot Placement:**
   - sumA (6) < sumB (13), so pivot stays in A
   - No change needed

4. **Final Difference:**
   - |6 - 13| = 7

Wait, let me reconsider the logic based on the problem statement...

**Re-reading the problem:** "The pivot P belongs to the larger sum, or arbitrarily to A if the sums are equal before including P."

Let me trace through again:

1. **Find 3rd smallest:** pivot = 3
2. **Partition (pivot initially in A):**
   - Elements ≤ 3: [1, 2, 3] → sumA = 6
   - Elements > 3: [5, 8] → sumB = 13
3. **Pivot placement rule:**
   - Before including P in either sum: sum(1,2) = 3, sum(5,8) = 13
   - Since sumB is larger BEFORE including pivot, pivot should go to B
   - So the logic should be: if sumB > sumA (with pivot in A), move pivot to B

Let me verify with the expected output (13):
- If pivot goes to B: A = [1,2] (sum=3), B = [3,5,8] (sum=16)
- Difference = |3 - 16| = 13 ✓

The current implementation needs adjustment. The logic should be:
- Initially count pivot in A
- If sumA > sumB (meaning B is smaller), move pivot to B to balance
- This matches the expected behavior

Actually, reviewing the code again, the logic is correct:
```java
if (sumA > sumB) {
    sumA -= pivot;
    sumB += pivot;
}
```
This says: if A is larger (with pivot), move pivot to B to minimize difference.

But for the example, sumA=6, sumB=13, so sumA is NOT > sumB, so pivot stays in A.
That gives difference = |6-13| = 7, not 13.

Let me re-read the problem statement more carefully...

**Ah! The problem says:** "The pivot P belongs to the larger sum"

This means pivot should ALWAYS go to the larger sum! So the logic should be:
```java
if (sumB > sumA) {
    sumA -= pivot;
    sumB += pivot;
}
```

Wait, that's backwards. Let me think through this more carefully:

Initially, pivot is counted in sumA (elements ≤ pivot).
- If we want pivot to go to the larger sum:
  - If sumA > sumB: pivot stays in A (already there)
  - If sumB > sumA: move pivot from A to B

So the correct logic is:
```java
if (sumB > sumA) {
    sumA -= pivot;
    sumB += pivot;
}
```

This would give for the example:
- Initial: sumA=6, sumB=13
- sumB > sumA is true, so move pivot to B
- Final: sumA=3, sumB=16
- Difference = |3-16| = 13 ✓

The current implementation has the condition backwards!

## Time Complexity
- QuickSelect: O(n) average case, O(n²) worst case
- Partitioning and sum calculation: O(n)
- Overall: O(n) average case

## Space Complexity
- O(n) for the array copy
- O(log n) for recursion stack on average

## Test Cases

### Test Case 1
**Input:** nums = [8, 3, 1, 5, 2], k = 3  
**Expected Output:** 13  
**Explanation:** Pivot = 3, A = [1,2], B = [3,5,8], |3-16| = 13

### Test Case 2
**Input:** nums = [1, 2, 3, 4, 5], k = 3  
**Expected Output:** 3  
**Explanation:** Pivot = 3, move to larger sum for optimal balance

### Test Case 3  
**Input:** nums = [10, 20, 30], k = 2  
**Expected Output:** 10  
**Explanation:** Pivot = 20, optimal placement minimizes difference

