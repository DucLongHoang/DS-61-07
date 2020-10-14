package e1;

import java.util.Random;

/**
 * Something...something...shared, does it work in Linux as well? Who knows...
 */
public class StringUtilities {

    /**
     * Returns a random number from a given interval. Bound numbers are INCLUDED!!!
     * @param lowerBound is the start of the interval
     * @param upperBound is the end of the interval
     * @return a number that is in the interval <lowerBound; upperBound>
     */
    public static int getRandomFromRange(int lowerBound, int upperBound){
        Random r = new Random();
        return r.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

    /**
     * Determines if array is sorted in ascending order or not
     * @param array is the array to check
     * @return true if array is in ascending order, else return false
     */
    public static boolean isAscending(int[] array){
        for(int i = 1; i < array.length; i++){
            if(array[i] < array[i - 1]){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a new array whose indexes are filled with the positions
     * of letters in String s appearing in String c.
     *
     * e.g. String s = sandi, String c = island -> returns an array [1][3][4][5][0]
     *
     * @param s is the string for which we want to make an array
     * @param c is the string-mix that contains letters of string s
     * @return an array with filled with positions of letter in String s
     */
    public static int[] getArrayWithPositions(String s, String c){
        int[] array = new int[s.length()];

        for(int i = 0; i < array.length; i++) {
            char letter = s.charAt(i);
            for(int j = 0; j < c.length(); j++){
                if(letter == c.charAt(j)){
                    array[i] = j;
                    break;
                }
            }
        }
        return array;
    }

    /**
     * Checks if a given string is a valid c of two others. That is, it contains
     * all characters of the other two and order of all characters in the individual
     * strings is preserved.
     * @param a First String to be mixed
     * @param b Second String to be mixed
     * @param c Mix of the two other Strings
     * @return true if the c is valid, false otherwise
     */
    public static boolean isValidMix (String a, String b, String c) {
        // if the length of String c is different than the sum of a + b -> return false
        if((a + b).length() != c.length())  return false;

        int[] stringA = getArrayWithPositions(a, c);
        int[] stringB = getArrayWithPositions(b, c);

        return isAscending(stringA) && isAscending(stringB);
    }

    /**
     * Generates a random valid mix for two Strings
     * @param a First String to be mixed
     * @param b Second String to be mixed
     * @return A String that is a random valid mix of the other two.
     */
    public static String generateRandomValidMix (String a, String b) {
        // length of new String
        int stringLength = (a + b).length();

        // array for creating new word
        char[] array = new char[stringLength];
        StringBuilder result = new StringBuilder(stringLength);

        Random r = new Random();
        // index of the first letter to add into array
        int index = r.nextInt(stringLength + 1 - a.length());

        // adding letters of the first String into the array
        for(int i = 0; i < a.length(); i++){
            array[index] = a.charAt(i);
            index = getRandomFromRange(index + 1, stringLength + 1 + i - a.length());
        }

        // adding letters of the second String in the remaining empty positions
        int indexB = 0;
        for(int i = 0; i < stringLength; i++){
            if(array[i] == '\u0000'){
                array[i] = b.charAt(indexB++);
            }
        }

        // creating the new String through StringBuilder
        for(int i = 0; i < stringLength; i++){
            result.append(array[i]);
        }

        return result.toString();
    }

    public static void main(String[] args){
        System.out.println(generateRandomValidMix("Bye" , "World"));
        System.out.println("This is a valid mix: " + isValidMix("Black", "Tiger", "TiBlgaerck"));

    }

}
