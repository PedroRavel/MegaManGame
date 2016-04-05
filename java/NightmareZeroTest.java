import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Color;
/**
 * Write a description of class NightmareZeroTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NightmareZeroTest extends Boss
{
    /**
     * Act - do whatever the NightmareZeroTest wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int x;
    int y;
    int frame;
    int timer;
    int delay = 10;
    
    int normalDelay = 150;
    int nearDeathDelay = 100;
    int actionDelay = normalDelay;
    
    int action = 0;
    int numOfFrames = 5;
    int getRight;
    int getLeft;
    int stunDelay = 0;
    int health = 100;
    
    int deathDelay = 25;
    int deathTimer = 0;
    int death;
    
    int shotAction = 0;
    int shotDelay = 3;
    int shotPosition = 0;
    int shotTimer = 0;
    
    boolean onePose = true;
    boolean checkFrame = false;
    
    boolean attack = false;
    boolean dashAttack;
    boolean ultimate = false;
    boolean shot = false;
    boolean shotBlast = false;
    
    boolean withinRange = false;
    boolean wave = false;
    
    //Color color = getImage().getColorAt(0,0);
    ArrayList<ZeroSlash> zSlashLeft = new ArrayList<ZeroSlash>();
    ArrayList<ZeroSlash> zSlashRight = new ArrayList<ZeroSlash>();
    
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int SLASH_LEFT = 2;
    private static final int SLASH_RIGHT = 3;
    private static final int DASH_LEFT = 4;
    private static final int DASH_RIGHT = 5;
    private static final int ULTIMATE = 6;
    private static final int SHOOT_RIGHT = 7;
    private static final int SHOOT_LEFT = 8;
    
    
    String[] poses = {"lt","rt","sl","sr","dl","dr","ul","shr","shl"};
    
    
    public NightmareZeroTest(){
      
        hitWidth = 70;
        hitHeight = 140;
        
        attackWidth = 200;
        attackHeight = 150;
    }
    
    public void act() 
    {
        // Add your action code here.
        
        if(health <= 0) deathAnimation();
        if(alive){
            checkFall(90);
        

        
            chooseAction();
            changeAction();
            zeroSlash();
        
            setImage("NightmareZero_" + poses[action] + "_" + frame + ".png");
            checkStun();
            eHitbox.setLocale(getX(),getY());
        

        } else {
            
            setImage("NightmareZeroDeath.png");
        
        }
        getRight = 0;
        getLeft = 0;
        while(getRight < zSlashRight.size())
        {
            ZeroSlash m = zSlashRight.get(getRight);
            getWorld().addObject(m,getX()+130,getY()-30); 
            m.shoots(true);
            if(m.isAtEdge()){
                getWorld().removeObject(m);
                zSlashRight.remove(m);
            }
            getRight++;

        }

        while(getLeft < zSlashLeft.size())
        {
            ZeroSlash m = zSlashLeft.get(getLeft);
            getWorld().addObject(m,getX()-130,getY()-30); 
            m.shoots(false);       
            if(m.isAtEdge()){
                getWorld().removeObject(m);
                zSlashLeft.remove(m);
            }

                getLeft++;

        }
    }    
    /*
    public void changeColor(){
        
        for(int y = getImage().getHeight()/4; y < getImage().getHeight(); y++){
            for(int x = getImage().getWidth()/4; x < getImage().getWidth(); x++){
                if(!getImage().getColorAt(x,y).equals(color))
                    getImage().setColorAt(x,y,Color.WHITE);
            }
        }
        
    }
    */    
    public void zeroSlash(){
        if(attack){
            if(action == SLASH_LEFT){
                if(frame > 4 && frame < 9)
                    getWorld().addObject(attackBox,getX()-130,getY());
                else 
                    getWorld().removeObject(attackBox);
                }
            else if(action == SLASH_RIGHT){           
                if(frame > 4 && frame < 9)
                    getWorld().addObject(attackBox,getX()+130,getY());
                else
                    getWorld().removeObject(attackBox);
            }
 
        }
    }
    public void deathAnimation(){
        getImage().clear();
        alive = false;
        deathTimer++;
        if(deathTimer >= deathDelay){
            getWorld().addObject(new Explosion(100,75),getX() + (Greenfoot.getRandomNumber(160) - 80), getY() + (Greenfoot.getRandomNumber(160) - 80));
            death++;
            deathTimer = 0;            
        }
        if(death >= 12) {
            destroyed();
            
        }
        
    }
    public void destroyed(){
        super.destroyed();
        getWorld().removeObject(this);
    }

    public void checkStun(){

        List<Bullet> bulls = getObjectsInRange(110,Bullet.class);
        for(Bullet bullet:bulls){
            bullet.isErased = true;
            if(stunDelay == 0){
                
                health -= bullet.getDamage();
                stunDelay = 70;
            }
        }
        if(stunDelay > 0){
            stunDelay--;
        }
        if(stunDelay < 70 && stunDelay != 0){
            if(stunDelay % 5 == 0)
                getImage().setTransparency(60);               
        }
    }
    public void changeAction(){
        
        if(withinRange){
            if(actionDelay > 0){
                actionDelay--;

            } else {
                frame = 0;
                int x = Greenfoot.getRandomNumber(40);
                if(x < 10 && x >= 0) {
                    dashAttack = true;
                    if(health > 50)
                    actionDelay = normalDelay;
                    else actionDelay = nearDeathDelay;
                }
                else if(x >= 10 && x < 20) {
                    attack = true;
                    wave = true;
                    if(health > 50)
                    actionDelay = normalDelay;
                    else actionDelay = nearDeathDelay;
                }
                else if(x >= 20 && x < 30) {
                    ultimate = true;
                    wave = true;
                    if(health > 50)
                    actionDelay = normalDelay;
                    else actionDelay = nearDeathDelay;
                }
                else if(x >= 30){
                    shot = true;
                    shotBlast = true;
                    if(health > 50)
                    actionDelay = normalDelay;
                    else actionDelay = nearDeathDelay;
                }
                
            
            
            }
        } else {
            frame = 0;
            withinRange = false;
            action = LEFT;
        }

    }
    
    public void createZeroShotUp(){
        
        shotTimer++;
        if(shotTimer >= shotDelay){
            if(shotPosition == 0){
                getWorld().addObject(new ZeroShotUp(), getX()+200,getY() + 150);
                getWorld().addObject(new ZeroShotUp(), getX()-200,getY() + 150);
                shotTimer = 0;
                shotPosition = 1;
            }
            else if(shotPosition == 1){
                getWorld().addObject(new ZeroShotUp(), getX()+400,getY() + 150);
                getWorld().addObject(new ZeroShotUp(), getX()-400,getY() + 150);
                shotTimer = 0;
                shotPosition = 2;
            }
            else if(shotPosition == 2){
                getWorld().addObject(new ZeroShotUp(), getX()+600,getY() + 150);
                getWorld().addObject(new ZeroShotUp(), getX()-600,getY() + 150);
                shotTimer = 0;
                shotPosition = 3;
            }
            else if(shotPosition == 3){
                getWorld().addObject(new ZeroShotUp(), getX()+800,getY() + 150);
                getWorld().addObject(new ZeroShotUp(), getX()-800,getY() + 150);
                shotTimer = 0;
                shotPosition = 0;
                wave = false;
            }
            
            
        }
        
        
        
    }
    
    public void chooseAction(){
        setRangeForMM(1000);
        timer++;
        //if(frame >= numOfFrames) frame = 0;
        if(numOfFrames > 0){
            if(timer >= delay){
                frame++;
                if(frame >= numOfFrames) frame = 0;
                timer = 0;
            
            }
        }
        
        if(!megaMan.isEmpty()){
            withinRange = true;
            for(MegaMan x: megaMan){
                if(attack){
                    
                    numOfFrames = 16;
                    delay = 4;
                    if(leftOf(x)){
                        action = SLASH_LEFT;
                        if(frame == 6 && wave) {
                            zSlashLeft.add(new ZeroSlash());
                            wave = false;
                        }
                    }
                    else{ 
                        action = SLASH_RIGHT;
                        if(frame == 6 && wave) {
                            zSlashRight.add(new ZeroSlash());
                            wave = false;
                        }
                    }
                    if(frame >= numOfFrames - 1){
                        
                        frame = 0;
                        attack = false;
                        
                    }
                    
                }
                else if(dashAttack){
                    numOfFrames = 3;
                    delay = 10;
                    if(leftOf(x)){
                        if(getX() > x.getX() + 100 && eHitbox.canMoveLeft()){
                            action = DASH_LEFT;
                            setLocation(getX() - 15, getY());
                        } else if(!x.inAir()) {
                            frame = 0;
                            dashAttack = false;
                            attack = true;
                            
                        }
                    }
                    else{ 
                        if(getX() < x.getX() - 100&& eHitbox.canMoveRight()){
                            action = DASH_RIGHT;
                            setLocation(getX() + 15, getY());
                        } else if(!x.inAir()){
                            frame = 0;
                            attack = true;
                            dashAttack = false;
                        }
                    }
                }
                else if(ultimate){
                    numOfFrames = 20;
                    delay = 5;
                    action = ULTIMATE;
                    if(frame >= 10 && wave) {
 
                        createZeroShotUp();
                        
                    }
                        if(frame >= numOfFrames - 1){
                        
                        frame = 0;
                        ultimate = false;
                        
                    }
                }
                else if(shot){
                    numOfFrames = 10;
                    if(frame == 4)shotBlast = true;
                    if(frame < 5)
                        delay = 8;
                    else
                        delay = 4;
                    if(leftOf(x)){
                        action = SHOOT_RIGHT;
                        if(frame == 5 && shotBlast){
                            getWorld().addObject(new ZeroShot("Lt"),getX()-80,getY() - 17);
                            shotBlast = false;
                        }
                    }
                    else{
                        action = SHOOT_LEFT;
                        if(frame == 5 && shotBlast){
                            getWorld().addObject(new ZeroShot("Rt"),getX()+80,getY() - 17);
                            shotBlast = false;
                        }
                    }
                }else{
                    numOfFrames = 5;
                    delay = 10;
                    if(leftOf(x))
                        action = LEFT;
                    else 
                        action = RIGHT;
                }
            }
        } else {
            frame = 0;
            withinRange = false;
            action = LEFT;
            
            
            
        }
    }
}












