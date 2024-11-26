import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RechEtdsection extends JFrame{
    private JPanel mainpanel;
    private JButton rechercherButton;
    private JTable table1;
    private JComboBox comboBox1;

    private String sspecialite;

    public RechEtdsection(){
        comboBox1.addItem("Licence Informatique");
        comboBox1.addItem("Licence electronique");
        comboBox1.addItem("Licence TIC");
        comboBox1.addItem("Mastere recherche");
        comboBox1.addItem("Mastere professionnel");
        comboBox1.addItem("Ingenieure Informatique");
        comboBox1.addItem("Mastere electronique");



        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sspecialite=(String) comboBox1.getSelectedItem();
                //columns names
                String[] columns={"id","Nom","Prénom","Email","Spécialité"};

                //Create a table model
                DefaultTableModel model=new DefaultTableModel(null,columns);

                //Create the JTable
                table1= new JTable(model);
                JScrollPane scrollPane=new JScrollPane(table1);
                getContentPane().add(scrollPane);

                try{try{
                    databaseConnection con=new databaseConnection();
                    String sql="SELECT * FROM `student` WHERE specialite='"+sspecialite+"' ";
                    ResultSet rs=con.s.executeQuery(sql);

                    while(rs.next()){
                        Object[] row={
                                rs.getString("id"),
                                rs.getString("Nom"),
                                rs.getString("Prénom"),
                                rs.getString("Email"),
                                rs.getString("Specialite")
                        };
                        model.addRow(row);}

                    con.c.close();
                }catch (SQLException se){System.out.println(se);}}

                catch(Exception e1){System.out.println(e1);}


            }
        });
    }



    public void showFrame(){
        RechEtdsection rechEtdsection=new RechEtdsection();
        rechEtdsection.setContentPane(rechEtdsection.mainpanel);
        rechEtdsection.setTitle("RechercheparSpecialite");
        rechEtdsection.setExtendedState(JFrame.MAXIMIZED_BOTH);
        rechEtdsection.setVisible(true);
    }

    public static void main(String[] args) {
       RechEtdsection rechEtdsection=new RechEtdsection();
        rechEtdsection.showFrame();
    }

}



