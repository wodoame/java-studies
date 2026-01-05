# XOR Approach for Finding Single Number (LeetCode 136)

## Problem
Find the element that appears once when every other element appears exactly twice.

## XOR Properties

The key insight is that XOR has these special properties:
- **`a ^ a = 0`** — Any number XORed with itself equals 0
- **`a ^ 0 = a`** — Any number XORed with 0 equals itself
- **Commutative & Associative** — Order of XOR operations doesn't matter

## How It Works

When you XOR all numbers together, the pairs cancel out to 0, leaving only the single number.

### Example: `[4, 2, 4, 3, 2]`

```
4 ^ 2 ^ 4 ^ 3 ^ 2
= (4 ^ 4) ^ (2 ^ 2) ^ 3
= 0 ^ 0 ^ 3
= 3
```

## Solution

```java
public int singleNumber(int[] nums) {
    int result = 0;
    for (int num : nums) {
        result ^= num;
    }
    return result;
}
```

## Complexity Analysis

| Aspect | Complexity |
|--------|-----------|
| Time | O(n) — Single pass through array |
| Space | O(1) — Only one variable needed |

## Why It's Better

Compared to sorting-based approaches:
- **No sorting overhead** — O(n log n) → O(n)
- **Constant space** — O(n) or O(log n) → O(1)
- **Elegant & mathematical** — Uses bitwise properties instead of brute force

