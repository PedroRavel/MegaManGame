import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class BoltBlaster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoltBlaster extends Enemy
{
    /**
     * Act - do whatever the BoltBlaster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
   
    ArrayList<Projectile> projectile = new ArrayList<Projectile>();
    boolean shoot = true;
   
    
    int projectileAmount;
    int boltNum = 0;
    int shotTimer;
    int time;
    
    
    public BoltBlaster(){
        hitWidth = 170;
        hitHeight =200;
        
        attackWidth = 170;
        attackHeight = 200;
        
        health = 3;
        numOfFrames = 4;
        delay = 10;
    }
    
    
    public void act() 
    {
    
        handleList();
        chooseAction();
        
        checkFall(120);
        getImage().scale(170,200);
         checkHit();
        if(health <= 0) deathAnimation();
        
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
        getWorld().addObject(new Explosion(175,150),getX(), getY());
        destroyed();
            
    }
    
        
    public void handleList(){
        projectileAmount = 0;
        while(projectileAmount < projectile.size()){
            getWorld().addObject(projectile.get(projectileAmount),getX(),getY());
    
            projectileAmount++;
        }
    }
    public void blast(){
        
            if(shotTimer > 0){
                shotTimer--;
            }else{
                projectile.add(new Projectile("BoltBlast",2,9));
                boltNum++;
                shotTimer = 50;
            }
        
    }
    public void idle(){
        numOfFrames = 4;
        delay = 10;
        timer++;
        if(numOfFrames > 0){
            if(timer >= delay){
                frame++;
                if(frame >= numOfFrames) frame = 0;
                timer = 0;
            
            }
        }
    }

    
    
    public void shoot(){
        numOfFrames = 11;
        delay = 6;
        timer++;
        if(shoot && frame > 9){
            blast();
        }
        if(timer >= delay){
            frame++;
            if(frame >= numOfFrames) frame = numOfFrames - 1;
            timer = 0;
            
            
        }
    }
    

    public void chooseAction(){
        eHitbox.setLocale(getX(),getY()); 
        attackBox.setLocale(getX(),getY());
        setRangeForMM(700);

        if(!megaMan.isEmpty()){
            for(MegaMan x: megaMan){
                
                
                
                if(boltNum < 3)
                    shoot = true;
                else if(boltNum >= 3){
                    time++;
                    shoot = false;
                }
                if(time > 120){ 
                    boltNum = 0;
                    time = 0;
                }
                if(leftOf(x) && shoot){
                    shoot();
                }
                else idle();
            }  
                
        }else {
            time = 0;
            shotTimer = 0;
            boltNum = 0;
            idle();
            
        }
        setImage("BoltBlaster" + frame + ".png");
    
    }
}

