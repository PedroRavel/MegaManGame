import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZeroShotUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZeroShotUp extends EnemyProjectile
{
    /**
     * Act - do whatever the ZeroShotUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int timer = 0;
    int frame = 0;
    int numOfFrames = 4;
    int delay = 5;
    public ZeroShotUp(){
        getImage().clear();
        
    }
    public void act() 
    {
        // Add your action code here.
        changeFrame();
        getImage().scale(110,180);
        setLocation(getX(),getY() - 20);
    }   
    
    public void remove(){
        if(getY() < 0) getWorld().removeObject(this);
    }
    
    public void changeFrame(){
        timer++;
        if(timer >= delay){
            frame++;
            if(frame >= numOfFrames) frame = 0;
            setImage("ZeroShotUp" + frame + ".png");
            timer = 0;
        }
    }
}
