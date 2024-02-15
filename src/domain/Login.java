package domain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.border.EmptyBorder;

import ui.*;

public class Login extends JPanel {

    private JLabel titolo, usernameLabel, passwordLabel;
    private JTextField usernameField, passwordField, errorField;
    private JButton login;
    private JPanel ammPanel, dipPanel, mainPanel;

    private BenefitFlow benefitFlow;
    private Dipendente d_corrente;

    public Login(BenefitFlow b) {
        this.benefitFlow = b;
        setLayout(new GridLayout(1, 1));

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 0, 10);

        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout()); 
        ammPanel = ammPanel();
        dipPanel = dipPanel();

        titolo = new JLabel("BenefitFlow");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titolo.setHorizontalAlignment(JLabel.LEFT);
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0));
        titlePanel.add(titolo);

        usernameLabel = new JLabel("Username");
        usernameField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(usernameField, gbc);

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        formPanel.add(passwordField, gbc);

        JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        errorPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
        errorField = new JTextField(27);
        errorField.setBorder(null);
        errorField.setBackground(new Color(238, 238, 238));
        errorField.setFont(new Font("Arial", Font.ITALIC, 12));
        errorField.setEnabled(false);
        errorField.setDisabledTextColor(Color.RED);
        errorPanel.add(errorField);

        login = new JButton("Login");
        buttonPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        buttonPanel.add(login);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (!username.equals("") && !password.equals("")) {
                    if("admin".equals(username) && "admin".equals(password)){
                        SwingUtilities.invokeLater(() -> {
                            remove(mainPanel);
                            add(ammPanel);
                            revalidate();
                            repaint();
                        });
                    }else{
                        List<Dipendente> d = benefitFlow.mostraDipendenti();
                        String nome = "";
                        for (Dipendente dipendente : d){
                            String matricola = dipendente.getMatricola();
                            if(matricola.equals(password)){
                               nome = dipendente.getNome();
                               d_corrente = dipendente;
                               break;
                            }
                        }
                        if (nome.equals("")) {
                            errorField.setText("Password errata");
                        } else if (nome.equals(username)) {
                            SwingUtilities.invokeLater(() -> {
                                remove(mainPanel);
                                add(dipPanel);
                                revalidate();
                                repaint();
                            });
                        }else{
                            errorField.setText("Username errato");
                        }
                    }
                    
                    usernameField.setText("");
                    passwordField.setText("");
                }else{
                    errorField.setText("Compilare tutti i campi");
                }

                
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        mainPanel.add(titlePanel,gbc);
        gbc.gridy = 1;
        gbc.weighty = 0.8;
        mainPanel.add(formPanel,gbc);
        gbc.gridy = 2;
        mainPanel.add(errorPanel, gbc);
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        mainPanel.add(buttonPanel,gbc);
        
        add(mainPanel);
    }

    public JPanel ammPanel(){
        JPanel ammPanel = new JPanel(new GridLayout(3, 1));
        JPanel choisePanelAmm = new JPanel(new GridLayout(4,2));
        JPanel buttonLogoutPanelAmm = new JPanel(new FlowLayout());

        JLabel textAmm = new JLabel("Sezione amministratore. Scegli cosa fare");
        textAmm.setFont(new Font("Arial", Font.BOLD, 15));
        textAmm.setHorizontalAlignment(JLabel.CENTER);

        JButton inserisciDipendente = new JButton("Inserisci Dipendente");
        JButton visualizzaDipendenti = new JButton("Visualizza Dipendenti");
        JButton mostraCongedi = new JButton("Visualizza Congedi");
        JButton gestisciRuolo = new JButton("Gestisci Ruolo");
        JButton gestisciCongedi = new JButton("Gestisci Congedi");
        JButton gestisciBP = new JButton("Assegna Buoni Pasto");
        JButton visualizzaBP = new JButton("Visualizza Buoni Pasto");
        choisePanelAmm.add(inserisciDipendente);
        choisePanelAmm.add(visualizzaDipendenti);
        choisePanelAmm.add(gestisciRuolo);
        choisePanelAmm.add(gestisciCongedi);
        choisePanelAmm.add(mostraCongedi);
        choisePanelAmm.add(gestisciBP);
        choisePanelAmm.add(visualizzaBP);

        JButton logoutAmm = new JButton("LogOut");
        buttonLogoutPanelAmm.add(logoutAmm);
        buttonLogoutPanelAmm.setBorder(new EmptyBorder(20, 0, 0, 0));

        logoutAmm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorField.setText("");
                SwingUtilities.invokeLater(() -> {
                    remove(ammPanel);
                    add(mainPanel);
                    revalidate();
                    repaint();
                });
            }
        });

        ammPanel.add(textAmm);
        ammPanel.add(choisePanelAmm);
        ammPanel.add(buttonLogoutPanelAmm);


        inserisciDipendente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InserisciNuovoDipendente(benefitFlow);
            }
        });

        visualizzaDipendenti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VisualizzaDipendenti(benefitFlow);
            }
        });

        gestisciRuolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestisciRuolo(benefitFlow);
            }
        });

        mostraCongedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VisualizzaCongedi(benefitFlow);
            }
        });

        gestisciCongedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestisciCongedi(benefitFlow);
            }
        });

        gestisciBP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InserisciBuonoPasto(benefitFlow);
            }
        });

        visualizzaBP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VisualizzaBuoniPasto(benefitFlow);
            }
        });

        return ammPanel;
    }

    public JPanel dipPanel(){
        JPanel dipPanel = new JPanel(new GridLayout(3, 1));
        JPanel choisePanelDip = new JPanel(new GridLayout(3, 2));
        JPanel buttonLogoutPanelDip = new JPanel(new FlowLayout());

        JLabel textDip = new JLabel("Sezione dipendente. Scegli cosa fare");
        textDip.setFont(new Font("Arial", Font.BOLD, 15));
        textDip.setHorizontalAlignment(JLabel.CENTER);

        JButton richiediCongedo = new JButton("Richiedi Congedo");
        JButton gestisciCongedo = new JButton("Gestisci Congedo");
        JButton visualizzaCongedi = new JButton("Visualizza Congedi");
        JButton gestisciBP = new JButton("Gestisci Buoni Pasto");
        JButton visualizzaBP = new JButton("Visualizza Buoni Pasto");
        choisePanelDip.add(richiediCongedo);
        choisePanelDip.add(gestisciCongedo);
        choisePanelDip.add(visualizzaCongedi);
        choisePanelDip.add(gestisciBP);
        choisePanelDip.add(visualizzaBP);

        JButton logoutDip = new JButton("LogOut");
        buttonLogoutPanelDip.add(logoutDip);
        buttonLogoutPanelDip.setBorder(new EmptyBorder(20, 0, 0, 0));

        logoutDip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorField.setText("");
                SwingUtilities.invokeLater(() -> {
                    remove(dipPanel);
                    add(mainPanel);
                    revalidate();
                    repaint();
                });
            }
        });

        richiediCongedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScegliBenefit(benefitFlow, d_corrente);
            }
        });

        gestisciCongedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScegliBenefitModifica(benefitFlow, d_corrente);
            }
        });

        visualizzaCongedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VisualizzaCongediPersonali(benefitFlow, d_corrente);
            }
        });

        dipPanel.add(textDip);
        dipPanel.add(choisePanelDip);
        dipPanel.add(buttonLogoutPanelDip);
        
        return dipPanel;
    }
}
