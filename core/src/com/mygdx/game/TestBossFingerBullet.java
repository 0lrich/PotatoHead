package com.mygdx.game;

public class TestBossFingerBullet implements FirstBossAttacks {


    @Override
    public void update(Player player, FirstBoss boss){

        boss.currentTexture = boss.shooting1;

    }

    @Override
    public boolean isdone() {
        return false;
    }
}
