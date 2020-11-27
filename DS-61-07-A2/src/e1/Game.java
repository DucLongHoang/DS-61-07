package e1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    // lists for hero army and beast army also a list of strings for the battle
    private List<Soldier> hero_army;
    private List<Soldier> beast_army;
    private List<String> battle_result;
    // constructor without parameters
    public Game(){
        hero_army=new LinkedList<>();
        beast_army=new LinkedList<>();
        battle_result=new ArrayList<>();
    }
    // constructor with parameters
    public Game(List<Soldier> hero_army, List<Soldier>beast_army){
        this.hero_army=hero_army;
        this.beast_army=beast_army;
        this.battle_result=new ArrayList<>();
    }
    // add a soldier to an army
    public void add_soldier(Soldier soldier){
        // if hero add to heroes
        if(soldier instanceof Human || soldier instanceof Hobbit || soldier instanceof Elf){
            hero_army.add(soldier);
        }
        else
            //else add to beasts
            beast_army.add(soldier);
    }
    public void remove_soldier(Soldier soldier){
        // if hero remove from heroes
        if(soldier instanceof Human || soldier instanceof Hobbit || soldier instanceof Elf){
            hero_army.remove(soldier);
        }
        else
            //else remove from beasts
            beast_army.remove(soldier);
    }
    // method for creating a battle
    public void battle(){
        // save the initial sizes of army inside two variables
        int hero_size = hero_army.size();
        int beast_size = beast_army.size();
        // count the turns for display
        int turn_counter = 1;
        // check that both armies have soldiers alive
        while(hero_size>0 && beast_size>0){
            // display the turn
            battle_result.add("Turn "+turn_counter+":\n");
            // iterate through the armies
            for(int i=0;i<Math.min(hero_size,beast_size);i++) {
                //System.out.println(i);
                // print the duels
                battle_result.add("\tFight between " + hero_army.get(i).get_name() + " (HP=" + hero_army.get(i).get_hp() + ") and " + beast_army.get(i).get_name() + " (HP=" + beast_army.get(i).get_hp() + ")\n");
                // hero attacks beast and if it dies then it's removed from the army
                beast_army.get(i).attack(hero_army.get(i));
                if (beast_army.get(i).get_hp() <= 0) {
                    battle_result.add("\t"+beast_army.get(i).getClass().getSimpleName() + " " + beast_army.get(i).get_name() + " dies!\n");
                    beast_army.remove(beast_army.get(i));
                    beast_size = beast_army.size();
                    //i=i-1;
                    continue;
                    // i = i - 1;
                }
                // beast attacks hero and if it dies then it's removed from the army
                hero_army.get(i).attack(beast_army.get(i));
                if (hero_army.get(i).get_hp() <= 0) {
                    battle_result.add("\t"+hero_army.get(i).getClass().getSimpleName() + " " + hero_army.get(i).get_name() + " dies!\n");
                    hero_army.remove(hero_army.get(i));
                    hero_size = hero_army.size();
                    //i = i - 1;
                    continue;
                }
            }
            // increase the turn's counter
            turn_counter++;
        }
        // check which army is empty to decide the winner
        if(hero_size==0)
            battle_result.add("\tBEASTS WIN!!\n");
        else
            battle_result.add("\tHEROES WIN!!\n");
        // call the print function
        print_result();
    }
    //print function which display the contents of the list which represents the output strings
    public void print_result()
    {
        for(String s : battle_result)
            System.out.print(s);
    }

    public List<String> get_result() {
        return battle_result;
    }
    public List<Soldier> get_heroes() {return hero_army; }
    public List<Soldier> get_beasts() {return beast_army; }
}
