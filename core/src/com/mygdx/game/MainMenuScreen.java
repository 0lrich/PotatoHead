package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
public class MainMenuScreen extends ScreenAdapter {
    private Stage stage;
    private Viewport viewport;
    private Table mainTable;
    @Override
    public void show() {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
        mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);
        addButton("Play").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play has been clicked... Enjoy!");
            }
        });
        addButton("Quit").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Bye!");
                Gdx.app.exit();
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    private TextButton addButton(String name) {
        TextButton button = new TextButton(name, new Skin());
        mainTable.add(button).width(700).height(60).padBottom(10);
        mainTable.row();
        return button;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}