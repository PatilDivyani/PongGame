import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    int x_Velocity;
    int y_Velocity;
    int initialSpeed;
    Random random;
    Ball(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        random = new Random();
        //to start ball in any direction
        int RandomXDirection = random.nextInt(2);
        if(RandomXDirection==0)
        {
            RandomXDirection--;
        }
        setXDirection(RandomXDirection);
        int RandomYDirection = random.nextInt(2);
        if(RandomYDirection==0)
        {
            RandomYDirection--;
        }
        setYDirection(RandomYDirection);
    }

    public void setXDirection(int randomXDirection) {
        x_Velocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        y_Velocity = randomYDirection;
    }

     void move(){
        x += x_Velocity;
        y += y_Velocity;
    }
    public void draw (Graphics g){
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
        g.drawLine(1000/2, 0, 1000/2,  555);
    }
}
