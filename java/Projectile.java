import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends EnemyProjectile
{
    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    public String name;
    public int frame;
    public int numOfFrames;
    public int delay;
    public int timer;
    public Projectile(String name, int numOfFrames, int delay){
        this.name = name;
        this.numOfFrames = numOfFrames;
        this.delay = delay;
    }
    public void act() 
    {
        // Add your action code here.
        animate();
        setLocation(getX()-8,getY());
        
    }
    
    public void setScale(int width, int height){
        getImage().scale(width, height);
    }
   
    public void move(int x, int y){
        setLocation(getX() + x, getY() + y);
    }
    public void animate(){
        timer++;
        if(timer>=delay){
            if(frame < numOfFrames)
                frame++;
            else
                frame = 0;
        }
        setImage(name + frame + ".png");
        
    }
    
    
}
