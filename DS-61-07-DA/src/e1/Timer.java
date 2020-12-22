package e1;

public class Timer implements Mode{
    Integer time;
    String description;
    public Timer(Integer time){
        this.time=time;
        this.description=" Mode Timer (" + time + " minutes remaining)";
    }
    public Integer getTime(){
        return time;
    }
    public void setTime(Integer time){
        this.time=time;
        this.description=" Mode Timer (" + time + " minutes remaining)";
    }
    @Override
    public String getDescription() {
        return description;
    }
}
