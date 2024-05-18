package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

/**
 * Класс который отвечает за игройвой персонаж
 * КАПЛЯ
 */
public class DropActor extends Actor {

    private Texture texture;
    private Random random;
    private Rectangle boundary;

    public DropActor(Texture texture, int startX, int startY, Random random){
        this.texture = texture;
        this.random = random;
        setY(startY);
        setX(startX);
        setWidth(50);
        setHeight(50);
        boundary = new Rectangle(startX, startY, getWidth(), getHeight());
    }

    public Rectangle getBoundary() {
        return boundary;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        float nextY = getY() - GameScreen.FALL_SPEED;
        setY(nextY);
        if (getY() < -texture.getHeight()){
            setY(GameScreen.SCREEN_HEIGHT);
            int x = random.nextInt(GameScreen.SCREEN_WIDTH - texture.getWidth());
            setX(x);
        }
        boundary.setPosition(getX(), getY());
    }

    public void reborn(){
        int x = random.nextInt(GameScreen.SCREEN_WIDTH - texture.getWidth());
        int y = GameScreen.SCREEN_HEIGHT + random.nextInt(GameScreen.SCREEN_HEIGHT);
        setPosition(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
