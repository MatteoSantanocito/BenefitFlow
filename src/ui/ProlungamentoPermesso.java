package ui;

import domain.Congedo;
import domain.BenefitFlow;
import domain.Dipendente;
import domain.Permesso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProlungamentoPermesso extends JFrame {

    private JLabel titolo;
    private JLabel codiceLabel, oraFineLabel;
    private JTextField codiceField, oraFineField, errorField;
    private JButton confermaButton;

    private BenefitFlow benefitFlow;
    private Dipendente dipendente;

    public ProlungamentoPermesso(BenefitFlow b, Dipendente d) {
        this.benefitFlow = b;
        this.dipendente = d;
        initComponent();
    }

    private void initComponent() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());

        titolo = new JLabel("Richiedi un prolungamento Permesso");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0));
        titlePanel.add(titolo);

        List<Congedo> lista = benefitFlow.mostraCongediApprovati(this.dipendente.getMatricola(), "P");
        JTable table = new JTable(new TabellaModello(lista));
        HeaderRenderer headerRenderer = new HeaderRenderer();
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700,200));
        table.setFocusable(false);
        TableColumn colonnaCodiceP = table.getColumnModel().getColumn(0);
        colonnaCodiceP.setMaxWidth(65);
        TableColumn colonnaMotivazioneP = table.getColumnModel().getColumn(5);
        colonnaMotivazioneP.setPreferredWidth(180);
        TableColumn colonnaDataP = table.getColumnModel().getColumn(2);
        colonnaDataP.setMinWidth(95);
        colonnaDataP.setMaxWidth(95);
        TableColumn colonnaOraInizioP = table.getColumnModel().getColumn(3);
        colonnaOraInizioP.setMinWidth(70);
        colonnaOraInizioP.setMaxWidth(70);
        TableColumn colonnaOraFineP = table.getColumnModel().getColumn(4);
        colonnaOraFineP.setMinWidth(70);
        colonnaOraFineP.setMaxWidth(70);
        TableColumn colonnaStatoP = table.getColumnModel().getColumn(6);
        colonnaStatoP.setPreferredWidth(100);
        table.setSelectionBackground(new Color(119, 119, 119, 255));

        codiceLabel = new JLabel("Codice");
        codiceField = new JTextField(3);
        codiceField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
            }
            
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceField, gbc);

        oraFineLabel = new JLabel("Ora fine");
        oraFineLabel.setBorder(new EmptyBorder(0, 30, 0, 0));
        oraFineField = new JTextField(6);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(oraFineLabel, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(oraFineField, gbc);

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

        if (lista.isEmpty()) {
            codiceLabel.setEnabled(false);
            codiceField.setEnabled(false);
            oraFineLabel.setEnabled(false);
            oraFineField.setEnabled(false);
            confermaButton.setEnabled(false);
            errorField.setText("Non sono presenti permessi approvati");
        }

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean trovato = false;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

                if (!codiceField.getText().isEmpty() && !oraFineField.getText().isEmpty()) {
                    try {
                        LocalTime oraFineFormatted = LocalTime.parse(oraFineField.getText(), formatter);
                        LocalTime oldHour = LocalTime.of(23,59);
                        for(Congedo b : lista){
                            if (b.getCodice().equals(codiceField.getText())) {
                                oldHour = ((Permesso) b).getOraFine();
                                trovato = true;
                                break;
                            }
                        }
                        if (trovato) {

                            try {
                                validaOraPermesso(oldHour, oraFineFormatted);
                                benefitFlow.richiediProlungamentoPermesso(codiceField.getText(), oraFineFormatted);
                                benefitFlow.confermaProlungamentoPermesso();
                                codiceField.setText("");
                                oraFineField.setText("");
                                ProlungamentoPermesso.this.dispose();
                            } catch (Exception ex) {
                                errorField.setText(ex.getMessage());
                            }
                            
                        }else{
                            errorField.setText("Codice non valido");
                            codiceField.setText("");
                        } 
                    } catch (Exception ex) {
                        System.err.println("Inserimento permesso fallito. Formato ora non valido.");
                        errorField.setText("Ora non valida (formato richiesto: HH:mm)");
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
        private final List<Congedo> lista;
        private final String[] colonne = {"Codice", "Matricola", "Data", "Ora inizio", "Ora fine", "Motivazione", "Stato"};

        public TabellaModello(List<Congedo> lista) {
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
            Permesso permesso = (Permesso) lista.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return permesso.getCodice();
                case 1:
                    return permesso.getMatricola();
                case 2:
                    return permesso.getData();
                case 3:
                    return permesso.getOraInizio();
                case 4:
                    return permesso.getOraFine();
                case 5:
                    return permesso.getMotivazione();
                case 6:
                    return permesso.getStato();
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

    public void validaOraPermesso(LocalTime oldHour, LocalTime oraFine){
        LocalTime oraMax = LocalTime.of(18, 00);

        if (oraFine.isBefore(oldHour)) {
            throw new IllegalArgumentException("Ora Fine è precedente alla vecchia Ora Fine");
        }

        if (oraFine.isAfter(oraMax)) {
            throw new IllegalArgumentException("Ora Fine successivo alla fine dell'orario lavorativo");
        }
    }

}