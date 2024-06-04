/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package chowdhuryj.model;

import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * class for HashTable
 */
public class HashTable implements AutoCompleter {


    private final HashSet<String> items;

    /**
     * constructor 1 for HashTable
     */
    public HashTable() {
        this.items = new HashSet<>();
    }

    /**
     * constructor 2 for HashTable
     * @param items items
     */
    public HashTable(List<String> items) {
        this.items = new HashSet<>(items);

    }


    /**
     * Returns true if word is added to the object
     * (a word shouldn't be added if it's already in the autocompleter)
     * If word is null or an empty string, an IllegalArgumentException is thrown
     *
     * @param word String to add to the object
     * @return true if the word was added
     * @throws IllegalArgumentException if word is null or an empty String
     */
    @Override
    public boolean add(String word) {

        // if the word is null or an empty string, throw an IllegalArgumentException
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word is null or an Empty String");
        }

        // for loop through the items array to see if the same word was added or not
        if(items.contains(word)) {
            return false;
        }

        items.add(word);

        // if the word isn't null or an empty string, add the word to the items list
        return true;


    }

    /**
     * returns true if the target is found in the autocomplete
     * if target is null, or an empty string, the method returns false
     *
     * @param target the string to be found
     * @return boolean value
     */
    @Override
    public boolean exactMatch(String target) {
        if (target == null || target.isEmpty()) {
            return false;
        } else {
            return items.contains(target);
        }
    }

    /**
     * returns the numbers of items in the AutoCompleter
     *
     * @return size of the array
     */
    @Override
    public int size() {
        return items.size();
    }

    /**
     * returns a string indicating the fully qualified name of the
     * data structure used to store the words for autocompleter
     *
     * @return string
     */
    @Override
    public String getBackingClass() {
        return items.getClass().getName();
    }

    /**
     * returns an array of all the strings in the object that begin with the prefix
     * if prefix is an empty string, an array of all the strings in the autocomplete is returned
     * if prefix is null, an empty array is returned
     *
     * @param prefix prefix
     * @return string array
     */
    @Override
    public String[] allMatches(String prefix) {

        int retCount = 0;
        Iterator<String> iterator = items.iterator();

        // actions to take when the prefix is empty or null
        if (prefix == null) {
            String[] nullArray = new String[0];
            return nullArray;
        } else if (prefix.isEmpty()) {
            String[] emptyPrefix = new String[items.size()];
            for (int i = 0; i < items.size(); i++) {
                emptyPrefix[i] = iterator.next();
            }
            return emptyPrefix;
        }

        iterator = items.iterator();

        // finding number of all strings that begin with prefix
        for(int i = 0; i < items.size(); i++) {
            if (iterator.next().startsWith(prefix)) {
                retCount++;
            }
        }

        iterator = items.iterator();

        List<String> actualList = new ArrayList<String>();
        while (iterator.hasNext()) {
            actualList.add(iterator.next());
        }

        String[] matches = new String[retCount];


        // adding all matching prefixes to an array
        int index = 0;
        for(int i = 0; i < items.size(); i++) {
            if (actualList.get(i).startsWith(prefix)) {
                matches[index++] = actualList.get(i);
            }
        }

        return matches;
    }
}
