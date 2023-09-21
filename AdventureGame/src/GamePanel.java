package main;

import java.awt.*;

import javax.swing.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings

    final int originalTileSize = 16; //16x16 tile default size of tiles
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //48x48 tile (actual tile size displayed on game screen
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    final int screenHeight = tileSize * maxScreenRow; // 576 pixel

    int FPS = 60;

    main.KeyHandler keyH = new main.KeyHandler();

    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() { // constructor of GamePanel

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //doublebuffering function is default for better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // auto call run method
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) { // as long as gameThread exists repeats while loop

           // System.out.println("Game loop is running"); //test do not uncomment unless testing game loop





            // 1 Update: update information such as char position
            update();
            //2 Draw: draw the screen with updated info
            repaint(); // how you call paintComponent method
        }

    }
    public void update() {
        if(keyH.upPressed == true) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;

        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;

        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;

        }

    }
    public void paintComponent(Graphics g) { // standard method to draw things on Jpanel

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();

    }
}
