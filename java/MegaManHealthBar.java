import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class MegaManHealtBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MegaManHealthBar extends Actor
{
    /**
     * Act - do whatever the MegaManHealtBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage bar;
  
    public MegaManHealthBar(){
        bar = new GreenfootImage(100,350);
        setImage(bar);
    }
    public void act() 
    {
        // Add your action code here.
        
    }    
    public void setBar(int i){
        bar = getImage();
        bar.clear();
        bar.drawRect(50,123 + i,21,135 - i);
        bar.fillRect(50,123 + i,21,135 - i);
        bar.setColor(Color.GREEN);


    }
}
