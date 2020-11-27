package e1;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LotrTest {

    Soldier s1 = new Elf("Legolas", 150, 50);
    Soldier s2 = new Human("Gandalf", 50, 40);
    Soldier s3 = new Hobbit("Frodo", 20, 5);
    Soldier s4 = new Orc("Lurtz", 190, 45);
    Soldier s5 = new Goblin("Mauhr", 290, 38);
    @Test
    void dice_test(){
        Dice dice = new Dice(s1);
        Dice dice1 = new Dice(s4);
        // check the dice rolls for heroes and soldiers are correct
        assertTrue(dice.getValue()<100);
        assertTrue(dice1.getValue()<90);
        // roll with seed
        dice.new_seed_roll(s1,2);
        // save the value
        int value1 = dice.getValue();
        // roll again with same seed
        dice.new_seed_roll(s1,2);
        // check that the roll is same as the previous one
        assertEquals(value1,(int) dice.getValue());
        // roll again with new seed
        dice.new_seed_roll(s1,3);
        // check that the roll is different from the one with previous seed
        assertTrue(dice.getValue()!=value1);
    }
    @Test
    void test1(){
        // check if the instances are correct
        assertTrue(s1 instanceof Elf);
        assertTrue(s2 instanceof Human);
        assertTrue(s3 instanceof Hobbit);
        assertTrue(s4 instanceof Orc);
        assertTrue(s5 instanceof Goblin);
        List<Soldier> heroes = new LinkedList<>();
        heroes.add(s1);
        heroes.add(s2);
        heroes.add(s3);
        List<Soldier> beasts = new LinkedList<>();
        beasts.add(s4);
        beasts.add(s5);
        int size_h = heroes.size();
        int size_b = beasts.size();
        Game game= new Game(heroes,beasts);
        // check if the game loaded the armies correctly
        assertEquals(size_b, game.get_beasts().size());
        assertEquals(size_h, game.get_heroes().size());
        // add a soldier to each army
        game.add_soldier(s2);
        game.add_soldier(s4);
        // check if the armies got the new soldier
        assertEquals(size_h+1, game.get_heroes().size());
        assertEquals(size_b+1, game.get_beasts().size());
        // remove a soldier from each army
        game.remove_soldier(s1);
        game.remove_soldier(s5);
        // check if the soldiers were removed
        assertEquals(size_h, game.get_heroes().size());
        assertEquals(size_b, game.get_beasts().size());
        // check that the output is empty
        assertEquals(game.get_result().size(),0);
    }
    @Test
    void test2(){
        List<Soldier> heroes = new LinkedList<>();
        heroes.add(s1);
        heroes.add(s2);
        heroes.add(s3);
        List<Soldier> beasts = new LinkedList<>();
        beasts.add(s4);
        beasts.add(s5);
        Game game = new Game(heroes,beasts);
        // check if the output is empty
        assertEquals(game.get_result().size(),0);
        game.battle();
        // check if winner army exists
        assertTrue(game.get_result().get(game.get_result().size()-1).contains("WIN"));
        // check if at least one army is empty
        assertTrue(Integer.valueOf(0).equals(game.get_heroes().size())||Integer.valueOf(0).equals(game.get_beasts().size()));
    }
}
