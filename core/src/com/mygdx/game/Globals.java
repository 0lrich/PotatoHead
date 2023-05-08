package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Globals { // This global class holds objects that should be accessible from every class
    public static ShapeRenderer globalRender = new ShapeRenderer();
    public static BulletHolder bulletHolder = new BulletHolder();
    public static PlatformHolder platformHolder = new PlatformHolder();
    public static WallHolder wallHolder = new WallHolder();
    public static ObjectHolder objectHolder = new ObjectHolder();
    public static SceneHolder sceneHolder = new SceneHolder();
    public static Player potato;
    public static FirstBoss farmer;
    public static OrthographicCamera camera = new OrthographicCamera();
    public static BitmapFont font = new BitmapFont(Gdx.files.internal("Font/font.fnt"), Gdx.files.internal("Font/font.png"), false);
    public static MainMenuScreen mainMenuScreen = new MainMenuScreen();
}
