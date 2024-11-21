## Nuts and bolts

A disorganised carpenter has a mixed pile of **_n_** nuts and **_n_** bolts. The goal is to find the corresponding pair of nuts and bolts. Each nut fits exactly one bolt and each bolt fits exactly one nut. By fitting a nut and bolt together, the carpenter can see which one is bigger (but the carpenter cannot compare two nuts or two bolts directly). Design an algorithm for the problem that uses at most proportional to **_N log N_** compares (probabilistically).

**_Hint:_** modify the quicksort partitioning part of quicksort.

```

```

## Selection in two sorted arrays

Given two sorted arrays **_a[]_** and **_b[]_** of lengths **_n1_** and **_n2_** and an integer **_0 <= k <= (n1 + n2)_**, design an algorithm to find a key of rank **_k_**. The order of growrth of the worst case running time of your algorithm should be **_log n_**, where **_n = n1 + n2_**.

**_Hint:_** there are two basic approaches.

-   Approach A: compute the media in a[] and the median in b[]. Recur in a subproblem of roughly half the size.
-   Approach B: design a constant-time algorithm to determine whether a[i] is a key of rank k. Use this subroutine and binary search.

```

```

## Decimal dominants

Given an array with **_n_** keys, design an algorithm to find all values that occur **_n/10_** times. The expected running time of your algorithm should be linear.

**_Hint:_** determine the (n/10)th largest key using quickselect and check if it occurs more than n/10 times.

```

```
