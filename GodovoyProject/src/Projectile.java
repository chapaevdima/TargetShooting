import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Projectile {
    double g=0.6;
    double xstart = 10;
    double x=xstart;
    double ystart = 942;
    double y=ystart;
    double vy;
    double vx;
    BufferedImage projectileImage;

    Projectile () throws IOException {
        this.projectileImage = ImageIO.read(Projectile.class.getResourceAsStream("снаряд.png"));
    }

    void draw (Graphics graphics) {
        //graphics.drawOval((int) x, (int) y,10,10);
        graphics.drawImage(projectileImage, (int) x, (int) y, null);
    }

    void update (int dt) {
        vy += g*dt;
        y += vy*dt/900;
        x += vx*dt/900;
        //System.out.println(x);
    }
}