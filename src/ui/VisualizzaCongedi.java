package ui;


import domain.Benefit;
import domain.BenefitFlow;
import domain.Ferie;
import domain.Permesso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisualizzaCongedi extends JFrame{
    private JLabel titolo;
    private BenefitFlow benefitFlow;

    public VisualizzaCongedi(BenefitFlow b) {
        this.benefitFlow = b;
        initComponent();
    }

    private void initComponent() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 0, 10);

        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel contenitore = new JPanel(new GridLayout(1,1));

        titolo = new JLabel("Visualizza Congedi");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        titlePanel.add(titolo);

        JButton visualizzaFerie = new JButton("Visualizza Ferie");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        buttonPanel.add(visualizzaFerie);

        JButton visualizzaPermesso = new JButton("Visualizza Permesso");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        buttonPanel.add(visualizzaPermesso);

        contenitore.setPreferredSize(new Dimension(700,200));
        contenitore.setBackground(Color.WHITE);


        HeaderRenderer headerRenderer = new HeaderRenderer();

        List<Benefit> listaF = benefitFlow.visualizzaCongediComplessivi("F");
        JTable tableF = new JTable(new TabellaFerie(listaF));
        tableF.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPaneF = new JScrollPane(tableF);
        scrollPaneF.setBorder(null);
        // TableColumn colonnaMotivazioneF = tableF.getColumnModel().getColumn(3);
        // colonnaMotivazioneF.setPreferredWidth(180);
        tableF.setSelectionBackground(new Color(119, 119, 119, 255));

        List<Benefit> listaP = benefitFlow.visualizzaCongediComplessivi("P");
        JTable tableP = new JTable(new TabellaPermessi(listaP));
        tableP.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPaneP = new JScrollPane(tableP);
        scrollPaneP.setBorder(null);
        // TableColumn colonnaMotivazioneP = tableP.getColumnModel().getColumn(3);
        // colonnaMotivazioneP.setPreferredWidth(180);
        tableP.setSelectionBackground(new Color(119, 119, 119, 255));


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(titlePanel, gbc);
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(buttonPanel, gbc);
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(contenitore, gbc);

        visualizzaFerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    contenitore.remove(scrollPaneP);
                    contenitore.add(scrollPaneF);
                    revalidate();
                    repaint();
                });
            }
        });

        visualizzaPermesso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    contenitore.remove(scrollPaneF);
                    contenitore.add(scrollPaneP);
                    revalidate();
                    repaint();
                });
            }
        });

        setResizable(true);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(850, 515);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    static class TabellaFerie extends AbstractTableModel {
        private final List<Benefit> lista;
        private final String[] colonne = {"Matricola", "Data Inizio", "Data Fine", "Motivazione", "Tipo", "Codice", "Stato"};

        public TabellaFerie(List<Benefit> lista) {
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
                    return ferie.getMatricola();
                case 1:
                    return ferie.getDataInizio();
                case 2:
                    return ferie.getDataFine();
                case 3:
                    return ferie.getMotivazione();
                case 4:
                    return ferie.getTipo();
                case 5:
                    return ferie.getCodice();
                case 6:
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

    static class TabellaPermessi extends AbstractTableModel {
        private final List<Benefit> lista;
        private final String[] colonne = {"Matricola", "Data", "Ora Inizio", "Ora Fine", "Motivazione", "Tipo", "Codice", "Stato"};

        public TabellaPermessi(List<Benefit> lista) {
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
                    return permesso.getMatricola();
                case 1:
                    return permesso.getData();
                case 2:
                    return permesso.getOraInizio();
                case 3:
                    return permesso.getOraFine();
                case 4:
                    return permesso.getMotivazione();
                case 5:
                    return permesso.getTipo();
                case 6:
                    return permesso.getCodice();
                case 7:
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

}
