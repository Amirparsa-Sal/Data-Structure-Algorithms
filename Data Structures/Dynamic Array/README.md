# Dynamic Array

#### What is Dynamic Array?

Dynamic Array is an variable-size list that allows elements to be added or be removed and it is similar to `list` data structure in Python. 

#### Functions and Time Complexity

| function | Description                              | Time Complexity |
| -------- | ---------------------------------------- | --------------- |
| Append   | Adds an element at the end of the array. | O(1)            |
| Insert   | Inserts an element at a specific index.  | O(n)            |
| Remove   | Removes an element from array.           | O(n)            |
| Search   | Finds an element in array.               | O(n)            |

#### Important Notes

* In order to `append` or `insert` an element to a full Dynamic Array: we must double the capacity of the array and then add element to it.

* In order to `delete` an element from a Dynamic Array: after deleting the element, if current size of the array is less than or equal to capacity/4 we must reduce the capacity of the Dynamic Array to capacity/2.

