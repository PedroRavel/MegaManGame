import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends ScrollingActor
{
    private static final int BOOMLIFE = 50;
    private static final int BOOMRADIUS = 50;
    int boomCounter = BOOMLIFE;
    int timer = 0;
    int frame = 0;
    int delay = 4;
    int numOfFrames = 14;
    
    int height;
    int width;
    
    public Explosion(int height, int width){
        this.height = height;
        this.width  = width;
        /*
        GreenfootImage boom = new GreenfootImage(BOOMRADIUS * 2, BOOMRADIUS * 2);
        boom.setColor(Color.RED);
        boom.setTransparency(125);
        boom.fillOval(0,0, BOOMRADIUS * 2, BOOMRADIUS * 2);
        setImage(boom);
        */
       getImage().clear();
    }
    public void act() 
    {
        setImage(getImages("Exp"));
        
        boomAct();
        getImage().scale(height,width);
    }    
    public void boomAct(){
         //if( boomCounter == BOOMLIFE)    destroyEverything(BOOMRADIUS);  
         //if( boomCounter-- == 0 ) {    
         getImage().scale(100,80);
         if(frame >= numOfFrames - 1){
             World w = getWorld();      
             w.removeObject(this); 
         }
         
    }
         public String getImages(String name){
         timer++;

           if(timer >= delay){

                frame++;
                if(frame >= numOfFrames) frame = 0;

                timer = 0;
            }
           
            return name + frame + ".png";
        }
    public void destroyEverything(int x){}
}

