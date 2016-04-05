import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class EnemyHitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyHitbox extends ScrollingActor
{
    /**
     * Act - do whatever the EnemyHitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Tiles upperBounds;
    Tiles lowerBounds;
    
    Tiles upperLeftBounds;
    Tiles upperRightBounds;
   
    Tiles upperMidBounds;
    Tiles lowerMidBounds;
    
    Tiles lowerLeftBounds;
    Tiles lowerRightBounds;
    
    Actor bullet;
    
    int actorHeight;
    int actorWidth;
    
    GreenfootImage hit; 
    public EnemyHitbox(int width,int height){
        getImage().clear();
        hit = new GreenfootImage(width,height);
        //hit.setColor(Color.red);
        //hit.setTransparency(50);
        //hit.fill();
        
        
        setImage(hit);
        actorHeight = getImage().getHeight();
        actorWidth = getImage().getWidth();
    }
    public void createHitbox(int x, int y){
        hit = new GreenfootImage(70,150);
    }

    public void act() 
    {  
        init();
    }    
    public void setLocale(int x, int y){
        setLocation(x,y);
        
    }
        public void init(){
        
            upperBounds = getOneObjectAtOffset(0,actorHeight/2,Tiles.class);
            lowerBounds = getOneObjectAtOffset(0,-actorHeight/2,Tiles.class);
        
            upperRightBounds = getOneObjectAtOffset(-actorWidth/2,actorWidth/3,Tiles.class);
            upperLeftBounds  = getOneObjectAtOffset( actorWidth/2,actorWidth/3,Tiles.class);
        
            upperMidBounds = getOneObjectAtOffset(-actorWidth/2,0,Tiles.class);
            lowerMidBounds  = getOneObjectAtOffset( actorWidth/2,0,Tiles.class);
        
            lowerRightBounds = getOneObjectAtOffset(-actorWidth/2,-actorWidth/2,Tiles.class);
            lowerLeftBounds  = getOneObjectAtOffset( actorWidth/2,-actorWidth/2,Tiles.class);
            
            bullet = getOneIntersectingObject(Bullet.class);

            
    }
    
    public boolean canMoveLeft(){
     
            return((lowerRightBounds == null && upperRightBounds == null && upperMidBounds == null));
 
    }
    public boolean canMoveRight(){

            return  ((lowerLeftBounds == null & upperLeftBounds == null && lowerMidBounds == null));
 
    }
    public boolean inAir(){

            return (upperBounds == null);

    }
}
