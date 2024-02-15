package ui;

import domain.BenefitFlow;
import domain.BuonoPasto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class VisualizzaBuoniPasto extends JFrame {

    private JLabel titolo;
    private BenefitFlow benefitFlow;

    public VisualizzaBuoniPasto(BenefitFlow b) {
        this.benefitFlow = b;
        initComponent();
    }

    private void initComponent() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel titlePanel = new JPanel(new FlowLayout());

        titolo = new JLabel("Elenco Buoni Pasto");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0));
        titlePanel.add(titolo);

        List<BuonoPasto> lista = benefitFlow.visualizzaBuoniPasto();
        JTable table = new JTable(new TabellaModello(lista));
        HeaderRenderer headerRenderer = new HeaderRenderer();
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500,200));
        table.setFocusable(false);
        TableColumn colonnaDataScadenza = table.getColumnModel().getColumn(3);
        colonnaDataScadenza.setMinWidth(105);
        colonnaDataScadenza.setMaxWidth(105);
        table.setSelectionBackground(new Color(119, 119, 119, 255));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(titlePanel, gbc);
        gbc.gridy = 1;
        gbc.weighty = 1;
        add(scrollPane, gbc);

        setResizable(true);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(600, 400);
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
                    return "Creato";
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