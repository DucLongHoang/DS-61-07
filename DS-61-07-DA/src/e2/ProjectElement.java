package e2;

import java.util.HashSet;

/**
 * abstract class for elements of a project
 */
public abstract class ProjectElement {
    // attribute - unique name
    private final String name;
    // store already used names
    private static final HashSet<String> names = new HashSet<>();

    // constructor
    public ProjectElement(String name){
        // check if name already used
        if(!ProjectElement.names.contains(name)) {
            this.name = name;
            names.add(name);
        }
        else throw new IllegalArgumentException("Element with this name already exists!!!");
    }

    // getters for variables
    public String getName(){ return this.name; }
    public abstract double getHours(Project project);
    public abstract double getSalary(Project project);

    // getting all info about object in one string
    protected String getInfo(Project project){
        return this.getClass().getSimpleName() + " " + getName() + ": "
                + getHours(project) + " hours, " + getSalary(project) + " $";
    }

    // just to be used by ProjectSegment class for correct indentation
    protected void printInfo(Project project, String tab){
        System.out.println(tab + getInfo(project));
    }

    // calls method above but with one less parameter
    protected void printInfo(Project project){ printInfo(project, ""); }

}
