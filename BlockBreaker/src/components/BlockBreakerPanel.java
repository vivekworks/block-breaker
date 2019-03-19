package components;

import game.Animate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlockBreakerPanel extends JPanel implements KeyListener {
    ArrayList<Block> panelBlocks = new ArrayList<>();
    Block paddle, pressEnter;
    Thread thread;
    Animate animate;
    boolean gameStarted;

    public BlockBreakerPanel() {
        paddle = new Block(250, 450, 150, 30, "\\src\\images\\Paddle.png");
        pressEnter = new Block(125, 520, 400, 30, "\\src\\images\\GameStart.png");
        for (int i = 0; i < 8; i++)
            panelBlocks.add(new Block((i * 80) + 2, 0, 80, 30, "\\src\\images\\Blue.png"));
        for (int i = 0; i < 8; i++)
            panelBlocks.add(new Block((i * 80) + 2, 32, 80, 30, "\\src\\images\\Pink.png"));
        for (int i = 0; i < 8; i++)
            panelBlocks.add(new Block((i * 80) + 2, 64, 80, 30, "\\src\\images\\Magenta.png"));
        for (int i = 0; i < 8; i++)
            panelBlocks.add(new Block((i * 80) + 2, 96, 80, 30, "\\src\\images\\Orange.png"));
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Block block : panelBlocks)
            block.draw(g, this);
        paddle.draw(g, this);
        if (!gameStarted)
            pressEnter.draw(g, this);
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void update() {
        repaint();
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !gameStarted) {
            animate = new Animate(this);
            thread = new Thread(animate);
            gameStarted = true;
            thread.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0)
            this.paddle.x -= 15;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x + 150 < this.getWidth())
            this.paddle.x += 15;
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
