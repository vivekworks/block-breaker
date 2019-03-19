package components;

import game.Animate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlockBreakerPanel extends JPanel implements KeyListener {
    ArrayList<Block> panelBlocks = new ArrayList<>();
    ArrayList<Block> balls = new ArrayList<>();
    Block paddle, pressEnter, ball;
    Thread thread;
    Animate animate;
    boolean gameStarted;
    int ballSize=25;
    public BlockBreakerPanel() {
        paddle = new Block(250, 450, 150, 30, "\\src\\images\\Paddle.png");
        pressEnter = new Block(125, 520, 400, 30, "\\src\\images\\GameStart.png");
        ball = new Block(312,420,25,25,"\\src\\images\\Ball.png");
        for (int i = 0; i < 8; i++)
            panelBlocks.add(new Block((i * 80) + 2, 0, 80, 30, "\\src\\images\\Blue.png"));
        for (int i = 0; i < 8; i++)
            panelBlocks.add(new Block((i * 80) + 2, 32, 80, 30, "\\src\\images\\Pink.png"));
        for (int i = 0; i < 8; i++)
            panelBlocks.add(new Block((i * 80) + 2, 64, 80, 30, "\\src\\images\\Magenta.png"));
        for (int i = 0; i < 8; i++)
            panelBlocks.add(new Block((i * 80) + 2, 96, 80, 30, "\\src\\images\\Orange.png"));
        balls.add(ball);
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Block block : panelBlocks)
            block.draw(g, this);
        for (Block ball : balls)
            ball.draw(g, this);
        paddle.draw(g, this);
        //ball.draw(g, this);
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
        for(Block ball :balls){
            ball.x+=ball.dx;
            if(ball.x > (getWidth() - ballSize) && ball.dx > 0 || ball.x < 0)
                ball.dx*=-1;
            if(ball.y < 0 || ball.intersects(paddle))
                ball.dy*=-1;
            ball.y+=ball.dy;
            for(Block block :panelBlocks){
                if(block.intersects(ball) && !block.destroyed){
                    block.destroyed=true;
                    ball.dy*=-1;
                }
            }
        }
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
