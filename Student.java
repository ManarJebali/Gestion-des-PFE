public class Student {
    private String lastName;
    private String firstName;
    private String id;
    private String field;
    private Project myProject;

    public Student(String lastName, String firstName, String id, String field, Project myProject) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
        this.field = field;
        this.myProject = myProject;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    public String getField() {
        return field;
    }

    public Project getMyProject() {
        return myProject;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setMyProject(Project myProject) {
        this.myProject = myProject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id='" + id + '\'' +
                ", field='" + field + '\'' +
                ", myProject=" + myProject +
                '}';
    }
}
