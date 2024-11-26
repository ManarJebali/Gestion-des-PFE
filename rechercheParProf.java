import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class rechercheParProf extends JFrame{
    private JTable table1;
    private JButton rechercherButton;
    private JTextField id;
    private JPanel mainpanel;

    private String pid;

    public rechercheParProf(){

        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pid=id.getText();
                //columns names
                String[] columns={"date","heure","place","president","rapporteur","examinateur","note","validation"};

                //Create a table model
                DefaultTableModel model=new DefaultTableModel(null,columns);

                //Create the JTable
                table1= new JTable(model);
                JScrollPane scrollPane=new JScrollPane(table1);
                getContentPane().add(scrollPane);

                try{try{
                    databaseConnection con=new databaseConnection();
                    String sql="SELECT * FROM `soutenance` WHERE place='"+pid+"' ";
                    ResultSet rs=con.s.executeQuery(sql);

                    while(rs.next()){
                        Object[] row={
                                rs.getString("date"),
                                rs.getString("heure"),
                                rs.getString("salle"),
                                rs.getString("president"),
                                rs.getString("rapporteur"),
                                rs.getString("examinateur"),
                                rs.getString("note"),
                                rs.getString("validation")
                        };
                        model.addRow(row);}

                    con.c.close();
                }catch (SQLException se){System.out.println(se);}}

                catch(Exception e1){System.out.println(e1);}


            }
        });}

    public void showFrame(){
        rechercheParProf rechercheParProfObject=new rechercheParProf();
        rechercheParProfObject.setContentPane(rechercheParProfObject.mainpanel);
        rechercheParProfObject.setTitle("RechercheparEncadreur");
        rechercheParProfObject.setExtendedState(JFrame.MAXIMIZED_BOTH);
        rechercheParProfObject.setVisible(true);
    }

    public static void main(String[] args) {
        rechercheParSalle rechercheParSalleObject=new rechercheParSalle();
        rechercheParSalleObject.showFrame();
    }


}
