package e2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Worker extends ProjectElement {
    // wage of worker
    private final double wage;
    // projects the worker is part of and the time he spent on it
    private final HashMap<Project, Double> projects;

    // constructor
    public Worker(String name, int wagePerHour){
        super(name);
        this.wage = wagePerHour;
        this.projects = new HashMap<>();
    }

    // getters and setters
    @Override
    public double getHours(Project project) {
        if(!this.projects.containsKey(project)) return 0;
        return this.projects.get(project);
    }
    @Override
    public double getSalary(Project project) {
        return this.wage * this.getHours(project);
    }
    // set hours worked on in a certain Project
    public void setHours(Project project, double hours){
        this.projects.put(project, hours);
    }

    // method returns a list of coworkers of Worker on a given ProjectSegment
    public List<String> getCoworkers(ProjectSegment ps){
        // list of coworkers - returned at the end
        List<String> coworkers = new LinkedList<>();

        for(ProjectElement e: ps.getElements()){     // loop through elements of Project/Team
            if(e instanceof Worker){        // if e is a Worker -> add to list
                if(!e.equals(this)) {       // condition so that Worker is not a coworker of himself
                    coworkers.add(e.getName()); }
            }
            // else e == ProjectSegment -> recursive call in sub-segment
            else { getCoworkers((ProjectSegment) e, coworkers); }
        }
        return coworkers;
    }

    // the same as the function above
    private void getCoworkers(ProjectSegment ps, List<String> result){
        // loop through elements of sub-segment
        for(ProjectElement e: ps.getElements()){
            // if e is a Worker -> add to result, but don't add himself to result
            if(e instanceof Worker){
                if(!e.equals(this)) { result.add(e.getName()); }
            }
            // else recursive call in sub-segment
            else { getCoworkers((ProjectSegment) e, result); }
        }
    }

    // prints out all coworkers of Worker on a given Project/Team
    public void printCoworkers(ProjectSegment ps){
        StringBuilder sb = new StringBuilder();
        List<String> names = getCoworkers(ps);

        for(String s : names) { sb.append(s).append(", "); }
        sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);     // delete last ', '

        System.out.println("Coworkers of " + this.getName()
                + " in " + ps.getClass().getSimpleName()
                + " " + ps.getName() + " are: " + sb.toString());
    }
}
