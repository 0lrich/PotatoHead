package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class BulletHolder {
    ArrayList<Bullet> bullets = new ArrayList<>();

    public BulletHolder(){

    }

    public void addBullet(float x, float y, float speedX, float speedY){
        bullets.add(new Bullet(x, y,  speedX, speedY));

    }
    public void addBullet(float x, float y, float size, float xSpeed, float ySpeed, Texture defaultTexture, float damage, boolean isFriendly){
        bullets.add(new Bullet(x, y,size,xSpeed,ySpeed,defaultTexture,damage,isFriendly));

    }
    public void update() {
        if (bullets != null) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).update();
                if(bullets.get(i).isActive==false){
                    bullets.remove(i);
                    i--;
                }
            }
        }
    }

    public void removeBullets(){
        if(bullets != null) {
            for (int i = bullets.size()-1; i >= 0; i--) {
                bullets.remove(i);
            }
        }
    }
    public void render(SpriteBatch batch){
        if(bullets != null) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).render(batch);
            }
        }
        //SET A TEMPORORY IF STATEMENT BREAK POINT THAT CHECKS IF BULLETS FRIENDLY

    }


}
