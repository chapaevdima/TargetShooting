import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gun {
    BufferedImage gunImage;

    Gun () throws IOException {
        this.gunImage = ImageIO.read(Gun.class.getResourceAsStream("пушка.png"));
    }

    void draw (Graphics graphics, int x, int y) {
        double angleInRadians = Math.atan2(y,x);
        double locationX;
        double locationY;
        locationX = gunImage.getWidth() / 2;
        locationY = gunImage.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(angleInRadians, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        graphics.drawImage(op.filter(gunImage, null), -295, 630, null);

    }
}
