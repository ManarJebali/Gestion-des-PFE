import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class rechercheProjet extends JFrame {
    private JTable table1;
    private JTextField titre;
    private JButton rechercherButton;
    private JPanel mainpanel;

    private String projectTitle;

    public rechercheProjet(){

        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectTitle=titre.getText();
                //columns names
                String[] columns={"Titre","Etudiant1","Etudiant2","Encadrant"};

                //Create a table model
                DefaultTableModel model=new DefaultTableModel(null,columns);

                //Create the JTable
                table1= new JTable(model);
                JScrollPane scrollPane=new JScrollPane(table1);
                getContentPane().add(scrollPane);

                try{try{
                    databaseConnection con=new databaseConnection();
                    String sql="SELECT * FROM `soutenance` WHERE Titre='"+projectTitle+"' ";
                    ResultSet rs=con.s.executeQuery(sql);

                    while(rs.next()){
                        Object[] row={
                                rs.getString("Titre"),
                                rs.getString("Etudiant1"),
                                rs.getString("Etudiant2"),
                                rs.getString("Encadrant"),
                        };
                        model.addRow(row);}

                    con.c.close();
                }catch (SQLException se){System.out.println(se);}}

                catch(Exception e1){System.out.println(e1);}


            }
        });}

    public void showFrame(){
        rechercheProjet rechercheProjetObject=new rechercheProjet();
        rechercheProjetObject.setContentPane(rechercheProjetObject.mainpanel);
        rechercheProjetObject.setTitle("RechercheProjet");
        rechercheProjetObject.setExtendedState(JFrame.MAXIMIZED_BOTH);
        rechercheProjetObject.setVisible(true);
    }

    public static void main(String[] args) {
        rechercheProjet rechercheProjetObject=new rechercheProjet();
        rechercheProjetObject.showFrame();
    }


}
