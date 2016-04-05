import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Bullet extends ScrollingActor
{
    
    int x;
    int y;
    int frame;
    int timer;
    int delay;
    int numOfFrames;
    int damage;
    
    boolean isErased = false;
    
    
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;

        getImage().clear();
        
    }
    public void act() 
    {

        

    
    }   
    
    public int getDamage(){
        return damage;
    }
    
    public void setDamage(int damage){
        this. damage = damage;
    }
    
    public void shoots(boolean dir){
        if(dir)
            setLocation(getX()+15,getY());
        else {
            setLocation(getX()-15,getY());
            getImage().mirrorHorizontally();
        }
    }
    public boolean checkForCollisions(){
        //if(getOneIntersectingObject(EnemyHitbox.class)!=null) 
        //if(getOneObjectAtOffset(getImage().getWidth()/4,0,EnemyHitbox.class)!=null ||
        //getOneObjectAtOffset(-getImage().getWidth()/4,0,EnemyHitbox.class)!=null)
           // isErased = true;
        
        return getOneObjectAtOffset(getImage().getWidth()/2,0,Tiles.class)!=null || 
                    getOneObjectAtOffset(-getImage().getWidth()/2,0,Tiles.class)!=null || 
                        isAtEdge()|| 
                            isErased;
                       
    }
    public void removeBullet(){
        getWorld().removeObject(this);
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