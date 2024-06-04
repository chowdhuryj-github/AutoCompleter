/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package chowdhuryj.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * class for Trie
 */
public class Trie implements AutoCompleter {

    private final ListMap<Character, Trie> entries;
    private boolean end;


    /**
     * constructor for Trie
     * @param items items
     */
    public Trie(List<String> items) {
        entries = new ListMap<>();
        for (String item : items) {
            this.add(item);
            end = false;
        }
    }

    @Override
    public boolean add(String word) {

        // checks for null word or empty word
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word is null or an Empty String");
        }

        Trie retCurr = this;
        boolean retAdded = false;

        // creating new Trie if the list is empty
        for (int i = 0; i < word.length(); i++) {
            if (!retCurr.entries.containsKey(word.charAt(i))) {

                // creating a new Trie because we have an original starting character
                retCurr.entries.put(word.charAt(i), new Trie(new ArrayList<>(0)));
                retCurr = retCurr.entries.get(word.charAt(i));
                retAdded = true;

            } else {

                // else we keep on adding to that same Trie
                retCurr = retCurr.entries.get(word.charAt(i));
                retAdded = false;
            }
        }

        retCurr.end = true;

        return retAdded;

    }

    @Override
    public boolean exactMatch(String target) {

        Trie walker;

        if(target == null || target.isEmpty()) {
            return false;
        } else {
            //Use a walker to walk to the next character in the trie for target
            walker = this;

            for(int i = 0; i < target.length() && walker != null; i++) {
                if(walker.entries.containsKey(target.charAt(i))) {
                    walker = walker.entries.get(target.charAt(i));
                } else {
                    walker = null;
                }
            }

        }

        return walker != null && walker.end;
    }


    /**
     * returns the numbers of items in the AutoCompleter
     * @return size of the array
     */
    @Override
    public int size() {
        return entries.size();
    }

    /**
     * returns a string indicating the fully qualified name of the
     * data structure used to store the words for autocompleter
     * @return string
     */
    @Override
    public String getBackingClass() {
        return entries.getClass().getName();
    }


    /**
     * wrapper method
     * @param prefix prefix
     * @return string array
     */
    @Override
    public String[] allMatches(String prefix) {

        Trie retCurr = this;
        boolean retStatus = true;

        if (prefix == null) {
            return new String[0];
        }

        for (int i = 0; i < prefix.length() && retStatus; i++) {
            if (retCurr.entries.containsKey(prefix.charAt(i))) {
                retCurr = retCurr.entries.get(prefix.charAt(i));
            } else {
                retStatus = false;
            }
        }

        return allMatchesRecursive(prefix, new ArrayList<>(), retCurr);
    }



    /**
     * returns an array of all the strings in the object that begin with the prefix
     * if prefix is an empty string, an array of all the strings in the autocomplete is returned
     * if prefix is null, an empty array is returned
     * @param prefix prefix
     * @param list list
     * @param trie trie
     * @return string array
     */
    public String[] allMatchesRecursive(String prefix, ArrayList<String> list, Trie trie) {

        Iterator<Map.Entry<Character, Trie>> entrySetIterator = trie.entries.entrySet().iterator();

        if(trie.end) {
            list.add(prefix);
        }

        while(entrySetIterator.hasNext()) {
            Map.Entry<Character, Trie> retEntry = entrySetIterator.next();

            Character retChar = retEntry.getKey();
            Trie retTrie = retEntry.getValue();

            allMatchesRecursive(prefix + retChar, list, retTrie);
        }

        //Turn list into an array and return
        String[] retArray = list.toArray(new String[0]);
        return retArray;


    }



}
