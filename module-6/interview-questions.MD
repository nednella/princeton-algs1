## Merging with a smaller auxiliary array

Suppose that a subarray **_a[0]_** to **_a[n - 1]_** is sorted and the subarray **_a[n]_** to **_a[2(n - 1)]_** is sorted. How can you merge the two subarrays so that **_a[0]_** to **_a[2(n - 1)]_** is sorted using an auxiliary array of length **_n_** (instead of **_2n_**)?

**_Hint:_** copy only the left half into the auxilliary array.

```

```

## Counting inversions

An _inversion_ in an array **_a[]_** is a pair of entries **_a[i]_** and **_a[j]_** such that **_i < j_** but **_a[i] > a[j]_**. Given an array, design a linearithmic algorithm to count the number of inversions.

**_Hint:_** count while mergesorting.

```

```

## Shuffling a linked list

Given a singly-linked listed containing **_n_** items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory, and run in time proportional to **_n log n_** in the worst case.

**_Hint:_** design a linear-time subroutine that can take two uniformly shuffled linked lists of size n1 and n2, and combined them into uniformly shuffled linked lists of size n1 + n2.

```

```
