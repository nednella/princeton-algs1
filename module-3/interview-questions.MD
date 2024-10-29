## 3-SUM in quadratic time

Design an algorithm for the 3-SUM problem that takes time proportional to **_n^2_** in the worst case. You may assume that you can sort the **_n_** integers in time proportional to **_n^2_** or better.

```

```

## Search in a bitonic array

An array is _bitonic_ if it is compromised of an increasing sequence of intergers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of **_n_** distinct integer values, determines whether a given integer is in the array.

-   Standard version: use **_~3 lg n_** compares in the worst case.
-   Signing bonus: use **_~2 lg n_** compares in the worst case and prove that no algorithm can guarantee to perform fewer than **_~2 lg n_** compares in the worst case.

```

```

## Egg drop

Suppose that you have an **_n_**-story building, with floors 1 through **_n_** and plenty of eggs. An egg breaks if it is dropped from floor **_T_** or higher and does not break otherwise. Your goal is to devise a strategy to determine the value of **_T_** given the following limitations on the number of eggs, and tosses.

-   Version 0: **_1_** egg, **_<= T_** tosses.
-   Version 1: **_~1 lg n_** eggs and **_~1 lg n_** tosses.
-   Version 2: **_~1 lg T_** eggs and **_~2 lg T_** tosses.
-   Version 3: **_2_** eggs and **_~ 2 sqrt(n)_** tosses.
-   Version 3: **_2_** eggs and **_<= c sqrt(T)_** tosses for some fixed constant C.

```

```
