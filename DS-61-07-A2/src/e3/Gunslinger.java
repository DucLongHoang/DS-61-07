package e3;

import java.util.ArrayList;
import java.util.List;

public class Gunslinger implements Behavior{

    private int bullets;
    private Behavior behavior;
    private State state;

    // private int rivalBullets;
    private Gunslinger rival;
    private List<GunslingerAction> rivalActions;

    /**
     * Constructor for a Gunslinger.
     * Every Gunslinger starts the game alive and with 0 bullets.
     */
    public Gunslinger(){
        this.state = State.ALIVE;
        this.bullets = 0;
        this.rivalActions = new ArrayList<GunslingerAction>();
    }

    /**
     *
     * @param g
     * @return
     */
    @Override
    public GunslingerAction action(Gunslinger g) {
        if(this.getLoads() + g.getLoads() == 0) {
            this.bullets++;
            return GunslingerAction.RELOAD;
        }
        if(this.getLoads() > g.getLoads()) {
            this.bullets--;
            return GunslingerAction.SHOOT;
        }
        if(this.getLoads() != 0 && g.getLoads() > this.getLoads()) {
            this.bullets++;
            return GunslingerAction.RELOAD;
        }
        if(this.getLoads() >= 5) return GunslingerAction.MACHINE_GUN;

        return GunslingerAction.PROTECT;
    }

    /**
     * Method invoked by class Gunfight
     * The Gunslinger decides which action to take
     * and communicates it to the class GunFight
     * @return an action to take
     */
    public GunslingerAction action(){
        return action(this.rival);
    }

    // Getter for the Gunslingers number of bullets
    public int getLoads(){
        return this.bullets;
    }

    // Getter for getting the state of the Gunslinger
    public State getState(){
        return this.state;
    }

    // changes state from ALIVE to DEAD
    public void changeState(){
        this.state = State.DEAD;
    }

    /**
     * Used by the class GunFight
     * Tells the Gunslinger the last action of the rival
     * Integration with method getRivalActions
     * @param action
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

    // setting the behavior of the Gunslinger
    public void setBehavior(Behavior behavior){
        this.behavior = behavior;
    }

}
