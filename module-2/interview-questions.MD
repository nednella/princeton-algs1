## Social network connectivity

Given a social network containing **_N_** members and a log file containing **_M_** timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be **_M log N_** or better and use extra space proportional to **_N_**.

```

```

## Union-find with specific canonical element

Add a method **_find()_** to the union-find data type so that **_find(i)_** returns the largest element in the connected component containing **_i_**. The operations, **_union()_**, **_connected()_**, and **_find()_** should all take logarithmic time or better. For example, if one of the connected components is **_{1, 2, 6, 9}_**, then the **_find()_** method should return 9 for each of the four elements in the connected components.

```

```

## Sucessor with delete

Given a set of **_n_** integers **_S = {0, 1, ..., n - 1}_** and a sequence of requests of the following form:

-   Remove **_x_** from **_S_**
-   Find the successor of **_x_**: the smallest **_y_** in **_S_** such that **_y_** is less than or equal to **_x_**.

Design a data type so that all operations (except construction) take logarithmic time or better in the worst case.

```

```
