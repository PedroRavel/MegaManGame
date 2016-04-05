import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lv2Charge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lv2Charge extends Bullet
{
    /**
     * Act - do whatever the Lv2Charge wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Lv2Charge(int x , int y){
        super(x,y);
        delay = 4;
        numOfFrames = 4;
    }
    public void act() 
    {
        setDamage(100);
        setImage(getImages("Lv2Charge"));
        getImage().scale(230,220);
    }   
}
