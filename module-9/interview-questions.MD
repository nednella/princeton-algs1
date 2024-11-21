## Java autoboxing and equals()

Consider two **_double_** values **_a_** and **_b_** and their corresponding **_Double_** values **_x_** and **_y_**.

-   Find values such that **_(a == b)_** is **_true_** but **_x.equals(y)_** is **_false_**.
-   Find values such that **_(a == b)_** is **_false_** but **_x.equals(y)_** is **_true_**.

**_Hint:_** IEEE floating point arithmetic has some peculiar rules for 0.0, -0.0, and NaN. Java requires that equals() implements an equivalence relation.

```

```

## Check if a binary tree is a BST

Given a binary tree where each **_Node_** contains a key, determine whether it is a binary search tree. Use extra space proportional to the height of the tree.

**_Hint:_** design a recursive function isBST(Node x, Key min, Key max) that determines whether x is the root of a binary search tree with all keys between min and max.

```

```

## Inorder traversal with constant extra space

Design an algorithm to perform an inorder traversal of a binary search tree using only a constant amount of extra space.

**_Hint:_** You may modify the BST during the traversal provided you restore it upon completion.

```

```

## Web tracking

Suppose that you are tracking **_n_** websites and **_m_** users, and you want to support the following API:

-   User visits to a website
-   How many times a given user has visited a website

What data structure would you use?

**_Hint:_** maintain a symbol table of symbol tables.

```

```
