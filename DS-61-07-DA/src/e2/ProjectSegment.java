package e2;

import java.util.LinkedList;
import java.util.List;

/**
 * abstract class for segments of a project = a group of project elements
 */
public abstract class ProjectSegment extends ProjectElement {
    // each segment is made up of elements or sub-segments
    private final List<ProjectElement> elements;

    // constructor
    public ProjectSegment(String name){
        super(name);
        this.elements = new LinkedList<>();
    }

    // getters - segments call sub-parts recursively and adds to result
    @Override
    public double getSalary(Project project){
        double result = 0;
        for(ProjectElement element: elements){
            result += element.getSalary(project);
        }
        return result;
    }
    @Override
    public double getHours(Project project){
        double result = 0;
        for(ProjectElement element: elements){
            result += element.getHours(project);
        }
        return result;
    }
    protected List<ProjectElement> getElements() { return this.elements; }

    @Override
    // prints info of the segment and its sub-parts recursively
    protected void printInfo(Project project, String tab) {
        System.out.println(tab + this.getInfo(project));
        for(ProjectElement element: this.elements){
            String childTab = tab + "\t";
            element.printInfo(project, childTab);
        }
    }

    // method adds ProjectElement element under this ProjectSegment
    protected void addElement(ProjectElement element){
        this.elements.add(element);
    }

}
