package e1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThermostatTest {
    @Test
    void Test_Thermostat() {
        Thermostat t = new Thermostat();
        t.changeMode(1,0);
        t.newTemperature(20.1);
        t.changeMode(2,10);
        t.newTemperature(20.5);
        t.newTemperature(20.6);
        t.newTemperature(20.4);
        t.changeMode(3,19);
        t.newTemperature(20.0);
        t.newTemperature(18.5);
        List<String> history = t.getHistory();
        assertEquals(history.get(0),"Manual mode is enabled.");
        assertEquals(history.get(2),"Timer mode is enabled 10 minutes.");
        assertEquals(history.get(7),"20.0 Mode Program (at 19.0 degrees) - Heating off.");
    }
}