import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class studentpanel extends JFrame {
    private JPanel mainpanel;
    private JLabel title;
    private JTextField id;
    private JTextField nom;
    private JTextField prenom;
    private JButton btn;
    private JTextField Email;
    private JComboBox comboBox1;
    private JTextField specialite;

    private String sid;
    private String snom;
    private String sprenom;
    private String sEmail;
    private String sspecialite;


    public studentpanel() {
        comboBox1.addItem("Licence Informatique");
        comboBox1.addItem("Licence electronique");
        comboBox1.addItem("Licence TIC");
        comboBox1.addItem("Mastere recherche");
        comboBox1.addItem("Mastere professionnel");
        comboBox1.addItem("Ingenieure Informatique");
        comboBox1.addItem("Mastere electronique");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sid=id.getText();
                snom=nom.getText();
                sprenom=prenom.getText();
                sEmail=Email.getText();
                sspecialite=(String) comboBox1.getSelectedItem();
                try{
                    if(sid.isEmpty()||snom.isEmpty()||sprenom.isEmpty()||sEmail.isEmpty()||sspecialite.isEmpty()){
                        JOptionPane.showMessageDialog(btn,"Veuillez entrer tous les champs");
                    } else if (sEmail.indexOf('@')==-1) {
                        JOptionPane.showMessageDialog(btn,"Veuillez verifier l'email");
                    } else{
                        databaseConnection c =new databaseConnection();
                        String sql="INSERT INTO student (id,Nom,Prénom,Email,Specialite)"+"VALUES('"+sid+"', '"+snom+"', '"
                                +sprenom+"', '"+sEmail+"', '"+sspecialite+"')";
                        c.s.executeUpdate(sql);
                        JOptionPane.showMessageDialog(btn, "Etudiant ajouté");
                        c.s.close();
                    }
                }catch(Exception ee) {
                    System.out.println(ee);
                }

             }
        });
    }

    public void showFrame(){
        studentpanel sp=new studentpanel();
        sp.setContentPane(sp.mainpanel);
        sp.setTitle("AjouterEtudiant");
        sp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        sp.setVisible(true);
    }

    public static void main(String[] args) {
        studentpanel sp=new studentpanel();
        sp.showFrame();
    }

}


