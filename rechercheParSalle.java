import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class rechercheParSalle extends JFrame {
    private JComboBox comboBox2;
    private JButton rechercherButton;
    private JTable table1;
    private JPanel mainpanel;

    private String salle;

    public rechercheParSalle(){

        comboBox2.addItem("C01");
        comboBox2.addItem("C11");
        comboBox2.addItem("AmphiB");
        comboBox2.addItem("AmphiK");
        comboBox2.addItem("A21");
        comboBox2.addItem("A22");
        comboBox2.addItem("A23");
        comboBox2.addItem("A24");
        comboBox2.addItem("A25");
        comboBox2.addItem("A26");
        comboBox2.addItem("A31");
        comboBox2.addItem("B31");

        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salle=(String) comboBox2.getSelectedItem();
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
                    String sql="SELECT * FROM `soutenance` WHERE place='"+salle+"' ";
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
        rechercheParSalle rechercheParSalleObject=new rechercheParSalle();
        rechercheParSalleObject.setContentPane(rechercheParSalleObject.mainpanel);
        rechercheParSalleObject.setTitle("RechercheparSalle");
        rechercheParSalleObject.setExtendedState(JFrame.MAXIMIZED_BOTH);
        rechercheParSalleObject.setVisible(true);
    }

    public static void main(String[] args) {
        rechercheParSalle rechercheParSalleObject=new rechercheParSalle();
        rechercheParSalleObject.showFrame();
    }


}
