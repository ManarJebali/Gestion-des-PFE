import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class modifierEtd extends JFrame {
    private JTextField prenom;
    private JTextField id;
    private JTextField nom;
    private JButton modifierbtn;
    private JPanel mdfEtd;
    private JComboBox comboBox1;
    private JTextField Email;

    private String sid;
    private String snom;
    private String sprenom;
    private String sEmail;
    private String sspecialite;


    public modifierEtd() {
        comboBox1.addItem("Licence Informatique");
        comboBox1.addItem("Licence electronique");
        comboBox1.addItem("Licence TIC");
        comboBox1.addItem("Mastere recherche");
        comboBox1.addItem("Mastere professionnel");
        comboBox1.addItem("Ingenieure Informatique");
        comboBox1.addItem("Mastere electronique");
        modifierbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sid=id.getText();
                sid=id.getText();
                snom=nom.getText();
                sprenom=prenom.getText();
                sEmail=Email.getText();
                sspecialite=(String) comboBox1.getSelectedItem();

                try{
                    databaseConnection c =new databaseConnection();
                    String sqldisplay="SELECT count(*) num FROM `student` WHERE id= '"+sid+"'";
                    ResultSet rs=c.s.executeQuery(sqldisplay);

                    if(rs.next()) {
                        int rowCount = rs.getInt("num");
                        if (rowCount == 1) {
                            String sql1 = "DELETE FROM `student` WHERE id='" + sid + "'";
                            c.s.executeUpdate(sql1);
                            String sql2="INSERT INTO student (id,Nom,Prénom,Email,Specialite)"+"VALUES('"+sid+"', '"+snom+"', '"
                                    +sprenom+"', '"+sEmail+"', '"+sspecialite+"')";
                            c.s.executeUpdate(sql2);
                            JOptionPane.showMessageDialog(modifierbtn, "Données modifiées avec succès");
                        } else {
                            JOptionPane.showMessageDialog(modifierbtn, "Cet étudiant n'existe pas");
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
        modifierEtd modifierEtudiant=new modifierEtd();
        modifierEtudiant.setContentPane(modifierEtudiant.mdfEtd);
        modifierEtudiant.setTitle("ModifierDonneesEtudiant");
        modifierEtudiant.setExtendedState(JFrame.MAXIMIZED_BOTH);
        modifierEtudiant.setVisible(true);
    }

    public static void main(String[] args) {
        SupprimerEtudiant sp=new SupprimerEtudiant();
        sp.showFrame();
    }
}
