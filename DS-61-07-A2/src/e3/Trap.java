package e3;

/**
 * Reloads in 1st round.
 * Blocks until his opponent has 4 bullets and shoots in the next round
 * cuz opponent probably wants to have the machine gun.
 */
public class Trap implements Behavior{
    @Override
    public GunslingerAction action(Gunslinger g) {
        if(g.getLoads() == 0) return GunslingerAction.RELOAD;
        if(g.getRivalLoads() < 4) return GunslingerAction.PROTECT;
        return GunslingerAction.SHOOT;
    }
    @Override
    public String tactic(){
        return "Trap";
    }
}
