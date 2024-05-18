package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen extends ScreenAdapter {

    private Game game;

    public MenuScreen(Game game){
        this.game = game;
    }

    private Stage stage;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        // прогружаем в переменную файл
        FileHandle fontFile = Gdx.files.internal("superfont.fnt");
        // создаем шрифт и назначаем его стилю кнопки
        buttonStyle.font = new BitmapFont(fontFile);

        Texture upTexture = new Texture("g9202.png");
        buttonStyle.up = new TextureRegionDrawable(upTexture);

        Texture downTexture = new Texture("g9244.png");
        buttonStyle.down = new TextureRegionDrawable(downTexture);

        TextButton gameButton = new TextButton("Play", buttonStyle);
        gameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen());
            }
        });

        TextButton exitButton = new TextButton("Exit", buttonStyle);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Table table = new Table();
        table.setFillParent(true);

        table.add(gameButton).pad(20);
        table.row();
        table.add(exitButton);


        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
       // stage.dispose();
    }
}
