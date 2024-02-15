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
import domain.Dipendente;

public class ScegliBenefitModifica extends JFrame{

    private BenefitFlow benefitFlow;
    private Dipendente dipendente;

    public ScegliBenefitModifica(BenefitFlow b, Dipendente d){
        this.benefitFlow = b;
        this.dipendente = d;
        initComponent();
    }

    private void initComponent(){
        setLayout(new GridLayout(2, 1));
        JPanel choisePanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JLabel textDip = new JLabel("Scegli quale congedo vuoi modificare");
        textDip.setFont(new Font("Arial", Font.BOLD, 15));
        textDip.setHorizontalAlignment(JLabel.CENTER);

        JButton prolugamentoFerie = new JButton("Prolunga ferie");
        buttonPanel.add(prolugamentoFerie);

        JButton prolungamentoPermesso = new JButton("Prolunga permesso");
        buttonPanel.add(prolungamentoPermesso);

        prolugamentoFerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProlungamentoFerie(benefitFlow, dipendente);
            }
        });

        prolungamentoPermesso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProlungamentoPermesso(benefitFlow, dipendente);
            }
        });

        add(textDip);
        add(choisePanel);
        choisePanel.add(buttonPanel);

        setResizable(false);
        setVisible(true);
        setTitle("BenefitFlow");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
