package e3;

/**
 * Interface for behaviours
 */
interface Behavior {
    GunslingerAction action(Gunslinger g);
    String tactic();
}
