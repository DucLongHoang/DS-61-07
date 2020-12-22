package e1;

public class Program implements Mode{
    double threshold;
    String description;
    public Program(double threshold){
        this.threshold=threshold;
        this.description=" Mode Program (at "+this.threshold +" degrees)";
    }
    public double getThreshold(){
        return threshold;
    }
    public void setThreshold(double threshold){
        this.threshold=threshold;
        this.description=" Mode Program (at "+this.threshold +" degrees)";
    }
    @Override
    public String getDescription() {
        return description;
    }
}
