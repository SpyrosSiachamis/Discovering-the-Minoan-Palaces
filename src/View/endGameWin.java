package src.View;

import src.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class endGameWin extends JFrame {
    boolean tie = false;
    Player plr;
    public endGameWin(Player plr1, Player plr2) {
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        if (plr1.getPoints() > plr2.getPoints()) {
            plr = plr1;
        }
        else if (plr1.getPoints() > plr2.getPoints()) {
            plr = plr2;
        }
        else if (plr1.getAmountOfStatues() > plr2.getAmountOfStatues() && plr1.getPoints() == plr2.getPoints()) {
            plr = plr1;
        }
        else if (plr1.getAmountOfStatues() < plr2.getAmountOfStatues() && plr1.getPoints() == plr2.getPoints()) {
            plr = plr2;
        }
        else {
            tie = true;
        }
        if (tie == false) {
            setTitle("Player " + plr.getName() + " Wins!");
            JPanel panel = new JPanel();
            panel.setLayout(null);
            JLabel label = new JLabel("Game Over!");
            label.setFont(new Font("Arial",Font.BOLD, 15));
            label.setBounds(100, 0, 200,60);
            JLabel winner = new JLabel("<html>Player "+ plr.getName() +" Won with " + plr.getPoints() + " points!<br>and " + plr.getAmountOfStatues() + " Statues</html>");
            winner.setFont(new Font("Arial",Font.BOLD, 17));
            winner.setBounds(25, 100, 250,80);
            JButton exit =  new JButton("Exit");
            exit.setFont(new Font("Arial",Font.BOLD, 15));
            exit.setBounds(65, 200, 150,50);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            panel.add(label);
            panel.add(winner);
            panel.add(exit);
            add(panel);
        }
        else {
            setTitle("Tie!");
            JPanel panel = new JPanel();
            panel.setLayout(null);
            JLabel label = new JLabel("Game Over!");
            label.setFont(new Font("Arial",Font.BOLD, 15));
            label.setBounds(100, 0, 200,60);
            JLabel winner = new JLabel("TIE!");
            winner.setFont(new Font("Arial",Font.BOLD, 17));
            winner.setBounds(25, 100, 250,80);
            JButton exit =  new JButton("Exit");
            exit.setFont(new Font("Arial",Font.BOLD, 15));
            exit.setBounds(65, 200, 150,50);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            panel.add(label);
            panel.add(winner);
            panel.add(exit);
            add(panel);
        }


    }
}
