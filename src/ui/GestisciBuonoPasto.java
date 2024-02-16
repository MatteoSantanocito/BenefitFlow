package ui;

import domain.BenefitFlow;
import domain.BuonoPasto;
import domain.Dipendente;

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
import java.util.List;

public class GestisciBuonoPasto extends JFrame {

    private JLabel titolo;
    private JLabel codiceLabel;
    private JTextField codiceField, errorField;
    private JButton confermaButton;
    private BenefitFlow benefitFlow;

    private Dipendente dipendente;

    public GestisciBuonoPasto(BenefitFlow b, Dipendente d) {
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

        titolo = new JLabel("Attiva i tuoi buoni pasto");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0));
        titlePanel.add(titolo);

        List<BuonoPasto> lista = benefitFlow.visualizzaBuoniPastoValidi(dipendente.getMatricola());
        JTable table = new JTable(new TabellaModello(lista));
        HeaderRenderer headerRenderer = new HeaderRenderer();
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700,200));
        table.setFocusable(false);
        TableColumn colonnaDataScadenza = table.getColumnModel().getColumn(3);
        colonnaDataScadenza.setMinWidth(105);
        colonnaDataScadenza.setMaxWidth(105);
        table.setSelectionBackground(new Color(119, 119, 119, 255));

        codiceLabel = new JLabel("Codice");
        codiceField = new JTextField(3);
        codiceField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
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

        confermaButton = new JButton("Conferma");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(codiceField, gbc);
        gbc.gridx = 2;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(confermaButton, gbc);


        formPanel.setBorder(new EmptyBorder(10, 0, 5, 0));

        JPanel errorPanel = new JPanel(new FlowLayout());
        errorPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        errorField = new JTextField(28);
        errorField.setBorder(null);
        errorField.setBackground(new Color(238, 238, 238));
        errorField.setFont(new Font("Arial", Font.ITALIC, 12));
        errorField.setHorizontalAlignment(SwingConstants.CENTER);
        errorField.setEnabled(false);
        errorField.setDisabledTextColor(Color.RED);
        errorPanel.add(errorField);


        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean trovato = false;
                if (!codiceField.getText().isEmpty()) {
                    for(BuonoPasto bp : lista){
                        if (bp.getCodiceBP().equals(codiceField.getText())) {
                            trovato = true;
                            break;
                        }
                    }

                    if (trovato) {
                        benefitFlow.confermaAttivazione(codiceField.getText());
                        codiceField.setText("");
                        GestisciBuonoPasto.this.dispose();
                    }else{
                        errorField.setText("Codice non valido");
                    }


                } else {
                    System.out.println("Compilare tutti i campi.");
                    errorField.setText("Compila il campo");
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
//        gbc.gridy = 4;
//        gbc.weighty = 0.1;
//        add(buttonPanel, gbc);

        setResizable(false);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    static class TabellaModello extends AbstractTableModel {
        private final List<BuonoPasto> lista;
        private final String[] colonne = {"Codice", "Matricola", "Valore", "Data di scadenza", "Stato"};

        public TabellaModello(List<BuonoPasto> lista) {
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
            BuonoPasto buonoPasto = lista.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return buonoPasto.getCodiceBP();
                case 1:
                    return buonoPasto.getMatricola();
                case 2:
                    String valore = String.format("%.2f", buonoPasto.getValore()) + "€";
                    return valore;
                case 3:
                    return buonoPasto.getDataScadenza();
                case 4:
                    return buonoPasto.getStato();
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