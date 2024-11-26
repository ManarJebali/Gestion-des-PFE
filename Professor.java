import java.util.ArrayList;

public class Professor {
    private String name;
    private String id;
    private boolean free;
    private ArrayList<Project> projects;

    public Professor(String name, String id, boolean free) {
        this.name = name;
        this.id = id;
        this.free = free;
        projects = new ArrayList<Project>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", free=" + free +
                ", projects=" + projects.toString()+
                '}';
    }
}
