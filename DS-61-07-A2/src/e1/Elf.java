package e1;

public class Elf implements Soldier {
    private String name;
    private Integer hp;
    private Integer armor;

    public Elf(String name, Integer hp, Integer armor) {
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
        return "Elf{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", armor=" + armor +
                '}';
    }
    // attack method, Elf attacked by a Beast
    @Override
    public void attack(Soldier soldier) {
        // get attack value from dice
        Integer value = new Dice(soldier).getValue();
        // if the attacker is Orc and the attack is greater than armor make the armor weaker by 10% and decrease HP
        if(soldier instanceof Orc) {
            if(value>((Integer)(int)(0.9*this.armor)))
                hp = hp - value;
        }
        else{
            // else if the attacker is not Orc and it's attack is greater than armor, decrease HP
            if(value>this.armor)
                hp = hp-value;
        }
    }
}
