# Binary Search Tree

### What is Binary Search Tree?

Binary Search Tree is a Binary Tree which follows one important rule: All of the nodes in left subtree of a node have smaller key than that node and all of the nodes in right subtree of a node have greater key than that node. We can choose whether we want to have multiple keys with same value or not.

Almost every important functions in BST can be done in o(log n) amortized time. The reason we say "amortized time" is that in some cases we have an super unbalanced BST which can increase the time complexity to log(n).

### Functions and Time Complexity

| Function |             Description             | Time Complexity(Amortized) | Time Complexity(Worst) |
| :------: | :---------------------------------: | :------------------------: | :--------------------: |
|  Insert  |   Inserts an element to the tree.   |          O(log n)          |          O(n)          |
|  Remove  |  Removes an element from the tree.  |          O(log n)          |          O(n)          |
|  Search  | Searchs for an element in the tree. |          O(log n)          |          O(n)          |