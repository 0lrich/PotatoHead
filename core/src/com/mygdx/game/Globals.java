package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
    public static Music music;
    public static Sound sound;
    public static boolean isWin = false;
    public static FirstBossHead farmerHead;
    public static FirstBossHand farmerHandRight;
    public static FirstBossHand farmerHandLeft;
    public static Pitchforks pitchforks = new Pitchforks();
    public static Texture cloudTexture = new Texture(Gdx.files.internal("cloudBlock.png"));
    public static Texture roadTexture2 = new Texture(Gdx.files.internal("roadBlock.png"));
    public static Texture roadStripeTexture = new Texture(Gdx.files.internal("roadStripe.png"));
    public static Texture woodBlockTexture = new Texture(Gdx.files.internal("woodBlock.png"));
    public static Texture nothingTexture = new Texture(Gdx.files.internal("nothing.png"));
    public static Texture defaultTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
    public static Texture gradientTexture = new Texture(Gdx.files.internal("gradient2.png"));
    public static Texture roadTexture = new Texture(Gdx.files.internal("tempRoad.png"));
    public static Texture cliffTexture = new Texture(Gdx.files.internal("cliff2.png"));
    public static Texture roadSignTexture = new Texture(Gdx.files.internal("roadSign.png"));
    public static Texture truckWheelTexture = new Texture(Gdx.files.internal("wheel.png"));
    public static Texture truckBaseTexture = new Texture(Gdx.files.internal("truckBase.png"));
    public static Texture truckHeadTexture = new Texture(Gdx.files.internal("truckHead.png"));
    public static Texture pitchForksTexture = new Texture(Gdx.files.internal("pitchforks.png"));
    public static Texture wallTexture;
    public static Texture platNoFallTexture;
    public static Texture platFallTexture;



}
