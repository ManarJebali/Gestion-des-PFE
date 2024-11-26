import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupprimerProjet extends JFrame{
    private JTextField titre;
    private JButton suppBtn;
    private JPanel SuppProjPanel;

    private String projectTitle;

    public SupprimerProjet() {
        suppBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectTitle=titre.getText();

                try{
                    databaseConnection c =new databaseConnection();
                    String sqldisplay="SELECT count(*) num FROM `projet` WHERE Titre= '"+projectTitle+"'";
                    ResultSet rs=c.s.executeQuery(sqldisplay);

                    if(rs.next()) {
                        int rowCount = rs.getInt("num");
                        if (rowCount == 1) {
                            String sql = "DELETE FROM `projet` WHERE Titre='" + projectTitle + "'";
                            c.s.executeUpdate(sql);
                            JOptionPane.showMessageDialog(suppBtn, "Projet supprimé");
                        } else {
                            JOptionPane.showMessageDialog(suppBtn, "Ce projet n'existe pas");
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
        SupprimerProjet supprimerProjetObject=new SupprimerProjet();
        supprimerProjetObject.setContentPane(supprimerProjetObject.SuppProjPanel);
        supprimerProjetObject.setTitle("SupprimerProjet");
        supprimerProjetObject.setExtendedState(JFrame.MAXIMIZED_BOTH);
        supprimerProjetObject.setVisible(true);
    }

    public static void main(String[] args) {
        SupprimerProjet sp=new SupprimerProjet();
        sp.showFrame();
    }

}
