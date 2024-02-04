import java.awt.*;
import java.util.*;


public class Ball extends Rectangle{

    Random random;
    int xVelocity;
    int yVelocity;
    int ballSpeed = 3;
    Ball(int x, int y, int ballDiameter, int diameter) {
        super(x, y, ballDiameter, diameter);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
            randomXDirection--;
       setXDirection(randomXDirection * ballSpeed);
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * ballSpeed);
    }
    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;


    }
    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;

    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.orange);
        g.fillOval(x, y, height, width);

    }

}
