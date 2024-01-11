package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import static com.badlogic.gdx.math.MathUtils.lerp;
import static com.mygdx.game.Globals.*;
import static java.lang.Math.min;


public class Player extends InGameObj {

    Animation idleAnimation = new Animation(new String[]{
            "PotatoIdle1.png",
            "PotatoIdle2.png",
            "PotatoIdle2.png",
            "PotatoIdle3.png"}, true, .1f);

    Animation runAnimation = new Animation(new String[]{
            "PotatoRun1.png",
            "PotatoRun2.png",
            "PotatoRun3.png",
            "PotatoRun4.png"}, true, .1f);
    Animation dashAnimation = new Animation(new String[]{
            //cant even see other animations cause dash is quick so only one png
            "PotatoDash3.png"}, false, .5f);
    Animation currentAnimation;
    private float dashTime = 0.25f;
    private boolean dashPressed = false;
    public float posX;
    public float posY;

    public float health;
    private float height;
    private float width;
    public float xVelocity = 0;
    public float yVelocity = 0;
    private float gravity = 1;
    private float jumpForce = 23;
    private boolean jumpPressed = false;
    private boolean canJump = true;
    boolean canFallThrough = false;
    boolean isFacingRight = true;
    boolean invulnerable = false;
    float invulnerableTime = 0;
    float reload = 0;
    float fireRate = 3;
    float maxCoyoteSeconds = 0.08f;
    float coyoteSeconds = 0;
    float dodgeCoolDown = 0;
    boolean isOnFloor = false;
    Vector2 playerSpawn;
    int damage = 1;

    boolean doNotRender = false;

    boolean debugToggle = false;
    boolean godKillerToggle = false;


    public Player(float x, float y, float health, float height, float width) {
        this.posX = x;
        this.posY = y;

        this.health = health;
        this.height = height;
        this.width = width;
        currentAnimation = idleAnimation;

        potato = this;

    }

    public void init(float x, float y, float health, float height, float width) {
        this.posX = x;
        this.posY = y;

        yVelocity = 0;
        xVelocity = 0;

        this.health = health;
        this.height = height;
        this.width = width;
        changeAnimation(idleAnimation);

        potato = this;
    }

    private void changeAnimation(Animation animation) {
        currentAnimation = animation;
    }

    /**
     * Respawns player if they die
     */
    public void death() {
        if (posY <= -2000) {//if player falls
            this.playerSpawn = Globals.sceneHolder.getPlayerSpawn();
            init(playerSpawn.x, playerSpawn.y, health - damage, 50, 36);
            if (health <= 0) {
                damage = 1;
            }
            invulnerable = true;
            invulnerableTime = Gdx.graphics.getDeltaTime() * 60;
            System.out.println(health);
        }
    }

    /**
     * this is where stuff that happens every frame is going to go
     */
    public void update(float deltaTime) {
        pitchforkCollide();
        death();
        dodgeCoolDown -= Gdx.graphics.getDeltaTime();
        invulnerableTime -= Gdx.graphics.getDeltaTime();
        shoot();
        movement();
        amIDead();

        if (reload > 0) reload -= 60 * deltaTime;

        //update all bullets
        for (int i = 0; i < Globals.bulletHolder.bullets.size(); i++) {
            if (amIHit(Globals.bulletHolder.bullets.get(i))) {
                health = health - Globals.bulletHolder.bullets.get(i).damage;
                Globals.bulletHolder.bullets.get(i).alreadyHitSomething();
            }
        }

        currentAnimation.update();

    }

    /**
     * this is where stuff that's drawn to the screen is going to go (as in you put it in there it'll be drawn always)
     */
    public void render(SpriteBatch batch) {

        batch.draw(currentAnimation.getCurrentFrame(), !isFacingRight ? posX + width : posX, posY, !isFacingRight ? -width : width, height);
        playerHUD(batch);
        if (!doNotRender) {
            if (debugToggle) {
                playerDebug(batch);
            }
            //sets up god-mode
            if (godKillerToggle) {
                health = 999999;
                damage = 99;
                fireRate = 20;
            } else {
                damage = 1;
                fireRate = 3;
            }

        }

    }


    /**
     * Sets the X and Y Velocity property based on keyboard input
     * x velocity should be set based on if A or D is pressed
     * y velocity should be set based on if ONLY W is pressed :) and you're going to have to add gravity
     * (^^^ should check to see of their allowed to jump, no double jumps ^^^)
     */
    private void calculateVelocity() {
        //fixme this is a template for getting keyboard input (this should actually be changing x and y velocity)
        if (dashPressed) {
            changeAnimation(dashAnimation);
        } else {
            if (Math.abs(xVelocity) > 2) {
                changeAnimation(runAnimation);
            } else {
                changeAnimation(idleAnimation);
            }
        }
        yVelocity -= gravity;

        float speed = 4;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xVelocity -= speed;
            isFacingRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xVelocity += speed;
            isFacingRight = true;
        }
        //region allows player to dash
        if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
            if (dodgeCoolDown > 1) {
                System.out.println("ON COOL-DOWN " + dodgeCoolDown + " SECONDS LEFT");
            } else {
                if (isFacingRight) {
                    jumpForce = 0;
                    gravity = 0;
                    xVelocity += 100;
                    dashPressed = true;
                    yVelocity = 0;
                    dodgeCoolDown = 3;
                } else {
                    jumpForce = 0;
                    gravity = 0;
                    xVelocity -= 100;
                    dashPressed = true;
                    yVelocity = 0;
                    dodgeCoolDown = 3;
                }
            }
        }
        if (dashPressed) {
            dashTime -= Gdx.graphics.getDeltaTime();
            invulnerable = true;
            if (dashTime < 0) {
                gravity = 1;
                dashTime = 0.25f;
                dashPressed = false;
                jumpForce = 23;
                invulnerable = false;
            }
        }
        //endregion

        //checks if the player is on a platform
        if (isOnFloor) {
            canJump = true;
            coyoteSeconds = maxCoyoteSeconds;
        } else {
            if (coyoteSeconds > 0) {
                coyoteSeconds -= Gdx.graphics.getDeltaTime();
            } else {
                canJump = false;
            }
        }

        //Allows the player to jump
        if (canJump) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                sound = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));
                sound.play(1);
                yVelocity = jumpForce;
                canJump = false;
            }
        }
        //stops the player from jumping while not touching platform
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            jumpPressed = true;
        } else {
            if (jumpPressed) {
                yVelocity = min(yVelocity, 2);
            }
            jumpPressed = false;
        }

        canFallThrough = Gdx.input.isKeyPressed(Input.Keys.S);
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            debugToggle = !debugToggle;
        }
        //toggles god-mode
        if (Gdx.input.isKeyJustPressed(Input.Keys.O) && debugToggle) {
            godKillerToggle = !godKillerToggle;
        }

        xVelocity = lerp(xVelocity, 0, 0.25f);
    }

    /**
     * attempts to move along a vector (xVelocity,yVelocity) from current position and will stop being
     * able to move in said direction if there's something in the way
     * also has to make sure you cant go offscreen
     */
    private void movement() {
        //fixme placeholder
        calculateVelocity();

        moveAndSlideV2(xVelocity, yVelocity, canFallThrough);

        changeSceneToggle();
    }

    /**
     * @param velX           player's velocity on the x-axis
     * @param velY           player's velocity on the y-axis
     * @param canFallThrough whether the player can fall through a platform
     */
    public void moveAndSlideV2(float velX, float velY, boolean canFallThrough) {

        isOnFloor = false;


        // This makes a fake player that detects if the players final position collides with the platform
        Rectangle testRect = new Rectangle(getPosX() + velX, getPosY() + velY, getWidth(), getHeight());
        for (Platform p : Globals.platformHolder.getPlatforms()) {
            if (this.posY < p.y + p.height - 1) {
                continue;
            }
            if (p.canFallThroughPlat && canFallThrough) {
                continue;
            }
            Rectangle platformRectangle = new Rectangle(p.x, p.y, p.width, p.height);
            if (testRect.overlaps(platformRectangle)) {
                this.posX += velX;
                this.posY = p.y + p.height;

                isOnFloor = true;
                yVelocity = 0;

                testRect.y = this.posY;

                for (Wall w : wallHolder.getWalls()) {

                    Rectangle rec = new Rectangle(w.x, w.y, w.width, w.height);
                    if (testRect.overlaps(rec)) {

                        posX = w.resolveX(testRect);

                        xVelocity = 0;

                        return;
                    }

                }

                return;
            }

        }

        for (Wall w : wallHolder.getWalls()) {

            Rectangle platformRectangle = new Rectangle(w.x, w.y, w.width, w.height);
            if (testRect.overlaps(platformRectangle)) {

                posX = w.resolveX(testRect);

                xVelocity = 0;

                posY += velY;

                return;
            }

        }

        this.posX += velX;
        this.posY += velY;
    }

    /**
     * Checks if player is touching pitchforks and damages player if they are
     */
    public void pitchforkCollide() {
        Rectangle testRect = new Rectangle(getPosX(), getPosY(), getWidth(), getHeight());
        Rectangle rect = new Rectangle(pitchforks.x, pitchforks.y, pitchforks.width, pitchforks.height);
        if (testRect.overlaps(rect)) {
            health--;
            posX = sceneHolder.getPlayerSpawn().x;
            posY = sceneHolder.getPlayerSpawn().y;
            xVelocity = 0;
            yVelocity = 0;
        }
    }

    /**
     * Allows the player to shoot potatoes
     */
    public void shoot() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && reload < 1) {
            sound = Gdx.audio.newSound(Gdx.files.internal("shoot.mp3"));
            sound.play(1);
            float tempSpeedX = 0;
            float tempSpeedy = 0;
            boolean isAim = false;

            float bulletSpeed = 50;
            if (Gdx.input.isKeyPressed(Input.Keys.I)) {
                tempSpeedy = bulletSpeed;
                isAim = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.K)) {
                tempSpeedy = -bulletSpeed;
                isAim = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                tempSpeedX = -bulletSpeed;
                isAim = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                tempSpeedX = bulletSpeed;
                isAim = true;
            }
            if (isAim) {
                bulletHolder.addBullet(posX, posY, tempSpeedX, tempSpeedy);
            } else if (isFacingRight) {
                bulletHolder.addBullet(posX, posY, bulletSpeed, 0);
            } else {
                bulletHolder.addBullet(posX, posY, -bulletSpeed, 0);
            }
            reload = 60 / fireRate;
        }
    }

    /**
     * Checks if player is being hit
     *
     * @param bullet is checked if colliding with player
     * @return true if the player was hit
     */
    public boolean amIHit(Bullet bullet) {
        if (invulnerable) {
            if (invulnerableTime < 0) {
                invulnerable = false;
            }
        } else if (!bullet.isFriendly) {
            Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
            Rectangle playerRectangle = new Rectangle(posX, posY, width, height);
            if (playerRectangle.overlaps(bulletRectangle)) {
                invulnerable = true;
                invulnerableTime = Gdx.graphics.getDeltaTime() * 60;
                posY += 50;
                return playerRectangle.overlaps(bulletRectangle);
            }
        }
        return false;
    }

    /**
     * Allows you to switch scene if debug mode is on
     */
    public void changeSceneToggle() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.R) && debugToggle) {
            if (sceneHolder.getScene() == 1) {
                sceneHolder.switchScene(2);
            } else if (sceneHolder.getScene() == 2) {
                sceneHolder.switchScene(3);
            } else if (sceneHolder.getScene() == 3) {
                sceneHolder.switchScene(4);
            } else {
                sceneHolder.switchScene(1);
            }

        }
    }

    /**
     * resets level if the player dies
     */
    public void amIDead() {
        if (health <= 0) {
            int tempScene = sceneHolder.getScene();
            sceneHolder.switchScene(tempScene);
        }
    }

    public void playerDebug(SpriteBatch batch) {

        Globals.font.draw(batch, "(" + posX + ", " + posY + ")", posX, posY + 100);
        Globals.font.draw(batch, "Controls:\n" +
                "MOVEMENT - W,A,D | SHOOT - SPACE | AIM - I,J,K,L\n\n" +
                "Debug Controls:\n" +
                "SWITCH SCENE - R | GOD-KILLER - O\n\n" +
                "Debug info:\n" +
                "LIVES: " + health + "\n" +
                "LEFT HAND ALIVE? " + farmerHandLeft.getIsAlive() + "\n" +
                "RIGHT HAND ALIVE? " + farmerHandRight.getIsAlive() + "\n" +
                "HEAD ALIVE? " + farmerHead.getIsAlive(), camera.position.x - 900, camera.position.y + 400);
    }

    public void playerHUD(SpriteBatch batch) {
        Globals.font.draw(batch, "LIVES : " + (int) health, camera.position.x - 900, camera.position.y + 550);
        Globals.font.draw(batch, "Press 'p' to toggle debug mode", camera.position.x - 900, camera.position.y + 450);
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
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

    /**
     * @return if the player can fall through a platform
     */
    public boolean getCanFallThrough() {
        return canFallThrough;
    }
}