import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ChargingAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChargingAnimation extends Actor
{
    /**
     * Act - do whatever the ChargingAnimation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int x;
    int y;
    int frame;
    int timer;
    int delay;
    int numOfFrames;
    
    public ChargingAnimation() 
    {
        this.x = x;
        this.y = y;
        delay = 10;
        numOfFrames = 10;
        getImage().clear();
        
    }    
    
    public void act(){
        
        
        
    }
    
    public void animate(MegaMan x){
        if(x.isCharging){
            setImage(getImages("ChargeEffect"));
            getImage().scale(300,300);
            
        } else {
           getImage().clear();
           frame = 0;
        }
        if(x.charge>20&&x.charge<=110) delay = 10;
        else if(x.charge>110&&x.charge<=200) delay = 4;
        else if(x.charge>200) delay = 2;
        
        setLocation(x.hit.getX(),x.hit.getY());
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

}
