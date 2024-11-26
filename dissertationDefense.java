import java.util.Date;

public class dissertationDefense {
    private Date date;
    private String hour;
    private String place;
    private float grade;
    private boolean validated;

    public dissertationDefense(Date date, String hour, String place, float grade, boolean validated) {
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.grade = grade;
        this.validated = validated;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
