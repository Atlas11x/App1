package com.mygdx.game;

import com.badlogic.gdx.Game;

public class GameStarter extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(GameStarter.this));
    }
}
