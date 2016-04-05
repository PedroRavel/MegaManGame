import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class EnemyAttackBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyAttackBox extends ScrollingActor
{
    /**
     * Act - do whatever the EnemyAttackBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int actorHeight;
    int actorWidth;
    public EnemyAttackBox(int x, int y){
        getImage().clear();
        GreenfootImage hit = new GreenfootImage(x,y);
        hit.setColor(Color.red);
        hit.setTransparency(50);
        hit.fill();
        setImage(hit);
        actorHeight = getImage().getHeight();
        actorWidth = getImage().getWidth();
        getImage().clear();
    }
    public void setLocale(int x, int y){
        setLocation(x,y);
        
    }
    public void act() 
    {
    }    
}
