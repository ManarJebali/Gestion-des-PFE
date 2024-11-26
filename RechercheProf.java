import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RechercheProf extends JFrame{
    private JButton rechercherButton;
    private JTextField id;
    private JTable table1;
    private JPanel mainpanel;

    private String pid;

    public RechercheProf(){

        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pid=id.getText();
                //columns names
                String[] columns={"id","Nom","Prénom"};

                //Create a table model
                DefaultTableModel model=new DefaultTableModel(null,columns);

                //Create the JTable
                table1= new JTable(model);
                JScrollPane scrollPane=new JScrollPane(table1);
                getContentPane().add(scrollPane);

                try{try{
                    databaseConnection con=new databaseConnection();
                    String sql="SELECT * FROM `teacher` WHERE id='"+pid+"' ";
                    ResultSet rs=con.s.executeQuery(sql);

                    while(rs.next()){
                        Object[] row={
                                rs.getString("id"),
                                rs.getString("Nom"),
                                rs.getString("Prénom"),
                        };
                        model.addRow(row);}

                    con.c.close();
                }catch (SQLException se){System.out.println(se);}}

                catch(Exception e1){System.out.println(e1);}


            }
        });
    }

    public void showFrame(){
        RechercheProf rechercheProf=new RechercheProf();
        rechercheProf.setContentPane(rechercheProf.mainpanel);
        rechercheProf.setTitle("RechercheEnseignat");
        rechercheProf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        rechercheProf.setVisible(true);
    }

    public static void main(String[] args) {
        RechercheProf rechercheProf=new RechercheProf();
        rechercheProf.showFrame();
    }

}

