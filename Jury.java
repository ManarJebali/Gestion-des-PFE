public class Jury {
    private Professor president;
    private Professor rapporteur;
    private Professor examiner;

    public Jury(Professor president, Professor rapporteur, Professor examiner) {
        this.president = president;
        this.rapporteur = rapporteur;
        this.examiner = examiner;
    }

    public Professor getPresident() {
        return president;
    }

    public void setPresident(Professor president) {
        this.president = president;
    }

    public Professor getRapporteur() {
        return rapporteur;
    }

    public void setRapporteur(Professor rapporteur) {
        this.rapporteur = rapporteur;
    }

    public Professor getExaminer() {
        return examiner;
    }

    public void setExaminer(Professor examiner) {
        this.examiner = examiner;
    }
}
