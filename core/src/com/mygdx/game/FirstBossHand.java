package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class FirstBossHand extends Boss {
    float rotation;
    Texture defaultTexture;
    Texture hitTexture;
    Texture deathTexture;
    FirstBossAttacks currentAttack;
    Random attackchoice = new Random();
    Texture shooting1;
    Texture shooting2;
    Texture shooting3;
    Texture openHandGrab;
    Texture closedFist;

    Boolean canGetHurt = true;
    ShapeRenderer bossBody = new ShapeRenderer();
    boolean bettername = false;
    boolean alreadyattacking = false;
    boolean disabledMovementpattern = false;
    boolean rightHand = false;
    boolean isAlive = true;

    public FirstBossHand(float x, float y, float health, float width, float height) {
        super(x, y, health, width, height);
        currentTexture = new Texture(Gdx.files.internal("RightHandFarmer.png"));
        defaultTexture = new Texture(Gdx.files.internal("RightHandFarmer.png"));
        hitTexture = new Texture(Gdx.files.internal("FarmerHurtHand.png"));
        deathTexture = new Texture(Gdx.files.internal("Dying hand.png"));
        shooting1 = new Texture(Gdx.files.internal("FarmerShootHand1.png"));
        shooting2 = new Texture(Gdx.files.internal("FarmerShootHand2.png"));
        shooting3 = new Texture(Gdx.files.internal("FarmerShootHand3.png"));
        openHandGrab = new Texture(Gdx.files.internal("FarmerOpenHandGrab.png"));
        closedFist = new Texture(Gdx.files.internal("Test boss closed fist.png"));
        bossBody.begin(ShapeRenderer.ShapeType.Filled);


        //the rectangle shape is drawn from the bottom left corner just so u know
        bossBody.rect(x, y, width, height);
        bossBody.end();
    }
    public void init(float x, float y, float health, float width, float height) {
        isAlive = true;
        super.init(x, y, health, width, height);
        currentTexture = new Texture(Gdx.files.internal("RightHandFarmer.png"));
        defaultTexture = new Texture(Gdx.files.internal("RightHandFarmer.png"));
        hitTexture = new Texture(Gdx.files.internal("FarmerHurtHand.png"));
        deathTexture = new Texture(Gdx.files.internal("Dying hand.png"));

        bossBody.begin(ShapeRenderer.ShapeType.Filled);
        // bossBody.setColor(100,100,100,.01f);

        //the rectangle shape is drawn from the bottom left corner just so u know
        bossBody.rect(x, y, width, height);
        bossBody.end();
        if (x > (float) Gdx.graphics.getWidth() / 2) {
            rightHand = true;
        }
    }

    /**
     * this is where stuff that happens every frame is gonna go
     * | |
     * | |
     * | |
     * \ /
     * V
     */
    public void update(Player player) {

        if (health > 0) {
            movementpattern();
            if (canGetHurt = true) {
                for (int i = 0; i < Globals.bulletHolder.bullets.size(); i++) {
                    if (amIHit(Globals.bulletHolder.bullets.get(i))) {
                        currentTexture = hitTexture;
                        health = health - Globals.bulletHolder.bullets.get(i).damage;
                        Globals.bulletHolder.bullets.get(i).alreadyHitSomething();
                    } else {
                        currentTexture = defaultTexture;
                    }
                }
            }
            if(!alreadyattacking) {
                //get random number
                float chosenattack;

               chosenattack = attackchoice.nextInt(2)+1;
                //set currentAttack to appropriate Attack
                // chosenattack = 2;
                switch ((int) chosenattack) {
                    case 1:
                        currentAttack = new FirstBossFingerBullet(player,this);
                        System.out.println("UNO");
                        alreadyattacking = true;
                        break;
                    case 2:
                        currentAttack = new FirstBossTargetedGrab(player,this);
                        System.out.println("dos");
                        alreadyattacking = true;
                        break;
                    case 3:
                        currentAttack = new FirstBossTargetedGrab(player,this);
                        System.out.println("tres");
                        alreadyattacking = true;
                        break;
                    case 4:
                        currentAttack = new FirstBossFingerBullet(player,this);
                        System.out.println("quatro");
                        break;
                    case 5:
                        currentAttack = new FirstBossFingerBullet(player,this);
                        System.out.println("Sinco");
                        break;
                }

            }
            else {
                currentAttack.update(player,this);
                if(currentAttack.isdone(this)){
                    alreadyattacking = false;
                }
            }
            Vector2 center = new Vector2(x +width/2, y +height/2);
            Vector2 pCenter = new Vector2(player.getPosX()+ player.getWidth()/2, player.getPosY() +player.getHeight()/2);
            float hypot =center.dst(pCenter);
            opposite = center.x -pCenter.x;
            if (!disabledMovementpattern) {
                rotation = (float) Math.asin(opposite / hypot);
            }
            } else if (health <= 0) {
            currentTexture = deathTexture;
            isAlive = false;
            y-=5;
            x += (float) Math.sin(y)*5;
        }


    }
float opposite;
    /**
     * this is where stuff that's drawn to the screen is gonna go (as in you put it in there it'll be drawn always)
     * | |
     * | |
     * \ /
     * V
     */
    public void render(SpriteBatch batch) {

        if (opposite < 0) {

            batch.draw(currentTexture, x, y, (width / 2), (height / 2), width, height, 1, 1, 90-(float) Math.toDegrees(rotation), 0, 0, currentTexture.getWidth(), currentTexture.getHeight(), false, true);

        } else {
            batch.draw(currentTexture, x, y, (width / 2), (height / 2), width, height, 1, 1, 90-(float) Math.toDegrees(rotation), 0, 0, currentTexture.getWidth(), currentTexture.getHeight(), false, false);
        }
    }

    public void movementpattern() {// boss probably moves around or maybe he doesnt this is just a test boss im making him do whatever but it should be here anyways
        if (!disabledMovementpattern) {
            if (y + height > Gdx.graphics.getHeight()) {
                bettername = true;
            }
            if (y < 0) {
                bettername = false;
            }
            if (bettername) {

                y -= 10;
            } else {

                y += 10;

            }
            if (!rightHand) {
                x = 0;
            } else {
                x = Gdx.graphics.getWidth() - width;
            }
        }
    }


    public boolean amIHit(Bullet bullet) {
        if (bullet.isFriendly) {
            Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
            Rectangle bossRectangle = new Rectangle(x, y, width, height);
            return bossRectangle.overlaps(bulletRectangle);
        }
        return false;
    }
    public boolean getIsAlive(){
        return isAlive;
    }
}
