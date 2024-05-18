package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

public class BucketActor extends Actor {

    private Texture texture;
    private Rectangle boundary;
    private Label labelActor;

    public BucketActor(Label labelActor){
        this.labelActor = labelActor;
        setWidth(100);
        setHeight(100);
        texture = new Texture("vedro33.png");
        int x = GameScreen.SCREEN_WIDTH/2 - texture.getWidth()/2;
        setX(x);
        boundary = new Rectangle(x, 0, getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Gdx.input.justTouched()){
            int x = Gdx.input.getX() - texture.getWidth() / 2;
            setX(x);
        }
        boundary.setPosition(getX(), getY());

        checkOverlap();
    }

    private void checkOverlap(){
        Stage stage = getStage();
        Array<Actor> actors = stage.getActors();
        for(int i = 0; i < actors.size; i++){
            Actor actor = actors.get(i);
            if(actor instanceof DropActor){
                DropActor dropActor = (DropActor) actor;
                if(boundary.overlaps(dropActor.getBoundary())){
                    dropActor.reborn();

                    String scores = labelActor.getText().toString();
                    int newScores = Integer.parseInt(scores) + 1;
                    labelActor.setText(newScores);
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
