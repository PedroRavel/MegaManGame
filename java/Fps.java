import greenfoot.*;

/**
 * CLASS: Fps
 * AUTHOR: danpost (greenfoot.org username)
 * VERSION: January 14, 2014
 * 
 * DESCRIPTION: An actor class that show how many frames the scenario is running at the current speed.
 * 
 * INSTRUCTIONS: As with any actor, create and add into world after including the class in your project.
 * 
 * CONTROLS:
 * 
 * * Left click on actor reduces speed of scenario
 * * Right click on actor increases speed of scenario
 * * Hovering mouse over actor will have it display current speed setting
 * 
 * NOTE: Using the speed slider in the application will result in a false speed display until
 * the actor is clicked on.
 */
public class Fps extends Actor
{
    int frames, speed = 50; // frame counter and speed setting
    boolean showSpeed, set, go; // controls

    /**
     * Sets the initial scenario speed and creates the initial image
     */
    public Fps()
    {
        Greenfoot.setSpeed(speed); // set the scenario speed to setting of field
        updateImage(); // set initial image of fps text
    }

    /**
     * Checks for mouse clicks and tracks frames per second of scenario
     */
    public void act()
    {
        if (!showSpeed && Greenfoot.mouseMoved(this))
        {
            showSpeed = true;
            updateImage();
        }
        if (showSpeed && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
        {
            showSpeed = false;
            updateImage();
        }
        // check for mouse clicks to change the scenario speed
        int d = 0; // field to hold any change in speed
        if (Greenfoot.mouseClicked(this))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            d = mouse.getButton()-2;
        }
        if (d*d == 1 && speed+d > 0 && speed+d <= 100)
        { // change requested and speed will stay within range
            // reset frame counter and control fields
            frames = 0;
            set = false;
            go = false;
            // change the speed
            speed += d;
            Greenfoot.setSpeed(speed);
            // update the display texts
            updateImage();
        }
        // get current fractional part of seconds of system time (0 to 999 milliseconds)
        int millis = (int)(System.currentTimeMillis()%1000);
        // code to begin the timing
        if (!set && !go)
        { // time has not begun and we are not set to begin
            if (millis > 100) set = true; // we are set to begin if past first 1/10 of a second
            return;
        }
        if (set && !go)
        { // time has not begun, but we are set to begin
            if (millis < 100) { go = true; set = false; } // zero tick, unset and begin time
            return;
        }
        // code to run the timing
        frames++; // count this frame
        if (!set && go)
        { // must wait for 1/10 of a second before looking for first 1/10 of a second again
            if (millis > 100) set = true; // reset after 1/10 of a second past last tick
            return;
        }
        if (set && go)
        { // looking for next first 1/10 of a second
            if (millis < 100)
            { // next tick
                set = false; // not looking for tick
                updateImage(); // update text display of fps actor
                frames = 0; // reset the frames counter
            }
        }
    }

    /**
     * Updates the image
     */
    private void updateImage()
    {
        if (showSpeed) setImage(new GreenfootImage("Speed: "+speed, 30, java.awt.Color.blue, null));
        else setImage(new GreenfootImage("FPS: "+frames, 30, java.awt.Color.black, null));
    }
}