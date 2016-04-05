import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lv1Charge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lv1Charge extends Bullet
{
    /**
     * Act - do whatever the Lv1Charge wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Lv1Charge(int x , int y){
        super(x,y);
        delay = 10;
        numOfFrames = 3;
    }
    public void act() 
    {
        setDamage(4);
        setImage(getImages("Lv1Charge"));
        getImage().scale(160,90);
    }    
}
