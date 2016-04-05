import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WallShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WallShot extends EnemyProjectile
{
    /**
     * Act - do whatever the WallShot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int timer = 0;
    int delay = 5;
    int frame = 0;
    int numOfFrames = 3;
    
    int speedX = 6;
    int speedY = 6;
    
    int shoot = 0;
    boolean shot = false;
    String dir;
    
    public WallShot(String dir){
        this.dir = dir;
    }
    
    
    
    public void act() 
    {
        
      
        
        changeFrame();
       
        decideDirection();
        
        
}   
    public void decideDirection(){
        if(dir.equals("up")){
            setLocation(getX(),getY() - speedY);
            getImage().rotate(90);
            getImage().scale(60,80);
        }else if(dir.equals("right")){
            setLocation(getX() + speedX,getY());
            getImage().mirrorHorizontally();
            getImage().scale(80,60);
        }else if(dir.equals("down")){
            setLocation(getX(),getY() + speedY);
            getImage().rotate(270);
            getImage().scale(60,80);
        }
        else{
            setLocation(getX() - speedX,getY());
            getImage().scale(80,60);
        }
    }
    
    public void changeFrame(){
        timer++;
        if(timer > delay){
            if(frame < numOfFrames - 1){
                frame++;
            }
            else {
                frame = 0;
                shot = false;
            }
            timer = 0;
        }
        setImage("WallShot_" + frame + ".png");
   }
}
    
    
    
    
    

