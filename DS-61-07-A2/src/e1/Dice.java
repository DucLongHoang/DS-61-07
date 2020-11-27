package e1;

import java.util.Random;

public class Dice {
    // field for the value of the dice
    private Integer value;

    public Dice(Soldier soldier) {
        this.value = s_roll(soldier);
    }
    // method that generates a random integer with values between 0 and the parameter
    private Integer roll(Integer max){
        return new Random().nextInt(max);
    }
    // method that rolls the dice differently based on the object's type
    private Integer s_roll(Soldier soldier) {
        if (soldier instanceof Elf || soldier instanceof Hobbit || soldier instanceof Human)
            return Math.max(roll(100), roll(100));
        else if(soldier instanceof Goblin || soldier instanceof Orc)
            return roll(90);
        return -1;
    }
    // generate a random number with seed
    private Integer seed_roll(Integer max, long seed){
        Random r = new Random();
        r.setSeed(seed);
        return r.nextInt(max);
    }
    // same as s_roll but with seed also
    private Integer s_seed_roll(Soldier soldier, long seed) {
        if (soldier instanceof Elf || soldier instanceof Hobbit || soldier instanceof Human)
            return Math.max(seed_roll(100,seed), seed_roll(100,seed));
        else if(soldier instanceof Goblin || soldier instanceof Orc)
            return seed_roll(90,seed);
        return -1;
    }
    public void new_roll(Soldier soldier){
        this.value = s_roll(soldier);
    }
    public void new_seed_roll(Soldier soldier, long seed) { this.value=s_seed_roll(soldier,seed);}
    // getter for the value of the dice
    public Integer getValue() {
        return value;
    }
}
