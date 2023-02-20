import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Panel extends JPanel implements Runnable{
    int width = 1000;
    int height = (int)(0.555*width);
    Dimension screen_size = new Dimension(width,height);

    int Paddel_height = 100; //define paddel size
    int Paddel_width = 25;

    Paddel paddel1;
    Paddel paddel2;
    int ball_dia = 20; //define ball size
    Ball ball;
    Thread gameThread;

    Graphics graphics;
    Image image;
    Score score = new Score(width, height);
    Panel(){
        newPaddel();
        newBall();
        this.setFocusable(true); //to focus on panel only
        this.addKeyListener(new AL()); //to enable - run the paddle

        this.setPreferredSize(screen_size);
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newBall() {
        Random random = new Random();
        ball = new Ball(width/2-ball_dia/2, random.nextInt(height-ball_dia), ball_dia, ball_dia);
    }

    private void newPaddel() {
        paddel1 = new Paddel(0,height/2-Paddel_height/2, Paddel_width, Paddel_height,1);  //initial position
        paddel2 = new Paddel(width-Paddel_width,height/2-Paddel_height/2, Paddel_width, Paddel_height,2);
    }

    @Override
    public void paint(Graphics g) { //to show on screen
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    private void draw(Graphics g) { //to access paint function
        paddel1.draw(g);
        paddel2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    @Override
    public void run() { //imp to make game work
        long last_time = System.nanoTime();
        double amountOfTicks = 60.00; //total seconds in a min
        double ns = 1000000000/amountOfTicks;  //calc
        double delta = 0;  //dt change in time
        while(true){
            long current_time = System.nanoTime();
            delta += (current_time - last_time)/ns; //in seconds
            last_time = current_time;
            if(delta>=1){
                move();
                repaint();
                checkCollsion();
                delta--;
            }
        }
    }

    private void checkCollsion() {
        if(ball.y<=0)
        {
            ball.setYDirection(-ball.y_Velocity);
        }
        if(ball.y>=height-ball_dia)
        {
            ball.setYDirection(-ball.y_Velocity);
        }
        if(ball.intersects(paddel1)){
            ball.x_Velocity = - ball.x_Velocity;
            ball.x_Velocity++ ;
            if(ball.y_Velocity>0){
                ball.y_Velocity++;
            }
            else{
                ball.y_Velocity--;
            }
            ball.setXDirection(ball.x_Velocity);
            ball.setYDirection(ball.y_Velocity);
        }
        if(ball.intersects(paddel2)){
            ball.x_Velocity = -ball.x_Velocity;
            ball.x_Velocity++;
            if(ball.y_Velocity>0){
                ball.y_Velocity++;
            }
            else{
                ball.y_Velocity--;
            }
            ball.setXDirection(ball.x_Velocity);
            ball.setYDirection(ball.y_Velocity);
        }
        if(paddel1.y<=0)
        {
            paddel1.y=0;
        }
        if(paddel1.y>height-Paddel_height)
        {
            paddel1.y=height-Paddel_height;
        }
        if(paddel2.y<=0)
        {
            paddel2.y=0;
        }
        if(paddel2.y>height-Paddel_height)
        {
            paddel2.y=height-Paddel_height;
        }
        if(ball.x>=width-ball_dia)
        {
            newPaddel();
            newBall();
            score.player1++;
        }
        if(ball.x<=0)
        {
            newPaddel();
            newBall();
            score.player2++;
        }
    }

    public void move() {
        paddel1.move();
        paddel2.move();
        ball.move();
    }


    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            paddel1.keyPressed(e);
            paddel2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddel1.keyReleased(e);
            paddel2.keyReleased(e);
        }
    }
}
