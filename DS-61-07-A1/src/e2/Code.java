package e2;

public class Code {

    /**
     * Determines whether a keypad is valid. To do this, it must be a rectangle and
     * the numbers must follow the alphanumeric sequence indicated. If the array
     * (or any of its sub-arrays) is null it will be returned false.
     * @param keypad The keypad to be analyzed.
     * @return true if it is valid, false otherwise.
     */
    public static boolean isKeypadValid (char[][] keypad) {
        return true;
    }

    /**
     * Checks if an array of movements is valid when obtaining a key on a keypad.
     * An array of movements is valid if it is formed by Strings that only have the
     * four capital letters U, D, L and R. If the array of Strings or any of the
     * Strings is null it will be returned false.
     * @param movements - Array of Strings representing movements.
     * @return true if it is valid, false otherwise.
     */
    public static boolean areMovementsValid(String [] movements) {
        return false;
    }

    /**
     * Given a keypad and an array of movements, it returns a String with the code
     * resulting from applying the movements on the keypad.
     * @param keypad alphanumeric keypad.
     * @param movements Array with the different movements
     *        to be made for each number of the key.
     * @return Resulting code.
     * @throws IllegalArgumentException if the keypad of the movements are invalid .
     */
    public static String obtainCode (char[][] keypad, String[] movements) {
        return "Hi";
    }




    public static void main(String[] args){

    }

}