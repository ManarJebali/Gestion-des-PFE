import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterProf extends JFrame{
    private JPanel ajtprof;
    private JTextField nom;
    private JTextField prenom;
    private JButton ajtbtn;
    private JTextField id;

    private String pid;
    private String pnom;
    private String pprenom;

    public AjouterProf() {

        ajtbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pid=id.getText();
                pnom=nom.getText();
                pprenom=prenom.getText();

                try{
                    if(pid.isEmpty()||pnom.isEmpty()||pprenom.isEmpty()){
                        JOptionPane.showMessageDialog(ajtbtn,"Veuillez entrer tous les champs");
                    } else{
                        databaseConnection c =new databaseConnection();
                        String sql="INSERT INTO teacher (id,Nom,Prénom)"+"VALUES('"+pid+"', '"+pnom+"', '"
                                +pprenom+"')";
                        c.s.executeUpdate(sql);
                        JOptionPane.showMessageDialog(ajtbtn, "Enseignant ajouté");
                    }
                }catch(Exception ee) {
                    System.out.println(ee);
                }

            }
        });
    }

    public void showFrame(){
        AjouterProf ap=new AjouterProf();
        ap.setContentPane(ap.ajtprof);
        ap.setTitle("AjouterEnseignant");
        ap.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ap.setVisible(true);
    }

    public static void main(String[] args) {
        AjouterProf ap=new AjouterProf();
        ap.showFrame();
    }

}
