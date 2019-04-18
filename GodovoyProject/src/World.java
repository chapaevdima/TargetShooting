import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class World {
    Gun gun = new Gun();
    Projectile projectile = new Projectile();
    ArrayList<Target> targets = new ArrayList<Target>();
    MyMouseListener mouseListener = new MyMouseListener();
    int shoots = 0;
    int hit = 0;
    int accuracy = 0;
    int upgrades = 0;
    BufferedImage backgroundImage;

    World() throws IOException {
        this.backgroundImage = ImageIO.read(World.class.getResourceAsStream("фон.png"));
        for (int i = 3; i < 8; i+=2) {
            for (int j = 0; j < 3; j+=2) {
                targets.add(new Target(i*200+(int)(Math.random()*200),j*250+(int)(Math.random()*250),i*200+ (int)(Math.random()*200),j*250+(int)(Math.random()*250)));
            }
        }
    }

    void draw(Graphics graphics) {
        graphics.drawImage(backgroundImage,0,0, null);
        for (int i = 0; i < targets.size(); i++) {
            targets.get(i).draw(graphics);
        }
        projectile.draw(graphics);
        gun.draw(graphics, mouseListener.xp, -924+mouseListener.yp);
    }

    void update(int dt) throws IOException {
        hit = 6 - targets.size() + 6*upgrades;
        if (shoots != 0) {
            accuracy = 100*hit/shoots;
        }
        if (mouseListener.start) {
            if (mouseListener.click) {
                shoots += 1;
                if (mouseListener.x < 1000) {
                    projectile.vx += mouseListener.x;
                } else {
                    projectile.vx = 1000;
                }
                projectile.vy -= -mouseListener.y + projectile.ystart;
                mouseListener.click = false;
            }
            projectile.update(dt);
            mouseListener.x = 0;
            mouseListener.y = (int) projectile.ystart;
            for (int i = 0; i < targets.size(); i++) {

                if (projectile.x > (targets.get(i).contactLineX -20) && projectile.x < (targets.get(i).contactLineX +20) && projectile.y > targets.get(i).y && projectile.y < (targets.get(i).y + targets.get(i).height)) {
                    mouseListener.start = false;
                    projectile.y = projectile.ystart;
                    projectile.x = projectile.xstart;
                    projectile.vx = 0;
                    projectile.vy = 0;
                    mouseListener.click  = true;
                    targets.remove(i);
                }
            }
        }

        for (int i = 0; i < targets.size(); i++) {
            targets.get(i).update();
        }

        if (projectile.y > projectile.ystart || projectile.x > 1800 || projectile.y < 0) {
            mouseListener.start = false;
            projectile.y = projectile.ystart;
            projectile.x = projectile.xstart;
            projectile.vx = 0;
            projectile.vy = 0;
            mouseListener.click = true;
        }

        if (targets.size() == 0) {
            upgrades++;
            for (int i = 3; i < 8; i+=2) {
                for (int j = 0; j < 3; j+=2) {
                    targets.add(new Target(i*200+(int)(Math.random()*200),j*250+(int)(Math.random()*250),i*200+ (int)(Math.random()*200),j*250+(int)(Math.random()*250)));
                }
            }
        }
    }
}
