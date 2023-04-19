package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TestBossTargetedPunch implements FirstBossAttacks{
        float damage = 1;
        float timer= 0;

    public TestBossTargetedPunch(Player player, FirstBossHand boss) {
        boss.currentTexture = boss.closedfist;
        update(player,boss);
    }

    @Override
    public void update(Player player, FirstBossHand boss) {

        Vector2 bossCenter = new Vector2(boss.x +boss.width/2, boss.y +boss.height/2);

        Vector2 playerCenter = new Vector2(player.getPosX()+ player.getWidth()/2, player.getPosY() +player.getHeight()/2);

        float opposite = bossCenter.x -playerCenter.x;
        float adjacent = bossCenter.y -playerCenter.y;
        timer += Gdx.graphics.getDeltaTime();
        Vector2 target = new Vector2(bossCenter.x-playerCenter.x,bossCenter.y-playerCenter.y);

        if ( 7 <= timer && timer <=20 ){
            if (target.x > boss.x) {
                target.nor();
                boss.x += target.x*10;
            }else {
                target.nor();
                boss.x -= target.x*10;

            }if (target.y > 0) {
                target.nor();
                boss.y -= target.y*10;
            }else {
                target.nor();
                boss.y += target.y*10;
            }
            isdone(boss);
        }else if (timer >5 ){
            boss.disabledMovementpattern = true;
            boss.currentTexture = boss.closedfist;
        }else {

        }

        isdone(boss);
    }

    @Override
    public boolean isdone(FirstBossHand boss) {
        if (timer>=10) {
            boss.disabledMovementpattern = false;
            return true;
        }
        return false;
    }
    }

