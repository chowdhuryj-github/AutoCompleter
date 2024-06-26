
# The AutoCompleter Lab Collection
Welcome to the AutoCompleter Lab Collection! This repository is a data structures project that I've worked on during my freshman year at the Milwaukee School of Engineering!

## Lab 8 | A Focus On Unit Testing
Created an AutoCompleter Interface that had the following methods implemented below. Worked on creating an Unordered List class which implemented the AutoCompleter Interface. Moved on to creating JUnit tests for all the methods in the AutoCompleter interface!

* ```boolean add(String word)``` — returns true if word is added to the object (a word should not be added if it is already in the auto completer). If word is null or an empty string, an IllegalArgumentException is thrown.
* ```int size()``` — returns the number of items in the auto completer.
* ```boolean exactMatch(String target)``` — returns true if target is found in the auto completer. If target is null or an empty string, the method returns false.
* ```String[] allMatches(String prefix)``` — returns an array of all the strings in the object that begin with the prefix. If prefix is an empty string, an array of all the strings in the auto completer are returned. If prefix is null, an empty array is returned.
* ```String getBackingClass()``` — returns a String indicating the fully qualified name of the data structure used to store the words for the AutoCompleter. E.g., "java.util.ArrayList".
* ```static String format(long nanoseconds)``` — returns a human-friendly string representing the number of nanoseconds. 

## Lab 11 | Building AutoCompleter Application
Created a JavaFX Application where users are able to type words into a search box. A list of matches is then shown after a letter is typed. Below the search bar, the time needed to find an exact match and all matched with the same letters typed in would be displayed. \n

### Ordered List & Binary Search Tree Implementation
Implemented the AutoCompleter interface using an ordered list and a binary search tree. For the ordered list, a ```Collections.binarySearch()``` call was utilised. 

## Lab 13 | Trie Data Structure
Implemented a Trie data structure and implemented the AutoCompleter interface using a Trie. Used a ```ListMap<K, V>``` class that implements the ```Map<K, V>``` interface. 

## Lab 14 | Hash Table Data Structure
Created a Hash Table to implement the AutoCompleter interface. Performed time complexity analysis and benchmarking on all data structure implementations. Created a summary document that gives a conclusion on how methods of different data structures work. Feel free to check the PDF in the repository!
