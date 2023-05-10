package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class FirstBossHead extends Boss {
    boolean disabledMovementpattern = false;

    boolean bettername = false;

    Boolean canGetHurt = true;

    Texture defaultTexture;
    Texture hitTexture;

    Texture deathTexture;
    float opposite;
    boolean isAlive = true;

    public FirstBossHead(float x, float y, float health, float width, float height) {
        super(x, y, health, width, height);
        currentTexture = new Texture(Gdx.files.internal("FarmerIdleHead.png"));
        defaultTexture = new Texture(Gdx.files.internal("FarmerIdleHead.png"));
        hitTexture = new Texture(Gdx.files.internal("FarmerHurtHead.png"));
        deathTexture = new Texture(Gdx.files.internal("FarmerAngryHead.png"));

    }

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
            Vector2 center = new Vector2(x +width/2, y +height/2);
            Vector2 pCenter = new Vector2(player.getPosX()+ player.getWidth()/2, player.getPosY() +player.getHeight()/2);
            float hypot =center.dst(pCenter);
             opposite = center.x -pCenter.x;

        } else if (health <= 0) {
        currentTexture = deathTexture;
        isAlive = false;

    }
    }

        public boolean amIHit (Bullet bullet){
            if (bullet.isFriendly) {
                Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
                Rectangle bossRectangle = new Rectangle(x, y, width, height);
                return bossRectangle.overlaps(bulletRectangle);
            }
            return false;
        }
        public void movementpattern (){// boss probably moves around or maybe he doesnt this is just a test boss im making him do whatever but it should be here anyways
            if (!disabledMovementpattern) {
                if (x + width > Gdx.graphics.getWidth()) {
                    bettername = true;
                }
                if (x < 0) {
                    bettername = false;
                }
                if (bettername) {
                    x -= 5;
                    y = (float) Math.sin(x * .01f) * 100 + Gdx.graphics.getHeight() / 2f;
                } else {
                    x += 10;
                    y = (float) Math.sin(x * .5) * 10 + Gdx.graphics.getHeight() / 2f;

                }
            }

        }

        public boolean getIsAlive(){
            return isAlive;
        }

    public void render(SpriteBatch batch) {

       //
        //    batch.draw(currentTexture, x, y, (width / 2), (height / 2), width, height, 1, 1, 90-(float) Math.toDegrees(rotation), 0, 0, currentTexture.getWidth(), currentTexture.getHeight(), false, true);
            batch.draw(currentTexture,x,y,width,height);
       // } else {
       //     batch.draw(currentTexture, x, y, (width / 2), (height / 2), width, height, 1, 1, 90-(float) Math.toDegrees(rotation), 0, 0, currentTexture.getWidth(), currentTexture.getHeight(), false, false);
       // }
        }
    }


