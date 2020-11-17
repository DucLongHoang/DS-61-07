package e3;

import java.util.List;

/**
 * Combines both Trap and AntiTrap strategies
 * Beats both of them but does not beat Crazy tactic
 */
public class Smart extends Trap implements Behavior{
    @Override
    public GunslingerAction action(Gunslinger g) {
        if(g.getLoads() > 4) return GunslingerAction.MACHINE_GUN;

        List<GunslingerAction> rivalActions = g.getRivalActions();
        int blocks = 0;
        int arraySize = rivalActions.size();

        if(arraySize >= 4){
            for(int i = arraySize - 1; i > arraySize - 4; i--){
                if(rivalActions.get(i) == GunslingerAction.PROTECT) blocks++;
            }

            // change tactic from Trap to AntiTrap
            // because opponent probably uses Trap tactic
            if(blocks >= 2){
                int myBullets = g.getLoads();
                int rivalBullets = g.getRivalLoads();

                if(myBullets == 0) return GunslingerAction.RELOAD;
                if(myBullets == 4 && rivalBullets == 1) return GunslingerAction.PROTECT;
                if(myBullets == 4 && rivalBullets == 0) return GunslingerAction.RELOAD;
                if(myBullets > 4) return GunslingerAction.MACHINE_GUN;
                return GunslingerAction.RELOAD;
            }
        }
        // Trap tactic
        return super.action(g);
    }

    @Override
    public String tactic() {
        return "Smart";
    }
}
