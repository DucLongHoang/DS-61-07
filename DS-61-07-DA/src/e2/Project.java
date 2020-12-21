package e2;

public class Project extends ProjectSegment {

    public Project(String name){
        super(name);
    }

    @Override
    public void addElement(ProjectElement e) {
        // cannot add a Project under a Project, everything else yes
        if(e instanceof Project) {
            throw new IllegalArgumentException(
                    "Cannot insert object of class: " + e.getClass().getSimpleName()
                            + " under class: " + this.getClass().getSimpleName());
        }
        else super.addElement(e);
    }

    // prints all info about project without parameters
    public void printInfo(){ printInfo(this, ""); }
}
