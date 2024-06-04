/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package chowdhuryj.model;

import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * class for BinarySearchTree
 */
public class BinarySearchTree implements AutoCompleter {

    private final TreeSet<String> items;

    /**
     * constructor for Unordered List
     * @param expected the list string being passed in
     */
    public BinarySearchTree(List<String> expected) {
        Set<String> unique = new HashSet<>(expected);
        expected.clear();
        expected.addAll(unique);
        items = new TreeSet<>(expected);
    }

    /**
     * Returns true if word is added to the object
     * (a word shouldn't be added if it's already in the auto-completer)
     * If word is null or an empty string, an IllegalArgumentException is thrown
     * @param word String to add to the object
     * @return true if the word was added
     * @throws IllegalArgumentException if word is null or an empty String
     */
    @Override
    public boolean add(String word) {

        // checks to see if word is null or an empty string
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word is null or an Empty String");
        }

        if(items.contains(word)) {
            return false;
        }

        // else just add the word to the items array
        items.add(word);
        return true;

    }


    /**
     * returns true if the target is found in the autocomplete
     * if target is null, or an empty string, the method returns false
     * @param target the string to be found
     * @return boolean value
     */
    @Override
    public boolean exactMatch(String target) {

        if(target == null || target.isEmpty()) {
            return false;
        } else {
            return items.contains(target);
        }

    }

    /**
     * returns the numbers of items in the AutoCompleter
     * @return size of the array
     */
    @Override
    public int size() {

        // finding the size of the tree
        return items.size();
    }

    /**
     * returns a string indicating the fully qualified name of the data
     * structure used to store the words for auto-completer
     * @return string
     */
    @Override
    public String getBackingClass() {
        // returns name of the class of the tree
        return items.getClass().getName();
    }

    /**
     * returns an array of all the strings in the object that begin with the prefix
     * if prefix is an empty string, an array of all the strings in the autocomplete is returned
     * if prefix is null, an empty array is returned
     * @param prefix prefix
     * @return string array
     */
    @Override
    public String[] allMatches(String prefix) {
        if(prefix == null) {

            String[] nullArray = new String[0];
            return nullArray;

        } else if(prefix.isEmpty()) {

            Iterator<String> retIterator = items.iterator();
            List<String> tempArray = new ArrayList<>();

            while(retIterator.hasNext()) {
                tempArray.add(retIterator.next());
            }

            return tempArray.toArray(new String[0]);

        }

        Iterator<String> newIterator = items.tailSet(prefix).iterator();
        List<String> newArrayList = new ArrayList<>();

        while(newIterator.hasNext()) {
            String newString = newIterator.next();
            if(newString.startsWith(prefix)) {
                newArrayList.add(newString);
            }
        }

        return newArrayList.toArray(new String[0]);

    }
}




