import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZeroShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZeroShot extends EnemyProjectile
{
    /**
     * Act - do whatever the ZeroShotUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int timer = 0;
    int frame = 0;
    int numOfFrames = 3;
    int delay = 5;
    
    String name;
    public ZeroShot(String name){
        getImage().clear();
        this.name = name;
        
    }
    public void act() 
    {
        // Add your action code here.
        
        changeFrame();
        getImage().scale(190,100);
        if(name.equals("Lt"))
            setLocation(getX() - 10,getY());
        else 
            setLocation(getX() + 10,getY());
        if(isAtEdge()) getWorld().removeObject(this);
    }   
    
    public void changeFrame(){
        timer++;
        if(timer >= delay){
            frame++;
            if(frame >= numOfFrames) frame = 0;
            setImage("ZeroShot" + name + frame + ".png");
            timer = 0;
        }
    }
}