package ui;

import domain.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisualizzaCongediPersonali extends JFrame{
    private JLabel titolo;
    private BenefitFlow benefitFlow;
    private Dipendente dipendente;

    public VisualizzaCongediPersonali(BenefitFlow b, Dipendente d) {
        this.benefitFlow = b;
        this.dipendente = d;
        initComponent();
    }

    private void initComponent() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 0, 10);

        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel contenitore = new JPanel(new GridLayout(1,1));

        titolo = new JLabel("Elenco Congedi");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        titlePanel.add(titolo);

        JButton visualizzaFerie = new JButton("Visualizza ferie");
        buttonPanel.add(visualizzaFerie);

        JButton visualizzaPermessi = new JButton("Visualizza permessi");
        buttonPanel.add(visualizzaPermessi);

        contenitore.setPreferredSize(new Dimension(700,200));
        contenitore.setBackground(Color.WHITE);

        HeaderRenderer headerRenderer = new HeaderRenderer();

        List<Benefit> listaF = benefitFlow.visualizzaCongedi("F", dipendente.getMatricola());
        JTable tableF = new JTable(new TabellaFerie(listaF));
        tableF.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPaneF = new JScrollPane(tableF);
        scrollPaneF.setBorder(null);
        TableColumn colonnaCodiceF = tableF.getColumnModel().getColumn(0);
        colonnaCodiceF.setMaxWidth(65);
        TableColumn colonnaDataInizioF = tableF.getColumnModel().getColumn(2);
        colonnaDataInizioF.setMinWidth(95);
        colonnaDataInizioF.setMaxWidth(95);
        TableColumn colonnaDataFineF = tableF.getColumnModel().getColumn(3);
        colonnaDataFineF.setMinWidth(95);
        colonnaDataFineF.setMaxWidth(95);
        TableColumn colonnaMotivazioneF = tableF.getColumnModel().getColumn(4);
        colonnaMotivazioneF.setPreferredWidth(220);
        TableColumn colonnaStatoF = tableF.getColumnModel().getColumn(5);
        colonnaStatoF.setPreferredWidth(100);
        tableF.setSelectionBackground(new Color(119, 119, 119, 255));
        tableF.setFocusable(false);

        List<Benefit> listaP = benefitFlow.visualizzaCongedi("P", dipendente.getMatricola());
        JTable tableP = new JTable(new TabellaPermessi(listaP));
        tableP.getTableHeader().setDefaultRenderer(headerRenderer);
        JScrollPane scrollPaneP = new JScrollPane(tableP);
        scrollPaneP.setBorder(null);
        TableColumn colonnaCodiceP = tableP.getColumnModel().getColumn(0);
        colonnaCodiceP.setMaxWidth(65);
        TableColumn colonnaMotivazioneP = tableP.getColumnModel().getColumn(5);
        colonnaMotivazioneP.setPreferredWidth(180);
        TableColumn colonnaDataP = tableP.getColumnModel().getColumn(2);
        colonnaDataP.setMinWidth(95);
        colonnaDataP.setMaxWidth(95);
        TableColumn colonnaOraInizioP = tableP.getColumnModel().getColumn(3);
        colonnaOraInizioP.setMinWidth(70);
        colonnaOraInizioP.setMaxWidth(70);
        TableColumn colonnaOraFineP = tableP.getColumnModel().getColumn(4);
        colonnaOraFineP.setMinWidth(70);
        colonnaOraFineP.setMaxWidth(70);
        TableColumn colonnaStatoP = tableP.getColumnModel().getColumn(6);
        colonnaStatoP.setPreferredWidth(100);
        tableP.setSelectionBackground(new Color(119, 119, 119, 255));
        tableP.setFocusable(false);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0.2;
        add(titlePanel, gbc);
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(buttonPanel, gbc);
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.weighty = 0.8;
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

        visualizzaPermessi.addActionListener(new ActionListener() {
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
        setSize(850, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    static class TabellaFerie extends AbstractTableModel {
        private final List<Benefit> lista;
        private final String[] colonne = {"Codice", "Matricola", "Data inizio", "Data fine", "Motivazione", "Stato"};

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

    static class TabellaPermessi extends AbstractTableModel {
        private final List<Benefit> lista;
        private final String[] colonne = {"Codice", "Matricola", "Data", "Ora inizio", "Ora fine", "Motivazione", "Stato"};

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

}
