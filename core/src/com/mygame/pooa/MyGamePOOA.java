package com.mygame.pooa;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.pooa.screens.Home;

public class MyGamePOOA extends Game {
	private SpriteBatch batch;
	private Home home;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		home = new Home(this);

		setScreen(home);
	}

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
	public void dispose () {
		batch.dispose();
	}
}
