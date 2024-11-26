import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class displayTeachers extends JFrame {
    private JPanel mainpanel;
    private JTable table1;

    public displayTeachers(){
        setTitle("Affichage des enseignants");
        //setContentPane(mainpanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        display();
    }

    public void display(){
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
            String sql="SELECT * FROM `teacher`";
            ResultSet rs=con.s.executeQuery(sql);

            while(rs.next()){
                Object[] row={
                        rs.getString("id"),
                        rs.getString("Nom"),
                        rs.getString("Prénom"),
                };
                model.addRow(row);}

            con.c.close();}catch (SQLException se){System.out.println(se);}

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(displayTeachers::new);
    }

}

