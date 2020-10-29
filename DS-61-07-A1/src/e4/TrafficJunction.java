package e4;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class TrafficJunction {

    // duration of traffic colours
    final int green = 15;
    final int yellow = 5;

    // for the blinking amber lights
    private boolean active = false;

    /**
     * Possible colours of a traffic light
     */
    public enum TrafficLight{
        RED(false), GREEN(false), AMBER(false);

        private boolean off_on;

        public String getState() {
            if(off_on) return "ON";
            return "OFF";
        }

        TrafficLight(boolean off_on) { this.off_on=off_on; }

        public void setState(boolean new_state) { this.off_on=new_state; }
    }

    Map< String, EnumMap<TrafficLight, Integer> > lights = new HashMap<>();

    /**
     * Creates a traffic junction with four traffic lights named north, south,
     * east and west. The north traffic light has just started its green cycle.
     */
    public TrafficJunction () {
        // setting NORTH to GREEN
        EnumMap<TrafficLight, Integer> init=new EnumMap<>(TrafficLight.class);
        TrafficLight.GREEN.setState(true);
        init.put(TrafficLight.GREEN,0);
        lights.put("NORTH", init);

        // setting the others to RED
        init = new EnumMap<>(TrafficLight.class);
        TrafficLight.RED.setState(true);
        init.put(TrafficLight.RED,0);
        lights.put("SOUTH",init);

        init = new EnumMap<>(TrafficLight.class);
        TrafficLight.RED.setState(true);
        init.put(TrafficLight.RED,0);
        lights.put("EAST",init);

        init = new EnumMap<>(TrafficLight.class);
        TrafficLight.RED.setState(true);
        init.put(TrafficLight.RED,0);
        lights.put("WEST",init);
    }

    /**
     * Indicates that a second of time has passed, so the traffic light with
     * the green or amber light should add 1 to its counter. If the counter
     * passes its maximum value the color of the traffic light must change.
     * If it changes to red then the following traffic light changes to green.
     * The order is: north, south, east, west and then again north.
     */
    public void timesGoesBy () {
        if(this.active) return;

        String[] rotate={"NORTH", "SOUTH", "EAST", "WEST"};

        // rotating through the lights: N -> S -> E -> W
        for(int i=0;i<3;i++)
        {
            // if current traffic light is GREEN
            if(lights.get(rotate[i]).containsKey(TrafficLight.GREEN)) {
                // check if 15 seconds have passed
                if (lights.get(rotate[i]).get(TrafficLight.GREEN) < green) {
                    // if not -> add +1 second
                    lights.get(rotate[i]).put(TrafficLight.GREEN, lights.get(rotate[i]).get(TrafficLight.GREEN) + 1);
                }
                else{   // 15 seconds have passed
                    lights.get(rotate[i]).remove(TrafficLight.GREEN);
                    // change current traffic light from GREEN to AMBER
                    TrafficLight.AMBER.setState(true);
                    lights.get(rotate[i]).put(TrafficLight.AMBER,0);    // setting timer for AMBER

                    // I have no idea what this is, but it works...
                    i = i + 1;
                }
            }
            // if current traffic light is AMBER
            if(lights.get(rotate[i]).containsKey(TrafficLight.AMBER)) {
                // check if 5 seconds have passed
                if (lights.get(rotate[i]).get(TrafficLight.AMBER) < yellow) {
                    // if not -> add +1 second
                    lights.get(rotate[i]).put(TrafficLight.AMBER, lights.get(rotate[i]).get(TrafficLight.AMBER) + 1);
                }
                else{   // if 5 seconds have passed
                    lights.get(rotate[i]).remove(TrafficLight.AMBER);
                    // change current traffic light from AMBER to RED
                    TrafficLight.RED.setState(true);
                    lights.get(rotate[i]).put(TrafficLight.RED,0);

                    // change the next traffic light from RED to GREEN
                    i = i + 1;
                    if (i != 3) {   // checking if at the last position
                        lights.get(rotate[i]).remove(TrafficLight.RED);
                        lights.get(rotate[i]).put(TrafficLight.GREEN,0);
                    }
                    else{           // if yes, then change the first traffic light
                        lights.get(rotate[0]).remove(TrafficLight.RED);
                        lights.get(rotate[0]).put(TrafficLight.GREEN,0);
                    }
                }
            }
        }
    }

    /**
     * If active is true all the traffic lights of the junction must change to
     * blinking amber (meaning a non - controlled junction).
     * If active is false, it resets the traffic lights cycle and starts again
     * with north at green and the rest at red.
     * @param active true or false
     */
    public void amberJunction (boolean active) {
        this.active = active;
        if(active){
            this.lights.get("NORTH").clear();
            this.lights.get("SOUTH").clear();
            this.lights.get("EAST").clear();
            this.lights.get("WEST").clear();
            EnumMap<TrafficLight, Integer> init=new EnumMap<>(TrafficLight.class);
            TrafficLight.AMBER.setState(true);
            init.put(TrafficLight.AMBER, -1);
            this.lights.put("NORTH",init);
            this.lights.put("SOUTH",init);
            this.lights.put("EAST",init);
            this.lights.put("WEST",init);
        }
        else
        {
            this.lights.get("NORTH").clear();
            this.lights.get("SOUTH").clear();
            this.lights.get("EAST").clear();
            this.lights.get("WEST").clear();
            TrafficLight.GREEN.setState(true);
            EnumMap<TrafficLight, Integer> init=new EnumMap<>(TrafficLight.class);
            init.put(TrafficLight.GREEN,0);
            lights.put("NORTH", init);
            init = new EnumMap<>(TrafficLight.class);
            TrafficLight.RED.setState(true);
            init.put(TrafficLight.RED,0);
            lights.put("SOUTH",init);
            init = new EnumMap<>(TrafficLight.class);
            TrafficLight.RED.setState(true);
            init.put(TrafficLight.RED,0);
            lights.put("EAST",init);
            init = new EnumMap<>(TrafficLight.class);
            TrafficLight.RED.setState(true);
            init.put(TrafficLight.RED,0);
            lights.put("WEST",init);
        }

    }
    /**
     * Returns a String with the state of the traffic lights.
     * The format for each traffic light is the following:
     * - For red colors: "[name: RED]"
     * - For green colors: "[name: GREEN counter]"
     * - For yellow colors with blink at OFF: "[name: YELLOW OFF counter]
     * - For yellow colors with blink at ON: "[name: YELLOW ON]
     * Examples :
     * [NORTH: GREEN 2][SOUTH: RED][EAST: RED][WEST: RED]
     * [NORTH: AMBER OFF 5][SOUTH: RED][EAST: RED][WEST: RED]
     * [NORTH: AMBER ON][SOUTH: AMBER ON][EAST: AMBER ON][WEST: AMBER ON]
     * @return String that represents the state of the traffic lights
     */
    @Override
    public String toString () {
        StringBuilder print = new StringBuilder();
        String[] rotate = {"NORTH", "SOUTH", "EAST", "WEST"};

        if(lights.get("NORTH").containsKey(TrafficLight.AMBER))
            if((lights.get("NORTH").get(TrafficLight.AMBER)) == -1)
            {
                for(String key: rotate) {
                    print.append("[").append(key).append(": AMBER ON]");
                }
            }
        if(print.toString().equals(""))
        {
            for(String key: rotate)
            {
                if(lights.get(key).containsKey(TrafficLight.GREEN))
                    print.append("[").append(key).append(": GREEN ").append(lights.get(key).get(TrafficLight.GREEN).toString()).append("]");
                if(lights.get(key).containsKey(TrafficLight.AMBER))
                    print.append("[").append(key).append(": AMBER OFF ").append(lights.get(key).get(TrafficLight.AMBER).toString()).append("]");
                if(lights.get(key).containsKey(TrafficLight.RED))
                    print.append("[").append(key).append(": RED]");
            }
        }
        //print="asd";
        return print.toString();
    }

    public static void main(String[] args) {
        TrafficJunction t1=new TrafficJunction();
        t1.amberJunction(true);
        System.out.println(t1);
        t1.amberJunction(false);
        System.out.println(t1);
        int i=0;
        while(i++<100)
        {
            t1.timesGoesBy();
            System.out.println(t1);
        }
        //1.timesGoesBy();
    }
}