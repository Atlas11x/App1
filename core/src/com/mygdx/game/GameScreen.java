package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class GameScreen extends ScreenAdapter {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	private int x;
	public static final int FALL_SPEED = 5;

	private Stage stage;
	private Viewport viewport;


	@Override
	public void show () {
		viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
		stage = new Stage(viewport);

		Label.LabelStyle labelStyle = new Label.LabelStyle();
		BitmapFont font = new BitmapFont(Gdx.files.internal("superfont.fnt"));
		labelStyle.font = font;
		labelStyle.fontColor = Color.GOLD;

		Label scores = new Label("0", labelStyle);
		scores.setPosition(10, SCREEN_HEIGHT - 10 - 20);
		stage.addActor(scores);

		Texture dropTexture = new Texture("drop_3.png");
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			int dropX = random.nextInt(SCREEN_WIDTH - dropTexture.getWidth());
			int dropY = SCREEN_HEIGHT + random.nextInt(SCREEN_HEIGHT);
			DropActor dropActor = new DropActor(dropTexture, dropX, dropY, random);
			stage.addActor(dropActor);
		}

		BucketActor bucketActor = new BucketActor(scores);
		stage.addActor(bucketActor);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void render (float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		stage.act();
		stage.draw();
	}
}
