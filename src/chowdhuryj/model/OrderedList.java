/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package chowdhuryj.model;

import java.util.List;
import java.util.TreeSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;

/**
 * class for Ordered List
 */
public class OrderedList implements AutoCompleter {

    private final List<String> items;

    /**
     * constructor for Ordered List
     * @param expected the list string being passed in
     */
    public OrderedList(List<String> expected) {
        TreeSet<String> sortedUnique = new TreeSet<>(expected);
        expected.clear();
        expected.addAll(sortedUnique);
        Collections.sort(expected);
        items = new LinkedList<>(expected);
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

        ListIterator<String> listIterator = items.listIterator();

        // if the word is null or an empty string, throw an IllegalArgumentException
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word is null or an Empty String");
        }

        // for loop through the items array to see if the same word was added or not
        if(items.contains(word)) {
            return false;
        }

        while(listIterator.hasNext()) {
            String nextWord = listIterator.next();
            if(word.compareTo(nextWord) < 0) {
                listIterator.previous();
                listIterator.add(word);
                return true;
            }
        }

        // items.add(word);
        // sort it out everytime we add
        // Collections.sort(items);

        // if the word isn't null or an empty string, add the word to the items list
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

        // checks to see if the target is null or an empty string
        if(target == null || target.isEmpty()) {
            return false;
        } else {
            // checks the sign of the index, if negative, target isn't in the array
            int index = Collections.binarySearch(items, target);
            return index > -1;
        }

    }

    /**
     * returns a string indicating the fully qualified name of the data
     * structure used to store the words for autocompleter
     * @return string
     */
    @Override
    public int size() {
        // produces the size of the array
        return items.size();
    }

    @Override
    public String getBackingClass() {
        // produces the name of the class
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

        // the prefix is null or empty
        if(prefix == null) {

            String[] nullArray = new String[0];
            return nullArray;

        } else if(prefix.isEmpty()) {

            ListIterator<String> retIteratorOne = items.listIterator(0);
            List<String> fillArrayString = new ArrayList<>();

            while(retIteratorOne.hasNext()) {
                fillArrayString.add(retIteratorOne.next());
            }

            String[] ret = fillArrayString.toArray(new String[0]);
            return ret;
        }

        // finding the first instance of the prefix
        int retIndex = Collections.binarySearch(items, prefix);
        if(retIndex < 0) {
            retIndex = -1 * (retIndex + 1);
        }

        // convert array list into an iterator and start from retIndex
        ListIterator<String> retIterator = items.listIterator(retIndex);

        // setting up an array list
        List<String> arrayString = new ArrayList<>();

        boolean flag = true;
        // going through the array list of items
        while(retIterator.hasNext() && flag) {
            String currString = retIterator.next();
            if (currString.startsWith(prefix)) {
                arrayString.add(currString);
            } else {
                flag = false;
            }
        }

        // returning it in an array of strings
        return arrayString.toArray(new String[0]);
    }
}
