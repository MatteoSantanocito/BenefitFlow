package ui;

import domain.BenefitFlow;
import domain.Dipendente;

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
    private JTextField matricolaField, dataField, oraInizioField, oraFineField, errorField;
    private JTextArea motivazioneField;
    private JButton confermaPermessoButton;

    private BenefitFlow benefitFlow;
    private Dipendente dipendente;

    public InserisciPermesso(BenefitFlow b, Dipendente d){
        this.benefitFlow = b;
        this.dipendente = d;
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
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0));
        titlePanel.add(titolo);


        matricolaLabel = new JLabel("Matricola");
        matricolaField = new JTextField(15);
        matricolaField.setText(dipendente.getMatricola());
        matricolaField.setEnabled(false);
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

        JPanel errorPanel = new JPanel(new FlowLayout());
        errorField = new JTextField(28);
        errorField.setBorder(null);
        errorField.setBackground(new Color(238, 238, 238));
        errorField.setFont(new Font("Arial", Font.ITALIC, 12));
        errorField.setEnabled(false);
        errorField.setDisabledTextColor(Color.RED);
        errorPanel.add(errorField);

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
                        boolean valida = validaDataOraPermesso(dataFormatted,oraInizioFormatted,oraFineFormatted);
                        if(valida){
                            benefitFlow.inserisciPermesso(matricolaField.getText(), motivazioneField.getText(), dataFormatted, oraInizioFormatted, oraFineFormatted);
                            benefitFlow.confermaPermesso();
                            matricolaField.setText("");
                            motivazioneField.setText("");
                            dataField.setText("");
                            oraInizioField.setText("");
                            oraFineField.setText("");
                            InserisciPermesso.this.dispose();
                        }else{
                            errorField.setText("Data e/o ora non valida");
                            dataField.setText("");
                            oraInizioField.setText("");
                            oraFineField.setText("");
                        }

                    } catch (Exception ex) {
                        System.err.println("Inserimento ferie fallito. Formato data e/o ora non valido.");
                        errorField.setText("Data e/o ora non valida (dd/MM/yyyy - HH:mm)");
                        dataField.setText("");
                        oraInizioField.setText("");
                        oraFineField.setText("");
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
        gbc.weighty = 0;
        add(errorPanel,gbc);
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(buttonPanel,gbc);

        setResizable(true);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(400, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public boolean validaDataOraPermesso(LocalDate data, LocalTime oraInizio, LocalTime oraFine){
        boolean valida = false;
        LocalDate dataCorrente = LocalDate.now();
        LocalTime oraCorrente = LocalTime.now();

        if ( data.isEqual(dataCorrente) ) {
            if( (oraInizio.equals(oraCorrente) || oraInizio.isAfter(oraCorrente)) && oraFine.isAfter(oraInizio)){
                valida = true;
            }
        }

        if(data.isAfter(dataCorrente)){
            if(oraFine.isAfter(oraInizio)){
                valida = true;
            }
        }

        return valida;
    }

}
