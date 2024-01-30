package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InserisciDipendente extends JFrame {

    private JLabel titolo, nomeLabel, cognomeLabel, dataDiNascitaLabel, matricolaLabel, codiceRuoloLabel;
    private JTextField nomeField, cognomeField, dataDiNascitaField, matricolaField, codiceRuoloField;

    private JButton confermaInserimentoButton;

    public InserisciDipendente(){
        initComponent();
    }

    private void initComponent() {
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(400, 500);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 1));
        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel formPanel = new JPanel(new GridLayout(3, 1));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        titolo = new JLabel("Inserisci Dipendente");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titolo.setHorizontalAlignment(JLabel.LEFT);
        titlePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        titlePanel.add(titolo);

        JPanel nomePanel = new JPanel(new FlowLayout());
        nomeLabel = new JLabel("Nome");
        nomeField = new JTextField(15);
        nomePanel.add(nomeLabel);
        nomePanel.add(nomeField);

        JPanel cognomePanel = new JPanel(new FlowLayout());
        cognomeLabel = new JLabel("Cognome");
        cognomeField = new JTextField(15);
        cognomePanel.add(cognomeLabel);
        cognomePanel.add(cognomeField);

        JPanel dataDiNascitaPanel = new JPanel(new FlowLayout());
        dataDiNascitaLabel = new JLabel("Data di Nascita");
        dataDiNascitaField = new JTextField(15);
        dataDiNascitaPanel.add(dataDiNascitaLabel);
        dataDiNascitaPanel.add(dataDiNascitaField);

        formPanel.add(nomePanel);
        formPanel.add(cognomePanel);
        formPanel.add(dataDiNascitaPanel);

        confermaInserimentoButton = new JButton("Conferma Inserimento");
        buttonPanel.add(confermaInserimentoButton);

        add(titlePanel);
        add(formPanel);
        add(buttonPanel);
    }

}
