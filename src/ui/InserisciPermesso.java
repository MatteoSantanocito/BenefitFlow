package ui;


import domain.BenefitFlow;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class InserisciPermesso extends JFrame {

    private JLabel titolo;
    private JLabel motivazioneLabel, matricolaLabel, dataLabel, oraInizioLabel, oraFineLabel;
    private JTextField matricolaField, dataField, oraInizioField, oraFineField;
    private JTextArea motivazioneField;
    private JButton confermaPermessoButton;

    private BenefitFlow benefitFlow;

    public InserisciPermesso(BenefitFlow b){
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


        titolo = new JLabel("Inserisci Permesso");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        titlePanel.add(titolo);


        matricolaLabel = new JLabel("Matricola");
        matricolaField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(matricolaLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(matricolaField, gbc);

        dataLabel = new JLabel("Data");
        dataField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataField, gbc);

        oraInizioLabel = new JLabel("Ora Inizio");
        oraInizioField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(oraInizioLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(oraInizioField, gbc);

        oraFineLabel = new JLabel("Ora fine");
        oraFineField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(oraFineLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(oraFineField, gbc);

        motivazioneLabel = new JLabel("Motivazione");
        motivazioneField = new JTextArea();
        motivazioneField.setLineWrap(true);
        motivazioneField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(motivazioneField);
        scrollPane.setPreferredSize(new Dimension(183, 50));
        scrollPane.setBorder(null);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(motivazioneLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(scrollPane, gbc);


        confermaPermessoButton = new JButton("Conferma");
        buttonPanel.setBorder(new EmptyBorder(10, 0, 30, 0));
        buttonPanel.add(confermaPermessoButton);

        confermaPermessoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                if (!matricolaField.getText().isEmpty() && !motivazioneField.getText().isEmpty() && !dataField.getText().isEmpty() && !oraInizioField.getText().isEmpty() && !oraFineField.getText().isEmpty()) {
                    try {
                        LocalDate dataFormatted = LocalDate.parse(dataField.getText(), formatterData);
                        LocalTime oraInizioFormatted = LocalTime.parse(oraInizioField.getText(), formatterTime);
                        LocalTime oraFineFormatted = LocalTime.parse(oraFineField.getText(), formatterTime);
                        benefitFlow.inserisciPermesso(matricolaField.getText(), motivazioneField.getText(), dataFormatted, oraInizioFormatted, oraFineFormatted);
                        benefitFlow.confermaPermesso();
                        matricolaField.setText("");
                        motivazioneField.setText("");
                        dataField.setText("");
                        oraInizioField.setText("");
                        oraFineField.setText("");
                        InserisciPermesso.this.dispose();
                    } catch (Exception ex) {
                        System.err.println("Inserimento ferie fallito. Formato data e/o ora non valido.");
                        matricolaField.setText("");
                        motivazioneField.setText("");
                        dataField.setText("");
                        oraInizioField.setText("");
                        oraFineField.setText("");
                    }
                } else {
                    System.out.println("Compilare tutti i campi.");
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

