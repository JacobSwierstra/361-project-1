# Project 1: DFA

* Author: Alex Silva, Kincaid Schmitt
* Class: CS361 Section 001
* Semester: Spring 2022

## Overview

This application takes in the a specified format of an input file
and creates a DFA (Deterministic Finite Automata) from the input 
file. 

## Reflection

In the beginning of this project, it was nice and not very many hurdles. 
It made us think and called for some creative solutions or just looking
deeper into how like the HashMaps work and such. Working with though 
through the Driver classs is how we went about it, where when the class 
called a method that wasn't implemented we would then create it.

The real struggle began when we started the Swap() method. At first
my partner and I assumed it would not be the big of a deal, which the
swapping part wasn't, but it came when trying to create a deep copy 
of the DFA to manipulate instead of the original. ....

## Compiling and Using

To compile, execute the following command in the main project directory:
```
$ javac fa/dfa/DFADriver.java
```

Run the compiled class with the command:
```
$ java fa.dfa.DFADriver ./tests/p1tc1.txt
```

The program will then run the chosen test file and create the following
DFA. Then creates a copy of that DFA and swaps the transitions and then
it run a seires of inputs that will either fail or pass through both of
them. Final tells us if the swap DFA is correct.

## Sources used

https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
https://docs.oracle.com/javase/8/docs/api/java/util/Set.html

----------
