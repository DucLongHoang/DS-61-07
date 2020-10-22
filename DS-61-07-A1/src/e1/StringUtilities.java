package e1;

import java.util.Random;

public class StringUtilities {

    /**
     * Returns a random number from a given interval. Bound numbers are INCLUDED!!!
     * @param lowerBound is the start of the interval
     * @param upperBound is the end of the interval
     * @return a number that is in the interval <lowerBound; upperBound>
     */
    private static int getRandomFromRange(int lowerBound, int upperBound){
        Random r = new Random();
        return r.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

    /**
     * Determines if array is sorted in ascending order or not
     * @param array is the array to check
     * @return true if array is in ascending order, else return false
     */
    private static boolean isAscending(int[] array){
        for(int i = 1; i < array.length; i++){
            if(array[i] < array[i - 1]){
                // return false if following element is smaller than the previous one
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
    private static int[] getArrayWithPositions(String s, String c){
        // array with size of letters of String s (in our case String a and String b)
        int[] array = new int[s.length()];

        // going through the letters of String s
        for(int i = 0; i < array.length; i++) {
            char letter = s.charAt(i);
            // checking the occurrence of said letter in the mixed String
            for(int j = 0; j < c.length(); j++){
                if(letter == c.charAt(j)){
                    // saving the position of found letter in array
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

        // getting arrays with positions of letters in String a/b found in String c
        int[] stringA = getArrayWithPositions(a, c);
        int[] stringB = getArrayWithPositions(b, c);

        // if positions are in ascending order -> order of letter is preserved
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

        // array and StringBuilder for creating the new String
        char[] array = new char[stringLength];
        StringBuilder result = new StringBuilder(stringLength);

        Random r = new Random();
        // index of the first letter to add into array
        int index = r.nextInt(stringLength + 1 - a.length());

        // adding letters of the first String into the array
        for(int i = 0; i < a.length(); i++){
            array[index] = a.charAt(i);
            // getting the lower and upperBound position logic took way too much time...
            index = getRandomFromRange(index + 1, stringLength + 1 + i - a.length());
        }

        // adding letters of the second String in the remaining empty positions
        int indexB = 0;
        for(int i = 0; i < stringLength; i++){
            // '\u0000' is the default value of a char array
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
