# Sparse Table

Sparse Table is a data structure for handling range queries faster and easier. It can answer some range queries in O(1) time complexity which is really cool and some other range queries in O(log n) time complexity which is also good, but it requires a preprocessing with O(n.log n) time complexity to handle queries. The answering time depends on the function which we are trying to find its result on our data. In fact, it depends on the concept of "overlap friendly" functions which I explain it in the following section.

### Overlap Friendly Functions

An overlap friendly function is a function that satisfies the following condition:  

f( f(a,b), f(b,c) ) = f( a, f(b,c) )    for all possible a,b,c  

for example `min` function is overlap friendly because:  

min{ min{a,b}, min{b,c} } = min{ a, min{b,c} } = min{ a, b, c }    for all possible a,b,c  

but `sum` function is not overlap friendly because:  

sum( sum(a,b), sum(b,c) ) = sum( a+b, b+c ) = a + 2b + c != sum(a, sum(b + c) )  

Sparse Table can handle queries for overlap friendly functions in O(1) time complexity but O(log n) time complexity for non overlap friendly functions.

### Constructing Sparse Table

If we have n data in our array, we'll need a table with (log n + 1) rows and n columns.  place (i,j) in table represents the result of the function for items in range [j, 2 ^ i + j - 1] in our array. Being more clear, we can calculate the amount of (i,j) place in table using this formula:  

Table[i,j] = f( Table[i-1,j], Table[i-1, j + 2 ^ (i-1)] )  if Table[i-1,j] and Table[i-1, j + 2 ^ (i-1)] exist  

### Answering Queries

#### Overlap Friendly Functions

Suppose that we want to answer the query in range [start, end] for an overlap friendly function f and suppose n is length of the range [start, end]. We can easily answer this query using this formula:  

Q[start, end] = f(Table[log(n), start], Table[log(n), end + 1 - 2 ^ (log n)])

#### Non Overlap Friendly Functions

We know that we can express any range as sum of some other ranges which have a length that is power of 2. for example: [1,7] = [1,4] + [5,6] + [7,7]. So, we can calculate range query for range [1,7] for non overlap friendly functions in Sparse Table like this:

Q[1,7] = f (Table[2,1], Table[1,5], Table[0,7])

### Functions and Time Complexity

|             Function             |                         Description                          | Time Complexity |
| :------------------------------: | :----------------------------------------------------------: | :-------------: |
|            construct             |                 Constructs the Sparse Table.                 |   O(n.log n)    |
|   rangeQuery(overlap friendly)   |  Calculates a range query for an overlap friendly function.  |      O(1)       |
| rangeQuery(non overlap friendly) | Calculates a range query for a non overlap friendly function. |    O(log n)     |

