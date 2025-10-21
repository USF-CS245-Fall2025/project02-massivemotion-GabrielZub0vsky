import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/** class MassiveMotion.java
 * @author Gabriel Zubovsky
 * Purpose: Produces a visual simulation of a cosmic system of a star and asteroids. 
 */
public class MassiveMotion extends JPanel implements ActionListener {
    protected Timer tm;
    protected Random random = new Random();
    protected MMProperties mmprop;
    protected List<CelestialBody> celestialObjs;
    
    /** Constructor for MassiveMotion
     * @param prop - MMProperties object that contains values of all properties used in project
     */
    public MassiveMotion(MMProperties prop) {
        mmprop = prop;
        tm = new Timer(mmprop.tmDelay, this); 

        /**
         * declaring list of celestial objects
         * creating star as first celestial object
         */
        CelestialBody star = new CelestialBody(mmprop.starPosX, mmprop.starPosY, 
            mmprop.starSize, mmprop.starMass, mmprop.starVelX, mmprop.starVelY);

        /**
         * initializing list depending on type of list specified by given MMProperties obj
         */
        switch (mmprop.listType) {
            case "arraylist" -> celestialObjs = new MyArrayList<>();
            case "single" -> celestialObjs = new SingleLinkedList<>();
            case "double" -> celestialObjs = new DoubleLinkedList<>();
            case "dummyhead" -> celestialObjs = new DummyHeadList<>();
            default -> {
                celestialObjs = new MyArrayList<>();
            }
        }
        
        celestialObjs.add(star);   // adding star to list
    }

    /** 
     * paintComponent() - paints all celestial bodies with each epoch
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        // painting star red
        g.setColor(Color.RED);
        g.fillOval(mmprop.starPosX, mmprop.starPosY, mmprop.starSize, mmprop.starSize);

        // painting all asteroids black
        g.setColor(Color.BLACK);
        for (int i = 1; i < celestialObjs.size(); i++) {
            try {
                g.fillOval(celestialObjs.get(i).bodyPosX, celestialObjs.get(i).bodyPosY, 
                           celestialObjs.get(i).bodySize, celestialObjs.get(i).bodySize);
            } catch (Exception e) {System.err.printf("Could not paint asteroid #%s", i);}
        }

        // Recommend you leave the next line as is
        tm.start();
    }

    /**
     * actionPerformed() - creates/removes celestial bodies, updates their coords 
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // updating coordinates of star according to its velocity
        mmprop.starPosX += mmprop.starVelX;
        mmprop.starPosY += mmprop.starVelY;
        
        // generating random values for generation probability and for asteroid coords
        int genXProb = random.nextInt(100);
        int genYProb = random.nextInt(100);
        int xPos = random.nextInt(mmprop.windowSizeX);
        int yPos = random.nextInt(mmprop.windowSizeY);

        // generating asteroid along x-axis, either at top edge or bottom edge of frame
        if(genXProb < mmprop.genX*100){
            CelestialBody cb;
            if(xPos % 2 == 0){
                cb = new CelestialBody(xPos, 0, mmprop.bodySize, mmprop.bodyMass, mmprop.bodyVelRange);
                System.out.printf("New celestial body at coords (%s, %s)\n", xPos, 0);
            } else {
                cb = new CelestialBody(xPos, mmprop.windowSizeY, mmprop.bodySize, mmprop.bodyMass, mmprop.bodyVelRange);
                System.out.printf("New celestial body at coords (%s, %s)\n", xPos, mmprop.windowSizeY);
            }
            celestialObjs.add(cb);
        }

        // generating asteroid along y-axis, either at left edge or right edge of frame
        if(genYProb < mmprop.genY*100){
            CelestialBody cb;
            if(yPos % 2 == 0){
                cb = new CelestialBody(0, yPos, mmprop.bodySize, mmprop.bodyMass, mmprop.bodyVelRange);
                System.out.printf("New celestial body at coords (%s, %s)\n", 0, yPos);
            } else {
                cb = new CelestialBody(mmprop.windowSizeX, yPos, mmprop.bodySize, mmprop.bodyMass, mmprop.bodyVelRange);
                System.out.printf("New celestial body at coords (%s, %s)\n", mmprop.windowSizeX, yPos);
            }  
            celestialObjs.add(cb);
        }

        // updating coords of all asteroids according to their velocities, 
        // removing asteroids if they fly out of the bounds of the window
        for(int i = 1; i < celestialObjs.size(); i++){
            try {
                celestialObjs.get(i).bodyPosX += celestialObjs.get(i).bodyVelocityX;
                celestialObjs.get(i).bodyPosY += celestialObjs.get(i).bodyVelocityY;
                
                if (celestialObjs.get(i).bodyPosX < 0 || celestialObjs.get(i).bodyPosX > mmprop.windowSizeX || 
                    celestialObjs.get(i).bodyPosY < 0 || celestialObjs.get(i).bodyPosY > mmprop.windowSizeY) {
                    celestialObjs.remove(i);
                }
            } catch (Exception e) {System.err.printf("Could not update location of asteroid #%s", i);}
        }

        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        
        MMProperties mmprop; // creating properties based on cmdline arg 
        if(args.length == 1){
            mmprop = new MMProperties(args[0]);
        } else {
            mmprop = new MMProperties("MassiveMotion.txt");
        }

        MassiveMotion mm = new MassiveMotion(mmprop);
        
        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mmprop.windowSizeX, mmprop.windowSizeY); 
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
