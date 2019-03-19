package components;

import java.awt.*;

public class Block extends Rectangle {
    Image img;
    int dx=3;
    int dy=-3;
    boolean destroyed = false;
    Block(int x, int y, int w, int h, String imgName) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        img = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+imgName);
    }

    public void draw(Graphics g, Component c) {
        if(!destroyed)
        g.drawImage(img, x, y, width, height, c);
    }
}