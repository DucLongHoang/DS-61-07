package e2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectTest {
    public static Project war = new Project("Sunrise");
    public static Project peace = new Project("Sunset");

    public static Team one = new Team("Alpha");
    public static Team two = new Team("Bravo");
    public static Team three = new Team("Bravo 2");   // sub-team of Team Bravo

    // workers of e2.Team Alpha
    public static Worker s1 = new Worker("Johny", 20);
    public static Worker s2 = new Worker("Phillip", 30);
    public static Worker s3 = new Worker("McKenzie", 25);

    // workers of e2.Team Bravo
    public static Worker s4 = new Worker("Thomas", 40);
    public static Worker s5 = new Worker("Inna", 30);

    // workers of e2.Team Bravo 2
    public static Worker s6 = new Worker("Eugene", 15);
    public static Worker s7 = new Worker("Moses", 10);

    // worker of e2.Project Thunder
    public static Worker a = new Worker("Anastasia", 70);


    @org.junit.jupiter.api.BeforeAll
    public static void setUp() {
        // adding workers to teams
        one.addElement(s1); one.addElement(s2); one.addElement(s3);
        two.addElement(s4); two.addElement(s5);
        three.addElement(s6); three.addElement(s7);
        // adding team three as sub-team of team two
        two.addElement(three);
        // adding all teams to the project
        war.addElement(one); war.addElement(two); war.addElement(a);
        // set all workers' hours
        s1.setHours(war,10); s2.setHours(war,10); s3.setHours(war,10);
        s4.setHours(war,15); s5.setHours(war,15);
        s6.setHours(war,5); s7.setHours(war,5);
        a.setHours(war,20);


        // set hours for Workers of Project 2
        s1.setHours(peace, 40); s2.setHours(peace, 50);
        // add Johny and Phillip under Project 2
        peace.addElement(s1); peace.addElement(s2);
        // add Team Bravo(and Bravo 2) under Project 2
        peace.addElement(two);
    }

    @Test
    void testSalaryAndHours(){
        assertEquals(3325, war.getSalary(war));
        assertEquals(2300, peace.getSalary(peace));

        assertEquals(90,war.getHours(war));
        assertEquals(90, peace.getHours(peace));
    }

    @Test
    void testIllegalOperations(){
        assertThrows(IllegalArgumentException.class, () -> new Worker("Johny", 50));
        assertThrows(IllegalArgumentException.class, () -> one.addElement(war));
        assertThrows(IllegalArgumentException.class, () -> war.addElement(peace));
    }

    @Test
    void testCoworkers(){
        String[] coworkers1 = new String[]{"Phillip", "McKenzie", "Thomas", "Inna", "Eugene", "Moses", "Anastasia"};
        String[] coworkers2 = new String[]{"Johny", "Thomas", "Inna", "Eugene", "Moses"};

        List<String> expected1 = new LinkedList<>(Arrays.asList(coworkers1));
        List<String> expected2 = new LinkedList<>(Arrays.asList(coworkers2));

        assertEquals(expected1.toString(), s1.getCoworkers(war).toString());
        assertEquals(expected2.toString(), s2.getCoworkers(peace).toString());
    }

}