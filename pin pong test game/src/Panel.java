import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.*;
import javax.swing.*;


public class Panel extends JPanel implements Runnable {
    static final int WIDTH = 1000;
    static final int HEIGHT =(int)(WIDTH *(0.5555));  //(int)(WIDTH * 5/9);
    static final Dimension SCREEN_SIZE = new Dimension(WIDTH,HEIGHT);

    static final int BALL_DIAMETER = 30;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameTread;
    Image img;
    Graphics grp;
    Random random;
    Paddel paddel1;
    Paddel paddel2;
    Ball ball;
    Score score;

    Panel() {
        newPaddel();
        newBall();
        score = new Score(WIDTH, HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameTread = new Thread(this);
        gameTread.start();

    }
    public void newBall()  {
        random = new Random();
        ball = new Ball((WIDTH/2)-(BALL_DIAMETER/2), random.nextInt(HEIGHT- BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }
    public void newPaddel() {
         paddel1 = new Paddel(0, (HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddel2 = new Paddel(WIDTH - PADDLE_WIDTH,(HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
    public void paint(Graphics g) {
        img = createImage(getWidth(), getHeight());
        grp = img.getGraphics();
        draw(grp);
        g.drawImage(img, 0, 0, this);

    }
    public void draw(Graphics g){
    paddel1.draw(g);
    paddel2.draw(g);
    ball.draw(g);
    score.draw(g);
    }
    public void move(){
        paddel1.move();
        paddel2.move();
        ball.move();

    }
    public void checkCollision() {
        // stops paddles at window edges

        if (paddel1.y <= 0)
            paddel1.y = 0;
        if (paddel1.y >= (HEIGHT - PADDLE_HEIGHT))  // 1000 - 100
            paddel1.y = HEIGHT - PADDLE_HEIGHT;
        if (paddel1.x <= 0)
            paddel1.x = 0;
        if (paddel1.x >= (WIDTH/2 - PADDLE_WIDTH))  // 1000 - 25
            paddel1.x = WIDTH/2 - PADDLE_WIDTH;
        if (paddel2.y <= 0)
            paddel2.y = 0;
        if (paddel2.y >= (HEIGHT - PADDLE_HEIGHT))  // 1000 - 100
            paddel2.y = HEIGHT - PADDLE_HEIGHT;
        if (paddel2.x <= WIDTH/2)
            paddel2.x = WIDTH/2;
        if (paddel2.x >= (WIDTH - PADDLE_WIDTH))  // 1000 - 25
            paddel2.x = WIDTH - PADDLE_WIDTH;
       //bounces ball off paddles
        if (ball.intersects(paddel1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
           ball.xVelocity++; // increase speed of ball
        if (ball.yVelocity> 0) {
            ball.yVelocity++; // increase speed of ball
        } else {
            ball.yVelocity--;
        }
        ball.setXDirection(ball.xVelocity);
        ball.setYDirection(ball.yVelocity);
        }
        if (ball.intersects(paddel2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // increase speed of ball
            if (ball.yVelocity> 0) {
                ball.yVelocity++; // increase speed of ball
            } else {
                ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity); // ??
            ball.setYDirection(ball.yVelocity);
        }
        //bounces ball off top and bottom window edges
       if (ball.y <= 0 ) {
           ball.setYDirection(-ball.yVelocity);
       }
       if (ball.y >= HEIGHT - BALL_DIAMETER) {
           ball.setYDirection(-ball.yVelocity);
       }
        // gives point to player and creates new paddles and ball
        if(ball.x <= 0 ) {
            score.player2++;
            newPaddel();
            newBall();
            System.out.println("Player 2: " + score.player2);
        }
        if(ball.x >= WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddel();
            newBall();
            System.out.println("Player 1: " + score.player1);
        }

    }
    public void run() {
        //loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;

            }
        }


    }
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddel1.keyPressed(e);
            paddel2.keyPressed(e);

           }
        public void keyReleased(KeyEvent e) {
               paddel1.keyReleased(e);
               paddel2.keyReleased(e);
        }

    }


}
