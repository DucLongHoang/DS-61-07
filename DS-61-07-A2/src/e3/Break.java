package e3;

/**
 * Uses MACHINE_GUN with no bullets
 */
public class Break implements Behavior {
    @Override
    public GunslingerAction action(Gunslinger g) {
        return GunslingerAction.MACHINE_GUN;
    }

    @Override
    public String tactic() {
        return "Break";
    }
}
