package e1;

import java.util.Random;

public class StringUtilities {
    private static final Random get_random=new Random(); //create a function that returns true/false randomly
    public static boolean getRandom(){
        return get_random.nextBoolean();
    }
    /**
     * Checks if a given string is a valid c of two others . That is , it contains
     * all characters of the other two and order of all characters in the individual
     * strings is preserved .
     *
     * @param a First String to be mixed
     * @param b Second String to be mixed
     * @param c Mix of the two other Strings
     * @return true if the c is valid , false otherwise
     */
    public static boolean isValidMix(String a, String b, String c) {
        int parse_c=0;
        int parse_a=0;
        int parse_b=0;
        if((a.length()+b.length())!=c.length())//check if c has the same number of characters with the sum of a+b
            return false;
        while (parse_c != c.length())//iterate thorough the characters of c
        {
            if (parse_a<a.length()&&a.charAt(parse_a) == c.charAt(parse_c))//check if the character of c is in a
                parse_a++;//iterate through a if the character from c is found in a
            else if (parse_b<b.length()&&b.charAt(parse_b) == c.charAt(parse_c))//check if the character of c is in b
                parse_b++;//iterate through b if the character from b is found
            else
                return false;// return false if the character from c is neither in a neither in b
            //move the iteration through c
            parse_c++;
        }
        return true;
    }

    /**
     * Generates a random valid mix for two Strings
     *
     * @param a First String to be mixed
     * @param b Second String to be mixed
     * @return A String that is a random valid mix of the other two .
     */
    public static String generateRandomValidMix(String a, String b) {
        StringBuilder sb_c=new StringBuilder();// here the merged string will be build
        int parse_c=0;
        int parse_a=0;
        int parse_b=0;
        while(parse_c!=(a.length()+b.length()))//parse string c each position at a time
        {
            if(getRandom())// get random true/false
            {
                if(parse_a<a.length())//if true and there are characters left in string a
                    sb_c.append(a.charAt(parse_a++));//put the character from a in the new string and iterate through a
                else {
                    if(parse_b<b.length())//if there are no characters left in a, check if there are left in b
                        sb_c.append(b.charAt(parse_b++));//if there are characters in b, put the character from b in c and iterate through c
                }
            }
            else{ // same as above but if the random is false, change the b for a
                if(parse_b<b.length())
                    sb_c.append(b.charAt(parse_b++));
                else {
                    if(parse_a<a.length())
                        sb_c.append(a.charAt(parse_a++));
                }
            }
            parse_c++;//iterate through c after each inserted character
        }
        return sb_c.toString();
    }
    public static void main(String []args){

        String A = "BA";
        String B = "W";
        String C = "ByeWorld";
        if (isValidMix(A, B, C))
            System.out.printf("%s is merged of %s and %s", C, A, B);
        else
            System.out.printf("%s is not interleaved of %s and %s", C, A, B);
        System.out.println(A.length());
        String d = generateRandomValidMix(A,B);
        System.out.println(d);
        if (isValidMix(A, B, d))
            System.out.printf("%s is merged of %s and %s", d, A, B);
        else
            System.out.printf("%s is not interleaved of %s and %s", d, A, B);
    }
}
