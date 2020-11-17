package e3;

/**
 * Anticipates the Trap tactic and counters it.
 * Blocks when Trap shoots -> Trap has 0 bullets
 * -> safe RELOAD and then MACHINE_GUN
 */
public class AntiTrap implements Behavior{
    @Override
    public GunslingerAction action(Gunslinger g) {
        int myBullets = g.getLoads();
        int rivalBullets = g.getRivalLoads();

        if(myBullets == 0) return GunslingerAction.RELOAD;
        if(myBullets == 4 && rivalBullets == 1) return GunslingerAction.PROTECT;
        if(myBullets == 4 && rivalBullets == 0) return GunslingerAction.RELOAD;
        if(myBullets > 4) return GunslingerAction.MACHINE_GUN;
        return GunslingerAction.RELOAD;
    }

    @Override
    public String tactic() {
        return "AntiTrap";
    }
}
