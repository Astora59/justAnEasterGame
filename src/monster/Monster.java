package monster;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Monster extends Entity {
    GamePanel gp;
    public Monster(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "unknown";
        speed = 2;
        direction = "down";
        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 34;
        solidArea.width = 16;
        solidArea.height = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

        getImage();
    }

    public void getImage() {
        try {

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/who/up/up1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/who/up/up2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/who/down/down1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/who/down/down2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/who/left/left1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/who/left/left2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/who/right/right1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/who/right/right2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // ✅ on utilise this.gp, pas un nouveau GamePanel
        int monsterCenterX = worldX + gp.tileSize / 2;
        int monsterCenterY = worldY + gp.tileSize / 2;
        int playerCenterX  = gp.player.worldX + gp.tileSize / 2;
        int playerCenterY  = gp.player.worldY + gp.tileSize / 2;

        int dx = playerCenterX - monsterCenterX;
        int dy = playerCenterY - monsterCenterY;

        if (Math.abs(dx) > Math.abs(dy)) {
            direction = dx > 0 ? "right" : "left";
        } else {
            direction = dy > 0 ? "down" : "up";
        }

//        collisionOn = false;
//        gp.cChecker.checkTile(this);

        if (!collisionOn) {
            switch (direction) {
                case "up":    worldY -= speed; break;
                case "down":  worldY += speed; break;
                case "left":  worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 15) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        // ✅ on utilise this.gp, pas un nouveau GamePanel
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image = null;
        switch (direction) {
            case "up":    image = spriteNum == 1 ? up1 : up2;     break;
            case "down":  image = spriteNum == 1 ? down1 : down2;  break;
            case "left":  image = spriteNum == 1 ? left1 : left2;  break;
            case "right": image = spriteNum == 1 ? right1 : right2; break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    }

