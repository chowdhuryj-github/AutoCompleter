/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package chowdhuryj.model;

import java.util.Random;

/**
 * Interface for AutoCompleter
 */
public interface AutoCompleter {

    /**
     * ASCII value for a
     */
    int FIRSTASCII = 26;

    /**
     * constant for 60
     */
    int SIXTY = 60;

    /**
     * constant for 24
     */
    int TWENTYFOUR = 24;

    /**
     * constant for 1000
     */
    int ONE_THOUSAND = 1000;

    /**
     * Returns true if word is added to the object
     * (a word shouldn't be added if it's already in the autocompleter)
     * If word is null or an empty string, an IllegalArgumentException is thrown
     * @param word String to add to the object
     * @return true if the word was added
     * @throws IllegalArgumentException if word is null or an empty String
     */
    boolean add(String word);

    /**
     * returns true if the target is found in the autocomplete
     * if target is null, or an empty string, the method returns false
     * @param target the string to be found
     * @return boolean value
     */
    boolean exactMatch(String target);

    /**
     * returns the numbers of items in the AutoCompleter
     * @return size of the array
     */
    int size();

    /**
     * returns a string indicating the fully qualified name of the
     * data structure used to store the words for autocompleter
     * @return string
     */
    String getBackingClass();

    /**
     * returns an array of all the strings in the object that begin with the prefix
     * if prefix is an empty string, an array of all the strings in the autocomplete is returned
     * if prefix is null, an empty array is returned
     * @param prefix prefix
     * @return string array
     */
    String[] allMatches(String prefix);

    /**
     * returns a human-friendly string representing the number of nanoseconds
     * the format of the string must be consistent
     * @param nanoseconds nanoseconds
     * @return string
     */
    static String format(long nanoseconds) {

        long microSeconds = nanoseconds / 1_000;
        long miliSeconds = nanoseconds / 1_000_000;
        long seconds = nanoseconds / 1_000_000_000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if(days > 0) {
            String retDayHourMin = days + " day(s) " +
                    (hours % TWENTYFOUR) + " hour(s) " + (minutes % SIXTY) + " minute(s) ";
            return retDayHourMin;
        }

        if(hours > 0) {
            String retHourMin = hours + " hour(s) " +
                    (minutes % SIXTY) + " minute(s) " + (seconds % SIXTY) + " second(s) ";
            return retHourMin;
        }

        if(minutes > 0) {
            double leftoverSeconds = (seconds % SIXTY) + (miliSeconds % ONE_THOUSAND) / 1_000.0;
            String retMin = minutes + " minute(s) "
                    + String.format("%.1f", leftoverSeconds) + " second(s) ";
            return retMin;
        }

        if(seconds > 0) {
            double remainingMilliseconds = (miliSeconds % ONE_THOUSAND) / 1_000.0;
            double finalSeconds = seconds + remainingMilliseconds;
            String retSeconds = finalSeconds + " second(s) ";
            return retSeconds;
        }

        if(miliSeconds > 0) {
            double remainingMicroseconds = (microSeconds % ONE_THOUSAND) / 1_000.0;
            double finalMilliseconds = miliSeconds + remainingMicroseconds;
            String retMiliseconds = finalMilliseconds + " millisecond(s) ";
            return retMiliseconds;
        }

        if(microSeconds > 0) {
            double remainingNanoseconds = (nanoseconds % ONE_THOUSAND) / 1_000.0;
            double finalMicroseconds = microSeconds + remainingNanoseconds;
            String retMicroseconds = finalMicroseconds + " microsecond(s) ";
            return retMicroseconds;
        }

        if (nanoseconds > 0) {
            String retNanoseconds = nanoseconds + " nanosecond(s) ";
            return retNanoseconds;
        }

        return null;

    }

    /**
     * random string generator
     * @param length length
     * @return String
     */
    static String getString(int length) {
        Random random = new Random();
        StringBuilder retString = new StringBuilder();
        for(int i = 0; i < length; i++) {
            retString.append((char) (random.nextInt(FIRSTASCII) + 'a'));
        }
        return String.valueOf(retString);

    }

}
