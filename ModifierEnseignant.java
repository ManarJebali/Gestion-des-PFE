import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifierEnseignant extends JFrame{
    private JButton modifierButton;
    private JTextField nom;
    private JTextField prenom;
    private JTextField id;
    private JPanel mdfProfPanel;

    private String teacherId;
    private String pnom;
    private String pprenom;


    public ModifierEnseignant() {

        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherId=id.getText();
                pnom=nom.getText();
                pprenom=prenom.getText();
                try{
                    databaseConnection c =new databaseConnection();
                    String sqldisplay="SELECT count(*) num FROM `teacher` WHERE id= '"+teacherId+"'";
                    ResultSet rs=c.s.executeQuery(sqldisplay);

                    if(rs.next()) {
                        int rowCount = rs.getInt("num");
                        if (rowCount == 1) {
                            String sql1 = "DELETE FROM `teacher` WHERE id='" + teacherId + "'";
                            c.s.executeUpdate(sql1);
                            String sql2="INSERT INTO teacher (id,Nom,Prénom)"+"VALUES('"+teacherId+"', '"+pnom+"', '"
                                    +pprenom+"')";
                            c.s.executeUpdate(sql2);
                            JOptionPane.showMessageDialog(modifierButton, "Données modifiées avec succès");
                        } else {
                            JOptionPane.showMessageDialog(modifierButton, "Cet Enseignant n'existe pas");
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
        ModifierEnseignant modifierEnseignant=new ModifierEnseignant();
        modifierEnseignant.setContentPane(modifierEnseignant.mdfProfPanel);
        modifierEnseignant.setTitle("ModifierDonneesEnseignant");
        modifierEnseignant.setExtendedState(JFrame.MAXIMIZED_BOTH);
        modifierEnseignant.setVisible(true);
    }

    public static void main(String[] args) {
        studentpanel sp=new studentpanel();
        sp.showFrame();
    }
}
