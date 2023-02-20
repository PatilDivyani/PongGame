import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class Paddel extends Rectangle {
    int id; //to know which paddel is running
    int y_Velocity;
    int speed = 10;
    Paddel(int x, int y, int Paddel_width, int Paddel_height, int id ){
       super(x, y , Paddel_width, Paddel_height);
        this.id = id;
    }
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.blue);
        }
        else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);
    }
    public void keyPressed(KeyEvent e)
    {
        switch (id) {
            case 1 : {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                }
                break;
            }
            case 2 : {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
                break;
            }
        }
    }
    public void keyReleased(KeyEvent e)
    {
        switch (id) {
            case 1 : {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;
            }
            case 2 : {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
            }
        }
    }
    public void setYDirection(int i) {
        y_Velocity = i;
    }

    public void move() {
        y += y_Velocity;

    }
}
