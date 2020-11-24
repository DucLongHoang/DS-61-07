package e2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.util.Arrays.copyOf;

public class Matrix implements Iterable<Integer> {
    // attributes of the class
    private int rowIndex, columnIndex;
    private int[][] matrix;
    private boolean rowColumn;

    // constructor 1
    public Matrix(int rows, int columns){
        this.matrix = new int[rows][columns];
        this.rowColumn = true;
    }
    // constructor 2
    public Matrix(int[][] matrix) {
        if(!isValidMatrix(matrix)) {
            throw new IllegalArgumentException("Not a valid matrix.");
        }
        this.matrix = matrix;
        this.rowColumn = true;
    }

    // checks if 2D array is non-ragged
    private boolean isValidMatrix(int[][] matrix){
        for(int[] a: matrix){
            if (a.length != matrix[0].length) {
                return false; }
        }
        return true;
    }
    // checks if Cell exists
    private boolean isValidCell(int row, int column){
        if(row < 0 || column < 0) { return false; }
        return row < this.getRows() && column < this.getColumns();
    }

    // returns the number of columns of the matrix
    public int getColumns() {
        return this.matrix[0].length;
    }
    // returns number of rows of the matrix
    public int getRows() {
        return this.matrix.length;
    }
    // returns the matrix as a 2D array
    public int[][] getMatrix() {
        return this.matrix;
    }

    // returns value of a matrix cell at row x column
    public int getCellValue(int row, int column){
        return this.matrix[row][column];
    }
    // sets value of a matrix cell at row x column
    public void setCellValue(int row, int column, int value){
        if(!isValidCell(row, column)) {
            throw new IndexOutOfBoundsException("Cell does not exist.");
        }
        this.matrix[row][column] = value;
    }
    public void setRowsFirst(boolean rowFirst){
        this.rowColumn = rowFirst;
    }

    // returns a copy of row n
    public int[] getArrayFromRow(int n){
        if(n < 0 || n >= this.getRows()) {
            throw new IndexOutOfBoundsException("Row does not exist.");
        }
        return copyOf(matrix[n], this.getColumns());
    }
    // returns a copy of column n
    public int[] getArrayFromCol(int n){
        if(n < 0 || n >= this.getColumns()){
            throw new IndexOutOfBoundsException("Column does not exist.");
        }
        int[] array = new int[this.getRows()];
        for(int i = 0; i < array.length; i++){
            array[i] = this.matrix[i][n];
        }
        return array;
    }

    /**
     * Return the matrix in a String version
     * @return Matrix in a String form
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();         // to build the String
        for(int i = 0; i < this.getRows(); i++){
            sb.append("[");
            for(int j = 0; j < this.getColumns(); j++){
                sb.append(this.matrix[i][j]).append(", ");
            }
            sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);
            sb.append("]\n");
        }
        return sb.toString();
    }

    // returns iterator to iterate through matrix
    // iterator can be rows-first or columns-first
    @Override
    public Iterator<Integer> iterator() {
        this.rowIndex = 0;  this.columnIndex = 0;
        if(rowColumn) { return this.rowColumnIterator(); }
        else return this.columnRowIterator();
    }
    // returns a row-column iterator
    public Iterator<Integer> rowColumnIterator(){
        return new rowColumnIterator();
    }
    // returns a column-row iterator
    public Iterator<Integer> columnRowIterator(){
        return new columnRowIterator();
    }


    // inner class for a rows-first iterator
    private class rowColumnIterator implements Iterator<Integer>{
        @Override
        public boolean hasNext() {
            if(rowIndex >= getRows()) { return false; }
            return (columnIndex < getColumns()) ||
                    (rowIndex < getRows() - 1);
        }

        @Override
        public Integer next() {
            if(!this.hasNext()){
                throw new NoSuchElementException("There is nothing more.");
            }
            if(columnIndex >= getColumns()){
                rowIndex++; columnIndex = 0;
            }
            return matrix[rowIndex][columnIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Method 'remove()' not implemented yet...");
        }
    }
    // inner class for a columns-first iterator
    private class columnRowIterator implements Iterator<Integer>{
        @Override
        public boolean hasNext() {
            if(columnIndex >= getColumns()) { return false; }
            return (rowIndex < getRows()) ||
                    (columnIndex < getColumns() - 1);
        }

        @Override
        public Integer next() {
            if(!this.hasNext()){
                throw new NoSuchElementException("There is nothing more.");
            }
            if(rowIndex >= getRows()){
                columnIndex++; rowIndex = 0;
            }
            return matrix[rowIndex++][columnIndex];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Method 'remove()' not implemented yet...");
        }
    }

}
