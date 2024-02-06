package ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.BenefitFlow;

public class ScegliBenefit extends JFrame{

    private BenefitFlow benefitFlow;

    public ScegliBenefit(BenefitFlow b){
        this.benefitFlow = b;
        initComponent();
    }

    private void initComponent(){
        setLayout(new GridLayout(2, 1));
        JPanel choisePanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JLabel textDip = new JLabel("Scegli cosa fare.");
        textDip.setFont(new Font("Arial", Font.BOLD, 15));
        textDip.setHorizontalAlignment(JLabel.CENTER);

        JButton inserisciFerie = new JButton("Inserisci Ferie");
        buttonPanel.add(inserisciFerie);

        JButton inserisciPermesso = new JButton("Inserisci Permesso");
        buttonPanel.add(inserisciPermesso);

        inserisciFerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InserisciFerie(benefitFlow);
            }
        });

        inserisciPermesso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InserisciPermesso(benefitFlow);
            }
        });

        add(textDip);
        add(choisePanel);
        choisePanel.add(buttonPanel);

        setResizable(false);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
