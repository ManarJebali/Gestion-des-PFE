import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupprimerSoutenance extends JFrame{
    private JButton supprimerBtn;
    private JTextField heure;
    private JTextField date;
    private JPanel SuppSoutenance;
    private JComboBox comboBox2;

    private String sdate;
    private String sheure;
    private String splace;

    public SupprimerSoutenance() {

        comboBox2.addItem("C01");
        comboBox2.addItem("C11");
        comboBox2.addItem("amphiB");
        comboBox2.addItem("amphiK");
        comboBox2.addItem("A21");
        comboBox2.addItem("A22");
        comboBox2.addItem("A23");
        comboBox2.addItem("A24");
        comboBox2.addItem("A25");
        comboBox2.addItem("A26");
        comboBox2.addItem("A31");
        comboBox2.addItem("B31");

        supprimerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sdate=date.getText();
                sheure=heure.getText();
                splace=(String) comboBox2.getSelectedItem();


                try{
                    databaseConnection c =new databaseConnection();
                    String sqldisplay="SELECT count(*) num FROM `soutenance` WHERE Datesoutenance= '"+sdate+"' AND Heure= '"+sheure+"' AND Place= '"+splace+"'";
                    ResultSet rs=c.s.executeQuery(sqldisplay);

                    if(rs.next()) {
                        int rowCount = rs.getInt("num");
                        if (rowCount == 1) {
                            String sql = "DELETE FROM `soutenance` WHERE Datesoutenance='" + date + "' AND Heure= '"+sheure+"' AND Place= '"+splace+"'";
                            c.s.executeUpdate(sql);
                            JOptionPane.showMessageDialog(supprimerBtn, "Soutenance supprimée");
                        } else {
                            JOptionPane.showMessageDialog(supprimerBtn, "Cette Soutenance n'existe pas");
                        }
                    }
                }catch(SQLException se) {
                    System.out.println(se);
                }
                catch(Exception ee) {
                    System.out.println(ee);
                }

            }
        });
    }

    public void showFrame(){
        SupprimerSoutenance supprimerSoutenance=new SupprimerSoutenance();
        supprimerSoutenance.setContentPane(supprimerSoutenance.SuppSoutenance);
        supprimerSoutenance.setTitle("SupprimerSoutenance");
        supprimerSoutenance.setExtendedState(JFrame.MAXIMIZED_BOTH);
        supprimerSoutenance.setVisible(true);
    }

    public static void main(String[] args) {
        SupprimerSoutenance supprimerSoutenance=new SupprimerSoutenance();
        supprimerSoutenance.showFrame();
    }

}
