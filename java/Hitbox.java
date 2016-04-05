import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Hitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hitbox extends Actor
{
    /**
     * Act - do whatever the Hitbox wants to do. This method is called whenever
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
    
    IntroTile intro;
    
    Actor left;
    Actor right;
    
    int actorHeight;
    int actorWidth;
    
    public Hitbox(int x , int y){
        GreenfootImage hit = new GreenfootImage(x,y);
       // hit.setColor(Color.red);
       // hit.setTransparency(50);
       // hit.fill();
        setImage(hit);
        actorHeight = getImage().getHeight();
        actorWidth = getImage().getWidth();

    }
    
    public void act() 
    {     
        init();
    
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
            
            
            right = getOneObjectAtOffset((int)(-actorWidth/1.5), 0 ,EnemyHitbox.class);
            left  = getOneObjectAtOffset((int)(actorWidth/1.5), 0,EnemyHitbox.class);
            
            intro = getOneObjectAtOffset(0,actorHeight/2,IntroTile.class);
            
            

            
    }
    
    public boolean checkIntro(){
        return intro !=null;
    }
    
    public boolean wasHit(){
        return getOneIntersectingObject(EnemyProjectile.class)!=null || getOneIntersectingObject(EnemyAttackBox.class)!=null;
    }
    public boolean canMoveLeft(){
     
            return((lowerRightBounds == null && upperRightBounds == null && upperMidBounds == null) );
 
    }
    public boolean canMoveRight(){

            return  ((lowerLeftBounds == null & upperLeftBounds == null && lowerMidBounds == null) );
 
    }
    public boolean inAir(){

            return (upperBounds == null);

    }
    public void adjustHitbox(int x,int y){
        GreenfootImage hit = new GreenfootImage(x,y);
        hit.setColor(Color.red);
        hit.fill();
        hit.setTransparency(50);
        setImage(hit);
    }
}
