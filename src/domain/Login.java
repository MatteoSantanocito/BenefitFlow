package domain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.border.EmptyBorder;

import ui.GestisciRuolo;
import ui.InserisciNuovoDipendente;
import ui.ScegliBenefit;
import ui.VisualizzaCongedi;

public class Login extends JPanel {

    private JLabel titolo, usernameLabel, passwordLabel;
    private JTextField usernameField, passwordField;
    private JButton login;
    private JPanel ammPanel, dipPanel, mainPanel;

    private BenefitFlow benefitFlow;

    public Login(BenefitFlow b) {
        this.benefitFlow = b;
        setLayout(new GridLayout(1, 1));
        mainPanel = new JPanel(new GridLayout(3, 1));
        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel formPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel(new FlowLayout()); 
        ammPanel = ammPanel();
        dipPanel = dipPanel();

        titolo = new JLabel("BenefitFlow");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titolo.setHorizontalAlignment(JLabel.LEFT);
        titlePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        titlePanel.add(titolo);

        JPanel usernamePanel = new JPanel(new FlowLayout());
        usernameLabel = new JLabel("Username");
        usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        formPanel.add(usernamePanel);
        formPanel.add(passwordPanel);

        login = new JButton("Login");
        buttonPanel.add(login);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

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
                           break;
                        }
                    }
                    if (nome.equals(username)) {
                        SwingUtilities.invokeLater(() -> {
                            remove(mainPanel);
                            add(dipPanel);
                            revalidate();
                            repaint();
                        });
                    }else{
                        System.out.println("Password errata o dipendente non registrato.");
                    }
                }
                
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        mainPanel.add(titlePanel);
        mainPanel.add(formPanel);
        mainPanel.add(buttonPanel);
        add(mainPanel);
    }

    public JPanel ammPanel(){
        JPanel ammPanel = new JPanel(new GridLayout(3, 1));
        JPanel choisePanelAmm = new JPanel(new GridLayout(3,2));
        JPanel buttonLogoutPanelAmm = new JPanel(new FlowLayout());

        JLabel textAmm = new JLabel("Sezione amministratore. Scegli cosa fare");
        textAmm.setFont(new Font("Arial", Font.BOLD, 15));
        textAmm.setHorizontalAlignment(JLabel.CENTER);

        JButton inserisciDipendente = new JButton("Inserisci Dipendente");
        JButton mostraCongedi = new JButton("Visualizza congedi");
        JButton gestisciRuolo = new JButton("Gestisci Ruolo");
        JButton gestisciBenefit = new JButton("Gestisci Benefit");
        JButton gestisciBP = new JButton("Assegna Buoni Pasto");
        choisePanelAmm.add(inserisciDipendente);
        choisePanelAmm.add(mostraCongedi);
        choisePanelAmm.add(gestisciRuolo);
        choisePanelAmm.add(gestisciBenefit);
        choisePanelAmm.add(gestisciBP);

        JButton logoutAmm = new JButton("LogOut");
        buttonLogoutPanelAmm.add(logoutAmm);
        buttonLogoutPanelAmm.setBorder(new EmptyBorder(20, 0, 0, 0));

        logoutAmm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        return ammPanel;
    }

    public JPanel dipPanel(){
        JPanel dipPanel = new JPanel(new GridLayout(3, 1));
        JPanel choisePanelDip = new JPanel(new FlowLayout());
        JPanel buttonLogoutPanelDip = new JPanel(new FlowLayout());

        JLabel textDip = new JLabel("Sezione dipendente. Scegli cosa fare");
        textDip.setFont(new Font("Arial", Font.BOLD, 15));
        textDip.setHorizontalAlignment(JLabel.CENTER);

        JButton inserisciBenefit = new JButton("Inserisci Benefit");
        choisePanelDip.add(inserisciBenefit);

        JButton logoutDip = new JButton("LogOut");
        buttonLogoutPanelDip.add(logoutDip);
        buttonLogoutPanelDip.setBorder(new EmptyBorder(20, 0, 0, 0));

        logoutDip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    remove(dipPanel);
                    add(mainPanel);
                    revalidate();
                    repaint();
                });
            }
        });

        inserisciBenefit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScegliBenefit(benefitFlow);
            }
        });

        dipPanel.add(textDip);
        dipPanel.add(choisePanelDip);
        dipPanel.add(buttonLogoutPanelDip);
        
        return dipPanel;
    }
}
