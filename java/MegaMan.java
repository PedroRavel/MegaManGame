import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
/**
 * Write a description of class MegaMan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MegaMan extends Actor
{
    /**
     * Act - do whatever the MegaMan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    
    int timer;
    int airTimer;
    int numOfFrames;
    int frame;
    int oneFrame;
    int shotFrame;
    int charge;
    int delay;
    int walkSpeed = 6;
    int dasher;
    int action = 0;
    int actorHeight;
    int actorWidth;
    int jumpTime;
    int stepUp;
    int getRight;
    int getLeft;
    int countFrames = 0;
    int test = 0;
    int stunDelay = 0;
    
    int health = 135;
    int barHealth = 0;
    World world;

    ArrayList<Bullet> bulletRight = new ArrayList<Bullet>();
    ArrayList<Bullet> bulletLeft = new ArrayList<Bullet>();

    double gravity = 1;
    double jumpSpeed = 14;
    double fallSpeed; 
    
    int introFallspeed = 30;
    int introFrame = 0;
    int introDelay = 3;
    int introTimer = 0;
    
    int dashSpeed = 12; 
    int shootStance;
    int runShot;
    
    boolean airShotStance = false;
    boolean shoots = false;
    boolean isCharging = false;
    boolean restart = true;
    boolean oneAnimation = false;
    boolean dash = false;
    boolean dashJump = false;
    boolean back = false;
    boolean jumping = false;
    boolean falling = false;
    boolean wallSlide;
    boolean delayShot = false;
    
    boolean intro = true;
    
    boolean chargeLv1 = false;
    boolean chargeLv2 = false;
    boolean chargeLv3 = false;
    
    boolean idle = true;
    boolean runShoot = false;
    
    boolean left = false;
    boolean right = true;
    
    boolean stunned = false;
    
    boolean lookingLeft = false;
    
    

    private static final int IDLE_RIGHT  = 0;  
    private static final int IDLE_LEFT   = 1; 
    private static final int RIGHT       = 2; 
    private static final int LEFT        = 3; 
    private static final int JUMP_RIGHT  = 4; 
    private static final int JUMP_LEFT   = 5;
    private static final int DASH_RIGHT  = 6; 
    private static final int DASH_LEFT   = 7;  
    private static final int SHOOT_RIGHT = 8; 
    private static final int SHOOT_LEFT  = 9;
    private static final int WKICK_LEFT  = 10; 
    private static final int WALL_LEFT   = 11;  
    private static final int WKICK_RIGHT = 12; 
    private static final int WALL_RIGHT  = 13;
  
    private static final int WSHOOT_RIGHT = 14; 
    private static final int WSHOOT_LEFT  = 15;
    
    private static final int JUMP_SHOOT_RIGHT = 16; 
    private static final int JUMP_SHOOT_LEFT  = 17;
    
    private static final int RUN_SHOOT_RIGHT = 18; 
    private static final int RUN_SHOOT_LEFT  = 19;
    
    private static final int STUN_LEFT = 20; 
    private static final int STUN_RIGHT  = 21;    
    
    
    private static final int DASH_SHOOT_LEFT = 22; 
    private static final int DASH_SHOOT_RIGHT  = 23;  
    
    
    String[] poses = {"mm_ri_","mm_li_","mm_rt_","mm_lt_","mm_ju_","mm_jl_","mm_dr_","mm_dl_","mm_sr_","mm_sl_"
    ,"mm_kl_","mm_wl_","mm_kr_","mm_wr_","mm_rs_","mm_ls_","mm_ur_","mm_ul_","mm_br_","mm_bl_","mm_stl_","mm_str_","mm_dls_","mm_drs_"};
    
    boolean[] stances = {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
    ,false,false,false,false,false,false,false,false};
    
    String pose = "mm_ri_";
    
    Hitbox hit;
    ChargingAnimation cEffect;
    MegaManHealthBarPic mmBar;

    public MegaMan(int numOfFrames, int frame){
        this.numOfFrames = numOfFrames;
        this.frame = frame;
        hit = new Hitbox(85,140);
        actorHeight = hit.getImage().getHeight();
        actorWidth = hit.getImage().getWidth();
        

    }

    public void act() 
    { 

        adjustFrame();

        setImage(getImages(poses[action]));
        mmBar.bar.setBar(barHealth);
        
        world = getWorld();
        getRight = 0;
        getLeft = 0;
        
        
        
        
       
        if(getOneIntersectingObject(TestTile.class)!=null){
            if(getWorld() instanceof StageOne){
               StageTwo stageTwo = new StageTwo();
               Greenfoot.setWorld(stageTwo);
            } else {
               StageOne stageOne = new StageOne();
               Greenfoot.setWorld(stageOne);
            }
        }
        
       
        while(getRight < bulletRight.size())
        {

            Bullet m = bulletRight.get(getRight);
            if(!dash)
                world.addObject(m,getX()+70,getY()-30); 
            else 
                world.addObject(m,getX()+90,getY() + 10); 
            m.shoots(true);
            
            if(m.checkForCollisions()){
                m.removeBullet();
                bulletRight.remove(m);
            }
            getRight++;

        }

        while(getLeft < bulletLeft.size())
        {
           
            Bullet m = bulletLeft.get(getLeft);
            if(!dash)
                world.addObject(m,getX()-70,getY()-30); 
            else 
                world.addObject(m,getX()-90,getY() + 10); 
            m.shoots(false);       
            
            if(m.checkForCollisions()){
                m.removeBullet();
                bulletLeft.remove(m);
            }

            getLeft++;

        }
        if(intro)
            introMegaman();
        else
            activeMegaman();
    }
    public void introMegaman(){
        if(!hit.checkIntro()){
            setImage("mm_in_0.png");
            boundedIntroMove();
        }else{
            introTimer++;
            if(introTimer >= 25)
                intro = false;
            if(introTimer > introDelay){
                if(introFrame < 17){
                    introFrame++;
                    introTimer = 0;
                }
                
                
            }
            setImage("mm_in_" + introFrame + ".png");
        }
    }
    
        public void activeMegaman(){
            boundedMove();
             stun();
             cEffect.animate(this);
             if(!stunned){
             
                 if(inAir()&&!hit.canMoveLeft()&&Greenfoot.isKeyDown("left")&&!Greenfoot.isKeyDown("x")){
                fallSpeed = 3;
                jumping = false;
                jumpTime = 0;
                oneFrame = 0;
                wallSlide = true;
                dasher = 0;
                numOfFrames = 0;
                delay = 0;
            
                action = WALL_LEFT;
            

            }
            else if(Greenfoot.isKeyDown("x")&&inAir()&&!hit.canMoveLeft()&&Greenfoot.isKeyDown("left")){
                jump();
                
                if(jumpTime<=50) wallSlide = false;
            
                else if(jumpTime>50) {
                    fallSpeed = 3;
                    wallSlide = true;
                    numOfFrames = 0;
                    delay = 0;
                    action = WALL_LEFT;
                }
                
            }
            
            else if(inAir()&&!hit.canMoveRight()&&Greenfoot.isKeyDown("right")&&!Greenfoot.isKeyDown("x")){
                fallSpeed = 3;
                dasher = 0;
                jumping = false;
                jumpTime = 0;
                oneFrame = 0;
                wallSlide = true;
                
                numOfFrames = 0;
                delay = 0;
                stances[WALL_RIGHT] = true;
                action = WALL_RIGHT;
             

            }
            else if(Greenfoot.isKeyDown("x")&&inAir()&&!hit.canMoveRight()&&Greenfoot.isKeyDown("right")){
                jump();
            
                if(jumpTime<=50) wallSlide = false;
                else if(jumpTime>50){
                    fallSpeed = 3;
                    wallSlide = true;
                    numOfFrames = 0;
                    delay = 0;
                    stances[WALL_RIGHT] = true;
                    action = WALL_RIGHT;
                }
                
            }
            else wallSlide = false;
        }
        
        
        if(inAir()){
            checkHead();
            falling = true;
         // checkLanding();
        } else {
            fallSpeed = 0;
            jumping = true;
        }
        
        for(int i = 0;i<stances.length;i++) stances[i] = false;
        oneAnimation = false;
        if(!stunned)dash();
        if(!stunned)
        shoot();

        if(!inAir()){
           
            hit.setLocation(hit.getX(),hit.upperBounds.getY()-((actorHeight/2)+15));
            setLocation(hit.getX(),hit.getY()); 
            fallSpeed = 0;
            jumpTime = 0;
            jumping = false;
            wallSlide = false;
            airShotStance = false;
            if(test!=0) test = 0;
        }
      
        
        
        
        
        
        
       ///////////////////////////////////////////////////////////////////////////////////////////////////
        if(!stunned){
            if((inAir()&&!shoots)||(wallSlide&&Greenfoot.isKeyDown("x"))){
            
                if(left){
                    stances[JUMP_LEFT] = true;
                }
                if(right){
                    stances[JUMP_RIGHT]   = true;
                }
            
                oneAnimation = true;
                if(!dash){
                    falling = true;  
                }
            
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        
        
        
        
        
        if(Greenfoot.isKeyDown("x") && !stunned){
           
           if(!dash){
                falling = true;
                jumping = true;
            } 
           if(dash&&!inAir()) dashJump = true;
        } else { 
            if(!wallSlide) jumpTime = 30;

        }
        if(jumping) jump();
        
        if(falling&&!dash) fall();
        
        if(!Greenfoot.isKeyDown("z")&&!dash&&!inAir()) dasher = 0;
        
        
        if(Greenfoot.isKeyDown("z") && !stunned){
            idle = false;
            if(Greenfoot.isKeyDown("x")&&!inAir()){
                
            }
            if(dasher==0){
                dash = true;
                dasher++;
            } 

            
        } else{
            dash = false;
            if(dashJump&&dasher >= 0&&test==0){
                dasher = 0;
                test = 1;
            }
           
           
        }
        
        
        ////////////////////////////////////////////////////////////////
        if(Greenfoot.isKeyDown("c")){
            charge++;
            runShot++;
            if(charge>20)
            isCharging = true;
            if(delayShot) shotFrame = 0;
            delayShot = false;
            if(inAir()){
                 if(!wallSlide)
                airShotStance = true;
            }

            if(shoots&&!idle&&runShot<=50&&!dash) shootStance = 0;
 
            if(countFrames == 0) shoots = true;
        }
        
        else if(!Greenfoot.isKeyDown("c")){
            isCharging = false;
            if(charge>20&&charge<=110){
                chargeLv1 = true;
                chargeLv2 = false;
                chargeLv3 = false;
            }
            else if(charge>110&&charge<=200){
                chargeLv1 = false;
                chargeLv2 = true;
                chargeLv3 = false;
            }
            else if(charge>200){
                chargeLv1 = false;
                chargeLv2 = false;
                chargeLv3 = true;
            }

            if(!shoots){
                runShoot = false;
                countFrames = 0;
                shootStance = 0;
             
            }
            
            charge = 0;
            runShot = 0;
            
            
            
        }
        if(Greenfoot.isKeyDown("left") && !stunned) lookingLeft = true;
        if(Greenfoot.isKeyDown("right") && !stunned) lookingLeft = false;
        
        
        if(!stunned){
            if(Greenfoot.isKeyDown("left")&&hit.canMoveLeft()){ 
                idle = false;
                if(!dash){
                    hit.setLocation(hit.getX()-walkSpeed,hit.getY());
                    setLocation(hit.getX(),hit.getY());
                    if(!inAir()&&!shoots)
                    stances[LEFT] = true;
                }
                left = true;
                right = false;
        
            }
            else if(Greenfoot.isKeyDown("right")&&hit.canMoveRight()) {
                idle = false;
            
                if(!dash){
                    hit.setLocation(hit.getX()+walkSpeed,hit.getY());
                    setLocation(hit.getX(),hit.getY());
                    if(!inAir()&&!shoots)
                
                    stances[RIGHT] = true;
                }
                right = true;
                left = false;

            } else {
                idle = true;
            
            }
            if(right) stances[IDLE_RIGHT] = true;
            else if(left)stances[IDLE_LEFT]    = true;
        }
    }    
   protected void addedToWorld(World w){
            cEffect = new ChargingAnimation();
            mmBar = new MegaManHealthBarPic();
          
            w.addObject(cEffect,hit.getImage().getWidth()/2,hit.getImage().getHeight()/2);
            w.addObject(mmBar,100,150);
        
    }
    public void stun(){
        
        
            if(stunDelay == 0 && hit.wasHit()){
                stunned = true;
                health -=10;
                barHealth +=10;
                stunDelay = 60;

            } 
            if(stunDelay > 0) {
                stunDelay--;
            }
            if(stunned){
                if(lookingLeft) {
                    hit.setLocation(hit.getX()+2,hit.getY());
                    setLocation(hit.getX(),hit.getY());
                } 
                if(!lookingLeft){
                    hit.setLocation(hit.getX()-2,hit.getY());
                    setLocation(hit.getX(),hit.getY());
                }
            }
            if(stunDelay < 35)
                stunned = false;
            if(stunDelay < 35 && stunDelay != 0) getImage().setTransparency(100);
    }
        public void boundedIntroMove(){
            hit.setLocation(hit.getX(),(hit.getY()+introFallspeed));
            setLocation(hit.getX(),hit.getY());
            if((introFallspeed+hit.getY() <= (int)(getWorld().getHeight()/1.6)&&((MegaManStages)getWorld()).getYOffset()< - 20)){
                hit.setLocation(getX(),(int)(getWorld().getHeight()/1.6));
                setLocation(hit.getX(),hit.getY());
                ((MegaManStages)getWorld()).shiftWorld(0,-(int)introFallspeed);
            }
            if((introFallspeed+hit.getY() >= (int)(getWorld().getHeight()/1.6))&&((MegaManStages)getWorld()).getYOffset()>((MegaManStages)getWorld()).getBottomYLimit()){
                hit.setLocation(getX(),(int)(getWorld().getHeight()/1.6));
                setLocation(hit.getX(),hit.getY());
                ((MegaManStages)getWorld()).shiftWorld(0,-(int)introFallspeed);
            } 
        }
    public void boundedMove(){

            if(walkSpeed+hit.getX() <= getWorld().getWidth()/2&&((MegaManStages)getWorld()).getXOffset()<-10){

                
                if((Greenfoot.isKeyDown("left")&&hit.canMoveLeft())||(dash&&hit.canMoveLeft())||(dashJump&&hit.canMoveLeft())||(stunned & !lookingLeft)){

                    hit.setLocation(getWorld().getWidth()/2,getY());
                    setLocation(hit.getX(),hit.getY());  

                    if(!dash&&!dashJump&&!stunned){
                        
                        ((MegaManStages)getWorld()).shiftWorld(walkSpeed,0);
                    }
                    else if((dash||dashJump) && !stunned){
                        ((MegaManStages)getWorld()).shiftWorld(dashSpeed,0);
                        
                    }
                    else {
                        ((MegaManStages)getWorld()).shiftWorld(2,0);
                    }
  
                }
            }
            
        
            else if(walkSpeed+hit.getX() >= getWorld().getWidth()/2&&((MegaManStages)getWorld()).getXOffset()>((MegaManStages)getWorld()).getRightXLimit()){
                if(Greenfoot.isKeyDown("right")&&hit.canMoveRight()||(dash&&hit.canMoveRight())||(dashJump&&hit.canMoveRight())||(stunned & lookingLeft)){
                    
                    hit.setLocation(getWorld().getWidth()/2,getY());
                    setLocation(hit.getX(),hit.getY());  
                    
                    if(!dash&&!dashJump&&!stunned)((MegaManStages)getWorld()).shiftWorld(-walkSpeed,0);
                    else if((dash||dashJump)&&!stunned)((MegaManStages)getWorld()).shiftWorld(-dashSpeed,0);
                    else ((MegaManStages)getWorld()).shiftWorld(-2,0);

                }
        
         
            } 
            if((walkSpeed+hit.getY() <= (int)(getWorld().getHeight()/1.6)&&((MegaManStages)getWorld()).getYOffset()< - 20)&&inAir()){
                    if(!dash){
                       hit.setLocation(getX(),(int)(getWorld().getHeight()/1.6));
                        setLocation(hit.getX(),hit.getY());

                        ((MegaManStages)getWorld()).shiftWorld(0,-(int)fallSpeed);
                    
                    } 
                }
            else if((walkSpeed+hit.getY() >= (int)(getWorld().getHeight()/1.6))&&inAir()&&((MegaManStages)getWorld()).getYOffset()>((MegaManStages)getWorld()).getBottomYLimit()){

                if(!dash){
                    hit.setLocation(getX(),(int)(getWorld().getHeight()/1.6));
                    setLocation(hit.getX(),hit.getY());
                    
     
                    if(fallSpeed<=20) ((MegaManStages)getWorld()).shiftWorld(0,-(int)fallSpeed);
                    else ((MegaManStages)getWorld()).shiftWorld(0,-20);

                }
         
            } 
        
        
    }
    
    public boolean inAir(){       
         return hit.upperBounds == null ;
       
    }
    public boolean test2(){
        Platforms ground = getOneObjectAtOffset(0,-actorHeight/2,Platforms.class);
        
        return ground == null;
    }
    public void wallShootRight(){
                numOfFrames = 0;
                delay = 0;
                action = WSHOOT_RIGHT;
    }
    
    public void wallShootLeft(){
                numOfFrames = 0;
                delay = 0;
                action = WSHOOT_LEFT;
    }
    public void checkHead(){
        int actorHead = -actorHeight/2;
        stepUp = 0;
        
        while(fallSpeed < 0 && stepUp > fallSpeed && getOneObjectAtOffset(0,actorHead + (stepUp),Tiles.class) == null){
            stepUp--;
           
        }
        if(fallSpeed<0){
            fallSpeed = stepUp;
        }
        
    }
    public void checkLanding(){
        int actorFeet = actorHeight/2;
        int stepDown = 0;
        while(fallSpeed>0&&stepDown<fallSpeed && getOneObjectAtOffset(0,actorFeet + (stepDown),Tiles.class) == null) stepDown++;
        if(fallSpeed > 0) fallSpeed = stepDown;
        
    }
    
    public void jump(){
        jumpTime++;
        if(getOneObjectAtOffset(0,-actorHeight/2 + (stepUp),Tiles.class) == null)
            if(jumpTime<20)fallSpeed = -jumpSpeed;
        else jumping = false;
       falling = true;

    }

    public void fall(){
        hit.setLocation(hit.getX(),(int)(hit.getY()+fallSpeed));
        setLocation(hit.getX(),hit.getY());
        fallSpeed = (fallSpeed + gravity);

    }

    public void dash(){

        if(dash){
            dasher++;

            if(left){
                if(!shoots)stances[DASH_LEFT] = true;
                else if(shoots)stances[DASH_SHOOT_LEFT] = true;
                if(hit.canMoveLeft()){
                    hit.setLocation((int)(hit.getX()-dashSpeed),hit.getY());
                    setLocation(hit.getX(),hit.getY());
                }
            }
            else if(right){
                if(!shoots)stances[DASH_RIGHT] = true;
                else if(shoots)stances[DASH_SHOOT_RIGHT] = true;
                if(hit.canMoveRight()){
                    hit.setLocation((int)(hit.getX()+dashSpeed),hit.getY());
                    setLocation(hit.getX(),hit.getY());
                }
            }    

        }
        if(dashJump&&airTimer == 0){
            dash = false;
            back = true;

        
        }
        if(back){
            airTimer++;
                if(left){
                    if(hit.canMoveLeft()){
                        hit.setLocation(hit.getX()-dashSpeed,hit.getY());
                        setLocation(hit.getX(),hit.getY());
                    }
                }
                else if(right){ 
                    if(hit.canMoveRight()){
                        hit.setLocation(hit.getX()+dashSpeed,hit.getY());
                        setLocation(hit.getX(),hit.getY());
                    }
                }

            }
        if(airTimer > 1) {
            if(!inAir()){
                airTimer = 0;
                back = false;
                dashJump = false;
            }
        }
        if(wallSlide){
            airTimer = 0;
            back = false;
            dashJump = false;
        }
        if(dasher>=50){
            dash = false;
        }
        
    }
    public void resetShot(){
         if(!shoots) {
            
            if(getShotFrame()>0) 
                setShotFrame(0);
            charge = 0;
        }
    }
  
   public void shoot(){

       if(shoots){
           if(charge == 1&&((right&&!wallSlide)||(wallSlide&&left))) bulletRight.add(new BaseBullet(0,0));
           else if(charge == 1&&((left&&!wallSlide)||wallSlide&&right)) bulletLeft.add(new BaseBullet(0,0));
       }
        
        if(idle&&shootStance != 0&&!wallSlide&&!inAir()) {
           runShoot = false;
           shoots = false;
        }
        else if(!idle&&!inAir()){
          
            if(shootStance>=50) {
                shoots = false;
                
            }
            if(shoots){
              
                shootStance++;
                runShoot = true;
                if(left) stances[RUN_SHOOT_LEFT] = true;
                if(right) stances[RUN_SHOOT_RIGHT] = true;
            }
        }
        
       
        else if(!inAir()&&!wallSlide&&!runShoot){

            if(shoots){
     
                if(left) stances[SHOOT_LEFT] = true;
                if(right) stances[SHOOT_RIGHT] = true;
            
            }
           if(countFrames>=1) {
                shoots = false;
        
            }
            if(!shoots) {
                
                
                if(getShotFrame()!=0){ 
                    
                    shotFrame = 0;
     
                }


            }

        }
        else if(inAir()&&!wallSlide){
            shootStance++;
            if(shootStance>=20) {
                shoots = false;
            }
            if(shoots){
                if(shootStance<=20){
                    if(left){
                        countFrames = 0;
                        stances[JUMP_SHOOT_LEFT] = true;
                    } else if(right){
                        countFrames = 0;
                        stances[JUMP_SHOOT_RIGHT] = true;
                    }
                } 
            }
        }
        else if(dash){
            shootStance++;
            
            if(shootStance>=50) {
                shoots = false;
            }
            if(shoots){
                
               // if(shootStance<=50){
                    
                   
                   
                    if(left){
                    
                        stances[DASH_SHOOT_LEFT] = true;
                    } else if(right){
                   
                        stances[DASH_SHOOT_RIGHT] = true;
                   // }
                } 
            }
        }
        
         else {
            shootStance++;
            if(shootStance>=20) {
                shoots = false;
            }
            if(shoots){
                if(wallSlide&&left){
                    if(shootStance<=20){
                        wallShootRight();
                    }
                }
                else if(wallSlide&&right){
                    if(shootStance<=20){
                        wallShootLeft();
                    }
                }
            }
        }
        if(!isCharging){
  
            if(chargeLv1){
                shoots = true;
                if((right&&!wallSlide)||(wallSlide&&left)) bulletRight.add(new Lv1Charge(getX()+40,getY()));
                else if((left&&!wallSlide)||wallSlide&&right) bulletLeft.add(new Lv1Charge(getX()-40,getY()));
                chargeLv1 = false;
            }
            else if(chargeLv2){
                shoots = true;
                if((right&&!wallSlide)||(wallSlide&&left)) bulletRight.add(new Lv3Charge(getX()+40,getY()));
                else if((left&&!wallSlide)||wallSlide&&right) bulletLeft.add(new Lv3Charge(getX()-40,getY()));
                chargeLv2 = false;
            }
            else if(chargeLv3){
      
                shoots = true;
                if((right&&!wallSlide)||(wallSlide&&left)) bulletRight.add(new Lv2Charge(getX()+40,getY()));
                else if((left&&!wallSlide)||wallSlide&&right) bulletLeft.add(new Lv2Charge(getX()-40,getY()));
                chargeLv3 = false;                    
            }
        }
   }

   
    public void adjustFrame(){
        //System.out.println("Frame: " + frame + " numOfFrames: " + numOfFrames);
        if(stunned){
            numOfFrames = 4;
            delay = 3;
            if(!lookingLeft)           action = STUN_LEFT;
            else if(lookingLeft)     action = STUN_RIGHT;;            
            
        }
       
        if(!wallSlide){
            
            if(stances[IDLE_RIGHT]||stances[IDLE_LEFT]){
                numOfFrames = 5;
                delay = 11;
                restart = true;

                if(stances[IDLE_RIGHT]) action = IDLE_RIGHT;//pose = "mm_ri_";
                else if(stances[IDLE_LEFT])  action = IDLE_LEFT;//pose = "mm_li_";

            }


            if(stances[SHOOT_LEFT]||stances[SHOOT_RIGHT]){
            
            
                numOfFrames = 7;
                delay = 5;
                restart = true;
                if(stances[SHOOT_LEFT]) action = SHOOT_LEFT;
                if(stances[SHOOT_RIGHT]) action = SHOOT_RIGHT;
            }
            if(stances[RUN_SHOOT_RIGHT]||stances[RUN_SHOOT_LEFT]){
                numOfFrames = 14;
                delay = 3;
            
       
                restart = true;
                if(stances[RUN_SHOOT_LEFT]) action = RUN_SHOOT_LEFT;
                if(stances[RUN_SHOOT_RIGHT]) action = RUN_SHOOT_RIGHT;
            }
        
            if(stances[RIGHT] || stances[LEFT]){
                numOfFrames = 16;
                delay = 3;
                restart = false;

                if(stances[LEFT]) action = LEFT;// pose = "mm_lt_";
                else if(stances[RIGHT]) action = RIGHT;//pose = "mm_rt_";
            }
            if(stances[JUMP_RIGHT] || stances[JUMP_LEFT]){
                numOfFrames = 8;
                delay = 3;

                if(stances[JUMP_LEFT]) action = JUMP_LEFT; //pose = "mm_jl_";
                else if(stances[JUMP_RIGHT]) action = JUMP_RIGHT;//pose = "mm_ju_";
            }
            if(stances[JUMP_SHOOT_RIGHT] || stances[JUMP_SHOOT_LEFT]){
                numOfFrames = 0;
                shotFrame = 0;
                delay = 0;

                if(stances[JUMP_SHOOT_LEFT]) action = JUMP_SHOOT_LEFT; //pose = "mm_jl_";
                else if(stances[JUMP_SHOOT_RIGHT]) action = JUMP_SHOOT_RIGHT;//pose = "mm_ju_";
            }
            if(stances[DASH_SHOOT_RIGHT] || stances[DASH_SHOOT_LEFT]){
                numOfFrames = 3;
                delay = 2;
              

                if(stances[DASH_SHOOT_LEFT])  action = DASH_SHOOT_LEFT;//pose = "mm_dl_";
                else if(stances[DASH_SHOOT_RIGHT]) action = DASH_SHOOT_RIGHT;//pose = "mm_dr_";
            }
            if(stances[DASH_RIGHT] || stances[DASH_LEFT]){
                numOfFrames = 2;
                delay = 2;
                if(inAir()) oneAnimation = false;

                if(stances[DASH_LEFT])  action = DASH_LEFT;//pose = "mm_dl_";
                else if(stances[DASH_RIGHT]) action = DASH_RIGHT;//pose = "mm_dr_";
            }

        }  
    }

    public int getFrame(){
        return frame;
    }

    public int getShotFrame(){
        return shotFrame;
    }

    public void setFrame(int f){
        frame = f;
    }

    public void setShotFrame(int s){
        shotFrame = s;
    }

    public String getImages(String name){
        timer++;

        if(!oneAnimation&&!shoots){ 
            if(frame >= numOfFrames) frame = 0;
            if(timer >= delay){
                frame++;
                if(frame >= numOfFrames) 
                    if(restart) frame = 0;
                    else        frame = 2;

                timer = 0;
            }
            return name + frame + ".png";
        }
        else if(oneAnimation&&!shoots){

            if(!inAir()) oneFrame = 0;
            if(oneFrame >= numOfFrames){
                oneFrame = 0;
                timer = 0;
            }

            else if(timer >= delay){

                oneFrame++;
                if(oneFrame >= numOfFrames) oneFrame = numOfFrames-1;
                

                timer = 0;
            }
            return name + oneFrame + ".png";
        }
        else if(shoots){ 

            if(shotFrame >= numOfFrames) shotFrame = 0;

            if(timer >= delay){

                shotFrame++;
                if(shotFrame >= numOfFrames) {
                //if(restart) 
                    shotFrame = 0;
                    countFrames = 1;
                    
                }
                //else        frame = 2;

                timer = 0;
            }

                return name + shotFrame + ".png";
        }

        return null;
    }

    
}
