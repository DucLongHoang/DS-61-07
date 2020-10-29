package e2;

public class Matrix {
    private int rows, columns;
    private int[][] matrix;

    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
    }

    public Matrix(int[][] array) {
        if(array == null) throw new IllegalArgumentException();
    }

    // returns the number of columns of the matrix
    public int getColumns() {
        return columns;
    }

    // returns number of rows of the matrix
    public int getRows() {
        return rows;
    }

    // getting value of matrix cell at row x column
    public int getCellValue(int row, int column){
        return matrix[row][column];
    }

    // setting value of matrix cell at row x column
    public void setCellValue(int row, int column, int value){
        matrix[row][column] = value;
    }

    // returns the matrix as a 2D array
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Return the matrix in a String version
     * @return Matrix in a String form
     */
    @Override
    public String toString() {
        return "Matrix{}";
    }
}
