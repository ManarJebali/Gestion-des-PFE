import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class rechercheIntelligenteSoutenance extends JFrame {

    private JTextField searchField;
    private JTable resultTable;

    private Connection connection;
    private PreparedStatement preparedStatement;

    public rechercheIntelligenteSoutenance() {
        setTitle("Recherche de Soutenance");
        setSize(600, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        Font titleFont = new Font("Cooper Black", Font.BOLD, 28);
        Color titleColor = Color.decode("#3FCDE5");
        JLabel titleLabel = new JLabel("<html><font color='#3FCDE5'>Recherche de Soutenance</font></html>");
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        // Create a JPanel for the search field
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20); // Initialize the instance variable
        searchPanel.add(searchField);

        // Create a JScrollPane and a JTable
        resultTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultTable);

        // Set layout and add components to the JFrame
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH); // Add titlePanel to the top of the JFrame
        add(searchPanel, BorderLayout.CENTER); // Add searchPanel to the center
        add(scrollPane, BorderLayout.SOUTH); // Add scrollPane to the bottom

        connectToDatabase();
        displaySoutenances();
        searchSoutenances();

        // Add a DocumentListener to the search field to update the table dynamically
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchSoutenances();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchSoutenances();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchSoutenances();
            }
        });
    }

    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/student";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displaySoutenances() {
        try {
            String query = "SELECT * FROM `soutenance`";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Creating a new DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"Date", "Heure", "Place", "Président", "Rapporteur", "Examinateur", "Note", "Validation"});

            // Filling the model with data from the database
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getDate("datesoutenance"),
                        resultSet.getTime("heure"),
                        resultSet.getString("place"),
                        resultSet.getString("president"),
                        resultSet.getString("rapporteur"),
                        resultSet.getString("examinateur"),
                        resultSet.getString("note"),
                        resultSet.getString("validation"),
                });
            }

            // Setting the model for the JTable
            resultTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchSoutenances() {
        String searchText = searchField.getText();
        try {
            String query = "SELECT * FROM `soutenance` WHERE datesoutenance LIKE ? OR heure LIKE ? OR place LIKE ? OR president LIKE ? OR rapporteur LIKE ? OR examinateur LIKE ? OR note LIKE ? OR validation LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            for (int i = 1; i <= 8; i++) {
                preparedStatement.setString(i, "%" + searchText + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            // Creating a new DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"Date", "Heure", "Place", "Président", "Rapporteur", "Examinateur", "Note", "Validation"});

            // Filling the model with the results of the query
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getDate("datesoutenance"),
                        resultSet.getTime("heure"),
                        resultSet.getString("place"),
                        resultSet.getString("president"),
                        resultSet.getString("rapporteur"),
                        resultSet.getString("examinateur"),
                        resultSet.getString("note"),
                        resultSet.getString("validation"),
                });
            }

            // Setting the model for the JTable
            resultTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                rechercheIntelligenteSoutenance rechercheSoutenance = new rechercheIntelligenteSoutenance();
                rechercheSoutenance.setVisible(true);
                rechercheSoutenance.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }
}
