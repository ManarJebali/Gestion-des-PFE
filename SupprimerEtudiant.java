import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupprimerEtudiant extends JFrame{
    private JTextField id;
    private JButton supprimerButton;
    private JPanel supppanel;

    private String sid;

    public SupprimerEtudiant() {
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sid=id.getText();

                try{
                        databaseConnection c =new databaseConnection();
                        String sqldisplay="SELECT count(*) num FROM `student` WHERE id= '"+sid+"'";
                        ResultSet rs=c.s.executeQuery(sqldisplay);

                        if(rs.next()) {
                            int rowCount = rs.getInt("num");
                            if (rowCount == 1) {
                                String sql = "DELETE FROM `student` WHERE id='" + sid + "'";
                                c.s.executeUpdate(sql);
                                JOptionPane.showMessageDialog(supprimerButton, "Etudiant supprimé");
                            } else {
                                JOptionPane.showMessageDialog(supprimerButton, "Cet étudiant n'existe pas");
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
        SupprimerEtudiant supprimerEtudiant=new SupprimerEtudiant();
        supprimerEtudiant.setContentPane(supprimerEtudiant.supppanel);
        supprimerEtudiant.setTitle("SupprimerEtudiant");
        supprimerEtudiant.setExtendedState(JFrame.MAXIMIZED_BOTH);
        supprimerEtudiant.setVisible(true);
    }

    public static void main(String[] args) {
        SupprimerEtudiant sp=new SupprimerEtudiant();
        sp.showFrame();
        }

}



