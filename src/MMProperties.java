import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** class MMProperties.java
 * @author Gabriel Zubovsky
 * Purpose: Represents all properties used in the MassiveMotion project
 */
public class MMProperties {
    /**
     * Properties for Massive Motion object
     */
    protected String listType = "arraylist";
    protected int tmDelay, windowSizeX, windowSizeY;
    
    /**
     * Properties for first celestial object (star)
     */
    protected int starPosX, starPosY, starSize, starVelX, starVelY;
    protected double starMass;

    /**
     * Properties for all successive celestial bodies
     */
    protected double genX, genY, bodyMass;
    protected int bodyPosX, bodyPosY, bodySize, bodyVelRange;

    /**
     * Constructor for MMProperties, validates and initializes all properties 
     * @param fileName - name of input file given to the program
     */
    public MMProperties(String fileName){
        Properties prop = new Properties();
        
        /**
         * validation of input file
         */
        try (InputStream input = new FileInputStream(fileName)) {
            prop.load(input);

            /**
             * validation for list type 
             */
            String[] possibleListTypes = new String[]{"arraylist", "single", "double", "dummyhead"};
            for (String s : possibleListTypes) {
                if (prop.getProperty("list") == null || !prop.getProperty("list").equals(s)) {
                    listType = "arraylist";
                } else {
                    listType = s;
                }
            }

            /**
             * validation for all other properties  
             */ 
            try {
                tmDelay = Integer.parseInt(prop.getProperty("timer_delay"));
                if (tmDelay < 0) {tmDelay = 75;}
            } 
            catch (NumberFormatException e) {tmDelay = 75;}

            try {
                windowSizeX = Integer.parseInt(prop.getProperty("window_size_x"));
                if (windowSizeX <= 0) {windowSizeX = 1024;}
            } 
            catch (NumberFormatException e) {windowSizeX = 1024;}

            try {
                windowSizeY = Integer.parseInt(prop.getProperty("window_size_y"));
                if (windowSizeY <= 0) {windowSizeY = 768;}            
            } 
            catch (NumberFormatException e) {windowSizeY = 768;}

            try {
                genX = Double.parseDouble(prop.getProperty("gen_x"));
                if (genX < 0 || genX > 1) {genX = 0.06;}
            } 
            catch (NumberFormatException e) {genX = 0.06;}

            try {
                genY = Double.parseDouble(prop.getProperty("gen_y"));
                if (genY < 0 || genY > 1) {genY = 0.06;}
            } 
            catch (NumberFormatException e) {genY = 0.06;}

            try {
                starPosX = Integer.parseInt(prop.getProperty("star_position_x"));
                if (starPosX < 0 || starPosX > windowSizeX) {starPosX = 512;}
            } 
            catch (NumberFormatException e) {starPosX = 512;}

            try {
                starPosY = Integer.parseInt(prop.getProperty("star_position_y"));
                if (starPosY < 0 || starPosY > windowSizeY) {starPosY = 384;}
            } 
            catch (NumberFormatException e) {starPosY = 384;}

            try {
                starSize = Integer.parseInt(prop.getProperty("star_size"));
                if (starSize <= 0) {starSize = 30;}
            } 
            catch (NumberFormatException e) {starSize = 30;}

            try {
                starMass = Integer.parseInt(prop.getProperty("star_mass"));
                if (starMass == 0) {starMass = 2E29;}
            } 
            catch (NumberFormatException e) {starMass = 2E29;}

            try {starVelX = Integer.parseInt(prop.getProperty("star_velocity_x"));} 
            catch (NumberFormatException e) {starVelX = 0;}

            try {starVelY = Integer.parseInt(prop.getProperty("star_velocity_y"));} 
            catch (NumberFormatException e) {starVelY = 0;}

            try {
                bodySize = Integer.parseInt(prop.getProperty("body_size"));
                if(bodySize <= 0) {bodySize = 10;}
            } 
            catch (NumberFormatException e) {bodySize = 10;}

            try {
                bodyMass = Integer.parseInt(prop.getProperty("body_mass"));
                if(bodyMass <= 0) {bodyMass = 1E21;}
            } 
            catch (NumberFormatException e) {bodyMass = 1E21;}

            try {
                bodyVelRange = Integer.parseInt(prop.getProperty("velocity"));
                if (bodyVelRange == 0) {bodyVelRange = 3;}
            } 
            catch (NumberFormatException e) {bodyVelRange = 3;}

        } catch (IOException badinput){
            /**
             * if given input file does not exist or cannot be read, 
             * replace with default values from MassiveMotion.txt
             */
            listType = "arraylist";
            tmDelay = 75;
            windowSizeX = 1024;
            windowSizeY = 768;
            genX = 0.06;
            genY = 0.06;
            starPosX = 512;
            starPosY = 384;
            starSize = 30;
            starMass = 2E29;
            starVelX = 0;
            starVelY = 0;
            bodySize = 10;
            bodyMass = 1E21;
            bodyVelRange = 3;
        }
    }
}