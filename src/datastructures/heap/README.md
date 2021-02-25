# Binary Heap Tree

### What is Binary Heap Tree?

Binary Heap Tree is a tree-based data structure which stores data in a full binary tree. A full binary tree is a tree in which all non-leaf nodes has 2 children. Heaps can be used to implement Priority Queues.

Binary Heap Tree has 2 types: Max Heap and Min Heap

In a Min Heap, every parent node have a value which is smaller than their children and vice versa for a Max Heap.

Heaps can be implemented using arrays. Removing smallest element in Min Heap can be done in O(1) time complexity. while removing other elements can be done in O(n) complexity. We can optimize this process using a Hash Map and a Binary Search Tree to O(log n). You can see this optimization in `BinaryHeapOptimized.java` file.
### Functions and Time Complexity

|  Function  |            Description            | Binary Heap | Binary Heap Optimized |
| :--------: | :-------------------------------: | :---------: | :-------------------: |
| ExtractMin |     Removes the min element.      |    O(1)     |         O(1)          |
|    Add     |   Adds an element to the Heap.    |  O(log n)   |       O(log n)        |
|   Remove   | Removes an element from the Heap. |    O(n)     |       O(log n)        |
|   Search   |  Searchs for an element in Heap.  |    O(n)     |         O(1)          |
