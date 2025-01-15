package src.View;

import src.model.Player;
import src.model.findings.Fresco;
import src.model.findings.RareFinding;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <b>WARNING: HAS NOT BEEN TESTED THOROUGHLY, COULD RETURN INVALID RESULTS</b>
 * <p>
 * The {@code endGameWin} class represents the end game screen that appears when a game finishes.
 * It calculates the scores of both players and determines the winner or if the game is a tie.
 * The result is displayed in a pop-up window with the option to exit the game.
 * <p>
 * The class evaluates the players' points from the positions they visited, rare findings, frescoes, and statues to determine the winner.
 * If the scores are tied, the number of rare findings, frescoes, and statues are used as tie-breakers.
 * If the tie cannot be broken, the game results in a draw.
 * <p>
 * <b>Pre-condition</b>: Two players must be passed to the constructor. These players' points from the positions they visited and findings they collected will all be used to calculate the result.
 * <p>
 * <b>Post-condition</b>: A window will pop up displaying the winner or a tie, with the option to exit the game.
 * <p>
 * <b>Invariant</b>: The window will always display the correct winner or indicate a tie based on the game results.
 */
public class endGameWin extends JFrame {
    boolean tie = false;
    Player plr;
    public endGameWin(Player plr1, Player plr2) {
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        int p1 = calcPlayerPoints(plr1);
        int p2 = calcPlayerPoints(plr2);
        int rare1 = 0;
        int rare2 = 0;
        int fresco1 = 0;
        int fresco2 = 0;
        int snakes1 = 0;
        int snakes2 = 0;
        if (p1 > p2) {
            plr = plr1;
        }
        else if (p2 > p1) {
            plr = plr2;
        }
        else if (p1 == p2) {
            for (int i=0; i<plr1.getFinds().size(); i++) {
                if (plr1.getFinds().get(i) instanceof RareFinding) {
                    rare1++;
                }
            }
            for (int i=0; i<plr2.getFinds().size(); i++) {
                if (plr2.getFinds().get(i) instanceof RareFinding) {
                    rare2++;
                }
            }
            if (rare1 > rare2) {
                plr = plr1;
            }
            else if (rare2 > rare1) {
                plr = plr2;
            }
            else if (rare1 == rare2) {
                for (int i=0; i<plr1.getFinds().size(); i++) {
                    if (plr1.getFinds().get(i) instanceof Fresco) {
                        fresco1++;
                    }
                }
                for (int i=0; i<plr2.getFinds().size(); i++) {
                    if (plr2.getFinds().get(i) instanceof Fresco) {
                        fresco2++;
                    }
                }
                if (fresco1 > fresco2) {
                    plr = plr1;
                }
                else if (fresco2 > fresco1) {
                    plr = plr2;
                }
                else if (fresco1 == fresco2) {
                    for (int i=0; i<plr1.getFinds().size(); i++) {
                        if (plr1.getFinds().get(i).getFindingName().equals("snakes")) {
                            snakes1++;
                        }
                    }
                    for (int i=0; i<plr2.getFinds().size(); i++) {
                        if (plr2.getFinds().get(i).getFindingName().equals("snakes")) {
                            snakes2++;
                        }
                    }
                    if (snakes1 > snakes2) {
                        plr = plr1;
                    }
                    else if (snakes2 > snakes1) {
                        plr = plr2;
                    }
                    else if (snakes1 == snakes2) {
                        tie = true;
                    }
                }
            }
        }
        if (tie == false) {
            setTitle("Player " + plr.getName() + " Wins!");
            JPanel panel = new JPanel();
            panel.setLayout(null);
            JLabel label = new JLabel("Game Over!");
            label.setFont(new Font("Arial",Font.BOLD, 15));
            label.setBounds(100, 0, 200,60);
            JLabel winner = new JLabel("<html>Player "+ plr.getName() +" Won!");
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
    public int calcPlayerPoints(Player plr) {
        int points = 0;
        int snakes=0;
        points+=plr.getPoints();
        if (plr.getAmountOfStatues() == 1){
            points+= -20;
        }
        else if (plr.getAmountOfStatues() == 2){
            points+= -15;
        }
        else if (plr.getAmountOfStatues() == 3) {
            points += 10;
        }
        else if (plr.getAmountOfStatues() == 4) {
            points += 15;
        }
        else if (plr.getAmountOfStatues() == 5) {
            points += 30;
        }
        else if (plr.getAmountOfStatues() >= 6) {
            points += 50;
        }
        plr.setPoints(points);
        System.out.println("Player " + plr.getName() +": " + points + " points");
        return plr.getPoints();
    }
}
