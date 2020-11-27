package e1;

public class Orc implements Soldier{
    private String name;
    private Integer hp;
    private Integer armor;

    public Orc(String name, Integer hp, Integer armor) {
        this.name = name;
        this.hp = hp;
        this.armor = armor;
    }

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
        return "Orc{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", armor=" + armor +
                '}';
    }
    // attack method, Orc attacked by a Hero
    @Override
    public void attack(Soldier soldier) {
        // get attack value from dice
        Integer value = new Dice(soldier).getValue();
        // if the attacker is Elf and the attack is greater than armor make the attack weaker by 10 points and decrease HP
        if(soldier instanceof Elf) {
            if(value>this.armor)
                hp = (hp-10)-value;
        }
        else{
            // else if the attacker is not an Elf and the attack is greater than armor, decrease HP
            if(value>this.armor)
                hp = hp-value;
        }
    }
}
