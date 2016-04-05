import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class RollingSpike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RollingSpike extends Enemy
{
    /**
     * Act - do whatever the RollingSpike wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    

    public int dir = 0;
    public String[] dirs = {"Left","Right"};
    
    public boolean movingLeft = true;
    public boolean movingRight = false;
    
    int move = 2;
    
    public int stunDelay = 0;
    
    public RollingSpike(){
        hitWidth= 60;
        hitHeight = 90;
    
        attackWidth = 100;
        attackHeight = 100;
        
        health = 5;
        delay = 3;
        numOfFrames = 3;
    }
    
    
    public void act() 
    {
        chooseAction();
        
        getImage().scale(110,100);
        
        checkHit();
        roll();
        if(health <= 0) deathAnimation();
      
    }    
    public void destroyed(){
        super.destroyed();      
        getWorld().removeObject(this);
    }
    
    public void nearMM(){
        setRangeForMM(1300);
        if(!megaMan.isEmpty()){
            setLocation(getX() - move,getY());
            eHitbox.setLocale(getX(),getY());
            attackBox.setLocale(getX(),getY());
        }
    }
    
    public void roll(){
        if(inAir()) fall();
        else stand(70);
        if(!eHitbox.canMoveLeft()) {
            move = -2;
            dir = 1;
        }
        else if(!eHitbox.canMoveRight()){
            move = 2;
            dir = 0;
        }
        nearMM();
    }
    public void checkHit(){

        List<Bullet> bulls = getObjectsInRange(50,Bullet.class);
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
    public void chooseAction(){
          
        timer++;
        if(numOfFrames > 0){
            if(timer >= delay){
                frame++;
                if(frame >= numOfFrames) frame = 0;
                timer = 0;
            
            }
        }
        setImage("RollingSpike" + dirs[dir] + "_" + frame + ".png");
    
    }
}
