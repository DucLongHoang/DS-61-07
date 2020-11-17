package e3;

import java.util.Random;

/**
 * Randomly selects an action
 * If not enough bullets for SHOOT/MACHINE_GUN -> randomly select action again
 */
public class Crazy implements Behavior{
    @Override
    public GunslingerAction action(Gunslinger g) {
        Random r = new Random();
        int myBullets = g.getLoads();
        while(true){
            int n = r.nextInt();
            if(myBullets == 0) return GunslingerAction.RELOAD;
            if(myBullets >= 5) return GunslingerAction.MACHINE_GUN;

            // randomizing which action to take
            switch(n % 3){
                case 0 -> {
                    return GunslingerAction.RELOAD;
                }
                case 1 -> {
                    if(myBullets > 0) return GunslingerAction.SHOOT;
                }
                case 2 -> {
                    return GunslingerAction.PROTECT;
                }
            }
        }
    }

    @Override
    public String tactic(){
        return "Crazy";
    }
}
