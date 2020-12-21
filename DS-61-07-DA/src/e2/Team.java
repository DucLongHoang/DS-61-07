package e2;

public class Team extends ProjectSegment {

    public Team(String name){
        super(name);
    }

    @Override
    public void addElement(ProjectElement e) {
        // can add only Team/Worker under
        if(e instanceof Team || e instanceof Worker) { super.addElement(e); }
        else throw new IllegalArgumentException(
                "Cannot insert object of class: " + e.getClass().getSimpleName()
                        + " under class: " + this.getClass().getSimpleName());
    }
}
