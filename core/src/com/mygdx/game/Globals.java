package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Globals { // This global class holds objects that should be accessible from every class
    public static BulletHolder bulletHolder = new BulletHolder();
    public static PlatformHolder platformHolder = new PlatformHolder();
    public static SceneHolder sceneHolder = new SceneHolder();
    public static BitmapFont font = new BitmapFont(Gdx.files.internal("Font/font.fnt"), Gdx.files.internal("Font/font.png"), false);
    public static MainMenuScreen mainMenuScreen = new MainMenuScreen();
}
