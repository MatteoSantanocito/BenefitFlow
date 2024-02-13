package ui;


import domain.Benefit;
import domain.BenefitFlow;
import domain.Dipendente;
import domain.Ferie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProlungamentoFerie extends JFrame {

    private JLabel titolo;
    private JLabel codiceLabel, dataFineLabel;
    private JTextField codiceField, dataFineField, errorField;
    private JButton confermaButton;

    private BenefitFlow benefitFlow;
    private Dipendente dipendente;

    public ProlungamentoFerie(BenefitFlow b, Dipendente d) {
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

        titolo = new JLabel("Richiedi un prolungamento Ferie");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0));
        titlePanel.add(titolo);

        List<Benefit> lista = benefitFlow.mostraBenefitApprovati(this.dipendente.getMatricola(), "F");
        System.out.println(lista);
        JTable table = new JTable(new TabellaModello(lista));
        HeaderRenderer headerRenderer = new HeaderRenderer();
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700,200));
        table.setFocusable(false);
        TableColumn colonnaCodiceF = table.getColumnModel().getColumn(0);
        colonnaCodiceF.setMaxWidth(65);
        TableColumn colonnaDataInizioF = table.getColumnModel().getColumn(2);
        colonnaDataInizioF.setMinWidth(95);
        colonnaDataInizioF.setMaxWidth(95);
        TableColumn colonnaDataFineF = table.getColumnModel().getColumn(3);
        colonnaDataFineF.setMinWidth(95);
        colonnaDataFineF.setMaxWidth(95);
        TableColumn colonnaMotivazioneF = table.getColumnModel().getColumn(4);
        colonnaMotivazioneF.setPreferredWidth(220);
        TableColumn colonnaStatoF = table.getColumnModel().getColumn(5);
        colonnaStatoF.setPreferredWidth(100);
        table.setSelectionBackground(new Color(119, 119, 119, 255));
        //table.setSelectionForeground(new Color(153, 221, 255));

        codiceLabel = new JLabel("Codice");
        codiceField = new JTextField(3);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceField, gbc);

        dataFineLabel = new JLabel("Data fine");
        dataFineField = new JTextField(15);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataFineLabel, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(dataFineField, gbc);

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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                if (!codiceField.getText().isEmpty() && !dataFineField.getText().isEmpty()) {
                    try {
                        LocalDate dataFineFormatted = LocalDate.parse(dataFineField.getText(), formatter);
                        int codice = Integer.parseInt(codiceField.getText());
                        LocalDate oldDate = null;
                        for(Benefit b : lista){
                            if (b.getCodice() == codice) {
                                oldDate = ((Ferie) b).getDataFine();
                                trovato = true;
                                break;
                            }
                        }
                        if (trovato) {
                            if(dataFineFormatted.isAfter(oldDate)){
                                benefitFlow.richiediProlungamentoFerie(codice, dataFineFormatted);
                                benefitFlow.confermaProlungamentoFerie();
                                codiceField.setText("");
                                dataFineField.setText("");
                                ProlungamentoFerie.this.dispose();
                            }else{
                                errorField.setText("Data non valida");
                                dataFineField.setText("");
                            }
                        }else{
                            errorField.setText("Codice non valido");
                            codiceField.setText("");
                        }  
                    } catch (Exception ex) {
                        System.err.println("Inserimento ferie fallito. Formato data non valido.");
                        errorField.setText("Data non valida (formato richiesto: dd/MM/yyyy)");
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
        private final List<Benefit> lista;
        private final String[] colonne = {"Codice", "Matricola", "Data Inizio", "Data Fine", "Motivazione", "Stato"};

        public TabellaModello(List<Benefit> lista) {
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
            Ferie ferie = (Ferie) lista.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return ferie.getCodice();
                case 1:
                    return ferie.getMatricola();
                case 2:
                    return ferie.getDataInizio();
                case 3:
                    return ferie.getDataFine();
                case 4:
                    return ferie.getMotivazione();
                case 5:
                    return ferie.getStato();
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