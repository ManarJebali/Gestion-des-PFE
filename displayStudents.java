import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class displayStudents extends JFrame {
    private JTable table1;
    private JPanel mainpanel;

    public displayStudents(){
        setTitle("AffichagedesEtudiants");
        //setContentPane(mainpanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
       display();
}

public void display(){
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
            String sql="SELECT * FROM `student`";
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

            con.c.close();}catch (SQLException se){System.out.println(se);}

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(displayStudents::new);
    }

}
