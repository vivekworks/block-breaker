package components;

import java.awt.*;

public class Block extends Rectangle {
    Image img;
    Block(int x, int y, int w, int h, String imgName) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        img = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+imgName);
    }

    public void draw(Graphics g, Component c) {
        g.drawImage(img, x, y, width, height, c);
    }
}