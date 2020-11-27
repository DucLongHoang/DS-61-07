package e3;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GunslingerTest {

    public static Gunslinger g1 = new Gunslinger();
    public static Gunslinger g2 = new Gunslinger();

    @Before
    void setUp(){
        g1.setBehavior(new Balanced());
        g2.setBehavior(new Break());
        g1.setLoads(0);
        g2.setLoads(0);
    }

    @Test
    void BehaviorTest() {
        setUp();
        assertSame("Balanced", g1.tactic());
        assertSame("Break", g2.tactic());
    }

    @Test
    void BulletsTest() {
        setUp();
        g1.setLoads(7);
        g2.setLoads(2);

        // both actions are MACHINE_GUN
        GunslingerAction action1 = g1.action();
        GunslingerAction action2 = g2.action();

        assertSame(action1, action2);
        assertEquals(2, g1.getLoads());
        assertEquals(-3, g2.getLoads());

        assertTrue(g1.enoughBullets());
        assertFalse(g2.enoughBullets());
    }

    @Test
    void StateTest() {
        assertSame(g1.getState(), State.ALIVE);
        g1.changeState();
        assertSame(g1.getState(), State.DEAD);
        g1.changeState();
        assertSame(g1.getState(), State.ALIVE);
    }

    @Test
    void Rivaltest(){
        setUp();
        // these two behaviors are more predictable -> easy testing
        g1.setBehavior(new AntiTrap());
        g2.setBehavior(new Trap());
        GunslingerAction action1;
        GunslingerAction action2;

        for(int i = 0; i < 6; i++){
            action1 = g1.action();
            action2 = g2.action();

            g1.rivalAction(action2);
            g2.rivalAction(action1);
        }

        assertEquals(1, g1.getRivalLoads());
        assertEquals(5, g2.getRivalLoads());

        g1.clearMemory(); g2.clearMemory();
        List<GunslingerAction> list1 = g1.getRivalActions();
        List<GunslingerAction> list2 = g2.getRivalActions();

        assertTrue(list1.isEmpty());
        assertTrue(list2.isEmpty());

    }

}