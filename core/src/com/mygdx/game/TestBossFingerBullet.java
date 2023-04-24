package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.Globals.bulletHolder;

public class TestBossFingerBullet implements FirstBossAttacks {

    float timer= 0f;


    public TestBossFingerBullet(Player player, FirstBossHand boss){
        boss.currentTexture = boss.shooting1;
        update(player, boss);

        //do change sprites and set initial variables


    }
    @Override
    public void update(Player player, FirstBossHand boss){
        Vector2 bossCenter = new Vector2(boss.x +boss.width/2, boss.y +boss.height/2);
        Vector2 playerCenter = new Vector2(player.getPosX()+ player.getWidth()/2, player.getPosY() +player.getHeight()/2);
        float opposite = bossCenter.x -playerCenter.x;
        float adjacent = bossCenter.y -playerCenter.y;
        timer += Gdx.graphics.getDeltaTime();
        Vector2 target = new Vector2(bossCenter.x-playerCenter.x,bossCenter.y-playerCenter.y);
        target.nor();
        if ( 10 <= timer && timer <=10.5f ){

            bulletHolder.addBullet(bossCenter.x, bossCenter.y,90,-target.x*10,-target.y*10,new Texture(Gdx.files.internal("Blurry potato.png")),0,false);
            isdone(boss);
        }else if (timer >7 ){
            boss.currentTexture = boss.shooting2;
        } else if (timer >5 ){
            boss.currentTexture = boss.shooting3;
        }
        isdone(boss);
    }

    @Override
    public boolean isdone(FirstBossHand boss) {
        if (timer>=10) {
            return true;
        }
        return false;
    }
}
