package ui;

import domain.BenefitFlow;
import domain.Dipendente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class VisualizzaDipendenti extends JFrame {

    private JLabel titolo;
    private BenefitFlow benefitFlow;

    public VisualizzaDipendenti(BenefitFlow b) {
        this.benefitFlow = b;
        initComponent();
    }

    private void initComponent() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel titlePanel = new JPanel(new FlowLayout());

        titolo = new JLabel("Elenco Dipendenti");
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(titlePanel, gbc);
        gbc.gridy = 1;
        gbc.weighty = 1;
        add(scrollPane, gbc);

        setResizable(false);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(850, 400);
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