package e1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ModesTest {
    @Test
    void Test_Off_Manual() {
        Mode mode = new Off();
        assertEquals(mode.getDescription(), " Mode Off - Heating off.");
        mode = new Manual();
        assertEquals(mode.getDescription(), " Mode Manual - Heating on.");
    }
    @Test
    void Test_Timer(){
        Mode mode = new Timer(15);
        assertEquals(((Timer)mode).getTime(),15);
        assertEquals(mode.getDescription()," Mode Timer (15 minutes remaining)");
        ((Timer)mode).setTime(10);
        assertEquals(((Timer)mode).getTime(),10);
        assertEquals(mode.getDescription()," Mode Timer (10 minutes remaining)");
    }
    @Test
    void Test_Program(){
        Mode mode = new Program(15.5);
        assertEquals(((Program)mode).getThreshold(),15.5);
        assertEquals(mode.getDescription()," Mode Program (at 15.5 degrees)");
        ((Program)mode).setThreshold(10.3);
        assertEquals(((Program)mode).getThreshold(),10.3);
        assertEquals(mode.getDescription()," Mode Program (at 10.3 degrees)");
    }
}
