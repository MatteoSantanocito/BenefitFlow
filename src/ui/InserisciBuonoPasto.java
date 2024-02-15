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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InserisciBuonoPasto extends JFrame {

    private JLabel titolo;
    private JLabel matricolaLabel, dataScadenzaLabel;
    private JTextField matricolaField, dataScadenzaField, errorField;
    private JButton confermaButton;
    private BenefitFlow benefitFlow;

    public InserisciBuonoPasto(BenefitFlow b) {
        this.benefitFlow = b;
        initComponent();
    }

    private void initComponent() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());

        titolo = new JLabel("Assegna Buono Pasto");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0));
        titlePanel.add(titolo);

        List<Dipendente> lista = benefitFlow.visualizzaDipendenti();
        JTable table = new JTable(new TabellaModello(lista));
        HeaderRenderer headerRenderer = new HeaderRenderer();
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700,200));
        table.setFocusable(false);
        TableColumn colonnaRuolo = table.getColumnModel().getColumn(4);
        colonnaRuolo.setPreferredWidth(300);
        TableColumn colonnaData = table.getColumnModel().getColumn(2);
        colonnaData.setMinWidth(105);
        colonnaData.setMaxWidth(105);
        table.setSelectionBackground(new Color(119, 119, 119, 255));
        //table.setSelectionForeground(new Color(153, 221, 255));

        matricolaLabel = new JLabel("Matricola");
        matricolaField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(matricolaLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(matricolaField, gbc);

        dataScadenzaLabel = new JLabel("Data Scadenza");
        dataScadenzaLabel.setBorder(new EmptyBorder(0, 30, 0, 0));
        dataScadenzaField = new JTextField(15);
        LocalDate dataScadenza = LocalDate.now().plusMonths(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataScadenzaField.setText(dataScadenza.format(formatter));
        dataScadenzaField.setEnabled(false);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataScadenzaLabel, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataScadenzaField, gbc);

        formPanel.setBorder(new EmptyBorder(20, 0, 5, 0));

        JPanel errorPanel = new JPanel(new FlowLayout());
        errorField = new JTextField(28);
        errorField.setBorder(null);
        errorField.setBackground(new Color(238, 238, 238));
        errorField.setFont(new Font("Arial", Font.ITALIC, 12));
        errorField.setHorizontalAlignment(SwingConstants.CENTER);
        errorField.setEnabled(false);
        errorField.setDisabledTextColor(Color.RED);
        errorPanel.add(errorField);

        confermaButton = new JButton("Conferma");
        buttonPanel.setBorder(new EmptyBorder(10, 0, 30, 0));
        buttonPanel.add(confermaButton);

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean trovato = false;
                if (!matricolaField.getText().isEmpty()) {
                    for(Dipendente d : lista){
                        if (d.getMatricola().equals(matricolaField.getText())) {
                            trovato = true;
                            break;
                        }
                    }

                    if (trovato) {
                        benefitFlow.creaBuonoPasto(matricolaField.getText(), dataScadenza);
                        benefitFlow.confermaBuonoPasto();
                        matricolaField.setText("");
                        InserisciBuonoPasto.this.dispose();
                    }else{
                        errorField.setText("Matricola non valida");
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
        add(titlePanel, gbc);
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        add(scrollPane, gbc);
        gbc.gridy = 2;
        gbc.weighty = 0;
        add(formPanel, gbc);
        gbc.gridy = 3;
        gbc.weighty = 0;
        add(errorPanel, gbc);
        gbc.gridy = 4;
        gbc.weighty = 0.1;
        add(buttonPanel, gbc);

        setResizable(false);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    static class TabellaModello extends AbstractTableModel {
        private final List<Dipendente> lista;
        private final String[] colonne = {"Nome", "Cognome", "Data di nascita", "Matricola", "Ruolo"};

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