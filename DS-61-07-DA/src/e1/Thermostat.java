package e1;

import java.util.ArrayList;
import java.util.List;

public class Thermostat {
    private boolean heating;
    private double temperature;
    private Mode modes;
    private History history = new History();

    public Thermostat() {
        this.heating = false;
        this.temperature = 0.0;
        this.modes=new Off();
    }

    public Thermostat(boolean heating, double temperature) {
        this.heating = heating;
        this.temperature = temperature;
        this.modes= new Off();
    }

    public void newTemperature(double temperature) {
        this.temperature = temperature;
        if (modes instanceof Off)
            this.history.add(this.temperature + modes.getDescription());
        if (modes instanceof Manual)
            this.history.add(this.temperature + modes.getDescription());
        if (modes instanceof Timer) {
            if (((Timer)this.modes).getTime() > 5) {
                ((Timer)this.modes).setTime(((Timer)this.modes).getTime()-5);
                this.history.add(this.temperature + this.modes.getDescription());
            }
            else{
                this.history.add("Timer mode is disabled.");
                this.heating=false;
                this.modes=new Off();
            }
        }
        if (this.modes instanceof Program){
            if(this.temperature<((Program)this.modes).getThreshold()){
                this.heating = true;
                this.history.add(this.temperature+ this.modes.getDescription()+" - Heating on.");
            }
            else{
                this.heating=false;
                this.history.add(this.temperature+ this.modes.getDescription()+" - Heating off.");
            }
        }

    }

    public void screenInfo() {
        String heat;
        if (heating)
            heat = " ON";
        else
            heat = " OFF";
        if(this.modes instanceof Off)
            System.out.println(temperature + heat + " O");
        else if(this.modes instanceof Manual)
            System.out.println(temperature + heat + " M");
        else if(this.modes instanceof Timer)
            System.out.println(temperature + heat + " T" + ((Timer)this.modes).getTime());
        else
            System.out.println(temperature + heat + " P" + ((Program)this.modes).getThreshold());
    }

    public void setOff() {
        this.heating = false;
        this.modes=new Off();
        history.add("Off mode is enabled.");
    }

    public void setManual() {
        this.heating = true;
        this.modes = new Manual();
        history.add("Manual mode is enabled.");
    }

    public void setTimer(Integer timer) {
        if(modes instanceof Program){
            System.out.println("Cannot switch from Timer to Program mode.");
            return;
        }
        this.heating = true;
        this.modes = new Timer(timer);
        history.add("Timer mode is enabled " + timer + " minutes.");
    }

    public void setProgram(double threshold) {
        if(modes instanceof Timer){
            System.out.println("Cannot switch from Program to Timer mode.");
            return;
        }
        if (this.temperature > threshold)
            this.heating = false;
        else
            this.heating = true;
        this.modes= new Program(threshold);
        history.add("Program mode is enabled at " + threshold + " degrees.");
    }

    public void changeMode(int mode, double value) {
        switch (mode) {
            case 0 -> setOff();
            case 1 -> setManual();
            case 2 -> setTimer((int) value);
            case 3 -> setProgram(value);
        }
    }
    public void print()
    {
        history.print();
    }
    public List<String> getHistory() {return history.getHistory();}
}