package ui;


import domain.BenefitFlow;
import domain.Dipendente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestisciRuolo extends JFrame {

    private JLabel titolo;
    private JLabel matricolaLabel, codiceRuoloLabel;
    private JTextField matricolaField, codiceRuoloField;
    private JButton confermaButton;
    private BenefitFlow benefitFlow;

    public GestisciRuolo(BenefitFlow b) {
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


        titolo = new JLabel("Gestisci Ruolo");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        titlePanel.add(titolo);

        List<Dipendente> lista = benefitFlow.mostraDipendenti();
        JTable table = new JTable(new TabellaModello(lista));
        HeaderRenderer headerRenderer = new HeaderRenderer();
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700,200));
        TableColumn colonnaRuolo = table.getColumnModel().getColumn(4);
        colonnaRuolo.setPreferredWidth(275);
        table.setSelectionBackground(new Color(119, 119, 119, 255));

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
        add(titlePanel, gbc);
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        add(scrollPane, gbc);
        gbc.gridy = 2;
        gbc.weighty = 0.8;
        add(formPanel, gbc);
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(buttonPanel, gbc);

        setResizable(true);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(850, 515);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    static class TabellaModello extends AbstractTableModel {
        private final List<Dipendente> lista;
        private final String[] colonne = {"Nome", "Cognome", "Data di Nascita", "Matricola", "Ruolo"};

        public TabellaModello(List<Dipendente> lista) {
            this.lista = lista;
        }

        @Override
        public int getRowCount() {
            return lista.size();
        }

        @Override
        public int getColumnCount() {
            return colonne.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Dipendente dipendente = lista.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return dipendente.getNome();
                case 1:
                    return dipendente.getCognome();
                case 2:
                    return dipendente.getDataDiNascita();
                case 3:
                    return dipendente.getMatricola();
                case 4:
                    if(dipendente.getRuolo() == null){
                        return "non assegnato";
                    }
                    return dipendente.getRuolo();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column){
            return colonne[column];
        }
    }

    static class HeaderRenderer extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(new Color(153, 221, 255));
            setFont(new Font("Arial", Font.BOLD, 12));

            return this;
        }
    }

}