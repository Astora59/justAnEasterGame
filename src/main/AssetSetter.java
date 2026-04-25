package main;

import monster.Monster;
import object.OBJ_egg;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_egg();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_egg();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_egg();
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 9 * gp.tileSize;

        gp.obj[3] = new OBJ_egg();
        gp.obj[3].worldX = 11 * gp.tileSize;
        gp.obj[3].worldY = 46 * gp.tileSize;

        gp.obj[4] = new OBJ_egg();
        gp.obj[4].worldX = 11 * gp.tileSize;
        gp.obj[4].worldY = 56 * gp.tileSize;

        gp.obj[5] = new OBJ_egg();
        gp.obj[5].worldX = 78/2 * gp.tileSize;
        gp.obj[5].worldY = 10 * gp.tileSize;

        gp.obj[6] = new OBJ_egg();
        gp.obj[6].worldX = 70/2 * gp.tileSize;
        gp.obj[6].worldY = 42 * gp.tileSize;

        gp.obj[7] = new OBJ_egg();
        gp.obj[7].worldX = 90/2 * gp.tileSize;
        gp.obj[7].worldY = 60 * gp.tileSize;

    }

    public void setMonster() {
        gp.monster[0] = new Monster(gp);
        gp.monster[0].worldX = 26 * gp.tileSize;
        gp.monster[0].worldY = 7 * gp.tileSize;

        gp.monster[1] = new Monster(gp);
        gp.monster[1].worldX = 30 * gp.tileSize;
        gp.monster[1].worldY = 40 * gp.tileSize;

        gp.monster[2] = new Monster(gp);
        gp.monster[2].worldX = 15 * gp.tileSize;
        gp.monster[2].worldY = 9 * gp.tileSize;

    }
}
