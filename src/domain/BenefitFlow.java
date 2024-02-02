package domain;

import ui.InserisciNuovoDipendente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class BenefitFlow extends JFrame {

    private static BenefitFlow benefitFlow;
    private Dipendente dipendenteCorrente;
    private Map<String, Ruolo> ruoli;
    private Map<String, Dipendente> elencoDipendenti;
    private Login login;

    public BenefitFlow(){
        this.elencoDipendenti = new HashMap<>();
        this.ruoli = new HashMap<>();
        loadRuoli();
        initComponent();
    }

    public void initComponent() {
        login  = new Login();

        setLayout(new BorderLayout());
        add(login,BorderLayout.CENTER);

        setVisible(true);
        setTitle("BenefitFlow");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //PANNELLO AMMINISTRATORE
        JPanel ammPanel = new JPanel(new GridLayout(3, 1));
        JPanel choisePanelAmm = new JPanel(new GridLayout(3,2));
        JPanel buttonLogoutPanelAmm = new JPanel(new FlowLayout());

        JLabel textAmm = new JLabel("Sezione amministratore. Scegli cosa fare");
        textAmm.setFont(new Font("Arial", Font.BOLD, 15));
        textAmm.setHorizontalAlignment(JLabel.CENTER);

        JButton inserisciDipendente = new JButton("Inserisci Dipendente");
        JButton mostraDipendente = new JButton("Visualizza congedi");
        JButton gestisciRuolo = new JButton("Gestisci Ruolo");
        JButton gestisciBenefit = new JButton("Gestisci Benefit");
        JButton gestisciBP = new JButton("Assegna Buoni Pasto");
        choisePanelAmm.add(inserisciDipendente);
        choisePanelAmm.add(mostraDipendente);
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
                    add(login, BorderLayout.CENTER);
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
                new InserisciNuovoDipendente(ruoli,benefitFlow);
            }
        });


        //PANNELLO DIPENDENTE
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
                    add(login, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                });
            }
        });

        dipPanel.add(textDip);
        dipPanel.add(choisePanelDip);
        dipPanel.add(buttonLogoutPanelDip);


        login.setFormListener(new FormListener() {
            @Override
            public void formEventListener(FormEvent fe) {
                String name = fe.getUsername();
                String pass = fe.getPassword();
                if("admin".equals(name) && "admin".equals(pass)){
                    SwingUtilities.invokeLater(() -> {
                        remove(login);
                        add(ammPanel, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    });
                }else{
                    try (BufferedReader reader = new BufferedReader(new FileReader("src/fileTesto/dipendenti.txt"))) {
                        String line;

                        while ((line = reader.readLine()) != null) {
                            String[] campi = line.split(", ");

                            String nome = null;
                            String matricola = null;

                            for (String campo : campi) {
                                if (campo.startsWith("Nome")) {
                                    nome = campo.split(":")[1].trim();
                                } else if (campo.startsWith("Matricola")) {
                                    matricola = campo.split(":")[1].trim();
                                }
                            }

                            if (name.equals(nome) && pass.equals(matricola)) {
                                SwingUtilities.invokeLater(() -> {
                                    remove(login);
                                    add(dipPanel, BorderLayout.CENTER);
                                    revalidate();
                                    repaint();
                                });
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public static BenefitFlow getInstance(){
        if(benefitFlow == null){
            benefitFlow = new BenefitFlow();
        }else{
            System.out.println("Istanza già creata");
        }
        return benefitFlow;
    }

    boolean flag = false;
    public Dipendente inserisciNuovoDipendente(String nome, String cognome, String dataDiNascita, String matricola, String codiceRuolo){
        Ruolo r = ruoli.get(codiceRuolo);
        Dipendente d = elencoDipendenti.get(matricola);
        if(d == null){
            if(r != null){
                this.dipendenteCorrente = new Dipendente(nome,cognome,dataDiNascita,matricola,r);
                System.out.println("Dipendente inserito correttamente!");
            }else{
                if (codiceRuolo != null) {
                    this.dipendenteCorrente = new Dipendente(nome, cognome, dataDiNascita, matricola);
                    System.out.println("Il ruolo inserito non esiste. Il dipendente è stato comunque inserito senza ruolo.");
                }else{
                    this.dipendenteCorrente = new Dipendente(nome, cognome, dataDiNascita, matricola);
                    System.out.println("Dipendente insertito correttamente senza ruolo!");
                }
            }
        }else{
            flag = true;
            System.out.println("Esiste già un dipendente con matricola " + matricola);
        }
        return dipendenteCorrente;
    }

    public void confermaInserimento(){
        if(dipendenteCorrente != null && !flag){
            this.elencoDipendenti.put(dipendenteCorrente.getMatricola(),dipendenteCorrente);
            System.out.println("Lista dipendenti aggiornata con successo!");
        }
        flag = false;
    }

    public List<Dipendente> mostraDipendenti(){
        List<Dipendente> listDipendenti = new ArrayList<>();
        listDipendenti.addAll(elencoDipendenti.values());
        return listDipendenti;
    }

    public void gestisciRuolo(String matricola, String codiceRuolo){
        Dipendente d = elencoDipendenti.get(matricola);
        if(d != null){
            Ruolo r = d.getRuolo();
            if(r == null){
                Ruolo ruolo = ruoli.get(codiceRuolo);
                d.setRuolo(ruolo);
                System.out.println("Ruolo assegnato correttamente.");
            } else {
                Ruolo ruolo = ruoli.get(codiceRuolo);
                d.setRuolo(ruolo);
                System.out.println("Ruolo modificato correttamente.");
            }
        }
    }

    public void confermaRuolo(){
        if(dipendenteCorrente != null){
            System.out.println("Il nuovo ruolo assegnato al dipendente " + dipendenteCorrente.getMatricola() + " è: " + dipendenteCorrente.getRuolo());
        }
    }

    public void loadRuoli() {
        Ruolo r1 = new Ruolo("ing01", "dataAnalist", 1);
        Ruolo r2 = new Ruolo("ing02", "dataAnalist", 2);
        Ruolo r3 = new Ruolo("ing03", "dataAnalist", 3);
        Ruolo r4 = new Ruolo("inf01", "javaDev", 1);
        Ruolo r5 = new Ruolo("inf02", "javaDev", 2);
        Ruolo r6 = new Ruolo("inf03", "javaDev", 3);
        Ruolo r7 = new Ruolo("ing04", "softwareDev", 1);
        Ruolo r8 = new Ruolo("ing05", "softwareDev", 2);
        Ruolo r9 = new Ruolo("ing06", "softwareDev", 3);
        this.ruoli.put("ing01", r1);
        this.ruoli.put("ing02", r2);
        this.ruoli.put("ing03", r3);
        this.ruoli.put("inf01", r4);
        this.ruoli.put("inf02", r5);
        this.ruoli.put("inf03", r6);
        this.ruoli.put("ing04", r7);
        this.ruoli.put("ing05", r8);
        this.ruoli.put("ing06", r9);
        System.out.println("Caricamento Ruoli ultimato");
    }
}