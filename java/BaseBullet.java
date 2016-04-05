import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BaseBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BaseBullet extends Bullet
{
    /**
     * Act - do whatever the BaseBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BaseBullet(int x , int y){
        super(x,y);
        delay = 10;
        numOfFrames = 4;
    }
    public void act() 
    { 
        setDamage(2);
        setImage(getImages("solar"));
        getImage().scale(110,65);
    }    
}
