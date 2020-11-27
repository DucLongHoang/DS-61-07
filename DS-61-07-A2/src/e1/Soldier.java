package e1;

public interface Soldier {
    //getters for fields
    Integer get_hp();
    String  get_name();
    Integer get_armor();
    // method for printing
    String toString();
    // attack method the object for which is applied is attacked by the parameter
    void attack(Soldier soldier);
}

