import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class rechercheIntelligenteProjet extends JFrame {

    private JTextField searchField;
    private JTable resultTable;

    private Connection connection;
    private PreparedStatement preparedStatement;

    public rechercheIntelligenteProjet() {
        setTitle("Recherche de Projet");
        setSize(600, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        Font titleFont = new Font("Cooper Black", Font.BOLD, 28);
        Color titleColor = Color.decode("#3FCDE5");
        JLabel titleLabel = new JLabel("<html><font color='#3FCDE5'>Recherche de Projet</font></html>");
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
        displayProjects();
        searchProjects();

        // Add a DocumentListener to the search field to update the table dynamically
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchProjects();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchProjects();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchProjects();
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

    private void displayProjects() {
        try {
            String query = "SELECT * FROM `projet`";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Creating a new DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"Titre", "Étudiant 1", "Étudiant 2", "Encadrant"});

            // Filling the model with data from the database
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("titre"),
                        resultSet.getString("etudiant1"),
                        resultSet.getString("etudiant2"),
                        resultSet.getString("encadrant")
                });
            }

            // Setting the model for the JTable
            resultTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchProjects() {
        String searchText = searchField.getText();
        try {
            String query = "SELECT * FROM `projet` WHERE titre LIKE ? OR etudiant1 LIKE ? OR etudiant2 LIKE ? OR encadrant LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            for (int i = 1; i <= 4; i++) {
                preparedStatement.setString(i, "%" + searchText + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            // Creating a new DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"Titre", "Étudiant 1", "Étudiant 2", "Encadrant"});

            // Filling the model with the results of the query
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("titre"),
                        resultSet.getString("etudiant1"),
                        resultSet.getString("etudiant2"),
                        resultSet.getString("encadrant")
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
                rechercheIntelligenteProjet rechercheProjet = new rechercheIntelligenteProjet();
                rechercheProjet.setVisible(true);
                rechercheProjet.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }
}
