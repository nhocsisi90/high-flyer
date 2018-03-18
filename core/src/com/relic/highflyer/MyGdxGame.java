package com.relic.highflyer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.relic.highflyer.screens.ScreenManager;

public class MyGdxGame extends Game {

	private SpriteBatch batch;

	@Override
	public void create() {
		ScreenManager.init(this);

		batch = new SpriteBatch();
		this.setScreen(ScreenManager.instance().getInitial());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
