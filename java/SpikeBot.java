import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class SpiikeBot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpikeBot extends Enemy
{
    /**
     * Act - do whatever the SpiikeBot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    
    public int stunDelay = 0;
    
    public SpikeBot(){
        hitWidth = 75;
        hitHeight = 75;
        
        attackWidth = 75;
        attackHeight = 75;
        
        health = 5;
        
        delay = 5;
        numOfFrames = 5;
    }
    
    
    public void destroyed(){
        super.destroyed();
        getWorld().removeObject(this);
    }
    public void checkHit(){

        List<Bullet> bulls = getObjectsInRange(60,Bullet.class);
        for(Bullet bullet:bulls){
            health -= bullet.getDamage();
            bullet.isErased = true;
            

        }
    }
    public void deathAnimation(){
        getImage().clear();
        alive = false;
        getWorld().addObject(new Explosion(100,75),getX(), getY());
        destroyed();
            
    }
        
    
    public void chase(){
        eHitbox.setLocale(getX(),getY());
        attackBox.setLocale(getX(),getY());
        setRangeForMM(1000);
        for(MegaMan x:megaMan){
            int deltaX = getX() - x.getX();
            int deltaY = getY() - x.getY();
            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                if (deltaX > 0) {
                    setLocation(getX() - 2, getY());
                }
                else if (deltaX < 0) {
                    setLocation(getX() + 2, getY());
                }
            }
            else {
                if (deltaY > 0) {
                    setLocation(getX(), getY() - 2);
                }
                else if (deltaY < 0) {
                    setLocation(getX(), getY() + 2);
                }
            }
        }
    }
    
    public void chooseAction(){
        timer++;
        if(numOfFrames > 0){
            if(timer >= delay){
                frame++;
                if(frame >= numOfFrames) frame = 0;
                timer = 0;
            
            }
        }
        setImage("SpikeBot_" + frame + ".png");
    }
    public void act() 
    {
        // Add your action code here.
        
        chooseAction();
        chase();
        getImage().scale(100,100);
        checkHit();
        if(health <= 0) deathAnimation();
    }    
}
