import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class rechercheParDate extends JFrame{
    private JTable table1;
    private JTextField date;
    private JButton rechercherButton;
    private JPanel mainpanel;

    private String sdate;

    public rechercheParDate(){

        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sdate=date.getText();
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
                    String sql="SELECT * FROM `soutenance` WHERE place='"+sdate+"' ";
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
        rechercheParDate rechercheParDateObject=new rechercheParDate();
        rechercheParDateObject.setContentPane(rechercheParDateObject.mainpanel);
        rechercheParDateObject.setTitle("RechercheparDate");
        rechercheParDateObject.setExtendedState(JFrame.MAXIMIZED_BOTH);
        rechercheParDateObject.setVisible(true);
    }

    public static void main(String[] args) {
        rechercheParDate rechercheParDateObject=new rechercheParDate();
        rechercheParDateObject.showFrame();
    }


}
