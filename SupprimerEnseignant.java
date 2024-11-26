import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupprimerEnseignant extends JFrame{
    private JPanel suppProf;
    private JTextField id;
    private JButton supprimerButton;

    private String teacherId;

    public SupprimerEnseignant() {
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherId=id.getText();

                try{
                    databaseConnection c =new databaseConnection();
                    String sqldisplay="SELECT count(*) num FROM `teacher` WHERE id= '"+teacherId+"'";
                    ResultSet rs=c.s.executeQuery(sqldisplay);

                    if(rs.next()) {
                        int rowCount = rs.getInt("num");
                        if (rowCount == 1) {
                            String sql = "DELETE FROM `teacher` WHERE id='" + teacherId + "'";
                            c.s.executeUpdate(sql);
                            JOptionPane.showMessageDialog(supprimerButton, "Enseignant supprimé");
                        } else {
                            JOptionPane.showMessageDialog(supprimerButton, "Cet Enseignant n'existe pas");
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
        SupprimerEnseignant supprimerEnseignant=new SupprimerEnseignant();
        supprimerEnseignant.setContentPane(supprimerEnseignant.suppProf);
        supprimerEnseignant.setTitle("SupprimerEnseignant");
        supprimerEnseignant.setExtendedState(JFrame.MAXIMIZED_BOTH);
        supprimerEnseignant.setVisible(true);
    }

    public static void main(String[] args) {
        SupprimerEnseignant supprimerEnseignant=new SupprimerEnseignant();
        supprimerEnseignant.showFrame();
    }

}
