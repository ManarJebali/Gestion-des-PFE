import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RechercheEtdId extends JFrame{
    private JTextField id;
    private JButton rechercherButton;
    private JPanel rechpanel;
    private JTable table1;
    private JPanel tablepanel;

    private String sid;


   public RechercheEtdId(){
       tablepanel=new JPanel();
       //rechpanel=new JPanel();
        //columns names
        String[] columns={"id","Nom","Prénom","Email","Spécialité"};

        //Create a table model
        DefaultTableModel model=new DefaultTableModel(null,columns);
       table1= new JTable(model);

       model.setColumnIdentifiers(columns);
       JScrollPane scrollPane=new JScrollPane(table1);
       getContentPane().add(tablepanel,"Center");


        tablepanel.add(scrollPane);

       tablepanel.setVisible(true);

        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sid=id.getText();
                /*//columns names
                String[] columns={"id","Nom","Prénom","Email","Spécialité"};

                //Create a table model
                DefaultTableModel model=new DefaultTableModel(null,columns);

                //Create the JTable
                table1= new JTable(model);
                JScrollPane scrollPane=new JScrollPane(table1);
                getContentPane().add(tablepanel);*/

               try{try{
                    databaseConnection con=new databaseConnection();
                    String sql="SELECT * FROM `student` WHERE id='"+sid+"' ";
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

                    con.s.close();
                }catch (SQLException se){System.out.println(se);}}

                catch(Exception e1){System.out.println(e1);}


            }
        });
    }

    public void showFrame(){
        RechercheEtdId rechercheEtdId=new RechercheEtdId();
        rechercheEtdId.setContentPane(rechercheEtdId.rechpanel);
        rechercheEtdId.setTitle("RechercheparId");
        rechercheEtdId.setExtendedState(JFrame.MAXIMIZED_BOTH);
        rechercheEtdId.setVisible(true);
    }

    public static void main(String[] args) {
        RechercheEtdId rechercheEtdId=new RechercheEtdId();
        rechercheEtdId.showFrame();
    }

}

