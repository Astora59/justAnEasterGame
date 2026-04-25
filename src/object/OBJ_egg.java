package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_egg extends SuperObject {



    public OBJ_egg() {
        int eggCounter = 0;
        name = "egg";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/objects/EasterEgg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
