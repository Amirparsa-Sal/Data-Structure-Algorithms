# Union Find(Disjoint Set)

### What is Union Find?

Union find(Disjoint Set) is a data structure which can keep a set of disjoint objects which can unify together and make a bigger union.

In this implementation of Union Find, we should make a bijection(mapping) from our objects to numbers in range [0,n). It uses this numbers to handle find and unify functions.

We can use a method called `Path Compression` to optimize our Union Find data structure. This method makes our functions work in O(n) amortized time.

### Functions and Time Complexity

| Function  |                     Description                      | Time Complexity |
| :-------: | :--------------------------------------------------: | :-------------: |
|   Find    |        Finds the group number of an element.         |      O(n)       |
|   Unify   | Merges two elements and their groups into one group. |      O(n)       |
| isUnified |      Checks if two element are in a same group.      |      O(n)       |

Note that all time complexity are amortized.