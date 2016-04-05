import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class NightmareZero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NightmareZero extends Boss
{
    /**
     * Act - do whatever the NightmareZero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int x;
    int y;
    int frame;
    int timer;
    int delay = 10;
    int actionDelay = 100;
    int action = 0;
    int numOfFrames = 5;
    
    boolean onePose = true;
    boolean checkFrame = false;
    boolean attack = false;
    
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int SLASH_LEFT = 2;
    private static final int SLASH_RIGHT = 3;
    
     String[] poses = {"lt","rt","sl","sr"};
     
    public NightmareZero(int width, int height){
     
    }
    
    public void act() 
    {   
        checkFall(90);
        //changeAction();
        chooseAction();
        setImage(getImages(poses[action]));
        
        //chooseImage();
        eHitbox.setLocale(getX(),getY());
        
    }    
    
    public void chooseAction(){
        //changePose();
        checkPose();
        setRangeForMM(1000);
        if(!megaMan.isEmpty()){
            for(MegaMan x: megaMan){
                if(leftOf(x)){
                    if(Greenfoot.getRandomNumber(1000) < 10){
                        
                        onePose = true;
                        changePose();
                        action = SLASH_LEFT;
 
                    } //else action = LEFT;
                }
                else if(rightOf(x)){
                    if(Greenfoot.getRandomNumber(1000) < 10){
                        
                        onePose = true;
                        changePose();
                        action = SLASH_RIGHT;
               
                    }//else action = RIGHT;
                }
                if(!onePose){
            
                    if(leftOf(x))action = LEFT;
                    else action = RIGHT;
                
                    
                }
            }
       
        } 

    }

    public void checkPose(){
        if(action == 0 || action == 1) {
            numOfFrames = 5;
            delay = 10;
        }else{
            numOfFrames = 16;
            delay = 4;
        }
    }
    public void changePose(){
        if(onePose && !checkFrame){
            frame = 0;
            checkFrame = true;
        }
        //action = Greenfoot.getRandomNumber(4);
    }
    
    public void switchAction(){
        frame = 0;
        action = Greenfoot.getRandomNumber(4);
    }
    /*
    public void chooseImage(){
        
        if(action == 0 || action == 1) {
            numOfFrames = 5;
            delay = 10;
            setImage(getImages(poses[action]));
        }
        else {
            
            numOfFrames = 15;
            delay = 4;
            
            if(frame < numOfFrames){
                timer++;
                if(timer >= delay){
                    frame++;
                    timer = 0;
                }
                setImage("NightmareZero_" + poses[action] + "_" + frame + ".png");
                
            }
            
        }
      
    }
    */
    
   public void chooseImage(){
        
        if(action == 2 || action == 3) {
            numOfFrames = 15;
            delay = 4;
            
            if(frame < numOfFrames){
                timer++;
                if(timer >= delay){
                    frame++;
                    timer = 0;
                }
                setImage("NightmareZero_" + poses[action] + "_" + frame + ".png");
                
            }
        }
        else {
            
            
            
        }
      
    }
     public String getImages(String name){
         timer++;
        
         if(action == 1 || action == 0){
             if(timer >= delay){

                frame++;
                if(frame >= numOfFrames) frame = 0;
                
                timer = 0;
            }
           
            return "NightmareZero_" + name + "_" + frame + ".png";
        }
        else {
            
            if(onePose){
                if(frame == numOfFrames-1) {
                    onePose = false;
                    checkFrame = false;
                    frame = 0;
                }
                if(timer >= delay){

                    frame++;
                    if(frame >= numOfFrames) frame = 0;
                
                    timer = 0;
                }
            }
           
            return "NightmareZero_" + name + "_" + frame + ".png";
            
        }
        

    }
}
