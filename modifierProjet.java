import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class modifierProjet extends JFrame{
    private JTextField etudiant2;
    private JTextField etudiant1;
    private JTextField encadrant;
    private JButton modifierBtn;
    private JTextField titre;
    private JPanel mainpanel;

    private String petudiant1;
    private String petudiant2;
    private String pencadrant;
    private String projectTitle;


    public modifierProjet() {

        modifierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectTitle=titre.getText();
                petudiant1=etudiant1.getText();
                petudiant2=etudiant2.getText();
                pencadrant=encadrant.getText();
                try{
                    databaseConnection c =new databaseConnection();
                    String sqldisplay="SELECT count(*) num FROM `projet` WHERE Titre= '"+projectTitle+"'";
                    ResultSet rs=c.s.executeQuery(sqldisplay);

                    if(rs.next()) {
                        int rowCount = rs.getInt("num");
                        if (rowCount == 1) {
                            String sql1 = "DELETE FROM `projet` WHERE Titre='" + projectTitle + "'";
                            c.s.executeUpdate(sql1);
                            String sql2="INSERT INTO student (Titre,Etudiant1,Etudiant2,Encadrant)"+"VALUES('"+projectTitle+"','"+petudiant1+"', '"+petudiant2+"', '"
                                    +pencadrant+"')";
                            c.s.executeUpdate(sql2);
                            JOptionPane.showMessageDialog(modifierBtn, "Données modifiées avec succès");
                        } else {
                            JOptionPane.showMessageDialog(modifierBtn, "Ce projet n'existe pas");
                        }
                    }
                }catch(SQLException se) {
                    System.out.println(se);
                }
                catch(Exception ee) {
                    System.out.println(ee);
                }

            }
        });
    }


    public void showFrame(){
        modifierProjet modifierProjetObject=new modifierProjet();
        modifierProjetObject.setContentPane(modifierProjetObject.mainpanel);
        modifierProjetObject.setTitle("ModifierDonneesProjet");
        modifierProjetObject.setExtendedState(JFrame.MAXIMIZED_BOTH);
        modifierProjetObject.setVisible(true);
    }

    public static void main(String[] args) {
        studentpanel sp=new studentpanel();
        sp.showFrame();
    }
}
