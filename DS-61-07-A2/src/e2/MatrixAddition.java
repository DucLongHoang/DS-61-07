package e2;

import java.util.Iterator;

public class MatrixAddition {

    /**
     * Returns a new Matrix that is the sum of 2 matrices
     * @param m1 first Matrix
     * @param m2 second Matrix
     * @return returns a new Matrix
     * @throws ArithmeticException if matrices have a different size
     */
    public static Matrix addition(Matrix m1, Matrix m2) throws ArithmeticException{
        if(m1.getColumns() != m2.getColumns() ||
                m1.getRows() != m2.getRows()){
            throw new ArithmeticException("Matrices have different dimensions.");
        }
        Matrix result = new Matrix(m1.getRows(), m1.getColumns());
        Iterator<Integer> it1 = m1.iterator();
        Iterator<Integer> it2 = m2.iterator();

        for(int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++)
                result.setCellValue(i, j, it1.next() + it2.next());
        }
        return result;
    }

    public static void main(String[] args){
        int[][] array1 = {{6,5,6},
                         {4,9,8}};
        Matrix m1 = new Matrix(array1);
        m1.setCellValue(1,0,10);
        System.out.println(m1.toString());

        int[][] array2 = {{4,5,3},
                         {2,7,0}};
        Matrix m2 = new Matrix(array2);
        System.out.println(m2.toString());

        Matrix result = addition(m1,m2);        // testing addition
        System.out.println(result.toString());

        Iterator<Integer> it = result.iterator();       // test rows-first iterator
        while(it.hasNext()){ System.out.print(it.next() + " "); }
        System.out.println();

        result.setRowsFirst(false);
        it = result.iterator();         // test columns-first iterator
        while(it.hasNext()) { System.out.print(it.next() + " "); }
        System.out.println("\n");


        int[] rowCopy = result.getArrayFromRow(1);
        int[] columnCopy = result.getArrayFromCol(1);

        // test row copy
        for(int i: rowCopy){ System.out.print(i + " "); }
        System.out.println();

        // test column copy
        for(int i: columnCopy){ System.out.print(i + " "); }
        System.out.println();

        it.remove();    // test unimplemented method

    }
}
