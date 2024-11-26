import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class homePage extends  JFrame {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        JPanel contentpanel=new JPanel(new GridBagLayout());
        contentpanel.setBackground(new Color(206, 232, 240));

        //create a menu bar
        JMenuBar menu=new JMenuBar();

        //create menus
        JMenu studentMenu=new JMenu("Etudiants");
        JMenu teacherMenu=new JMenu("Enseignants");
        JMenu projectMenu=new JMenu("Projets");
        JMenu soutenanceMenu=new JMenu("Soutenances");

        //create menu items
        JMenuItem addStudentMenuItem = new JMenuItem("Ajouter un Etudiant");
        JMenuItem deleteStudentMenuItem = new JMenuItem("Supprimer un Etudiant");
        JMenuItem modifyStudentMenuItem = new JMenuItem("Modifier les données d'un Etudiant");
        JMenuItem searchStudentByIdMenuItem = new JMenuItem("Rechercher un Etudiant");
        JMenuItem searchStudentBySectionMenuItem = new JMenuItem("Rechercher les Etudiants d'une section");
        JMenuItem searchStudentsMenuItem=new JMenuItem("Recherche des Etudiant");
        JMenuItem displayStudentsMenuItem = new JMenuItem("Afficher les Etudiants");

        JMenuItem addTeacherMenuItem = new JMenuItem("Ajouter un Prof");
        JMenuItem deleteTeacherMenuItem = new JMenuItem("Supprimer un Prof");
        JMenuItem modifyTeacherMenuItem = new JMenuItem("Modifier les données d'un Enseignant");
        JMenuItem searchTeacherMenuItem = new JMenuItem("Rechercher un Prof");
        JMenuItem searchTeachersMenuItem = new JMenuItem("Rechercher des Profs");
        JMenuItem displayTeachersMenuItem = new JMenuItem("Afficher les Profs");

        JMenuItem addProjectMenuItem = new JMenuItem("Ajouter un Projet");
        JMenuItem deleteProjectMenuItem = new JMenuItem("Supprimer un Projet");
        JMenuItem modifyProjectMenuItem = new JMenuItem("Modifier les données d'un Projet");
        JMenuItem searchProjectMenuItem = new JMenuItem("Rechercher un Projet");
        JMenuItem searchProjectsMenuItem = new JMenuItem("Recherche des Projets");
        JMenuItem displayProjectsMenuItem = new JMenuItem("Afficher les Projets");
        JMenuItem addSoutenanceMenuItem = new JMenuItem("Ajouter une soutenance");
        JMenuItem validateSoutenanceMenuItem = new JMenuItem("Valider une soutenance");
        JMenuItem deleteSoutenanceMenuItem = new JMenuItem("Supprimer une soutenance");
        JMenuItem modifySoutenanceMenuItem = new JMenuItem("Modifier la note");
        JMenuItem searchByProfessorMenuItem = new JMenuItem("Recherche par Enseignat");
        JMenuItem rechercheParDateMenuItem= new JMenuItem("Recherche par Date");
        JMenuItem searchSoutenanceMenuItem=new JMenuItem("Recherche des Soutenance");
        JMenuItem rechercheParSalleMenuItem = new JMenuItem("Recherche par Salle");

        JMenuItem displaySoutenancesMenuItem = new JMenuItem("Afficher les soutenances");

        //add menu items to the menus
        studentMenu.add(addStudentMenuItem);
        studentMenu.add(deleteStudentMenuItem);
        studentMenu.add(modifyStudentMenuItem);
        studentMenu.add(searchStudentByIdMenuItem);
        studentMenu.add(searchStudentsMenuItem);
        //studentMenu.add(searchStudentBySectionMenuItem);
        studentMenu.add(displayStudentsMenuItem);

        teacherMenu.add(addTeacherMenuItem);
        teacherMenu.add(modifyTeacherMenuItem);
        teacherMenu.add(searchTeacherMenuItem);
        teacherMenu.add(searchTeachersMenuItem);
        teacherMenu.add(displayTeachersMenuItem);

        projectMenu.add(addProjectMenuItem);
        projectMenu.add(deleteProjectMenuItem);
        projectMenu.add(modifyProjectMenuItem);
        projectMenu.add(searchProjectsMenuItem);
        projectMenu.add(displayProjectsMenuItem);

        soutenanceMenu.add(addSoutenanceMenuItem);
        soutenanceMenu.add(validateSoutenanceMenuItem);
        soutenanceMenu.add(deleteSoutenanceMenuItem);
        soutenanceMenu.add(modifySoutenanceMenuItem);
        soutenanceMenu.add(searchSoutenanceMenuItem);
        //soutenanceMenu.add(searchByProfessorMenuItem);
        //soutenanceMenu.add(rechercheParDateMenuItem);
        //soutenanceMenu.add(rechercheParSalleMenuItem);
        soutenanceMenu.add(displaySoutenancesMenuItem);

        //add menus to the menu bar
        menu.add(studentMenu);
        menu.add(teacherMenu);
        menu.add(projectMenu);
        menu.add(soutenanceMenu);

        //set the menu bar to the frame
        frame.setJMenuBar(menu);

        //set frame properties
        frame.setContentPane(contentpanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Create a JLabel for the title
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: navy; font-size: 40px; font-weight: bold;'>Gestion des PFE</div></html>");
       titleLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size and style

        // Add the title label to the content panel
        // Add the title label to the content panel with GridBagLayout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        contentpanel.add(titleLabel, gbc);


        addStudentMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    studentpanel sp=new studentpanel();
                    sp.showFrame();
            }catch(Exception ee){
                System.out.println(ee);
                }
            }
        });

        modifyStudentMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    modifierEtd modifierEtdObject=new modifierEtd();
                    modifierEtdObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        searchStudentByIdMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    EtudiantRechercheJFrame rechercheEtdId=new EtudiantRechercheJFrame();
                    rechercheEtdId.setVisible(true);
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        searchStudentBySectionMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    RechEtdsection rechercheEtdSection=new RechEtdsection();
                    rechercheEtdSection.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        searchStudentsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    rechercheIntelligenteEtudiant rechercheEtd=new rechercheIntelligenteEtudiant();
                    //rechercheEtdSection.showFrame();
                    rechercheEtd.setVisible(true);
                    rechercheEtd.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        deleteStudentMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SupprimerEtudiant supprimerEtudiantObject=new SupprimerEtudiant();
                    supprimerEtudiantObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        displayStudentsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    displayStudents ds=new displayStudents();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        addTeacherMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    AjouterProf ap=new AjouterProf();
                    ap.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        deleteTeacherMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SupprimerEnseignant supprimerEnseignantObject=new SupprimerEnseignant();
                    supprimerEnseignantObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        modifyTeacherMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ModifierEnseignant modifierEnseignant=new ModifierEnseignant();
                    modifierEnseignant.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        searchTeacherMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    RechercheProfJFrame rechercheProf=new RechercheProfJFrame();
                    //rechercheProf.showFrame();
                    rechercheProf.setVisible(true);
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        searchTeachersMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    rechercheIntelligenteEnseignant rechercheProfs=new rechercheIntelligenteEnseignant();
                    //rechercheProf.showFrame();
                    rechercheProfs.setVisible(true);
                    rechercheProfs.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        displayTeachersMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    displayTeachers dt=new displayTeachers();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        addProjectMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    AjouterProjet ap=new AjouterProjet();
                    ap.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        deleteProjectMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SupprimerProjet supprimerProjetObject=new SupprimerProjet();
                    supprimerProjetObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        modifyProjectMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    modifierProjet modifProjetObject=new modifierProjet();
                    modifProjetObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        searchProjectsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    rechercheIntelligenteProjet rechercheProjetObject=new rechercheIntelligenteProjet();
                    rechercheProjetObject.setVisible(true);
                    rechercheProjetObject.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        displayProjectsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    displayProjects dp=new displayProjects();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        addSoutenanceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    AjouterSoutenance ap=new AjouterSoutenance();
                    ap.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });
        validateSoutenanceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    validerSoutenance ap=new validerSoutenance();
                    ap.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        deleteSoutenanceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SupprimerSoutenance supprimerSoutenanceObject=new SupprimerSoutenance();
                    supprimerSoutenanceObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        modifySoutenanceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    modifierSoutenance modifierSoutenanceObject=new modifierSoutenance();
                    modifierSoutenanceObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        searchSoutenanceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    rechercheIntelligenteSoutenance rechercheSoutenance=new rechercheIntelligenteSoutenance();
                    rechercheSoutenance.setVisible(true);
                    rechercheSoutenance.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        rechercheParDateMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    rechercheParDate rechercheParDateObject=new rechercheParDate();
                    rechercheParDateObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        searchByProfessorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    rechercheParProf rechercheParProfObject=new rechercheParProf();
                    rechercheParProfObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        rechercheParSalleMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    rechercheParSalle rechercheParSalleObject=new rechercheParSalle();
                    rechercheParSalleObject.showFrame();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        displaySoutenancesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    displaySoutenances ds=new displaySoutenances();
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });


}
    }