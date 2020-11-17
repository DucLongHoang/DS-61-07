package e3;

import java.util.ArrayList;
import java.util.List;

public class Gunslinger {

    private int bullets;
    private Behavior behavior;
    private State state;

    private List<GunslingerAction> rivalActions;

    /**
     * Constructor for a Gunslinger.
     * Every Gunslinger starts the game alive and with 0 bullets.
     */
    public Gunslinger(){
        this.state = State.ALIVE;
        this.bullets = 0;
        this.rivalActions = new ArrayList<>();
    }

    /**
     * Method invoked by class Gunfight
     * The Gunslinger decides which action to take
     * and communicates it to the class GunFight
     * @return an action to take
     */
    public GunslingerAction action() {
        GunslingerAction action = this.behavior.action(this);
        switch (action) {
            case RELOAD -> this.bullets++;
            case SHOOT -> this.bullets--;
            case MACHINE_GUN -> this.bullets -= 5;
        }
        return action;
    }

    // checks if there are enough bullets for SHOOT/MACHINE_GUN
    public boolean enoughBullets(){
        return this.getLoads() >= 0;
    }

    // Clears the List with enemy actions - for new duel
    public void clearMemory(){
        this.rivalActions.clear();
    }

    // Setter for bullets - need to set to 0 for new duel
    public void setLoads(int loads){
        this.bullets = loads;
    }

    // Getter for the Gunslingers number of bullets
    public int getLoads(){
        return this.bullets;
    }

    // Getter for getting the state of the Gunslinger
    public State getState(){
        return this.state;
    }

    // changes state from ALIVE to DEAD and vice versa
    public void changeState(){
        if(this.getState() == State.ALIVE){
            this.state = State.DEAD;
        } else this.state = State.ALIVE;
    }

    /**
     * Used by the class GunFight
     * Tells the Gunslinger the last action of the rival
     * Integration with method getRivalActions
     * @param action is the action of the rival to be recorded in the List
     */
    public void rivalAction(GunslingerAction action){
        this.rivalActions.add(action);
    }

    /**
     * The rivals last actions will be at the end of the list
     * Used for guessing rivals strategy
     * @return List of rivals actions, last actions are at the end of the list
     */
    public List<GunslingerAction> getRivalActions(){
        return this.rivalActions;
    }

    /**
     * This method will use the List to determine the rivals load
     * @return the number of rivals bullets
     */
    public int getRivalLoads(){
        int rivalBullets = 0;
        List<GunslingerAction> enemyActions = getRivalActions();

        for (GunslingerAction enemyAction : enemyActions) {
            if (enemyAction == GunslingerAction.RELOAD) {
                rivalBullets++;
            } else if (enemyAction == GunslingerAction.SHOOT) {
                rivalBullets--;
            }
        }
        return rivalBullets;
    }

    // setting the tactic of the Gunslinger
    public void setBehavior(Behavior behavior){
        this.behavior = behavior;
    }

    // returns tactic in a String format
    public String tactic(){
        return this.behavior.tactic();
    }

}
