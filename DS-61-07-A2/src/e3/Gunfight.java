package e3;

/**
 * Class that represents the duel between two Gunslingers
 */
public class Gunfight {
    // rounds of the duel
    private static int rounds = 0;

    // wins and stalemate counters
    private static int player1 = 0;
    private static int player2 = 0;
    private static int stalemate = 0;
    // machine gun wins
    private static int MGWins1 = 0;
    private static int MGWins2 = 0;

    /**
     * Runs n number of duels where n is inputted by user and
     * prints out the number of wins for both sides and stalemates.
     * @param duels number of duels to run
     * @param g1 is the first Gunslinger
     * @param g2 si the second Gunslinger
     * @throws Exception if not enough bullets for an action
     */
    public static void moreDuels(int duels, Gunslinger g1, Gunslinger g2) throws Exception {
        for(int i = 0; i < duels; i++){ duel(g1, g2); }
        System.out.println("\nGames: " + duels);
        System.out.printf("Tactics: [P1:P2] = [%s:%s]\n", g1.tactic(), g2.tactic());
        System.out.format("Score: [P1:P2:ties] = [%d:%d:%d]\n", player1, player2, stalemate);
        System.out.format("Wins by MG: [P1:P2] = [%d:%d]\n", MGWins1, MGWins2);
    }

    /**
     * This method executes the duel between g1 and g2
     * @param g1 is the first Gunslinger
     * @param g2 is the second Gunslinger
     */
    public static void duel(Gunslinger g1, Gunslinger g2) throws Exception {
        // duel runs while both are alive
        while(g1.getState() == State.ALIVE && g2.getState() == State.ALIVE){
            // stop duel if too many rounds have passed
            if(rounds == 20) {
                System.out.println("...Duel takes too long...");
                break;
            }

            System.out.format("Round %d: -------------------- \n", (rounds + 1));

            // Gunslingers decide their action
            GunslingerAction action1 = g1.action();
            GunslingerAction action2 = g2.action();

            System.out.println("Gunslinger1: " + action1 + " - Bullets: " + g1.getLoads() + " |" + g1.tactic());
            System.out.println("Gunslinger2: " + action2 + " - Bullets: " + g2.getLoads() + " |" + g2.tactic());

            // check if actions are valid
            if(!g1.enoughBullets() || !g2.enoughBullets()){
                System.err.println("\n!!!Not enough bullets!!!\n");
                throw new Exception();
            }

            // Gunslingers record their enemy's action
            g1.rivalAction(action2);
            g2.rivalAction(action1);

            // both shoot/machine gun at the same time
            if((action1 == GunslingerAction.SHOOT && action2 == GunslingerAction.SHOOT)
            || (action1 == GunslingerAction.MACHINE_GUN && action2 == GunslingerAction.MACHINE_GUN)) break;

            // g1 does MACHINE_GUN
            if(action1 == GunslingerAction.MACHINE_GUN){
                MGWins1++;
                g2.changeState(); break; }

            // g2 does MACHINE_GUN
            if(action2 == GunslingerAction.MACHINE_GUN){
                MGWins2++;
                g1.changeState(); break; }

            // g1 shoots g2 while he is reloading
            if(action1 == GunslingerAction.SHOOT && action2 == GunslingerAction.RELOAD) {
                g2.changeState(); break; }

            // g2 shoots g1 while he is reloading
            if(action2 == GunslingerAction.SHOOT && action1 == GunslingerAction.RELOAD) {
                g1.changeState(); break;
            }

            System.out.println("The duel continues...");

            rounds++;
        }

        System.out.println("\nThe duel has ended...");

        if(g1.getState() == State.DEAD) {
            g1.changeState();
            player2++;
            System.out.println("Winner: GUNSLINGER2\n");
        }
        else if(g2.getState() == State.DEAD) {
            g2.changeState();
            player1++;
            System.out.println("Winner: GUNSLINGER1\n");
        }
        else {
            stalemate++;
            System.out.println("...in a stalemate\n");
        }

        // set duel starting conditions
        rounds = 0;
        g1.setLoads(0);
        g2.setLoads(0);
        g1.clearMemory();
        g2.clearMemory();
    }

    public static void main(String[] args) throws Exception {
        Gunslinger playerOne = new Gunslinger();
        Gunslinger playerTwo = new Gunslinger();

        // possible behaviors: Trap(), AntiTrap(), Smart(), Trivial(), Balanced(), Crazy(), Break()
        playerOne.setBehavior(new Balanced());
        playerTwo.setBehavior(new Break());

        //duel(playerOne, playerTwo);
        moreDuels(1000, playerOne, playerTwo);

    }

}
