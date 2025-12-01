Here is the content formatted into better-looking and structured markdown for your DSA Live session preparation:

# 💻 DSA Live Session Prep: Tomorrow's Agenda

Hello everyone! 👋

These are the essential questions we'll be discussing in **tomorrow's DSA Live session**. Please review and solve them beforehand. Be prepared: anyone may be called at random to explain their approach and solution!

---

## 1️⃣ Maximum Subarray Sum: Kadane's Algorithm

### Problem Statement

Given an integer array `nums`, find the **subarray** (a contiguous sequence of elements) that has the largest sum, and return its sum.

### Goal & Constraints

* **Algorithm:** **Kadane's Algorithm** is the typical solution.
* **Input Type:** `int[]` (Integer Array)
* **Subarray Condition:** Must be **contiguous** (adjacent elements).
* **Target Time Complexity:** $O(n)$, where $n$ is the length of the array.

> **Example:**
> Input: `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`
> Output: `6` (The subarray is `[4, -1, 2, 1]`)

---

## 2️⃣ Rotate Array: Efficient k-Step Right Rotation

### Problem Statement

Given an integer array `nums`, **rotate the array to the right by $k$ steps**, where $k$ is a non-negative integer.

### Implementation Requirements

Implement the solution using at least **two different methods**:

1.  **Method 1 (Temporary Array):** A straightforward method that uses $O(n)$ extra space (e.g., using a temporary array).
2.  **Method 2 (Optimized In-Place):** An optimized method that performs the rotation **in-place** with $O(1)$ extra space and $O(n)$ time complexity.

### Goal & Constraints

* **Input Type:** `int[]`
* **Rotation:** To the **right** by $k$ steps.
* **Optimization Goal:** Achieve $O(1)$ Space, $O(n)$ Time for the optimized method.

> **Example:**
> Input: `nums = [1, 2, 3, 4, 5, 6, 7]`, $k = 3$
> Output: `[5, 6, 7, 1, 2, 3, 4]`

---

## 3️⃣ Move Zeroes: In-Place and Relative Order Preservation

### Problem Statement

Given an integer array `nums`, **move all the $0$s to the end of the array** while **maintaining the relative order of the non-zero elements**.

### Goal & Constraints

* **Input Type:** `int[]`
* **Modification:** Must be **in-place** (modify the input array directly).
* **Order Requirement:** The **relative order of non-zero elements** must be preserved.
* **Optimization:** Your algorithm should minimize the total number of operations (writes/swaps).

> **Example:**
> Input: `nums = [0, 1, 0, 3, 12]`
> Output: `[1, 3, 12, 0, 0]`
