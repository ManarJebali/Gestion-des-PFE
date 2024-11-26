import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class rechercheIntelligenteEnseignant extends JFrame {

    private JTextField searchField;
    private JTable resultTable;

    private Connection connection;
    private PreparedStatement preparedStatement;

    public rechercheIntelligenteEnseignant() {
        setTitle("Recherche d'Enseignant");
        setSize(600, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        Font titleFont = new Font("Cooper Black", Font.BOLD, 28);
        Color titleColor = Color.decode("#3FCDE5");
        JLabel titleLabel = new JLabel("<html><font color='#3FCDE5'>Recherche d'Enseignant</font></html>");
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
        displayTeachers();
        searchTeachers();

        // Add a DocumentListener to the search field to update the table dynamically
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchTeachers();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchTeachers();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchTeachers();
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

    private void displayTeachers() {
        try {
            String query = "SELECT * FROM `teacher`";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Creating a new DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"ID", "Nom", "Prénom"});

            // Filling the model with data from the database
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prénom")
                });
            }

            // Setting the model for the JTable
            resultTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchTeachers() {
        String searchText = searchField.getText();
        try {
            String query = "SELECT * FROM `teacher` WHERE id LIKE ? OR nom LIKE ? OR prénom LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            for (int i = 1; i <= 3; i++) {
                preparedStatement.setString(i, "%" + searchText + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            // Creating a new DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"ID", "Nom", "Prénom"});

            // Filling the model with the results of the query
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prénom")
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
                rechercheIntelligenteEnseignant rechercheEnseignant = new rechercheIntelligenteEnseignant();
                rechercheEnseignant.setVisible(true);
                rechercheEnseignant.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }
}
