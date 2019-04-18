import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Target {
    int x;
    int y;
    int x0;
    int y0;
    int x1;
    int y1;
    int vx;
    int vy;
    int ax;
    int ay;
    int xc;
    int yc;
    int with = 60;
    int height = 100;
    int contactLineX;
    BufferedImage targetImage;

    Target (int x, int y, int x1, int y1) throws IOException {
        this.targetImage = ImageIO.read(Target.class.getResourceAsStream("мишень.png"));
        x0 = x;
        y0 = y;
        this.x1 = x1;
        this.y1 = y1;
        this.x = x0;
        this.y = y0;
        xc = (x0+x1)/2;
        yc = (y0+y1)/2;
        if (yc < 140) {
            yc = 140;
        }
    }

    void draw (Graphics graphics) {
        //graphics.drawOval(x,y, with, height);
        graphics.drawImage(targetImage, x, y, null);
    }

    void update () {
        contactLineX=x + with/2;
        ax = (xc-x)/4;
        ay = (yc-y)/4;
        vx += ax/5;
        vy += ay/5;
        x += vx/5;
        y += vy/5;
    }
}
