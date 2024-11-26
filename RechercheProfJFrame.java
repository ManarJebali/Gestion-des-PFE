import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RechercheProfJFrame extends JFrame {
        JTextField matriculefield;
        JButton cherchebouton, annulerbouton;

        public RechercheProfJFrame() {
            setTitle("Recherche d'un enseignant");
            setSize(400, 200);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            matriculefield = new JTextField(20);
            cherchebouton = new JButton("Chercher");
            annulerbouton = new JButton("Annuler");

            JPanel panelGauche = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel("Matricule : ");
            panelGauche.add(label);
            panelGauche.add(matriculefield);

            JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelBoutons.add(cherchebouton);
            panelBoutons.add(annulerbouton);

            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.add(panelGauche, BorderLayout.CENTER);
            contentPane.add(panelBoutons, BorderLayout.SOUTH);

            setContentPane(contentPane);

            cherchebouton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String matricule = matriculefield.getText().trim();
                    if (!matricule.isEmpty()) {
                        int m = Integer.parseInt(matricule);
                        try {
                            databaseConnection connection=new databaseConnection();
                            String query = "SELECT * FROM `teacher` WHERE id='"+matricule+"'";
                            //PreparedStatement stmt = connection.prepareStatement(query);
                            //stmt.setInt(1, m);
                            //ResultSet resultat = stmt.executeQuery();
                            ResultSet resultat=connection.s.executeQuery(query);
                            if (!resultat.next()) {
                                JOptionPane.showMessageDialog(null, "Aucun enseignant trouvé avec ce numéro de matricule",
                                        "Erreur", JOptionPane.ERROR_MESSAGE);
                            } else {
                                String nom = resultat.getString("Nom");
                                String prenom = resultat.getString("Prénom");

                                JOptionPane.showMessageDialog(null,
                                        "L'enseignant d'id " + m + " s'appelle " + prenom + " " + nom);
                            }
                        } catch (SQLException ex) {
                            System.err.println("Erreur SQL : " + ex.getMessage());
                        }

                    } else
                        JOptionPane.showMessageDialog(null, "Le champ ne doit pas être vide", "ERREUR",
                                JOptionPane.ERROR_MESSAGE);

                }
            });
            annulerbouton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }

            });
        }

    }
