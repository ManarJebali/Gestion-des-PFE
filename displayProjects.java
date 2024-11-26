import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class displayProjects extends JFrame{

    private JTable table1;
    private JPanel panel1;
    private JPanel mainpanel;

    public displayProjects(){
        setTitle("AffichagedesProjets");
        //setContentPane(mainpanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        display();
    }

    public void display(){
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
            String sql="SELECT * FROM `projet`";
            ResultSet rs=con.s.executeQuery(sql);

            while(rs.next()){
                Object[] row={
                        rs.getString("Titre"),
                        rs.getString("Etudiant1"),
                        rs.getString("Etudiant2"),
                        rs.getString("Encadrant"),
                };
                model.addRow(row);}

            con.c.close();}catch (SQLException se){System.out.println(se);}

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(displayProjects::new);
    }

}


