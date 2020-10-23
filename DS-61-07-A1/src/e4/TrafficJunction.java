package e4;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class TrafficJunction {
    final int green=15;
    final int yellow=5;
    public enum TrafficLight{
        RED(false), GREEN(false), AMBER(false);
        private boolean off_on;
        public String getState()
        {
            if(off_on)
                return "ON";
            return "OFF";
        }
        TrafficLight(boolean off_on)
        {
            this.off_on=off_on;
        }
        public void setState(boolean new_state)
        {
            this.off_on=new_state;
        }
    }
    Map<String,EnumMap<TrafficLight,Integer>> lights = new HashMap<>();
    /**
     * Creates a trafic junction with four traffic lights named north , south ,
     * east and west . The north traffic light has just started its green cycle .
     */
    public TrafficJunction () {
        EnumMap<TrafficLight,Integer> init=new EnumMap<>(TrafficLight.class);
        TrafficLight.GREEN.setState(true);
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
    /**
     * Indicates that a second of time has passed , so the traffic light with
     * the green or amber light should add 1 to its counter . If the counter
     * passes its maximum value the color of the traffic light must change .
     * If it changes to red then the following traffic light changes to green .
     * The order is: north , south , east , west and then again north .
     */
    public void timesGoesBy () {
        if(lights.get("NORTH").containsKey(TrafficLight.AMBER))
            if((lights.get("NORTH").get(TrafficLight.AMBER))==-1)
                return;
        String[] rotate={"NORTH","SOUTH","EAST","WEST"};
        for(int i=0;i<3;i++)
        {
            if(lights.get(rotate[i]).containsKey(TrafficLight.GREEN)) {
                if (lights.get(rotate[i]).get(TrafficLight.GREEN) < green) {
                    lights.get(rotate[i]).put(TrafficLight.GREEN, lights.get(rotate[i]).get(TrafficLight.GREEN) + 1);
                }
                else{
                    lights.get(rotate[i]).remove(TrafficLight.GREEN);
                    TrafficLight.AMBER.setState(true);
                    lights.get(rotate[i]).put(TrafficLight.AMBER,0);
                    break;
                }
            }
            if(lights.get(rotate[i]).containsKey(TrafficLight.AMBER)) {
                if (lights.get(rotate[i]).get(TrafficLight.AMBER) < yellow) {
                    lights.get(rotate[i]).put(TrafficLight.AMBER, lights.get(rotate[i]).get(TrafficLight.AMBER) + 1);
                }
                else{
                    lights.get(rotate[i]).remove(TrafficLight.AMBER);
                    TrafficLight.RED.setState(true);
                    lights.get(rotate[i]).put(TrafficLight.RED,0);
                    if((i+1)==3) {
                        lights.get(rotate[0]).remove(TrafficLight.RED);
                        lights.get(rotate[0]).put(TrafficLight.GREEN, 0);
                    }
                    else{
                        lights.get(rotate[++i]).remove(TrafficLight.RED);
                        lights.get(rotate[i]).put(TrafficLight.GREEN, 0);
                    }
                }
            }
        }
    }
    /**
     * If active is true all the traffic lights of the junction must change to
     * blinking amber ( meaning a non - controlled junction ).
     * If active is false it resets the traffic lights cycle and started again
     * with north at green and the rest at red.
     * @param active true or false
     */
    public void amberJunction ( boolean active ) {
        if(active){
            this.lights.get("NORTH").clear();
            this.lights.get("SOUTH").clear();
            this.lights.get("EAST").clear();
            this.lights.get("WEST").clear();
            EnumMap<TrafficLight,Integer> init=new EnumMap<>(TrafficLight.class);
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
            EnumMap<TrafficLight,Integer> init=new EnumMap<>(TrafficLight.class);
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
     * Returns a String with the state of the traffic lights .
     * The format for each traffic light is the following :
     * - For red colors : "[ name : RED ]"
     * - For green colors : "[ name : GREEN counter ]"
     * - For yellow colors with blink at OFF : "[ name : YELLOW OFF counter ]
     * - For yellow colors with blink at ON: "[ name : YELLOW ON]
     * Examples :
     * [ NORTH : GREEN 2][ SOUTH : RED ][ EAST : RED ][ WEST : RED ]
     * [ NORTH : AMBER OFF 5][ SOUTH : RED ][ EAST : RED ][ WEST : RED ]
     * [ NORTH : AMBER ON ][ SOUTH : AMBER ON ][ EAST : AMBER ON ][ WEST : AMBER ON]
     * @return String that represents the state of the traffic lights
     */
    @Override
    public String toString () {
        StringBuilder print= new StringBuilder();
        String[] rotate={"NORTH","SOUTH","EAST","WEST"};
        if(lights.get("NORTH").containsKey(TrafficLight.AMBER))
            if((lights.get("NORTH").get(TrafficLight.AMBER))==-1)
            {
                for(String key : rotate) {
                    print.append("[").append(key).append(": AMBER ON]");
                }
            }
        if(print.toString().equals(""))
        {
            for(String key : rotate)
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

