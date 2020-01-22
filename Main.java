import java.awt.*;
import javax.swing.*;
import java.lang.Thread;
import java.util.*;
import shapes.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Main extends JFrame {

    double playerX = 0;
    double playerY = 0;
    double playerAngle = 0;
    double movement = 1;
    Color bgColor = new Color(218, 218, 218);

    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();     

    private static final int SCREEN_WIDTH_PIXELS = 800;
    private static final int SCREEN_HEIGHT_PIXELS = 600;
    
    private static final double FOV_RADIANS = degreesToRadians(100);
    
    public Main() {
        
        setTitle("Renderer");
        setVisible(true);
        setResizable(false);
        setSize(SCREEN_WIDTH_PIXELS, SCREEN_HEIGHT_PIXELS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    

        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Pause");
        JMenu m2 = new JMenu("MiniMap");
        mb.add(m1);
        mb.add(m2);

        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 's':
                    playerX -= movement *  Math.cos(playerAngle);
                    playerY -= movement * Math.sin(playerAngle);
                    break;
                    case 'w':
                    playerX += movement *  Math.cos(playerAngle);
                    playerY += movement * Math.sin(playerAngle);
                    break;
                    case 'a':
                    playerY += movement *  Math.cos(playerAngle);
                    playerX -= movement * Math.sin(playerAngle);
                    break;
                    case 'd':
                    playerY -= movement *  Math.cos(playerAngle);
                    playerX += movement * Math.sin(playerAngle);
                    break;
                }
                System.out.println("x: "+playerX + " y: " +playerY);

            }

            public void keyReleased(KeyEvent e) {}



        });

        validate();
        //Game Objects
        gameObjects.add(new Circle(5, 0, 1, 0.3));
        gameObjects.add(new VerticalLine(-10, 10, 30, 0.6));
        
        while (true) {
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        new Main();
    }
    public void paint(Graphics g){
        //Draw Here


        //Draw the Canvas
        g.setColor(bgColor);
        g.fillRect(0, 0, SCREEN_WIDTH_PIXELS, 300);
        g.setColor(Color.GRAY);
        g.fillRect(0, SCREEN_HEIGHT_PIXELS/2, SCREEN_WIDTH_PIXELS, SCREEN_HEIGHT_PIXELS/2);
        //revalidate();
        deelio(g);

    }


    public void deelio(Graphics g) {
        for (int x=0; x<SCREEN_WIDTH_PIXELS; x++) {
            double dAngle = screenXToAngle(x);
            double checkAngle = playerAngle + dAngle;

            Foo closest = Foo.DUMMY;
            for (GameObject gameObject : gameObjects) {
                Foo foo = gameObject.intersect(playerX, playerY, checkAngle);
                if (foo.distance == -1) continue;
                if (foo.distance < closest.distance) closest = foo;
            }

            if (!closest.isVisible()) continue;
            

            int rectHeight = SCREEN_HEIGHT_PIXELS/2 - (int)(SCREEN_HEIGHT_PIXELS/closest.distance);
            g.setColor(closest.toColor());

            g.fillRect(x, (int)rectHeight, 1, SCREEN_HEIGHT_PIXELS-2*rectHeight);        

        }   
    }
    

    private static double screenXToAngle(int screenX) {
        return map((double)screenX, 0, (double)SCREEN_WIDTH_PIXELS, -FOV_RADIANS/2, FOV_RADIANS/2);
    }

    public static double map(double x, double inMin, double inMax, double outMin, double outMax) {
        // this function is the spicy sauce
        // https://www.arduino.cc/reference/en/language/functions/math/map/
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI/180;
    }
}
