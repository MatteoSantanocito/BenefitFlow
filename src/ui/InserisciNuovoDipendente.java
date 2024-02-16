package ui;

import domain.BenefitFlow;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class InserisciNuovoDipendente extends JFrame {

    private JLabel titolo;
    private JLabel nomeLabel, cognomeLabel, dataDiNascitaLabel, codiceRuoloLabel, sottotitolo;
    private JTextField nomeField, cognomeField, dataDiNascitaField, codiceRuoloField, errorField;
    private JButton confermaInserimentoButton;

    private BenefitFlow benefitFlow;

    public InserisciNuovoDipendente(BenefitFlow b){
        this.benefitFlow = b;
        initComponent();
    }

    private void initComponent() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 0, 10);

        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel subtitlePanel = new JPanel(new FlowLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());


        titolo = new JLabel("Inserisci Dipendente");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0));
        titlePanel.add(titolo);

        sottotitolo = new JLabel("I campi contrassegnati da (*) sono obbligatori");
        sottotitolo.setFont(new Font("Arial", Font.ITALIC, 12));
        subtitlePanel.add(sottotitolo);

        nomeLabel = new JLabel("Nome*");
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

        cognomeLabel = new JLabel("Cognome*");
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

        dataDiNascitaLabel = new JLabel("Data di nascita*");
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

        codiceRuoloLabel = new JLabel("Codice Ruolo");
        codiceRuoloField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceRuoloLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceRuoloField, gbc);

        JPanel errorPanel = new JPanel(new FlowLayout());
        errorField = new JTextField(30);
        errorField.setBorder(null);
        errorField.setBackground(new Color(238, 238, 238));
        errorField.setFont(new Font("Arial", Font.ITALIC, 12));
        errorField.setEnabled(false);
        errorField.setDisabledTextColor(Color.RED);
        errorPanel.add(errorField);

        confermaInserimentoButton = new JButton("Conferma Inserimento");
        buttonPanel.setBorder(new EmptyBorder(10, 0, 30, 0));
        buttonPanel.add(confermaInserimentoButton);

        confermaInserimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                if (!nomeField.getText().isEmpty() && !cognomeField.getText().isEmpty() && !dataDiNascitaField.getText().isEmpty()) {
                    try {
                        LocalDate dataDiNascitaFormatted = LocalDate.parse(dataDiNascitaField.getText(), formatter);
                        LocalDate dataMinima = LocalDate.now().minusYears(18);
                        if(dataDiNascitaFormatted.isBefore(dataMinima) || dataDiNascitaFormatted.equals(dataMinima)) {
                            benefitFlow.inserisciNuovoDipendente(nomeField.getText(), cognomeField.getText(), dataDiNascitaFormatted, codiceRuoloField.getText());
                            benefitFlow.confermaInserimento();
                            nomeField.setText("");
                            cognomeField.setText("");
                            dataDiNascitaField.setText("");
                            codiceRuoloField.setText("");
                            InserisciNuovoDipendente.this.dispose();
                        }else {
                            errorField.setText("Il dipendente deve essere maggiorenne!");
                        }
                    } catch (Exception ex) {
                        System.err.println("Inserimento nuovo dipendente fallito. Formato data non valido.");
                        errorField.setText("Data non valida (formato richiesto: dd/MM/yyyy)");
                        dataDiNascitaField.setText("");
                    }
                } else {
                    System.out.println("Compilare tutti i campi");
                    errorField.setText("Compilare tutti i campi obbligatori");
                } 
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0.1;
        add(titlePanel,gbc);
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(subtitlePanel,gbc);
        gbc.gridy = 2;
        gbc.weighty = 0.8;
        add(formPanel,gbc);
        gbc.gridy = 3;
        gbc.weighty = 0;
        add(errorPanel,gbc);
        gbc.gridy = 4;
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
