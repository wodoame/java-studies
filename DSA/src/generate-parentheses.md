The problem of generating all combinations of well-formed parentheses for a given number of pairs ($n$) is a classic example that can be solved effectively using **Backtracking** (or Depth-First Search).

Here is an explanation of the concept and how to solve it for $n=3$.

## 🎯 Concept: Constrained Generation with Backtracking

The goal is to build valid parenthesis strings incrementally. Backtracking is the perfect technique because, at each step, you explore adding either an open or a closing parenthesis, and if that choice leads to an invalid state, you **backtrack** (undo the choice) and try another one.

The constraints that define a "well-formed" string are:

1.  The total number of open parentheses must equal the total number of closing parentheses (both must equal $n$).
2.  At any point while building the string, the number of **open parentheses must be greater than or equal to the number of closing parentheses**. If you ever have more closing than open parentheses, the string is invalid immediately.

---

## 🛠️ The Backtracking Algorithm

We can use a recursive function (let's call it `backtrack`) that keeps track of three things:

1.  **`current_string`**: The string built so far.
2.  **`open_count`**: The number of open parentheses '(' used so far.
3.  **`close_count`**: The number of closing parentheses ')' used so far.

The function follows these rules:

### 1. Base Case (Success)

*   If the length of the `current_string` is $2n$ (i.e., `open_count` equals `close_count` and both equal $n$), then a complete, valid combination has been found. **Add it to the result list and return.**

### 2. Recursive Steps (Choices)

#### Choice A: Add an Open Parenthesis **'('**

*   You can add an open parenthesis if the number of open parentheses used is **less than $n$**.
*   If `open_count` $< n$:
    *   Call `backtrack(current_string + '(', open_count + 1, close_count)`.

#### Choice B: Add a Closing Parenthesis **')'**

*   You can add a closing parenthesis if the number of closing parentheses is **less than the number of open parentheses** used so far. This ensures the "well-formed" constraint is never violated.
*   If `close_count` $<$ `open_count`:
    *   Call `backtrack(current_string + ')', open_count, close_count + 1)`.

---

## 🧠 Walkthrough for `generateParenthesis(3)`

Start with: `backtrack("", 0, 0)`

| Step | Call to `backtrack` | Action | Resulting String | Open Count | Close Count | State |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **1** | `backtrack("", 0, 0)` | Add **'('** (Rule: $0 < 3$) | `(` | 1 | 0 |
| **2** | `backtrack("(", 1, 0)` | Add **'('** (Rule: $1 < 3$) | `((` | 2 | 0 |
| **3** | `backtrack("((", 2, 0)` | Add **'('** (Rule: $2 < 3$) | `(((` | 3 | 0 |
| **4** | `backtrack("(((", 3, 0)` | Add **')'** (Rule: $0 < 3$) | `((()` | 3 | 1 |
| **5** | `backtrack("((()", 3, 1)` | Add **')'** (Rule: $1 < 3$) | `((())` | 3 | 2 |
| **6** | `backtrack("((())", 3, 2)` | Add **')'** (Rule: $2 < 3$) | **`((()))`** | 3 | 3 | **BASE CASE FOUND!**
| **7** | (Backtrack) | Go back to Step 5, try alternative... | | | |
| **8** | `backtrack("((()", 3, 1)` | **Can't add '('.** Add **')'** (Rule: $1 < 3$) $\rightarrow$ *Already did this, resulting in Step 5.* | | | |
| **9** | (Backtrack) | Go back to Step 4, try alternative... | | | |
| **10** | `backtrack("(((", 3, 0)` | **Can't add '('.** Add **')'** (Rule: $0 < 3$) $\rightarrow$ *Already did this, resulting in Step 4.* | | | |
| **11** | (Backtrack) | Go back to Step 3, try alternative... | | | |
| **12** | `backtrack("((", 2, 0)` | Add **')'** (Rule: $0 < 2$) | `(())` | 2 | 1 |
| **13** | `backtrack("(()", 2, 1)` | Add **'('** (Rule: $2 < 3$) | `(()(` | 3 | 1 |
| **14** | `backtrack("(()(", 3, 1)` | Add **')'** (Rule: $1 < 3$) | `(()()` | 3 | 2 |
| **15** | `backtrack("(()()", 3, 2)` | Add **')'** (Rule: $2 < 3$) | **`(()())`** | 3 | 3 | **BASE CASE FOUND!**
| ... | | **And so on, exploring all paths...** | | | |

By systematically exploring every valid path and stopping immediately when a path becomes invalid (e.g., trying to add `)` when `close_count` $\ge$ `open_count`), the backtracking process guarantees that you find **all** and **only** the well-formed combinations.

The final output is: `["((()))","(()())","(())()","()(())","()()()"]`.

---