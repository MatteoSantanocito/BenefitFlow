package ui;


import domain.BenefitFlow;
import domain.Ruolo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.Map;

public class InserisciNuovoDipendente extends JFrame {

    private JLabel titolo;
    private JLabel nomeLabel, cognomeLabel, dataDiNascitaLabel, matricolaLabel, codiceRuoloLabel;
    private JTextField nomeField, cognomeField, dataDiNascitaField, matricolaField, codiceRuoloField;
    private JButton confermaInserimentoButton;

    private Map<String,Ruolo> ruoli;
    private BenefitFlow benefitFlow;

    public InserisciNuovoDipendente(Map<String,Ruolo> r, BenefitFlow b){
        this.ruoli = r;
        this.benefitFlow = b;
        initComponent();
    }

    private void initComponent() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 0, 10);

        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());


        titolo = new JLabel("Inserisci Dipendente");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        titlePanel.add(titolo);


        nomeLabel = new JLabel("Nome");
        nomeField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(nomeLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(nomeField, gbc);

        cognomeLabel = new JLabel("Cognome");
        cognomeField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(cognomeLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(cognomeField, gbc);

        dataDiNascitaLabel = new JLabel("Data di Nascita");
        dataDiNascitaField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataDiNascitaLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataDiNascitaField, gbc);

        matricolaLabel = new JLabel("Matricola");
        matricolaField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(matricolaLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(matricolaField, gbc);

        codiceRuoloLabel = new JLabel("Codice Ruolo");
        codiceRuoloField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceRuoloLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceRuoloField, gbc);


        confermaInserimentoButton = new JButton("Conferma Inserimento");
        buttonPanel.setBorder(new EmptyBorder(10, 0, 30, 0));
        buttonPanel.add(confermaInserimentoButton);

        confermaInserimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/fileTesto/dipendenti.txt", true))) {
                    String codiceRuolo = codiceRuoloField.getText();
                    Ruolo ruolo = ruoli.get(codiceRuolo);
                    writer.write("Nome: " + nomeField.getText() +
                            ", Cognome: " + cognomeField.getText() +
                            ", Data di nascita: " + dataDiNascitaField.getText() +
                            ", Matricola: " + matricolaField.getText() +
                            ", Ruolo: " + ruolo.toString());
                    nomeField.setText("");
                    cognomeField.setText("");
                    dataDiNascitaField.setText("");
                    matricolaField.setText("");
                    codiceRuoloField.setText("");
                    writer.newLine();
                    InserisciNuovoDipendente.this.dispose();
                } catch (IOException er) {
                    er.printStackTrace();
                }
            }
            
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(titlePanel,gbc);
        gbc.gridy = 1;
        gbc.weighty = 0.8;
        add(formPanel,gbc);
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        add(buttonPanel,gbc);

        setResizable(false);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
