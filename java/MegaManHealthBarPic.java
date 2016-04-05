import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MegaManHealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MegaManHealthBarPic extends Actor
{
    /**
     * Act - do whatever the MegaManHealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    MegaManHealthBar bar;
    public MegaManHealthBarPic(){
        
    }
    public void act() 
    {
        // Add your action code here.
 
 
        
        
        
    }    
    
    protected void addedToWorld(World w){
        bar = new MegaManHealthBar();
        w.addObject(bar,getX()-26,getY()-50);
    }
    
}
