import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class validerSoutenance extends JFrame{
    private JPanel mainpanel;
    private JTextField date;
    private JTextField heure;
    private JComboBox comboBox2;
    private JComboBox comboBox1;
    private JRadioButton validéeRadioButton;
    private JRadioButton nonValidéeRadioButton;
    private JButton addBtn;

        private String splace;
        private String sheure;
        private String sdate;
        private String svalidation;
        private String spresident;
        private String srapporteur;
        private String sexaminateur;
        private int snote;


        public validerSoutenance() {
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

            addBtn.addActionListener(new ActionListener() {
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
                        if(sdate.isEmpty()||sheure.isEmpty()||splace.isEmpty()||noteString.isEmpty()||svalidation.isEmpty()){
                            JOptionPane.showMessageDialog(addBtn,"Veuillez entrer tous les champs");
                        } else{
                            databaseConnection c =new databaseConnection();
                            String sql="UPDATE soutenance SET note = '"+snote+"', validation = '"+svalidation+"' WHERE Datesoutenance='"+sdate+"' AND Heure='"+sheure+"' AND Place='"+splace+"';";
                            c.s.executeUpdate(sql);
                            JOptionPane.showMessageDialog(addBtn, "Soutenance validée");
                        }
                    }
                    catch(Exception ee) {
                        System.out.println(ee);
                    }

                }
            });
        }

        public void showFrame(){
            validerSoutenance ajtS=new validerSoutenance();
            ajtS.setContentPane(ajtS.mainpanel);
            ajtS.setTitle("ValiderSoutenance");
            ajtS.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ajtS.setVisible(true);
        }

        public static void main(String[] args) {
            validerSoutenance ajtS=new validerSoutenance();
            ajtS.showFrame();
        }

    }

