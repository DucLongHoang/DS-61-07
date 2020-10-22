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
        // check if keypad even exists
        if(keypad == null) return false;

        // saving height and width of keypad
        int height = keypad.length;
        int width = keypad[0].length;

        // checking if upper-left corner == 1
        if(keypad[0][0] != '1') return false;

        // checking if == null
        for(int i = 0; i < keypad.length; i++){
            if(keypad[i] == null) return false;
        }

        // checking if any cell on keypad == default value (= '\u0000')
        // also, if it fails -> keypad is not rectangular
        try{
            for(int i = 0; i < height; i++)
                for(int j = 0; j < width; j++)
                    if(keypad[i][j] == '\u0000') return false;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Keypad is not rectangular: " + e.getMessage());
            return false;
        }

        // array with keypad values for easier manipulation in cycles
        char[] array = matrixToArray(keypad);

        // checking if numeral and/or alphabetical symbols are in order
        for(int i = 1; i < array.length; i++){
            if(array[i-1] > array[i]){
                if(i == 9 && array[9] == '0') continue;
                //System.out.println("Keypad is not in alphabetical order!");
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the keypad is rows-first or columns-first
     * @param keypad is keypad to check
     * @return true if keypad is rows-first, otherwise false
     */
    private static boolean rowsFirst(char[][] keypad){
        // saving dimension of keypad
        int height = keypad.length;
        int width = keypad[0].length;

        // if keypad has only one value
        if(height * width == 1) return true;    // btw doesn't matter if we return true or false...

        // keypad is just one row
        if(height < 2){ return false; }

        // keypad is just one column
        if(width < 2){ return true; }

        if(keypad[0][1] == '2') return false;       // keypad is columns-first
        else if(keypad[1][0] == '2') return true;   // keypad is rows-first

        return false;
    }

    /**
     * Returns a one-dimensional array with values of the two-dimensional matrix.
     * Automatically determines if it's rows-first or columns-first.
     * @param matrix that we transform into an array of the same values
     * @return an array that has the values of the matrix
     */
    private static char[] matrixToArray(char[][] matrix){
        // matrix dimensions
        int height = matrix.length;
        int width = matrix[0].length;

        // array that will have values of the matrix
        char[] array = new char[height * width];
        int index = 0;

        // check if matrix is rows-first or columns-first
        // and fill the array accordingly
        if(!rowsFirst(matrix)){
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    array[index] = matrix[i][j];
                    index++;
                }
            }
        } else{
            for(int i = 0; i < width; i++){
                for(int j = 0; j < height; j++){
                    array[index] = matrix[j][i];
                    index++;
                }
            }
        }
        return array;
    }

    /**
     * Checks if an array of movements is valid when obtaining a key on a keypad.
     * An array of movements is valid if it is formed by Strings that only have the
     * four capital letters U, D, L and R. If the array of Strings or any of the
     * Strings is null it will be returned false.
     * @param movements - Array of Strings representing movements.
     * @return true if it is valid, false otherwise.
     */
    public static boolean areMovementsValid(String[] movements) {
        // check if movements == null
        if (movements == null) return false;

        for(int i = 0; i < movements.length; i++){         // going through an array of String
            if(movements[i] == null) return false;        // if a movement sequence == null
            char[] array = movements[i].toCharArray();   // divide String into chars
            for(int j = 0; j < array.length; j++){
                char letter = array[j];                // saving one letter of array
                if("UDLR".indexOf(letter) == -1 ) return false;     // equals -1 if letter not found
            }
        }
        return true;
    }

    /**
     * Given a keypad and an array of movements, it returns a String with the code
     * resulting from applying the movements on the keypad.
     * @param keypad alphanumeric keypad.
     * @param movements Array with the different movements
     *        to be made for each number of the key.
     * @return Resulting code.
     * @throws IllegalArgumentException if the keypad or the movements are invalid .
     */
    public static String obtainCode (char[][] keypad, String[] movements) {
        // if movement or keypad is invalid -> throw exception
        if(!areMovementsValid(movements) || !isKeypadValid(keypad)) throw new IllegalArgumentException();

        // creating variables for work
        int height = keypad.length, width = keypad[0].length;
        int posX = 0, posY = 0, index = 0;

        // storing the code one by one
        char[] code = new char [movements.length];

        // not very elegant but works
        // the other option was to use a shit-ton of 'if's and 'else's
        // which is commented bellow the switch
        for(String movement: movements){
            char[] move = movement.toCharArray();
            for(char letter: move){
                // always check if movement gets us out of the keypad
                // if yes then undo the movement
                switch(letter){
                    case 'U': {
                        posY--;
                        if(posY < 0) posY++;
                        break;
                    }
                    case 'D': {
                        posY++;
                        if(posY > height - 1) posY--;
                        break;
                    }
                    case 'L': {
                        posX--;
                        if(posX < 0) posX++;
                        break;
                    }
                    case 'R': {
                        posX++;
                        if(posX > width - 1) posX--;
                        break;
                    }
                }

                /*
                if(letter == 'U') {
                    posY--;
                    if (posY < 0) { posY++; }
                } else if(letter == 'D'){
                    posY++;
                    if(posY > height-1){ posY--; }
                } else if(letter == 'L'){
                    posX--;
                    if(posX < 0){ posX++;}
                } else if(letter == 'R'){
                    posX++;
                    if(posX > width-1){ posX--; }
                }
                */
            }
            code[index++] = keypad[posY][posX];
        }

        // making a String out of the code through StringBuilder
        StringBuilder result = new StringBuilder(code.length);
        for(char letter: code) result.append(letter);

        return result.toString();
    }

    public static void main(String[] args){
        char[][] keypad = {
                {'1', '4', '7', '0', 'C'},
                {'2', '5', '8', 'A', 'D'},
                {'3', '6', '9', 'B', 'E'}
        };

        String[] movements = {"URU", "DRRU", "DDRR"};

        for(int i = 0; i < keypad.length; i++){
            for(int j = 0; j < keypad[0].length; j++){
                System.out.print(keypad[i][j]);
            }
            System.out.println("");
        }

        char[] array = matrixToArray(keypad);
        System.out.println("\nRows first: " + rowsFirst(keypad));
        for(char c: array){ System.out.print(c + " "); }

        String code = obtainCode(keypad, movements);
        System.out.println("\nCode: " + code);

    }

}