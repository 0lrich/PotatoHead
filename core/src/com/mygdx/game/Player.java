package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.math.MathUtils.lerp;
import static com.mygdx.game.Globals.*;
import static java.lang.Math.min;
import static java.lang.Math.signum;


public class Player extends InGameObj{
    private Texture currentTexture = new Texture(Gdx.files.internal("playerDefault.png"));
    private float dashTime = 0.25f;
    private boolean dashPressed = false;
    private float posX;
    private float posY;
    private float health;
    private float height;
    private float width;
    private float xVelocity = 0;
    private float yVelocity = 0;
    private float gravity = 1;
    private float speed = 4;
    private float jumpForce = 23;
    private boolean jumpPressed = false;
    private boolean canJump = true;
    private float bulletSpeed = 50;
    boolean canFallThrough = false;
    boolean isFacingRight = true;
    boolean invulnerable = false;
    float invlunerableTime = 0;
    float reload = 0;
    float fireRate = 3;
    float maxCoyoteSeconds = 0.08f;
    float coyoteSeconds = 0;
    boolean isOnFloor = false;
    Vector2 playerSpawn;
    int damage = 1;

    public Player(float x, float y, float health, float height, float width) {
        this.posX = x;
        this.posY = y;

        this.health = health;
        this.height = height;
        this.width = width;

        potato = this;
    }
    public void init(float x, float y, float health, float height, float width){
        this.posX = x;
        this.posY = y;

        yVelocity = 0;
        xVelocity = 0;

        this.health = health;
        this.height = height;
        this.width = width;

        potato = this;
    }
    public void death(){
        if(posY<=-6000){
            this.playerSpawn = Globals.sceneHolder.getPlayerSpawn();
            init(playerSpawn.x, playerSpawn.y, health-damage, 50,24);
            damage++;
            if(health<=0){damage = 1;}
            invulnerable = true;
            invlunerableTime = Gdx.graphics.getDeltaTime() * 60;
            System.out.println(health);
        }
    }

    /**
     * this is where stuff that happens every frame is gonna go
     *  | |
     *  | |
     *  | |
     *  \ /
     *   V
     */
    public void update(float deltaTime) {
        death();

        invlunerableTime -= Gdx.graphics.getDeltaTime();
        shoot(deltaTime);
        movement();
        amIDead();
        if (reload > 0) reload -= 60 * deltaTime;
        for (int i = 0; i < Globals.bulletHolder.bullets.size(); i++) {
            if (amIHit(Globals.bulletHolder.bullets.get(i))) {
                health = health - Globals.bulletHolder.bullets.get(i).damage;
                Globals.bulletHolder.bullets.get(i).alreadyHitSomething();
            }
        }



    }
    /**
     * this is where stuff that's drawn to the screen is gonna go (as in you put it in there it'll be drawn always)
     *     | |
     *     | |
     *     \ /
     *      V
     */
    public void render(SpriteBatch batch) {
        /*
        globalRender.begin(ShapeRenderer.ShapeType.Filled);
        globalRender.setColor(1,0,0,1);
        //the rectangle shape is drawn from the bottom left corner just so u know
        globalRender.rect(posX, posY,width, height);
        globalRender.end();
        */
        batch.draw(currentTexture, posX, posY, width,height);
        playerDebug(batch);
    }
    public void dispose () {}

    /**
     * Sets the X and Y Velocity property based on keyboard input
     * x velocity should be set based on if A or D is pressed
     * y velocity should be set based on if ONLY W is pressed :) and you're gonna have to add gravity
     * (^^^ should check to see of their allowed to jump, no double jumps ^^^)
     */
    private void calculateVelocity() {
        //fixme this is a template for getting keyboard input (this should actually be changing x and y velocity)

        yVelocity -= gravity;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xVelocity -= speed;
            isFacingRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xVelocity += speed;
            isFacingRight = true;
        }

        if (isOnFloor){
            canJump = true;
            coyoteSeconds = maxCoyoteSeconds;
        } else{
            if (coyoteSeconds > 0){
                coyoteSeconds -= Gdx.graphics.getDeltaTime();
            }else{
                canJump = false;
            }
        }

        if (canJump == true ) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                yVelocity = jumpForce;
                canJump = false;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            jumpPressed = true;
        }
        else{
            if (jumpPressed){
                yVelocity = min(yVelocity,2);
            }
            jumpPressed = false;
        }
        canFallThrough = false;

        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            canFallThrough = true;
        }

        xVelocity = lerp(xVelocity, 0, 0.25f);
    }

    /**
     *attempts to move along a vector (xVelocity,yVelocity) from current position and will stop being
     * able to move in said direction if there's something in the way
     * also has to make sure you cant go offscreen
     */
    private void movement(){
    //fixme placeholder
        calculateVelocity();


        //System.out.println(canFallThrough);
        moveAndSlideV2(xVelocity, yVelocity, canFallThrough);

        changeSceneToggle();
    }

    public void moveAndSlidePlatforms(float velX, float velY, boolean canFallThrough){

        isOnFloor = false;

        // This checks if you're going up so that there are no upwards collisions
        if(velY >= 0){
            this.posX += velX;
            this.posY += velY;
            return;
        }



        // This makes a fake player that detects if the players final position collides with the platform
        Rectangle testRect = new Rectangle(getPosX() + velX, getPosY() + velY, getWidth(), getHeight());
        for(Platform p : Globals.platformHolder.getPlatforms()){
            if(this.posY < p.y + p.height -1){
                continue;
            }
            if (p.canFallThroughPlat  && canFallThrough){
                continue;
            }
            Rectangle platformRectangle = new Rectangle(p.x, p.y, p.width, p.height);
            if(testRect.overlaps(platformRectangle)){
                this.posX += velX;
                this.posY = p.y + p.height;

                isOnFloor = true;
                yVelocity = 0;
                return;
            }

        }
        this.posX += velX;
        this.posY += velY;
        return;
    }

    public void moveAndSlideV2(float velX, float velY, boolean canFallThrough){

        isOnFloor = false;

        // This checks if you're going up so that there are no upwards collisions
//        if(velY >= 0){
//            this.posX += velX;
//            this.posY += velY;
//            //return;
//        }

        // This makes a fake player that detects if the players final position collides with the platform
        Rectangle testRect = new Rectangle(getPosX() + velX, getPosY() + velY, getWidth(), getHeight());
        for(Platform p : Globals.platformHolder.getPlatforms()){
            if(this.posY < p.y + p.height -1){
                continue;
            }
            if (p.canFallThroughPlat  && canFallThrough){
                continue;
            }
            Rectangle platformRectangle = new Rectangle(p.x, p.y, p.width, p.height);
            if(testRect.overlaps(platformRectangle)){
                this.posX += velX;
                this.posY = p.y + p.height;

                isOnFloor = true;
                yVelocity = 0;

                testRect.y = this.posY;

                for (Wall w : wallHolder.getWalls()){

                    Rectangle rec = new Rectangle(w.x, w.y, w.width, w.height);
                    if (testRect.overlaps(rec)){

                        posX = w.resolveX(testRect);

                        xVelocity = 0;

                        // posY = w.resolveY(testRect);
                        return;
                    }

                }

                return;
            }

        }

        for (Wall w : wallHolder.getWalls()){

            Rectangle platformRectangle = new Rectangle(w.x, w.y, w.width, w.height);
            if (testRect.overlaps(platformRectangle)){
                System.out.println("gfdshughosdih");

                float pastPos = posX;

                posX = w.resolveX(testRect);

                xVelocity = 0;

                posY += velY;

               // posY = w.resolveY(testRect);
                return;
            }

        }

        this.posX += velX;
        this.posY += velY;
        return;
    }

    public void moveAndSlideWalls(float velX, float velY){
        // Resets floor function
        isOnFloor = false;

        // This makes a fake player that detects if the players final position collides with the wall
        Rectangle testRect = new Rectangle(getPosX() + velX, getPosY() + velY, getWidth(), getHeight());

            if(isRectCollideWithWalls(testRect)) {

                // Gets magnitude of velocity and makes unit vectors going the opposite direction
                float tempMag = (float) Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
                float tempUnitVectorX = -velX / tempMag;
                float tempUnitVectorY = -velY / tempMag;

                // Loops over every wall on screen, "i" should represent number of pixels the testRect has moved
                for(int i = 0; isRectCollideWithWalls(testRect); i++) {

                    // Magnitude has to be bigger than the number of pixels it takes to get out of the wall
                    if(i>tempMag){
                        //System.out.println("Velocity that it's trying to go in: (" + tempUnitVectorX + ", " + tempUnitVectorY + ")");
                    }

                    // Moves the testRect in the direction of the temp unit vectors until it's no longer in the wall
                    testRect.x += tempUnitVectorX;
                    // Special case: if the unit vector pushes the rectangle past its position the other way, then it resets that direction
                    if(tempUnitVectorX > 0 ){
                        if(testRect.x > this.posX){
                            testRect.x = this.posX;
                        }
                    }
                    else if (tempUnitVectorX < 0){
                        if(testRect.x < this.posX){
                            testRect.x = this.posX;
                        }
                    }
                    if(!isRectCollideWithWalls(testRect)){
                        float tempVelY = -tempUnitVectorY * i;
                        this.posX = testRect.getX();
                        this.posY = testRect.getY();
                        this.xVelocity = 0;
                        moveAndSlideWalls(0,tempVelY);
                        return;
                    }

                    testRect.y += tempUnitVectorY;
                    if(tempUnitVectorY > 0 ){
                        if(testRect.y > this.posY){
                            testRect.y = this.posY;
                        }
                    }
                    else if (tempUnitVectorY < 0){
                        if(testRect.y < this.posY){
                            testRect.y = this.posY;
                        }
                    }
                    if(!isRectCollideWithWalls(testRect)){
                        if(tempMag==0){
                            System.out.println();
                        }
                        float tempVelX = -tempUnitVectorX * i;
                        this.posY = testRect.getY();
                        this.posX = testRect.getX();
                        this.yVelocity = 0;

                        moveAndSlideWalls(tempVelX,0);
                        if(-tempUnitVectorY<0) {
                            isOnFloor = true;
                        }
                        return;
                    }
                }
            }

        this.posX = testRect.getX();
        this.posY = testRect.getY();
    }

    public void moveAndSlideWallsV2(float velX, float velY){
        // Resets floor function
        //isOnFloor = false;

        if (velX == 0 && velY == 0) return;

        // This makes a fake player that detects if the players final position collides with the wall
        Rectangle testRect = new Rectangle(getPosX() + velX, getPosY() + velY, getWidth(), getHeight());

        int i = 0;

        if(isRectCollideWithWalls(testRect)) {

            // Gets magnitude of velocity and makes unit vectors going the opposite direction
            float tempMag = (float) Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
            float tempUnitVectorX = -velX / tempMag;
            float tempUnitVectorY = -velY / tempMag;

            // Loops over every wall on screen, "i" should represent number of pixels the testRect has moved
            for(i = 0; isRectCollideWithWalls(testRect); i++) {


                if (signum(testRect.x - posX) != signum(velX) && velX != 0) break;
                if (signum(testRect.y - posY) != signum(velY) && velY != 0) break;

                testRect.x += tempUnitVectorX;
                testRect.y += tempUnitVectorY;



            }

            xVelocity = testRect.x - posX;
            yVelocity = testRect.y - posY;


        }


        this.posX = testRect.getX();
        this.posY = testRect.getY();

        if (isRectCollideWithWalls(testRect)){
            System.out.println("feck");
        }

        //if (posX != tempPosX) System.out.println("gfohskgsrehyiogebgrsbhuyra " + posX + " " + tempPosX);


    }

    public boolean isRectCollideWithWalls(Rectangle testRect){
        for(int i = 0; i < wallHolder.getWalls().size(); i++){
            Wall w = wallHolder.getWalls().get(i);
            Rectangle wallRectangle = new Rectangle(w.x, w.y, w.width, w.height);
            if(testRect.overlaps(wallRectangle)){
                return true;
            }
        }
        return false;
    }
    public void shoot(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && reload < 1) {

            float tempSpeedx = 0;
            float tempSpeedy = 0;
            boolean isAim = false;

            if (Gdx.input.isKeyPressed(Input.Keys.I)) {
                tempSpeedy = bulletSpeed;
                isAim = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.K)) {
                tempSpeedy = -bulletSpeed;
                isAim = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                tempSpeedx = -bulletSpeed;
                isAim = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                tempSpeedx = bulletSpeed;
                isAim = true;
            }
            if (isAim) {
                bulletHolder.addBullet(posX,posY,tempSpeedx,tempSpeedy);
            } else if (isFacingRight) {
                bulletHolder.addBullet(posX, posY, bulletSpeed, 0);
            } else {
                bulletHolder.addBullet(posX, posY, -bulletSpeed, 0);
            }
            reload = 60/fireRate;
        }
    }
    public boolean amIHit(Bullet bullet) {
        if (invulnerable){
          if (invlunerableTime <0){
              invulnerable = false;
          }
        }
            else if (!bullet.isFriendly) {
            Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
            Rectangle playerRectangle = new Rectangle(posX, posY, width, height);
            if (playerRectangle.overlaps(bulletRectangle)) {
                invulnerable = true;
                invlunerableTime = Gdx.graphics.getDeltaTime() * 60;
                posY+= 50;
                return playerRectangle.overlaps(bulletRectangle);
            }
        }
        return false;
    }
    public void setInvulnerablity(){

    }
    public void changeSceneToggle(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            if(sceneHolder.getScene() == 1){
                sceneHolder.switchScene(2);
            } else if(sceneHolder.getScene() == 2){
                sceneHolder.switchScene(3);
            } else if(sceneHolder.getScene() == 3){
                sceneHolder.switchScene(4);
            } else{
                sceneHolder.switchScene(1);
            }

        }
    }

    private boolean tempCollision(Platform platform){
        if (platform.platformStanding(this)){
            while(platform.platformStanding(this)){
                posY +=1;
            }
            yVelocity = 0;
            canJump = true;
            return true;
        }
        if (posY < 0){
            posY = 0;
            yVelocity = 0;
            canJump = true;
            return true;
        }
        canJump = false;
        return false;
    }
    public void amIDead(){
        if (health <= 0){
            sceneHolder.switchScene(0);
        }
    }
    public void printLocation(){
        System.out.println("Player location: (" + posX + ", " + posY + ")");
    }
    public void playerDebug(SpriteBatch batch){
        Globals.font.draw(batch, "Player location: (" + posX + ", " + posY + ")",  posX, posY + 100);
        Globals.font.draw(batch, "MOVEMENT: WASD\nSHOOT: SPACE\nAIM: IJKL\nSWITCH SCENE: R",  posX, posY + 250);
    }
    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public boolean getCanFallThrough() {
        return canFallThrough;
    }
}