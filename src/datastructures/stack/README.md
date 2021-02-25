# Stack

### What is Stack?

Stack is a linear data structure which follows **last in first out**(LIFO) rule. it means that every object that is pushed into stack later, returns earlier. Stack can be implemented by data structures such as Linked List or static array.

### Functions and Time Complexity

| Function |                     Description                      | Time Complexity |
| :------: | :--------------------------------------------------: | :-------------: |
|   Push   |            Adds an element to the Stack.             |      O(1)       |
|   Pop    |      Removes an element from top of the Stack.       |      O(1)       |
|   Peek   | Returns the Stack's top element without removing it. |      O(1)       |
|  Search  |              finds an object in Stack.               |      O(n)       |

### Important Note

If we have a Stack which is implemented with static array, we may need to increase the size of the array in order to push elements into Stack. So, it takes O(n) time at worst case to push an element.