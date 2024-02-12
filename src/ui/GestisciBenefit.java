package ui;

import domain.Benefit;
import domain.BenefitFlow;
import domain.Ferie;
import domain.Permesso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestisciBenefit extends JFrame {
    private JLabel titolo;
    private BenefitFlow benefitFlow;
    private JButton pressed;
    private JTextField errorFieldCode, errorFieldState;

    public GestisciBenefit(BenefitFlow b) {
        this.benefitFlow = b;
        initComponent();
    }

    private void initComponent() {

        setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JPanel tablePanel = new JPanel(new GridBagLayout());


        //titolo generico
        JPanel titlePanel = new JPanel(new FlowLayout());
        titolo = new JLabel("Gestisci Benefit");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titolo);


        //primo pannello per scelta benefit
        JPanel chosePanel = new JPanel(new GridLayout(2,1));
        chosePanel.setBorder(new EmptyBorder(0, 0, 40, 0));

        JPanel subtitlePanelBenefit = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleChoseBenefit = new JLabel("1. Scegli il tipo di Benefit");
        titleChoseBenefit.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitlePanelBenefit.add(titleChoseBenefit);

        JPanel buttonPanelBenefit = new JPanel(new FlowLayout());
        JButton visualizzaFerie = new JButton("Visualizza Ferie");
        JButton visualizzaPermesso = new JButton("Visualizza Permesso");
        buttonPanelBenefit.add(visualizzaFerie);
        buttonPanelBenefit.add(visualizzaPermesso);

        chosePanel.add(subtitlePanelBenefit);
        chosePanel.add(buttonPanelBenefit);


        //secondo pannello inserimento codice
        JPanel insertCodePanel = new JPanel(new GridLayout(3,1));

        JPanel subTitlePanelCode = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleCode = new JLabel("2. Inserisci il codice");
        titleCode.setFont(new Font("Arial", Font.PLAIN, 15));
        subTitlePanelCode.add(titleCode);

        JPanel codePanel = new JPanel(new FlowLayout());
        JTextField code = new JTextField(3);
        JButton confermaCodice = new JButton("Conferma codice");
        code.setEnabled(false);
        confermaCodice.setEnabled(false);
        codePanel.add(code);
        codePanel.add(confermaCodice);

        JPanel errorPanelCode = new JPanel(new FlowLayout());
        errorPanelCode.setBorder(new EmptyBorder(10, 0, 0, 0));
        errorFieldCode = new JTextField(25);
        errorFieldCode.setBorder(null);
        errorFieldCode.setBackground(new Color(238, 238, 238));
        errorFieldCode.setFont(new Font("Arial", Font.ITALIC, 12));
        errorFieldCode.setEnabled(false);
        errorFieldCode.setDisabledTextColor(Color.RED);
        errorPanelCode.add(errorFieldCode);

        insertCodePanel.add(subTitlePanelCode);
        insertCodePanel.add(codePanel);
        insertCodePanel.add(errorPanelCode);


        //terzo pannello conferma stato
        JPanel insertStatePanel = new JPanel(new GridLayout(3,1));
        insertStatePanel.setBorder(new EmptyBorder(0, 0, 40, 0));

        JPanel subTitlePanelState = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleState = new JLabel("3. Inserisci lo stato");
        titleState.setFont(new Font("Arial", Font.PLAIN, 15));
        subTitlePanelState.add(titleState);

        JPanel statePanel = new JPanel(new FlowLayout());
        JTextField state = new JTextField(10);
        JButton confermaStato = new JButton("Conferma stato");
        state.setEnabled(false);
        confermaStato.setEnabled(false);
        statePanel.add(state);
        statePanel.add(confermaStato);

        JPanel errorPanelState = new JPanel(new FlowLayout());
        errorPanelState.setBorder(new EmptyBorder(10, 0, 0, 0));
        errorFieldState = new JTextField(25);
        errorFieldState.setBorder(null);
        errorFieldState.setBackground(new Color(238, 238, 238));
        errorFieldState.setFont(new Font("Arial", Font.ITALIC, 12));
        errorFieldState.setEnabled(false);
        errorFieldState.setDisabledTextColor(Color.RED);
        errorPanelState.add(errorFieldState);

        insertStatePanel.add(subTitlePanelState);
        insertStatePanel.add(statePanel);
        insertStatePanel.add(errorPanelState);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        formPanel.add(titlePanel,gbc);
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(chosePanel,gbc);
        gbc.gridy = 2;
        gbc.weighty = 0;
        formPanel.add(insertCodePanel,gbc);
        gbc.gridy = 3;
        gbc.weighty = 0;
        formPanel.add(insertStatePanel,gbc);


        //tabelle
        JPanel contenitoreBenefit = new JPanel(new GridLayout(1,1));
        JPanel contenitoreBenefitSovrapposti = new JPanel(new GridLayout(1,1));

        JPanel titleTableBenefit = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleTableBenefit.setPreferredSize(new Dimension(750,22));
        JLabel titleList = new JLabel("Lista Benefit");
        titleList.setFont(new Font("Arial", Font.BOLD, 15));
        titleTableBenefit.add(titleList);
        contenitoreBenefit.setPreferredSize(new Dimension(750,200));
        contenitoreBenefit.setBackground(Color.WHITE);

        JPanel titleTableBenefitSovrapposti = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleTableBenefitSovrapposti.setPreferredSize(new Dimension(750,22));
        JLabel titleListSovrapposti = new JLabel("Lista Benefit sovrapposti");
        titleListSovrapposti.setFont(new Font("Arial", Font.BOLD, 15));
        titleTableBenefitSovrapposti.add(titleListSovrapposti);
        contenitoreBenefitSovrapposti.setPreferredSize(new Dimension(750,200));
        contenitoreBenefitSovrapposti.setBackground(Color.WHITE);

        HeaderRenderer headerRenderer = new HeaderRenderer();

        List<Benefit> listaF = benefitFlow.mostraBenefit("F");
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

        List<Benefit> listaP = benefitFlow.mostraBenefit("P");
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

        visualizzaFerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressed = (JButton) e.getSource();
                SwingUtilities.invokeLater(() -> {
                    contenitoreBenefit.remove(scrollPaneP);
                    contenitoreBenefit.add(scrollPaneF);
                    contenitoreBenefitSovrapposti.removeAll();
                    revalidate();
                    repaint();
                });
                code.setEnabled(true);
                confermaCodice.setEnabled(true);
            }
        });

        visualizzaPermesso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressed = (JButton) e.getSource();
                SwingUtilities.invokeLater(() -> {
                    contenitoreBenefit.remove(scrollPaneF);
                    contenitoreBenefit.add(scrollPaneP);
                    contenitoreBenefitSovrapposti.removeAll();
                    revalidate();
                    repaint();
                });
                code.setEnabled(true);
                confermaCodice.setEnabled(true);
            }
        });

        confermaCodice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HeaderRenderer headerRenderer = new HeaderRenderer();
                JScrollPane scrollPane_S;
                visualizzaFerie.setEnabled(false);
                visualizzaPermesso.setEnabled(false);

                List<Benefit> listFerieElab = benefitFlow.mostraBenefit("F");
                List<Benefit> listPermessiElab = benefitFlow.mostraBenefit("P");

                String stringCode = code.getText();

                if(!stringCode.isEmpty()){
                    int codice = Integer.parseInt(stringCode);
                    if(pressed == visualizzaFerie){
                        boolean flagPremuto = false;
                        for (Benefit f : listFerieElab){
                            if(f.getCodice() == codice){
                                flagPremuto = true;
                                List<Benefit> listaSovrapposti = benefitFlow.sovrapposizioneBenefit(codice);

                                JTable tableF_S = new JTable(new TabellaFerie(listaSovrapposti));
                                tableF_S.getTableHeader().setDefaultRenderer(headerRenderer);
                                scrollPane_S = new JScrollPane(tableF_S);
                                scrollPane_S.setBorder(null);
                                TableColumn colonnaCodiceF = tableF_S.getColumnModel().getColumn(0);
                                colonnaCodiceF.setMaxWidth(65);
                                TableColumn colonnaDataInizioF = tableF_S.getColumnModel().getColumn(2);
                                colonnaDataInizioF.setMinWidth(95);
                                colonnaDataInizioF.setMaxWidth(95);
                                TableColumn colonnaDataFineF = tableF_S.getColumnModel().getColumn(3);
                                colonnaDataFineF.setMinWidth(95);
                                colonnaDataFineF.setMaxWidth(95);
                                TableColumn colonnaMotivazioneF = tableF_S.getColumnModel().getColumn(4);
                                colonnaMotivazioneF.setPreferredWidth(220);
                                TableColumn colonnaStatoF = tableF_S.getColumnModel().getColumn(5);
                                colonnaStatoF.setPreferredWidth(100);
                                tableF_S.setSelectionBackground(new Color(119, 119, 119, 255));
                                tableF_S.setFocusable(false);

                                SwingUtilities.invokeLater(() -> {
                                    contenitoreBenefitSovrapposti.removeAll();
                                    contenitoreBenefitSovrapposti.add(scrollPane_S);
                                    revalidate();
                                    repaint();
                                });

                                state.setEnabled(true);
                                confermaStato.setEnabled(true);

                                errorFieldCode.setText("");
                                break;
                            }
                        }
                        if (!flagPremuto){
                            SwingUtilities.invokeLater(() -> {
                                contenitoreBenefitSovrapposti.removeAll();
                                revalidate();
                                repaint();
                            });

                            state.setEnabled(false);
                            confermaStato.setEnabled(false);

                            errorFieldCode.setText("Codice non valido");
                            code.setText("");
                        }
                    }else if(pressed == visualizzaPermesso){
                        boolean flagPremuto = false;
                        for (Benefit p : listPermessiElab){
                            if(p.getCodice() == codice){
                                flagPremuto = true;
                                List<Benefit> listaSovrapposti = benefitFlow.sovrapposizioneBenefit(codice);

                                JTable tableP_S = new JTable(new TabellaPermessi(listaSovrapposti));
                                tableP_S.getTableHeader().setDefaultRenderer(headerRenderer);
                                scrollPane_S = new JScrollPane(tableP_S);
                                scrollPane_S.setBorder(null);
                                TableColumn colonnaCodiceP = tableP_S.getColumnModel().getColumn(0);
                                colonnaCodiceP.setMaxWidth(65);
                                TableColumn colonnaMotivazioneP = tableP_S.getColumnModel().getColumn(5);
                                colonnaMotivazioneP.setPreferredWidth(180);
                                TableColumn colonnaDataP = tableP_S.getColumnModel().getColumn(2);
                                colonnaDataP.setMinWidth(95);
                                colonnaDataP.setMaxWidth(95);
                                TableColumn colonnaOraInizioP = tableP_S.getColumnModel().getColumn(3);
                                colonnaOraInizioP.setMinWidth(70);
                                colonnaOraInizioP.setMaxWidth(70);
                                TableColumn colonnaOraFineP = tableP_S.getColumnModel().getColumn(4);
                                colonnaOraFineP.setMinWidth(70);
                                colonnaOraFineP.setMaxWidth(70);
                                TableColumn colonnaStatoP = tableP_S.getColumnModel().getColumn(6);
                                colonnaStatoP.setPreferredWidth(100);
                                tableP_S.setSelectionBackground(new Color(119, 119, 119, 255));
                                tableP_S.setFocusable(false);

                                SwingUtilities.invokeLater(() -> {
                                    contenitoreBenefitSovrapposti.removeAll();
                                    contenitoreBenefitSovrapposti.add(scrollPane_S);
                                    revalidate();
                                    repaint();
                                });

                                state.setEnabled(true);
                                confermaStato.setEnabled(true);

                                errorFieldCode.setText("");
                                break;
                            }
                        }
                        if (!flagPremuto){
                            System.out.println("Codice non trovato");
                            SwingUtilities.invokeLater(() -> {
                                contenitoreBenefitSovrapposti.removeAll();
                                revalidate();
                                repaint();
                            });

                            state.setEnabled(false);
                            confermaStato.setEnabled(false);

                            errorFieldCode.setText("Codice non valido");
                            code.setText("");
                        }
                    }
                }else{
                    errorFieldCode.setText("Riempire il campo");
                    state.setEnabled(false);
                    confermaStato.setEnabled(false);
                }
            }
        });


        confermaStato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stato = state.getText();
                if(!stato.isEmpty()){
                    if((stato.equals("approvato") || stato.equals("non approvato"))){
                        benefitFlow.confermaBenefit(stato);
                        GestisciBenefit.this.dispose();
                    }else{
                        errorFieldState.setText("inserire 'approvato' o 'non approvato'");
                        state.setText("");
                        code.setEnabled(false);
                        confermaCodice.setEnabled(false);
                    }
                }else{
                    errorFieldState.setText("Riempire il campo");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        tablePanel.add(titleTableBenefit,gbc);
        gbc.gridy = 1;
        tablePanel.add(contenitoreBenefit,gbc);
        gbc.gridy = 2;
        tablePanel.add(titleTableBenefitSovrapposti,gbc);
        gbc.gridy = 3;
        tablePanel.add(contenitoreBenefitSovrapposti,gbc);

        add(formPanel, BorderLayout.LINE_START);
        add(tablePanel, BorderLayout.CENTER);

        setResizable(true);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    static class TabellaFerie extends AbstractTableModel {
        private final List<Benefit> lista;
        private final String[] colonne = {"Codice", "Matricola", "Data Inizio", "Data Fine", "Motivazione", "Stato"};

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
        private final String[] colonne = {"Codice", "Matricola", "Data", "Ora Inizio", "Ora Fine", "Motivazione", "Stato"};

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
