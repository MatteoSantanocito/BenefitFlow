package ui;

import domain.BenefitFlow;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class InserisciFerie extends JFrame {

    private JLabel titolo;
    private JLabel motivazioneLabel, matricolaLabel, dataInizioLabel, dataFineLabel;
    private JTextField matricolaField, dataInizioField, dataFineField, errorField;
    private JTextArea motivazioneField;
    private JButton confermaFerieButton;

    private BenefitFlow benefitFlow;

    public InserisciFerie(BenefitFlow b){
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


        titolo = new JLabel("Inserisci Ferie");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0));
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

        dataInizioLabel = new JLabel("Data Inizio");
        dataInizioField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataInizioLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataInizioField, gbc);

        dataFineLabel = new JLabel("Data Fine");
        dataFineField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataFineLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataFineField, gbc);

        motivazioneLabel = new JLabel("Motivazione");
        motivazioneField = new JTextArea();
        motivazioneField.setLineWrap(true);
        motivazioneField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(motivazioneField);
        scrollPane.setPreferredSize(new Dimension(183, 50));
        scrollPane.setBorder(null);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(motivazioneLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(scrollPane, gbc);


        JPanel errorPanel = new JPanel(new FlowLayout());
        errorField = new JTextField(28);
        errorField.setBorder(null);
        errorField.setBackground(new Color(238, 238, 238));
        errorField.setFont(new Font("Arial", Font.ITALIC, 12));
        errorField.setEnabled(false);
        errorField.setDisabledTextColor(Color.RED);
        errorPanel.add(errorField);

        confermaFerieButton = new JButton("Conferma");
        buttonPanel.setBorder(new EmptyBorder(10, 0, 30, 0));
        buttonPanel.add(confermaFerieButton);

        confermaFerieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                if (!matricolaField.getText().isEmpty() && !motivazioneField.getText().isEmpty() && !dataInizioField.getText().isEmpty() && !dataFineField.getText().isEmpty()) {
                    try {
                        LocalDate dataInizioFormatted = LocalDate.parse(dataInizioField.getText(), formatter);
                        LocalDate dataFineFormatted = LocalDate.parse(dataFineField.getText(), formatter);
                        benefitFlow.inserisciFerie(matricolaField.getText(), motivazioneField.getText(), dataInizioFormatted, dataFineFormatted);
                        benefitFlow.confermaFerie();
                        matricolaField.setText("");
                        motivazioneField.setText("");
                        dataInizioField.setText("");
                        dataFineField.setText("");
                        InserisciFerie.this.dispose();
                    } catch (Exception ex) {
                        System.err.println("Inserimento ferie fallito. Formato data non valido.");
                        errorField.setText("Data non valida (formato richiesto: dd/MM/yyyy)");
                        dataInizioField.setText("");
                        dataFineField.setText("");
                    } 
                } else {
                    System.out.println("Compilare tutti i campi.");
                    errorField.setText("Compilare tutti i campi");
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
        add(errorPanel, gbc);
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(buttonPanel,gbc);

        setResizable(false);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(400, 385);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

