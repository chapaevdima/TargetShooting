import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyPanel extends JPanel {
    World world = new World();
    long prevTime =  System.currentTimeMillis();

    public MyPanel() throws IOException {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long curTime = System.currentTimeMillis();
        int dt = (int) (curTime - this.prevTime);
        this.prevTime = curTime;

        world.draw(g);
        try {
            world.update(dt);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
