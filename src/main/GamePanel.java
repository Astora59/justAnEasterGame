package main;

import entity.Player;
import monster.Monster;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3; //sur un écran 1920x1080 ça parait petit alors on agrandit le scale de x3

    public final int tileSize = originalTileSize * scale; //48x48

    //définir la taille des écrans
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 74;

    public int overlayAlpha = 0; // commence totalement transparent
    //public Monster monster;
    public Monster monster[] = new Monster[3];

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];


    public GamePanel() {
      this.setPreferredSize(new Dimension(screenWidth, screenHeight));
      this.setBackground((Color.BLACK));
      this.setDoubleBuffered(true);
      this.addKeyListener(keyH);
      this.setFocusable(true);
    }

    boolean musicChanged = false;
    public void musicChange() {

        if(player.eggCounter > 4 && !musicChanged) {
            stopMusic(0);
            playMusic(1);
            musicChanged = true;
        }

        if(player.eggCounter == 8 && musicChanged) {
            stopMusic(1);
            System.exit(0);

        }

        overlayAlpha = Math.min(255, player.eggCounter * 30);
    }




    public void setupGame() {
        aSetter.setObject();
        aSetter.setMonster();
        playMusic(0);


    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        //boucle qui dit : tant que le jeu est lancé, on continue la boucle de jeu
        while(gameThread != null) {
            long currentTime = System.nanoTime();



            //update : on change les informations suite à une action
            update();
            //draw : on affiche l'update
            //on appelle paintComponent avec repaint
            repaint();



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update() {
        player.update();
        musicChange();

        if (player.eggCounter >= 5) {
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].update();

                    if (cChecker.checkMonster(player, i)) {
                        System.exit(0);
                    }
                }
            }
        }


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
        for(int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        if(overlayAlpha > 0) {
            g2.setColor(new Color(0, 0, 0, overlayAlpha));
            g2.fillRect(0, 0, screenWidth, screenHeight);
        }

        if (player.eggCounter >= 5) {
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].draw(g2);
                }
            }
        }

        g2.dispose();



    }
    Sound music = new Sound();
    Sound se = new Sound();
    public void playMusic(int i) {
        music.stop();
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(int i) {
        sound.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
