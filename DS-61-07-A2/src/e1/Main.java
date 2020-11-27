package e1;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Soldier s1 = new Elf("Legolas", 150, 50);
        Soldier s2 = new Human("Gandalf", 50, 40);
        Soldier s3 = new Hobbit("Frodo", 20, 5);
        Soldier s4 = new Orc("Lurtz", 190, 45);
        Soldier s5 = new Goblin("Mauhr", 290, 38);
        List<Soldier> heroes = new LinkedList<>();
        heroes.add(s1);
        heroes.add(s2);
        heroes.add(s3);
        List<Soldier> beasts = new LinkedList<>();
        beasts.add(s4);
        beasts.add(s5);
        Game game1= new Game(heroes,beasts);
        System.out.println(game1.get_heroes().size());
        game1.add_soldier(s1);
        System.out.println(game1.get_heroes().size());
        System.out.println(game1.get_result());
        game1.battle();
        System.out.println(game1.get_result().get(game1.get_result().size()-1));
        Dice d = new Dice(s1);
        System.out.println(d.getValue());
        d.new_seed_roll(s1,3);
        System.out.println(d.getValue());
        d.new_seed_roll(s1,2);
        System.out.println(d.getValue());
        d.new_seed_roll(s1,3);
        System.out.println(d.getValue());
    }
}
