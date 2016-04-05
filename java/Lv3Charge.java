import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lv3Charge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lv3Charge extends Bullet
{
    /**
     * Act - do whatever the Lv3Charge wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Lv3Charge(int x , int y){
        super(x,y);
        delay = 4;
        numOfFrames = 6;
    }
    public void act() 
    {
        setDamage(6);
        setImage(getImages("Lv3Charge"));
        getImage().scale(230,140);
    }   
}
