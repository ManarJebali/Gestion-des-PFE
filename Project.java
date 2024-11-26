import java.util.ArrayList;
import java.util.Date;

public class Project {
    private String title;
    private Date startDate;
    private Date finishDate;
    private ArrayList<Student> students;
    private Professor prof;

    public Project(String title, Date startDate, Date finishDate, Professor prof) {
        this.title = title;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.prof = prof;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Professor getProf() {
        return prof;
    }

    public void setProf(Professor prof) {
        this.prof = prof;
    }



    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", students=" + students.toString()+
                ", prof=" + prof +
                '}';
    }
}
