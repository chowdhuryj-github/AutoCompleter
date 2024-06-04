/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package chowdhuryj.gui;

import chowdhuryj.model.AutoCompleter;
import chowdhuryj.model.OrderedList;
import chowdhuryj.model.Trie;
import chowdhuryj.model.HashTable;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * class for Data Structures Benchmark
 */
public class DataStructuresBenchmark {

    private static final int STRING_LENGTH = 10;

    /**
     * method for runBenchmarks()
     * @param listType listType
     * @param operation operation
     * @param size size
     * @param multiplier multiplier
     * @param numberOfTests numberOfTests
     * @return long[] array
     */
    public static long[]runBenchmarks(String listType, String operation, int size, int multiplier,
                                      int numberOfTests) {

        long[]retLongArray = new long[numberOfTests];
        int currentSize = size;

        for(int i = 0; i < numberOfTests; i++) {

            currentSize = currentSize * multiplier;
            AutoCompleter newList = createList(listType, currentSize);
            long startTime;
            long endTime;
            long elapsedTime;

            switch(operation) {
                case "add":
                    startTime = System.nanoTime();
                    add(newList, AutoCompleter.getString(STRING_LENGTH));
                    endTime = System.nanoTime();
                    elapsedTime = endTime - startTime;
                    retLongArray[i] = elapsedTime;
                    break;
                case "exactMatch":
                    startTime = System.nanoTime();
                    exactMatch(newList, AutoCompleter.getString(STRING_LENGTH));
                    endTime = System.nanoTime();
                    elapsedTime = endTime - startTime;
                    retLongArray[i] = elapsedTime;
                    break;
                case "allMatches":
                    startTime = System.nanoTime();
                    allMatches(newList, AutoCompleter.getString(STRING_LENGTH));
                    endTime = System.nanoTime();
                    elapsedTime = endTime - startTime;
                    retLongArray[i] = elapsedTime;
                    break;
            }

        }

        return retLongArray;

    }

    /**
     * Method for creating a list using AutoCompleter
     * @param listType type of list
     * @param size the size of the array
     * @return array list
     * @throws IllegalArgumentException error
     */
    public static AutoCompleter createList(String listType, int size) {

        String[] stringArray = new String[size];
        for(int i = 0; i < size; i++) {
            String retString = AutoCompleter.getString(STRING_LENGTH);
            stringArray[i] = retString;
        }

        AutoCompleter list;

        switch(listType) {
            case "chowdhuryj.model.OrderedList":
                list = new OrderedList(new ArrayList<>(Arrays.asList(stringArray)));
                return list;
            case "chowdhuryj.model.Trie":
                list = new Trie(new ArrayList<>(Arrays.asList(stringArray)));
                return list;
            case "chowdhuryj.model.HashTable":
                list = new HashTable(new ArrayList<>(Arrays.asList(stringArray)));
                return list;
            default:
                throw new IllegalArgumentException("Wrong list type: " + listType);

        }
    }

    /**
     * method for adding a string
     * @param retList retList
     * @param string string
     */
    public static void add(AutoCompleter retList, String string) {
        retList.add(string);
    }

    /**
     * method for finding exact macthes
     * @param retList retList
     * @param target target
     * @return boolean
     */
    public static boolean exactMatch(AutoCompleter retList, String target) {
        return retList.exactMatch(target);
    }

    /**
     * method for finding all prefix matches
     * @param retList retList
     * @param prefix prefix
     * @return the string array
     */
    public static String[] allMatches(AutoCompleter retList, String prefix) {
        return retList.allMatches(prefix);
    }



}
