# Fenwick Tree(Binary Indexed Tree)

###  What is Fenwick Tree?

Fenwick Tree or Binary Indexed Tree is a tree which can efficiently calculate the range queries such as calculating the sum of some elements in an array.

Indexing in Fenwick Tree is 1 based. Each place in Fenwick Tree has responsibility to control some other places. For example 6th place in Fenwick Tree with 110 binary representation is responsible to control 2 places which are 6th and 5th place. Meaning that 6th place in Fenwick Tree is the sum of 6th and 5th places in our array.

### How can we determine responsibilities?

Each place in Fenwick Tree is responsible to `2^(number of zeros in the right side of binary representation)` places including itself.

#### Example: Determining responsibilities for 6th place

Binary representation of 6: 110    Number of zeros in the right side: 1

So 6th place is responsible to `2^(1) = 2` places. So 6th place is responsible to 6th and 5th places. 

Also we can find parents of each place(which are responsible to that place) by adding the place index to `value of less significant 1 bit` in its binary representation.

#### Example: Finding the parents of 2nd place in a Fenwick Tree with size 8

`Binary representation of 2: 0010   Value of less significant 1 bit: 2    2 + 2 = 4  `

`Binary representation of 4: 0100   Value of less significant 1 bit: 4    4 + 4 = 8`

So 4th and 8th places are parents of 2nd place.

### Functions and Time Complexity

|   Function    |                    Description                    | Time Complexity |
| :-----------: | :-----------------------------------------------: | :-------------: |
|   Construct   |           Constructing the Fenwick Tree           |      O(n)       |
| PrefixSum(n)  |   Calculates the sum of elements up to index n.   |    O(log n)     |
| RangeSum(l,r) | Calculates the sum of elements from l to r index. |    O(log n)     |



