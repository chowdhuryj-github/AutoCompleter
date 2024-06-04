
# The AutoCompleter Lab Collection

## Lab 8 | A Focus On Unit Testing
Created an AutoCompleter Interface that had the following methods implemented:
* ```boolean add(String word)``` — returns true if word is added to the object (a word should not be added if it is already in the auto completer). If word is null or an empty string, an IllegalArgumentException is thrown.
* ```int size()``` — returns the number of items in the auto completer.
* ```boolean exactMatch(String target)``` — returns true if target is found in the auto completer. If target is null or an empty string, the method returns false.
* ```String[] allMatches(String prefix)``` — returns an array of all the strings in the object that begin with the prefix. If prefix is an empty string, an array of all the strings in the auto completer are returned. If prefix is null, an empty array is returned.
* ```String getBackingClass()``` — returns a String indicating the fully qualified name of the data structure used to store the words for the AutoCompleter. E.g., "java.util.ArrayList".
* ```static String format(long nanoseconds)``` — returns a human-friendly string representing the number of nanoseconds. 
Worked on creating an Unordered List class which implemented the AutoCopleter Interface. Moved on to creating JUnit tests for all the methods in the AutoCompleter interface.



