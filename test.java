import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class test extends JFrame {
    private JTable table;

    public test() {
        setTitle("JTable Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sample data
        Object[][] data = {
                {"John", 25, "Engineer"},
                {"Jane", 30, "Doctor"},
                {"Doe", 35, "Artist"}
        };

        // Column names
        String[] columns = {"Name", "Age", "Occupation"};

        // Create a table model
        DefaultTableModel model = new DefaultTableModel(data, columns);

        // Create the JTable
        table = new JTable(model);

        // Set column widths (optional)
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            test example = new test();
            example.setVisible(true);
        });
    }
}
