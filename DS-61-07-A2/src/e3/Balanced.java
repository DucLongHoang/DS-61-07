package e3;

/**
 * This guy plays pretty safe.
 * Reloads at 0 and shoots only if he has more or the same
 * amount of bullets as his opponent
 */
public class Balanced implements Behavior{
    @Override
    public GunslingerAction action(Gunslinger g) {
        int myBullets = g.getLoads();
        int rivalBullets = g.getRivalLoads();
        if(myBullets > 4) return GunslingerAction.MACHINE_GUN;
        if(myBullets == 0) return GunslingerAction.RELOAD;
        if(myBullets < rivalBullets) return GunslingerAction.PROTECT;
        return GunslingerAction.SHOOT;
    }
    @Override
    public String tactic(){
        return "Balanced";
    }
}
