import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class WallShooter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WallShooter extends Enemy
{
    /**
     * Act - do whatever the WallShooter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int timer = 0;
    int delay = 10;
    int numOfFrames = 4;
    int frame = 0;
    
    int shotDelay = 0;
    
    boolean shot = false;
    
    String dir;
    
    
    public WallShooter(String dir){
        this.dir = dir;

        setImage("WallShooter_0.png");
        hitWidth = 70;
        hitHeight =100;
        
        attackWidth = 70;
        attackHeight = 100;
        
        health = 3;
        
        
    }
    
    public void act() 
    {
        // Add your action code here.
        shoot();
        checkHit();
        chooseAction();
        getImage().scale(100,100);
        decideDirection();
        if(frame >= 3 && timer > 9){
            getWorld().addObject(new WallShot(dir),getX(),getY());
        }
        if(health <= 0) deathAnimation();
    }    
    
    public void shoot(){
       if(shotDelay > 0){
           shotDelay--;
        }else{
            setRangeForMM(800);
            if(!megaMan.isEmpty())
                shot = true;
            shotDelay = 140;
        }
    }
    
    public void decideDirection(){
        if(dir.equals("up")){
            getImage().rotate(90);
        }else if(dir.equals("right")){
            getImage().rotate(180);
        }else if(dir.equals("down")){
            getImage().rotate(270);
        }
        else{}
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
        getWorld().addObject(new Explosion(getImage().getWidth(),getImage().getHeight()),getX(), getY());
        destroyed();
            
    }
    
    public void chooseAction(){
        if(shot){
            timer++;
            if(timer > delay){
                if(frame < numOfFrames - 1)
                    frame++;
                else {
                    frame = 0;
                    shot = false;
                }
            timer = 0;
            }
        }
        setImage("WallShooter_" + frame + ".png");
    }
    
}
