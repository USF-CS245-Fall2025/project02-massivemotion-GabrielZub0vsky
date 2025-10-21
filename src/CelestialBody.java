import java.util.Random;

/**
 * @author Gabriel Zubovsky
 * Purpose: creates celestial bodies and represents them with their attributes
 */
public class CelestialBody {
    private final Random random = new Random();
    protected int bodyPosX, bodyPosY, bodySize, bodyVelRange, bodyVelocityX, bodyVelocityY;
    protected double bodyMass;

    /**
     * constructor for star celestial obj
     * @param posx - x-coordinate of star's position
     * @param posy - y-coordinate of star's position
     * @param size - size of the star
     * @param mass - mass of the star
     * @param velx - x-velocity of the star
     * @param vely - y-velocity of the star
     */ 
    public CelestialBody(int posx, int posy, int size, double mass, int velx, int vely){
        bodyPosX = posx;
        bodyPosY = posy;
        bodySize = size;
        bodyMass = mass;
        bodyVelocityX = velx;
        bodyVelocityY = vely;
    }

    /**
     * constructor for asteroid celestial bodies
     * @param posx - x-coordinate of asteroid
     * @param posy - y-coordinate of asteroid
     * @param size - size of the asteroid
     * @param mass - mass of the asteroid
     * @param vel - randomly generated velocity between -1*vel and vel, excluding 0
     */
    public CelestialBody(int posx, int posy, int size, double mass, int vel){
        bodyPosX = posx;
        bodyPosY = posy;
        bodySize = size;
        bodyMass = mass;
        bodyVelRange = vel;

        // randomly assigning non-zero int value in vel range for x-velocity 
        do { 
           bodyVelocityX = random.nextInt(2*bodyVelRange + 1) - bodyVelRange; 
        } while(bodyVelocityX == 0);

        // randomly assigning non-zero int value in vel range for y-velocity 
        do {
           bodyVelocityY = random.nextInt(2*bodyVelRange + 1) - bodyVelRange; 
        } while(bodyVelocityY == 0);
    }
}