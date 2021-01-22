# Queue

### What is Queue?

Queue is an abstract data structure which represents real queues. adding an object to the Queue is called `Enqueue` and once an object is added, it goes to the end of the Queue. removing an object from the Queue is called `DeQueue` and objects must be removed from the front of the Queue. Queue can be implemented by Linked List and static arrays.

### Functions and Time Complexity

| Function |                      Description                       | Time Complexity |
| :------: | :----------------------------------------------------: | :-------------: |
| EnQueue  |        Adds an element to the end of the Queue.        |      O(1)       |
| DeQueue  |    Removes an element from the front of the Queue.     |      O(1)       |
|   Peek   | Returns the Queue's first element without removing it. |      O(1)       |
|  Search  |               finds an object in Queue.                |      O(n)       |

### Important Note

If we have a Queue which is implemented with static array, we may need to increase the size of the array in order to push elements into Queue. So, it takes O(n) time at worst case to push an element into a Queue with dynamic size.