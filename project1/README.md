**COMP 482 Project 1**

**Idea**: Recall the Roommate problem.  

In the roommate problem there are *n* people and you want to pair up the individuals to create a matching so that you could place them into *n/2* rooms.
The difficuly is that each person ranks of all of the other people and your matching cannot contain an instability, a situation where there are pairs (*w, x*) and (*y, z*) and 

1) *w* prefers *y* to *x*. 
2) *y* prefers *w* to *z*. 

You will be writing a Java program which will receive an instance of the roommate problem and determine the number of stable matchings. 

**Input Format**: The input file will be called input1.txt and be in the same directory as the java and class files. The first line will be an even number *n* the number *k* of people.
The next *n* lines will be the preference lists of the *n* people represented by a whitespace seperated list from best to worst. Notice that the *i<sup>th</sup>* such line will be a permutation 
of { *1,2, . . . , n* } − { *i* }.

**Output**: Your program will output a greeting (to show the program is running) and either the word **NO** meaning there are no stable matchings or the word **YES** and a number *j* which is 
the number of stable matchings.

**Examples**: If input1.txt contains

4

2 3 4

1 3 4

4 1 2

3 1 2

then your program will output: 

`Yes 1`

because there is exactly 1 stable matching { (1,2), (3,4) }. 

If input1.txt contains

6

2 3 4 5 6

3 4 5 1 6

4 5 1 2 6

5 1 2 3 6

1 2 3 4 6

1 2 3 4 5

then your program will output: 

`No` 

because there are no stable matchings (Hint Why: In any matching someone is paired with 6. That person prefers anyone else 
and there is someone who has that person as their first choice.). 

If input1.txt contains

4

3 4 2

4 3 1

2 1 4

1 2 3

then your program will output: 

`Yes 2` 

because { (1,3), (2,4) } and { (1,4), (2,3) } are both stable matchings.

**Details**: The program must be written in Java. The program must compile with the command javac *.java andrun  with  the  command  java  Project1. 
One  common  problem  students  have  with  this  is  placing  the  classes  into packages.  Don’t do this (just use the default package).  
Output should be sent to System.out.  The program shouldbe reasonably commented, indented, structured.  The program should be submitted by 
placing all files in a directory named after you, zipping this directory and submitting via canvas.

**How**: This is for you to determine. Any valid method is acceptable. In class, we outlined an exponential time bruteforce method to generate all 
matchings and test all possible instabilities. This brute force technique is acceptable.
