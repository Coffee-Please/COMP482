# Project 2 | Sorting Algorithms 

**Idea**: You will be given a set of integers and need to find the *i<sup>th</sup>* smallest using three methods

1.  Sort the integers (maybe with a built in sort).
2.  Use the natural variant of quick sort.
3.  Use the median of medians method.

**Input Format**: The input file will be called input2.txt and be in the same directory as the java and class files. The first line will be the values of *i* (the item desired) 
and *N* (the number of items) seperated by white space. The remaining lines will consist of at least *N* additional integers (only the first *N* should be used - any extra ignored)
seperated by white space.

**Output**: The output will be the *i<sup>th</sup>* smallest value out of the *N* values calculated using the the three methods. In other words, the output should be the result 
you would get by sorting the *N* items and returning the item in array position *i*. 

A reminder of how the methods work:

SORT - use any sort (including Arrays.sort or Collections.sort) and return the *i<sup>th</sup>* item.

QUICKSORT Variant - choose a pivot in any way you wish (first item, random item, last item, ...)  and split thearray into items smaller than the pivot and items larger than 
the pivot. Recurse on the side that contiains the *i<sup>th</sup>* item. I.E. if there are *j*¿*i* items smaller than the pivot then recurse on the items smaller still looking 
for item *i*.  On the other hand, if there are *k*¡*i* items smaller than the pivot the recurse on the items larger than the pivot with a new value of *i* = *i* - *k* - 1 (the *k* items and the pivot 
can be discarded).

MEDIAN OF MEDIANS - pseudocode

```java
public int ithItem(int i, int N, int[] data) {
  int answer, mom, j=0, k=0, l;
  int[] medians, smaller, larger;
  if (N < someSmallValueLikeFifty) {
    arrays.sort(data);
    answer = data[i];
  } // end if
  else {
    medians = new int[N/5];
    smaller = new int[something];
    larger = new int[something];

    for (l=0; l<N/5; l++) {
      medians[l] = median(data[5*l],data[5*l+1],...,data[5*l+4]);
    } // end for

    mom = ithItem(N/10,N/5,medians);

    for (l=0; l<N; l++) {
      if (data[l] <= mom)
        smaller[j++] = data[l];
      else
        larger[k++] = data[l];
    } // end for

    if (i<j)
      ans = ithItem(i, j, smaller);
    else
      ans = ithItem(i-j, k, larger);

  } // end else

  return ans;
} // end ithItem
```

**Examples**: If input2.txt contained

```
3 7

1 4 5 7 9 11 13
```

then the output would be

```
Sort finds 7.

Quick finds 7.

MOM finds 7.
```

If input2.txt contained

```
6 10

4 19 10 12 8 60 1 23
```

then the output woud be:

```
Sort finds 8.

Quick finds 8.

MOM finds 8.
```

**Details**: The program must be written in Java. The program must compile with the command javac *.java and run  with  the  command  java  Project2. One  common  problem  students  have  with  this  is  placing  the  classes  into packages. Don’t do this (just use the default package).  Output should be 
sent to System.out. The program shouldbe reasonably commented, indented, structured. The program should be submitted by placing all files in a directory named 
after you, zipping this directory and submitting via canvas.
