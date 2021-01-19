# Linked List

### What is Linked List?

Linked List is a data structure in which the data are stored in some nodes that are connected together. In `Singly Linked List`, each node has a pointer to the next node. In `Doubly Linked List`, each node has a pointer to its previous node also. Finally, we have a Linked List called `Circular Linked List` in which its `tail` and `head` are connected to each other.

### Functions and Time Complexity

Assuming that we have stored `head` and `tail` nodes in each list:

|  Function   |               Description                | Singly LL | Doubly LL | Circular LL |
| :---------: | :--------------------------------------: | :-------: | :-------: | :---------: |
|   AddLast   | Adds an element at the end of the list.  |   O(1)    |   O(1)    |    O(1)     |
|  AddFirst   | Adds an element at the head of the list. |   O(1)    |   O(1)    |    O(1)     |
|    AddAt    |   Adds an element at a specific index.   |   O(n)    |   O(n)    |    O(n)     |
| RemoveLast  |      Removes the tail of the list.       |   O(n)    |   O(1)    |    O(1)     |
| RemoveFirst |      Removes the head of the list.       |   O(1)    |   O(1)    |    O(1)     |
|  RemoveAt   | Removes an element at a specific index.  |   O(n)    |   O(n)    |    O(n)     |
|   Search    |        Finds an element in list.         |   O(n)    |   O(n)    |    O(n)     |

