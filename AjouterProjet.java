import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterProjet extends JFrame{
    private JTextField etudiant2;
    private JTextField etudiant1;
    private JTextField encadrant;
    private JButton ajouterBtn;
    private JPanel mainpanel;
    private JTextField titre;

    private String petudiant1;
    private String petudiant2;
    private String pencadrant;
    private String pTitle;


    public AjouterProjet() {

        ajouterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pTitle=titre.getText();
                petudiant1=etudiant1.getText();
                petudiant2=etudiant2.getText();
                pencadrant=encadrant.getText();
                try{
                    if(pTitle.isEmpty()||petudiant1.isEmpty()||pencadrant.isEmpty()) {
                        JOptionPane.showMessageDialog(ajouterBtn, "Veuillez entrer tous les champs");
                    }else{
                        databaseConnection c =new databaseConnection();
                        String sql="INSERT INTO student (titre,etudiant1,etudiant2,encadrant)"+"VALUES('"+pTitle+"','"+petudiant1+"', '"+petudiant2+"', '"
                                +pencadrant+"')";
                        c.s.executeUpdate(sql);
                        JOptionPane.showMessageDialog(ajouterBtn, "Projet ajouté");
                    }
                }catch(Exception ee) {
                    System.out.println(ee);
                }

            }
        });
    }

    public void showFrame(){
        AjouterProjet ap=new AjouterProjet();
        ap.setContentPane(ap.mainpanel);
        ap.setTitle("AjouterProjet");
        ap.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ap.setVisible(true);
    }

    public static void main(String[] args) {
        AjouterProjet ap=new AjouterProjet();
        ap.showFrame();
    }

}
