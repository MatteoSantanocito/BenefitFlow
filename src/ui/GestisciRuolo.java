package ui;


import domain.BenefitFlow;
import domain.Dipendente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestisciRuolo extends JFrame {

    private JLabel titolo;
    private JLabel matricolaLabel, codiceRuoloLabel;
    private JTextField matricolaField, codiceRuoloField;
    private JButton confermaButton;
    private JTextArea textArea;
    private BenefitFlow benefitFlow;

    public GestisciRuolo(BenefitFlow b){
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
        JPanel textAreaPanel = new JPanel(new FlowLayout());


        titolo = new JLabel("Gestisci Ruolo");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        titlePanel.add(titolo);

        List<Dipendente> lista = benefitFlow.mostraDipendenti();

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textAreaPanel.add(scrollPane);
        textArea.setRows(13);
        textArea.setColumns(46);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(10,12,0,10));

        for (Dipendente dipendente: lista){
            textArea.append(dipendente.toString());
            textArea.append("\n\n");
        }


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
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceRuoloLabel, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceRuoloField, gbc);


        confermaButton = new JButton("Conferma");
        buttonPanel.setBorder(new EmptyBorder(10, 0, 30, 0));
        buttonPanel.add(confermaButton);

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                benefitFlow.confermaRuolo(matricolaField.getText(), codiceRuoloField.getText());
                matricolaField.setText("");
                codiceRuoloField.setText("");
                GestisciRuolo.this.dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(titlePanel,gbc);
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        add(textAreaPanel,gbc);
        gbc.gridy = 2;
        gbc.weighty = 0.8;
        add(formPanel,gbc);
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(buttonPanel,gbc);

        setResizable(false);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(850, 515);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
