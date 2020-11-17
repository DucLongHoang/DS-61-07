package e3;

/**
 * Demolishes random tactics and stalemates the rest
 * Balanced (edited) is his weakness
 * Made by: not me
 */
public class Trivial implements Behavior {
    @Override
    public GunslingerAction action(Gunslinger g) {
        if(g.getLoads() == 0)  return GunslingerAction.RELOAD;
        return GunslingerAction.SHOOT;
    }

    @Override
    public String tactic() {
        return "Trivial";
    }
}
