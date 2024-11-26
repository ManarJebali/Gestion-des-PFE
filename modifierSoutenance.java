import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class modifierSoutenance extends JFrame{
    private JTextField date;
    private JTextField heure;
    private JComboBox comboBox1;
    private JRadioButton validéeRadioButton;
    private JRadioButton nonValidéeRadioButton;
    private JButton modifierBtn;
    private JComboBox comboBox2;
    private JPanel mainpanel;

    private String splace;
    private String sheure;
    private String sdate;
    private String svalidation;
    private int snote;


    public modifierSoutenance() {
        comboBox1.addItem(1);
        comboBox1.addItem(2);
        comboBox1.addItem(3);
        comboBox1.addItem(4);
        comboBox1.addItem(5);
        comboBox1.addItem(6);
        comboBox1.addItem(7);
        comboBox1.addItem(8);
        comboBox1.addItem(9);
        comboBox1.addItem(10);
        comboBox1.addItem(11);
        comboBox1.addItem(12);
        comboBox1.addItem(13);
        comboBox1.addItem(14);
        comboBox1.addItem(15);
        comboBox1.addItem(16);
        comboBox1.addItem(17);
        comboBox1.addItem(18);
        comboBox1.addItem(19);
        comboBox1.addItem(20);

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

        modifierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sdate=date.getText();
                sheure=heure.getText();
                splace=(String) comboBox2.getSelectedItem();
                snote=(Integer) comboBox1.getSelectedItem();
                String noteString=String.valueOf(snote);
                if (validéeRadioButton.isSelected()){
                    svalidation="validee";
                } else if (nonValidéeRadioButton.isSelected()) {
                    svalidation="non validee";
                }
                try{
                    databaseConnection c =new databaseConnection();
                    String sqldisplay="SELECT count(*) num FROM `soutenance` WHERE Datesoutenance= '"+sdate+"' AND Heure= '"+sheure+"' AND Place= '"+splace+"'";
                    ResultSet rs=c.s.executeQuery(sqldisplay);

                    if(rs.next()) {
                        int rowCount = rs.getInt("num");
                        if (rowCount == 1) {
                            String sql = "UPDATE`soutenance` SET note='"+snote+"' WHERE Datesoutenance='" + date + "' AND Heure= '"+sheure+"' AND Place= '"+splace+"'";
                            c.s.executeUpdate(sql);
                            JOptionPane.showMessageDialog(modifierBtn, "Données modifiées avec succès");
                        } else {
                            JOptionPane.showMessageDialog(modifierBtn, "Cette Soutenance n'existe pas");
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
        modifierSoutenance modifierSoutenanceObject=new modifierSoutenance();
        modifierSoutenanceObject.setContentPane(modifierSoutenanceObject.mainpanel);
        modifierSoutenanceObject.setTitle("ModifierDonneesSoutenance");
        modifierSoutenanceObject.setExtendedState(JFrame.MAXIMIZED_BOTH);
        modifierSoutenanceObject.setVisible(true);
    }

    public static void main(String[] args) {
        modifierSoutenance modifierSoutenanceObject=new modifierSoutenance();
        modifierSoutenanceObject.showFrame();
    }
}
