import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Enemy extends ScrollingActor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    int health;
    
    int timer;
    int numOfFrames;
    int delay;
    int frame;
    
    int actorHeight = getImage().getHeight()/2;
    
    double gravity = 1;
    double fallSpeed; 
    
    int hitWidth;
    int hitHeight;
    
    int attackWidth;
    int attackHeight;
    
    boolean alive = true;
    
    EnemyHitbox eHitbox;
    EnemyAttackBox attackBox;
    
    List<MegaMan> megaMan;
    
    
    public void act() 
    {
        eHitbox.setLocale(getX(),getY());
    }    
    protected void addedToWorld(World w){
        
        eHitbox = new EnemyHitbox(hitWidth,hitHeight);
        attackBox = new EnemyAttackBox(attackWidth,attackHeight);
        w.addObject(eHitbox,this.getX(),this.getY());
        w.addObject(attackBox,this.getX(),this.getY());
    }
    
    public void destroyed(){
        getWorld().removeObject(eHitbox);
        getWorld().removeObject(attackBox);
    }
    
    public void setRangeForMM(int x){
        megaMan = getObjectsInRange(x,MegaMan.class);
    }
    
    public abstract void chooseAction();
    
    public boolean leftOf(MegaMan x){
        return x.getX() < getX();
    }
    
    public boolean rightOf(MegaMan x){
        return x.getX() > getX();
    } 
    
    public boolean inAir(){
        return eHitbox.upperBounds == null;
    }
    
    public void canMoveRight(){
        
    }
    public void canMoveLeft(){
        
    }
    public void checkFall(int yOff){
        if(inAir()) fall();
        else stand(yOff);
        
    }
    
    public void fall(){
        setLocation(getX(),(int)(getY()+fallSpeed));
        fallSpeed = (fallSpeed + gravity);
    }
    public void stand(int yOff){
        fallSpeed = 0;
        setLocation(getX(),eHitbox.upperBounds.getY()-yOff);
        //setLocation(getX(),getY());
    }
}
