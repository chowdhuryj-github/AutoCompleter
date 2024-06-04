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
import java.util.Set;

/**
 * class for Unordered List
 */
public class UnorderedList implements AutoCompleter {

    private final List<String> items;


    /**
     * constructor for Unordered List
     * @param expected the list string being passed in
     */
    public UnorderedList(List<String> expected) {
        Set<String> unique = new HashSet<>(expected);
        expected.clear();
        expected.addAll(unique);
        items = expected;
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

        // for loop to check if word is already present
        for (String item : items) {
            if (item.equals(word)) {
                return false;
            }
        }

        // if word isn't null, an empty string or already in list
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
        if (target == null || target.isEmpty()) {
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
        return items.size();
    }

    /**
     * returns a string indicating the fully qualified name of the
     * data structure used to store the words for autocompleter
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
     * @param prefix prefix
     * @return string array
     */
    @Override
    public String[] allMatches(String prefix) {

        int retCount = 0;

        // actions to take when the prefix is empty or null
        if (prefix == null) {
            String[] nullArray = new String[0];
            return nullArray;
        } else if (prefix.isEmpty()) {
            String[] emptyPrefix = new String[items.size()];
            for (int i = 0; i < items.size(); i++) {
                emptyPrefix[i] = items.get(i);
            }
            return emptyPrefix;
        }

        // finding number of all strings that begin with prefix
        for(int i = 0; i < items.size(); i++) {
            if (items.get(i).startsWith(prefix)) {
                retCount++;
            }
        }

        String[] matches = new String[retCount];

        // adding all matching prefixes to an array
        int index = 0;
        for(int i = 0; i < items.size(); i++) {
            if (items.get(i).startsWith(prefix)) {
                matches[index++] = items.get(i);
            }
        }

        return matches;

    }

}
