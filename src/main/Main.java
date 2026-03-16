package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
      JFrame window = new JFrame();
      //quitter la window la ferme
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //la taille de la fenêtre ne peut être modifié
      window.setResizable(false);
      window.setTitle("just a regular game");

      GamePanel gamePanel = new GamePanel();
      window.add(gamePanel);

      window.pack();

      //la fenêtre va être affiché au milieu de l'écran
      window.setLocationRelativeTo(null);
      window.setVisible(true);

      gamePanel.startGameThread();
    }

}
