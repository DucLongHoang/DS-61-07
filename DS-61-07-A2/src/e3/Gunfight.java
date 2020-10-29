package e3;

import java.util.List;

/**
 * Class that represents the duel between two Gunslingers
 */
public class Gunfight {
    // rounds of the duel
    private static int rounds = 0;

    /**
     * This method executes the duel between g1 and g2
     * @param g1 is the first Gunslinger
     * @param g2 is the second Gunslinger
     */
    public static void duel(Gunslinger g1, Gunslinger g2){
        while(g1.getState() == State.ALIVE && g2.getState() == State.ALIVE){
            if(rounds == 20) break;
            System.out.format("Round %d: -------------------- \n", (rounds + 1));

            GunslingerAction action1 = g1.action();
            GunslingerAction action2 = g2.action();

            System.out.println("Gunslinger1: " + action1);
            System.out.println("Gunslinger2: " + action2);

            if((action1 == GunslingerAction.SHOOT && action2 == GunslingerAction.SHOOT)
            || (action1 == GunslingerAction.MACHINE_GUN && action2 == GunslingerAction.MACHINE_GUN)) break;

            if(action1 == GunslingerAction.SHOOT && action2 == GunslingerAction.RELOAD) g2.changeState();
            if(action2 == GunslingerAction.SHOOT && action1 == GunslingerAction.RELOAD) g1.changeState();

            System.out.println("The duel continues...");

            rounds++;
        }

        if(g1.getState() == State.DEAD) System.out.println("Winner: GUNSLINGER2");
        else if(g2.getState() == State.DEAD) System.out.println("Winner: GUNSLINGER1");
        else System.out.println("The duel has ended in a stalemate");
    }

    public static void main(String[] args){
        Gunslinger playerOne = new Gunslinger();
        /*
        Gunslinger playerTwo = new Gunslinger();
        playerOne.rival = playerTwo;
        playerTwo.rival = playerOne;

        duel(playerOne, playerTwo);
        */
        playerOne.rivalAction(GunslingerAction.RELOAD);
        playerOne.rivalAction(GunslingerAction.SHOOT);
        List<GunslingerAction> rivalActions = playerOne.getRivalActions();
        for(GunslingerAction rivalAction : rivalActions){
            System.out.println(rivalAction);
        }


    }

}
