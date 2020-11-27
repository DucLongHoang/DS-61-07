package e2;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    public static int[][] arr1 = {{1}};
    public static int[][] arr2 = 
            {{1, 2}, 
            {3, 4}};

    public static int[][] arr3 = 
            {{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}};

    public static int[][] arr4 = 
            {{1, 2, 3, 4, 5}};

    public static int[][] arr5 = 
            {{1},
            {2},
            {3}};

    public static Matrix m1 = new Matrix(arr1);
    public static Matrix m2 = new Matrix(arr2);
    public static Matrix m3 = new Matrix(arr3);
    public static Matrix m4 = new Matrix(arr4);
    public static Matrix m5 = new Matrix(arr5);

    public static Matrix e1 = new Matrix(4, 1);
    public static Matrix e2 = new Matrix(2, 3);
    public static Matrix e3 = new Matrix(1, 1);
    public static Matrix e4 = new Matrix(5, 4);

    @Test
    void invalidConstructors(){
        int[][] isNull = null;
        int[][] arrIsNull = {{1, 2}, null, {4, 5}};
        int[][] ragged1 =
                {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11}};

        int[][] ragged2 =
                {{1, 2, 3, 4},
                {},
                {9, 10, 11, 12}};
        
        assertThrows(IllegalArgumentException.class, () -> new Matrix(isNull));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(arrIsNull));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(ragged1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(ragged2));

        assertThrows(IllegalArgumentException.class, () -> new Matrix(10, -3));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(0, 6));
    }
    
    @Test
    void getColumns() {
        assertEquals(1, m1.getColumns());
        assertEquals(2, m2.getColumns());
        assertEquals(3, m3.getColumns());
        assertEquals(5, m4.getColumns());
        assertEquals(1, m5.getColumns());
        assertEquals(1, e1.getColumns());
        assertEquals(3, e2.getColumns());
        assertEquals(1, e3.getColumns());
        assertEquals(4, e4.getColumns());
    }

    @Test
    void getRows() {
        assertEquals(1, m1.getRows());
        assertEquals(2, m2.getRows());
        assertEquals(3, m3.getRows());
        assertEquals(1, m4.getRows());
        assertEquals(3, m5.getRows());
        assertEquals(4, e1.getRows());
        assertEquals(2, e2.getRows());
        assertEquals(1, e3.getRows());
        assertEquals(5, e4.getRows());
    }

    @Test
    void getMatrix() {
        int[][] get1 = {{0}, {0}, {0}, {0}};
        int[][] get2 =
                {{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};

        assertArrayEquals(arr2, m2.getMatrix());
        assertArrayEquals(arr4, m4.getMatrix());
        assertArrayEquals(get1, e1.getMatrix());
        assertArrayEquals(get2, e4.getMatrix());

    }

    @Test
    void getCellValue() {
        assertThrows(IndexOutOfBoundsException.class, () -> m5.getCellValue(-6, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> e3.getCellValue(1, 0));

        assertEquals(2, m5.getCellValue(1, 0));
        assertEquals(0, e4.getCellValue(4, 3));
    }

    @Test
    void setCellValue() {
        m3.setCellValue(1, 2, 681);
        m4.setCellValue(0, 3, 7894);
        e2.setCellValue(0, 2, 530);
        e3.setCellValue(0, 0, 1);

        assertThrows(IndexOutOfBoundsException.class, () -> m2.setCellValue(6, 7, 82));
        assertThrows(IndexOutOfBoundsException.class, () -> e1.setCellValue(4, 1, 28));
        assertEquals(681, m3.getCellValue(1, 2));
        assertEquals(7894, m4.getCellValue(0, 3));
        assertEquals(530, e2.getCellValue(0, 2));
        assertEquals(1, e3.getCellValue(0, 0));

        m3.setCellValue(1, 2, 6);
        m4.setCellValue(0, 3, 4);
        e2.setCellValue(0, 2, 0);
        e3.setCellValue(0, 0, 0);
    }

    @Test
    void setRowsFirst() {
        Iterator<Integer> it = m3.iterator();
        assertFalse(it instanceof Matrix.columnRowIterator);
        assertTrue(it instanceof Matrix.rowColumnIterator);

        m3.setRowsFirst(false);
        it = m3.iterator();
        assertTrue(it instanceof Matrix.columnRowIterator);

        m3.setRowsFirst(true);
    }

    @Test
    void getArrayFromRow() {
        assertThrows(IndexOutOfBoundsException.class, () -> m5.getArrayFromRow(3));
        assertThrows(IndexOutOfBoundsException.class, () -> e3.getArrayFromRow(-2));

        int[] row1 = {1, 2, 3, 4, 5};
        int[] row2 = {0};
        int[] row3 = {-59, 0, 318, 0};

        assertArrayEquals(row1, m4.getArrayFromRow(0));
        assertArrayEquals(row2, e3.getArrayFromRow(0));

        e4.setCellValue(3, 0, -59);
        e4.setCellValue(3, 2, 318);

        assertArrayEquals(row3, e4.getArrayFromRow(3));

        e4.setCellValue(3, 0, 0);
        e4.setCellValue(3, 2, 0);
    }

    @Test
    void getArrayFromCol() {
        assertThrows(IndexOutOfBoundsException.class, () -> m4.getArrayFromCol(5));
        assertThrows(IndexOutOfBoundsException.class, () -> e4.getArrayFromCol(-6));

        int[] row1 = {3, 6, 9};
        int[] row2 = {0};
        int[] row3 = {0, -55};

        assertArrayEquals(row1, m3.getArrayFromCol(2));
        assertArrayEquals(row2, e3.getArrayFromCol(0));

        e2.setCellValue(1, 2, -55);

        assertArrayEquals(row3, e2.getArrayFromCol(2));

        e2.setCellValue(1, 2, 0);

    }

    @Test
    void testToString() {
        String s1 = "[1, 2]\n[3, 4]";
        String s2 = "[1, 2, 3, 4, 5]";
        String s3 = "[0, 0, 0]\n[0, 0, 0]";
        String s4 = "[0, -888, 0]\n[3, 0, 67]";

        assertEquals(s1, m2.toString());
        assertEquals(s2, m4.toString());
        assertEquals(s3, e2.toString());

        e2.setCellValue(0, 1, -888);
        e2.setCellValue(1, 0, 3);
        e2.setCellValue(1, 2, 67);

        assertEquals(s4, e2.toString());

        e2.setCellValue(0, 1, 0);
        e2.setCellValue(1, 0, 0);
        e2.setCellValue(1, 2, 0);

    }

    @Test
    void rowColumnIterator() {
        Iterator<Integer> it = e2.rowColumnIterator();
        while(it.hasNext()){ assertEquals(0, it.next()); }

        assertThrows(NoSuchElementException.class, it::next);
        assertFalse(it.hasNext());
        assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test
    void columnRowIterator() {
        m4.setRowsFirst(false);
        Iterator<Integer> it = m4.iterator();
        assertTrue(it instanceof Matrix.columnRowIterator);

        int i = 1;
        while(it.hasNext()){ assertEquals(i++, it.next()); }

        assertThrows(NoSuchElementException.class, it::next);
        assertFalse(it.hasNext());
        assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test
    void addition(){
        assertThrows(ArithmeticException.class, () -> MatrixAddition.addition(m4, e4));

        int[][] r1 = {{2, 4}, {6, 8}};
        int[][] r2 = {{0}, {0}, {0}, {0}};
        Matrix a1 = MatrixAddition.addition(m2, m2);
        Matrix a2 = MatrixAddition.addition(e1, e1);

        assertArrayEquals(r1, a1.getMatrix());
        assertArrayEquals(r2, a2.getMatrix());
    }
}