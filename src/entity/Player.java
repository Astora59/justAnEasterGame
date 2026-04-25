package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;


    public final int screenX;
    public final int screenY;
    public int eggCounter = 0;


    public Player(GamePanel gp,KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 34;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 16;
        solidArea.height = 10;


        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/player/up/up1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/player/up/up2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/player/down/down1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/player/down/down2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/player/left/left1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/player/left/left2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/player/right/right1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/player/right/right2.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if(keyH.upPressed == true) {
                direction = "up";
            }
            else if(keyH.downPressed == true) {
                direction = "down";
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
            }

            //collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);



            //if collision is false
            if(collisionOn == false) {
                switch(direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX  += speed;
                        break;

                }
            }
        }





        spriteCounter++;
        if(spriteCounter > 15) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void pickUpObject(int i)  {
        if(i != 999) {
            gp.playSE(2);
            eggCounter++;
            gp.obj[i] = null;
            System.out.println(eggCounter);
            if (eggCounter == 8) {
                gp.playSE(3);
            }


        }
    }


    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "up":

                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }

                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
