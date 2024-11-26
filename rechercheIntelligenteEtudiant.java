import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class rechercheIntelligenteEtudiant extends JFrame {


        private JTextField searchField;
        //private JButton searchButton;
        private JTable resultTable;

        private Connection connection;
        private PreparedStatement preparedStatement;

        public rechercheIntelligenteEtudiant() {
            setTitle("Recherche d'Étudiant");
            setSize(600, 400);
            //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a JPanel for the title
            JPanel titlePanel = new JPanel();
            titlePanel.setBackground(Color.WHITE);
            Font titleFont = new Font("Cooper Black", Font.BOLD, 28);
            Color titleColor = Color.decode("#3FCDE5");
            JLabel titleLabel = new JLabel("<html><font color='#3FCDE5'>Recherche des étudiants</font></html>");
            titleLabel.setFont(titleFont);
            titlePanel.add(titleLabel);


            // Create a JPanel for the search field
            JPanel searchPanel = new JPanel();
            searchField = new JTextField(20);
            searchPanel.add(searchField);
            //searchPanel.add(searchButton);


            resultTable = new JTable();
            JScrollPane scrollPane = new JScrollPane(resultTable);

            setLayout(new BorderLayout());
            add(searchPanel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);

            // Set layout and add components to the JFrame
            setLayout(new BorderLayout());
            add(titlePanel, BorderLayout.NORTH);
            add(searchPanel, BorderLayout.CENTER);
            add(scrollPane, BorderLayout.SOUTH);

            connectToDatabase();
            displayStudents();
            searchStudents();

            // Add a DocumentListener to the search field to update the table dynamically
            searchField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    searchStudents();
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    searchStudents();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    searchStudents();
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

    private void displayStudents() {
        try {
            String query = "SELECT * FROM `student`";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Creating a new DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"ID", "Nom", "Prénom", "Email", "Spécialité"});

            // Filling the model with data from the database
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Prénom"),
                        resultSet.getString("Email"),
                        resultSet.getString("Specialite")
                });
            }

            // Setting the model for the JTable
            resultTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchStudents() {
        String searchText = searchField.getText();
        try {
            String query = "SELECT * FROM `student` WHERE id LIKE ? OR Nom LIKE ? OR Prénom LIKE ? OR Email LIKE ? OR Specialite LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchText + "%");
            preparedStatement.setString(2, "%" + searchText + "%");
            preparedStatement.setString(3, "%" + searchText + "%");
            preparedStatement.setString(4, "%" + searchText + "%");
            preparedStatement.setString(5, "%" + searchText + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            // Création d'un nouveau DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"ID", "Nom", "Prénom", "Email", "Spécialité"});

            // Remplissage du modèle avec les résultats de la requête
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prénom"),
                        resultSet.getString("Email"),
                        resultSet.getString("Specialite")
                });
            }

            // Définition du modèle pour la JTable
            resultTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    private static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Names of columns
            String[] columnNames = new String[metaData.getColumnCount()];
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            // Data of the table
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            while (resultSet.next()) {
                Object[] rowData = new Object[metaData.getColumnCount()];
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                model.addRow(rowData);
            }
            return model;
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    rechercheIntelligenteEtudiant rechercheEtd=new rechercheIntelligenteEtudiant();
                    rechercheEtd.setVisible(true);
                    rechercheEtd.setExtendedState(JFrame.MAXIMIZED_BOTH);

                }
            });
        }
}

