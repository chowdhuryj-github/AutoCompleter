/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package test;

import chowdhuryj.model.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * class for AutoCompleterTest
 */
public class AutoCompleterTest {

    private final String completeType = "BinarySearchTree";

    private AutoCompleter generateCompleter(String type, List<String> values) {
        AutoCompleter list;
        switch(type) {
            case "Unordered":
                list = (AutoCompleter) new UnorderedList(values);
                break;
            case "Ordered":
                list = (AutoCompleter) new OrderedList(values);
                break;
            case "BinarySearchTree":
                list = new BinarySearchTree(values);
                break;
            case "Trie":
                list = new Trie(values);
                break;
            case "HashTable":
                list = new HashTable(values);
                break;
            default:
                throw new IllegalArgumentException("Invalid Type: " + type);
        }
        return list;
    }

    /**
     * this is a unit test for adding a word to list
     * checks to see if the same word has been added or not
     * checks to see if an IllegalArgumentException is thrown
     * checks to see if a null or empty string is added to list
     */
    @Test
    @DisplayName("Unit test: Add Method")
    public void testAddingWordToList() {

        // initializing an array and passing it via a constructor
        List<String> expected = new ArrayList<>();
        AutoCompleter list = generateCompleter(completeType, expected);

        // adding a few words
        list.add("foo");
        list.add("bar");
        list.add("taco");
        list.add("cat");
        System.out.println("Array Initialized!");
        System.out.println(expected);

        System.out.println();

        // testing to see if a word can be added
        boolean addCheck = list.add("hello");
        Assertions.assertTrue(addCheck);
        System.out.println("Word Added. Test passed successfully!");
        System.out.println(expected);

        System.out.println();

        // testing to see if no word is added that's already present
        boolean falseCheck = list.add("foo");
        Assertions.assertFalse(falseCheck);
        System.out.println("Same Word Not Added. Test passed successfully!");
        System.out.println(expected);

        System.out.println();

        // testing to see if null or an empty string is added to the list
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            list.add(null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            list.add("");
        });
        System.out.println("Null or Empty String Not Added. Test passed successfully!");
        System.out.println(expected);

    }


    /**
     * this is a unit test for checking the size of an array
     * checks to see if the correct number is returned when array is empty
     * checks to see if the correct number is returned when elements are added
     */
    @Test
    @DisplayName("Unit Test: Size Method")
    public void testSizeInitialize() {

        // initializing an array and passing it via a constructor
        List<String> expected = new ArrayList<>();
        AutoCompleter list = generateCompleter(completeType, expected);

        // checking the size of the array when empty
        Assertions.assertEquals(0, list.size());
        System.out.println("Array is Empty! Test passed successfully!");

        System.out.println();

        // adding elements to the array
        list.add("foo");
        list.add("bar");
        list.add("taco");
        list.add("cat");

        // checking new size of the array
        Assertions.assertEquals(4, list.size());
        System.out.println("Array has 4 elements! Test passes successfully!");
        System.out.println(expected);

    }


    /**
     * unit test for exactMatchCheck()
     */
    @Test
    @DisplayName("Unit Test: Exact Match")
    public void exactMatchCheck() {

        // initializing an array and passing it via a constructor
        List<String> expected = new ArrayList<>();
        AutoCompleter list = generateCompleter(completeType, expected);


        // adding elements to the array
        list.add("foo");
        list.add("bar");
        list.add("taco");
        list.add("cat");
        System.out.println("Array Initialized!");
        System.out.println(expected);

        System.out.println();

        // checking if "foo" is present in the array
        boolean presentCheck = list.exactMatch("foo");
        Assertions.assertTrue(presentCheck);
        System.out.println("foo is in the array. Test passed successfully!");

        // checking if "hello" is present in the array
        boolean notPresentCheck = list.exactMatch("hello");
        Assertions.assertFalse(notPresentCheck);
        System.out.println("hello is not in the array. Test passed successfully!");

    }

    /**
     * unit test for exactMatchCheckOne()
     */
    @Test
    @DisplayName("Unit Test: Exact Match One")
    public void exactMatchCheckOne() {

        // initializing an array and passing it via a constructor
        List<String> expected = new ArrayList<>();
        AutoCompleter list = generateCompleter(completeType, expected);

        // adding elements to the array
        list.add("food");
        list.add("foo");
        list.add("for");
        list.add("foods");
        list.add("foe");
        list.add("fort");
        list.add("fools");
        System.out.println("Array Initialized!");
        System.out.println(expected);

        System.out.println();

        // checking if "foo" is present in the array
        boolean presentCheck = list.exactMatch("foo");
        Assertions.assertTrue(presentCheck);
        System.out.println("foo is in the array. Test passed successfully!");

    }

    /**
     * unit test for matchingPrefixes
     */
    @Test
    @DisplayName("Unit Test: All Matches Method")
    public void matchingPrefixes() {

        // initializing an array and passing it via a constructor
        List<String> expected = new ArrayList<>();
        AutoCompleter list = generateCompleter(completeType, expected);

        // adding elements to the array
        list.add("foo");
        list.add("football");
        list.add("bar");
        list.add("barstool");
        list.add("taco");
        list.add("tacos");
        list.add("cat");
        list.add("cation");
        System.out.println("Array Initialized!");
        System.out.println(expected);

        System.out.println();

        // checking string of elements that match the prefix
        String[] matches = list.allMatches("foo");
        String[] expectedMatches = {"foo", "football"};
        Assertions.assertEquals(Arrays.toString(expectedMatches), Arrays.toString(matches));
        System.out.println("Correct string array returned for 'foo'! Test passed successfully!");
        System.out.println(Arrays.toString(matches));

        System.out.println();

        // checking string of elements with an empty prefix
        String[] emptyArray = list.allMatches("");
        String[] expectedEmptyArray = {"foo", "football", "bar",
                "barstool", "taco", "tacos", "cat", "cation"};
        Assertions.assertEquals(emptyArray.length, expectedEmptyArray.length);
        System.out.println("Correct string array returned for " +
                "empty prefix! Test passed successfully!");
        System.out.println(Arrays.toString(emptyArray));

        System.out.println();

        // checking string of elements with a null prefix
        String[] nullArray = list.allMatches(null);
        String[] expectedNullArray = {};
        Assertions.assertEquals(Arrays.toString(nullArray), Arrays.toString(expectedNullArray));
        System.out.println("Correct string array returned for " +
                "null prefix! Test passed successfully!");
        System.out.println(Arrays.toString(nullArray));

    }

    /**
     * unit test for matchingPrefixes
     */
    @Test
    @DisplayName("Unit Test: All Matches Method One")
    public void matchingPrefixesOne() {

        // initializing an array and passing it via a constructor
        List<String> expected = new ArrayList<>();
        AutoCompleter list = generateCompleter(completeType, expected);

        // adding elements to the array
        list.add("food");
        list.add("foo");
        list.add("for");
        list.add("foods");
        list.add("foe");
        list.add("fort");
        list.add("fools");
        System.out.println("Array Initialized!");
        System.out.println(expected);

        System.out.println();

        // checking string of elements that match the prefix
        String[] matches = list.allMatches("foo");
        String[] expectedMatches = {"foo", "food", "foods", "fools"};
        Assertions.assertEquals(Arrays.toString(expectedMatches), Arrays.toString(matches));
        System.out.println("Correct string array returned for 'foo'! Test passed successfully!");
        System.out.println(Arrays.toString(matches));

    }

    /**
     * unit test for getClassMethod
     */
    @Test
    @DisplayName("Unit Test: Get Class Method")
    public void testGetBackingClass() {

        // initializing an array and passing it via a constructor
        List<String> expected = new ArrayList<>();
        AutoCompleter list = generateCompleter(completeType, expected);

        // adding elements to the array
        list.add("foo");
        list.add("bar");
        list.add("taco");
        list.add("cat");
        System.out.println("Array Initialized!");
        System.out.println(expected);

        System.out.println();

        // checking if the correct array list type is returned
        String className = list.getBackingClass();
        String expectedClassNameOne = "java.util.ArrayList";
        String expectedClassNameTwo = "java.util.TreeSet";
        String expectedClassNameThree = "chowdhuryj.model.ListMap";
        String expectedClassNameFour = "java.util.HashSet";
        Assertions.assertTrue(Objects.equals(className, expectedClassNameOne)
                || Objects.equals(className, expectedClassNameTwo)
                || Objects.equals(className, expectedClassNameThree)
                || Objects.equals(className, expectedClassNameFour));
        System.out.println("Correct array list type returned! Test passed successfully!");
        System.out.println(className);

    }

    /**
     * unit test for testingFormat()
     */
    @Test
    @DisplayName("Unit Test: Format")
    public void testingFormat() {

        // checking if the formats match
        Assertions.assertEquals("7 nanosecond(s) ", AutoCompleter.format(7L));
        Assertions.assertEquals("318.8 microsecond(s) ", AutoCompleter.format(318_800L));
        Assertions.assertEquals("998.8 millisecond(s) ", AutoCompleter.format(998_800_000L));
        Assertions.assertEquals("18.8 second(s) ", AutoCompleter.format(18_800_000_000L));

        long minutes42 = 2_520_000_000_000L;
        long seconds55_3 = 55_300_000_000L;
        Assertions.assertEquals("42 minute(s) 55.3 second(s) ",
                AutoCompleter.format(minutes42 + seconds55_3));

        long hours14 = 50_400_000_000_000L;
        long minutes22 = 1_320_000_000_000L;
        long seconds8 = 8_000_000_000L;
        Assertions.assertEquals("14 hour(s) 22 minute(s) 8 second(s) ",
                AutoCompleter.format(hours14 + minutes22 + seconds8));

        long days2 = 172_800_000_000_000L;
        long hours5 = 18_000_000_000_000L;
        long minutes32 = 1_920_000_000_000L;
        Assertions.assertEquals("2 day(s) 5 hour(s) 32 minute(s) ",
                AutoCompleter.format(days2 + hours5 + minutes32));

    }




}
