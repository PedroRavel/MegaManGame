import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platforms here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platforms extends ScrollingActor
{


    
    public void act() 
    {
        
    }    
    
    public void flipVertically(){
        getImage().mirrorVertically();
    }
    
    
    public void flipHorizontally(){
        getImage().mirrorHorizontally();
    }
    
    public void rotateRight(){
        getImage().rotate(90);
    }
    
    public void rotateLeft(){
        getImage().rotate(270);
    }
}
