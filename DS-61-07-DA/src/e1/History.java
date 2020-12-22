package e1;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<String> history;
    public History(){
        this.history=new ArrayList<>();
    }
    public void add(String log){
        this.history.add(log);
    }
    public void print(){
        for(String string : history)
            System.out.println(string);
    }
    public List<String> getHistory(){
        return history;
    }
}
