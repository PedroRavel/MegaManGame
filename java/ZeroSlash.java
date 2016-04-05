import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZeroSlash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZeroSlash extends EnemyProjectile
{
    /**
     * Act - do whatever the ZeroSlash wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public ZeroSlash(){
        getImage().clear();
    }
    public int scaleY = 300;
    public boolean scale = true;
    public int timer = 160;
    public void act() 
    {
        // Add your action code here.
        
        changeScale();
        getImage().scale(scaleY - 150,scaleY);
        
    }    
        public void shoots(boolean dir){
        
        if(dir){
            setImage("ZeroSlash.png");
            setLocation(getX()+15,getY());
            getImage().mirrorHorizontally();
        }
        else {
            setImage("ZeroSlash.png");
            setLocation(getX()-15,getY());
            
        }
    }
    public void changeScale(){
        /*
        if(scale){
            
            timer++;
            if(timer >= 160) {
                scaleY = 220;
                scale = false;
            }
        } else {
            
            timer--;
            if(timer <= 159){
                scaleY = 200;
                scale = true;
            }
        }
        */
        
    }
    
    
}
