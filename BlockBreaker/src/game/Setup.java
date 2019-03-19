package game;

import components.BlockBreakerPanel;

import javax.swing.*;
import java.awt.*;

public class Setup {
    public void launch(){
        JFrame frame = new JFrame("Block Breaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,700);
        frame.setResizable(false);
        BlockBreakerPanel bbPanel = new BlockBreakerPanel();
        //bbPanel.setBackground(Color.white);
        frame.getContentPane().add(bbPanel);
        frame.setVisible(true);
    }
}