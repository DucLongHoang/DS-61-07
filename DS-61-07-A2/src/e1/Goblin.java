package e1;

public class Goblin implements Soldier {
    private String name;
    private Integer hp;
    private Integer armor;

    public Goblin(String name, Integer hp, Integer armor) {
        this.name = name;
        this.hp = hp;
        this.armor = armor;
    }
    // getters and printing methods
    @Override
    public Integer get_hp() {
        return this.hp;
    }

    @Override
    public String get_name() {
        return this.name;
    }

    @Override
    public Integer get_armor() {
        return this.armor;
    }

    @Override
    public String toString() {
        return "Goblin{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", armor=" + armor +
                '}';
    }
    //attack method (a goblin is attacked by a Hero)
    @Override
    public void attack(Soldier soldier) {
        // get attack value from dice
        Integer value = new Dice(soldier).getValue();
        // if the attacker is Hobbit and the attack is greater than armor make the attack stronger by 5 points and decrease HP
        if(soldier instanceof Hobbit) {
            if(value>this.armor)
                hp = (hp+5)-value;
        }
        else{
            // else the attacker is not Hobbit so just decrease the HP if the attack value is greater than armor
            if(value>this.armor)
                hp = hp-value;
        }
    }
}
